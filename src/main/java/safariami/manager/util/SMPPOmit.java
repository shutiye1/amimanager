package safariami.manager.util;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.cloudhopper.smpp.SmppBindType;
import com.cloudhopper.smpp.SmppSession;
import com.cloudhopper.smpp.SmppSessionConfiguration;
import com.cloudhopper.smpp.impl.DefaultSmppClient;
import com.cloudhopper.smpp.impl.DefaultSmppSessionHandler;
import com.cloudhopper.smpp.pdu.*;
import com.cloudhopper.smpp.type.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class SMPPOmit implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(SMPPOmit.class);
    private SmppSession session = null;
    private DefaultSmppClient clientBootstrap;
    private ScheduledThreadPoolExecutor monitorExecutor;
    private ThreadPoolExecutor executor;
    private Environment env;

    SMPPOmit(Environment env) throws UnrecoverablePduException, SmppChannelException, InterruptedException, SmppTimeoutException, RecoverablePduException {
        this.env = env;
        // for monitoring thread use, it's preferable to create your own instance
        // of an executor with Executors.newCachedThreadPool() and cast it to ThreadPoolExecutor
        // this permits exposing thinks like executor.getActiveCount() via JMX possible
        // no point renaming the threads in a factory since underlying Netty
        // framework does not easily allow you to customize your thread names
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        // to enable automatic expiration of requests, a second scheduled executor
        // is required which is what a monitor task will be executed with - this
        // is probably a thread pool that can be shared with between all client bootstraps
        monitorExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1, new ThreadFactory() {
            private AtomicInteger sequence = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("SmppClientSessionWindowMonitorPool-" + sequence.getAndIncrement());
                return t;
            }
        });

        // a single instance of a client bootstrap can technically be shared
        // between any sessions that are created (a session can go to any different
        // number of SMSCs) - each session created under
        // a client bootstrap will use the executor and monitorExecutor set
        // in its constructor - just be *very* careful with the "expectedSessions"
        // value to make sure it matches the actual number of total concurrent
        // open sessions you plan on handling - the underlying netty library
        // used for NIO sockets essentially uses this value as the max number of
        // threads it will ever use, despite the "max pool size", etc. set on
        // the executor passed in here
        clientBootstrap = new DefaultSmppClient(executor, 1, monitorExecutor);

//        clientBootstrap = new DefaultSmppClient(Executors.newCachedThreadPool(), 1, monitorExecutor);

        //
        // setup configuration for a client session
        //
        DefaultSmppSessionHandler sessionHandler = new ClientSMPPSessionHandler();

        SmppSessionConfiguration config = new SmppSessionConfiguration();
        config.setWindowSize(1);
        config.setName("hes.sms.sender");
        config.setType(SmppBindType.TRANSCEIVER);
        config.setHost(env.getProperty("smpp.host"));
        config.setPort(Integer.valueOf(env.getProperty("smpp.port")));
        config.setConnectTimeout(10000);
        config.setSystemId(env.getProperty("smpp.username"));
        config.setPassword(env.getProperty("smpp.password"));
        config.getLoggingOptions().setLogBytes(true);
        // to enable monitoring (request expiration)
        config.setRequestExpiryTimeout(30000);
        config.setWindowMonitorInterval(15000);
        config.setCountersEnabled(true);

        //
        // create session, enquire link, submit an sms, close session
        //

            // create session a session by having the bootstrap connect a
            // socket, send the bind request, and wait for a bind response
            session = clientBootstrap.bind(config, sessionHandler);

            // demo of a "synchronous" enquireLink call - send it and wait for a response
            EnquireLinkResp enquireLinkResp1 = session.enquireLink(new EnquireLink(), 10000);
            logger.info("enquire_link_resp #1: commandStatus [" + enquireLinkResp1.getCommandStatus() + "=" + enquireLinkResp1.getResultMessage() + "]");

    }

    public void send(String dest) throws Exception {

        if(session == null) {
            throw new Exception("Client Not connected to SMSC");
        }

        String text160 = env.getProperty("smpp.message");
        byte[] textBytes = CharsetUtil.encode(text160, CharsetUtil.CHARSET_GSM);

        SubmitSm submit = new SubmitSm();

        // add delivery receipt
        //submit.setRegisteredDelivery(SmppConstants.REGISTERED_DELIVERY_SMSC_RECEIPT_REQUESTED);

        submit.setSourceAddress(new Address((byte)0x02, (byte)0x00, env.getProperty("smpp.sourceNumber")));
        submit.setDestAddress(new Address((byte)0x01, (byte)0x01, dest));
        submit.setShortMessage(textBytes);

        //SubmitSmResp submitResp = session.submit(submit, 10000);

        logger.info("sendWindow.size: {}", session.getSendWindow().getSize());
    }

    public void send(String dest, String serialNo, String sms, String source) throws Exception {

        logger.info("Sending SMS To: " + serialNo +"  phone: "+dest);

        if(session == null) {
            throw new Exception("Client Not connected to SMSC");
        }

        byte[] textBytes = CharsetUtil.encode(sms, CharsetUtil.CHARSET_GSM);

        SubmitSm submit = new SubmitSm();

        // add delivery receipt
        //submit.setRegisteredDelivery(SmppConstants.REGISTERED_DELIVERY_SMSC_RECEIPT_REQUESTED);

        submit.setSourceAddress(new Address((byte)0x02, (byte)0x00, source));
        submit.setDestAddress(new Address((byte)0x01, (byte)0x01, dest));
        submit.setShortMessage(textBytes);

        //SubmitSmResp submitResp = session.submit(submit, 10000);

        logger.info("sendWindow.size: {}", session.getSendWindow().getSize());
    }


    @Override
    public void close() throws IOException {
        if (session != null) {
            //logger.info("Cleaning up session... (final counters)");
            if (session.hasCounters()) {
                logger.info("tx-enquireLink: {}", session.getCounters().getTxEnquireLink());
                logger.info("tx-submitSM: {}", session.getCounters().getTxSubmitSM());
                logger.info("tx-deliverSM: {}", session.getCounters().getTxDeliverSM());
                logger.info("tx-dataSM: {}", session.getCounters().getTxDataSM());
                logger.info("rx-enquireLink: {}", session.getCounters().getRxEnquireLink());
                logger.info("rx-submitSM: {}", session.getCounters().getRxSubmitSM());
                logger.info("rx-deliverSM: {}", session.getCounters().getRxDeliverSM());
                logger.info("rx-dataSM: {}", session.getCounters().getRxDataSM());
            }
            session.unbind(5000);
            session.destroy();
            // alternatively, could call close(), get outstanding requests from
            // the sendWindow (if we wanted to retry them later), then call shutdown()
        }

        // this is required to not causing server to hang from non-daemon threads
        // this also makes sure all open Channels are closed to I *think*
        logger.info("Shutting down client bootstrap and executors...");
        clientBootstrap.destroy();
        executor.shutdownNow();
        monitorExecutor.shutdownNow();
        logger.info("Done. Exiting");
    }

    public static class ClientSMPPSessionHandler extends DefaultSmppSessionHandler {

        ClientSMPPSessionHandler() {
            super(logger);
        }

        @SuppressWarnings("rawtypes")
		@Override
        public void firePduRequestExpired(PduRequest pduRequest) {
            logger.warn("PDU request expired: {}", pduRequest);
        }

        @SuppressWarnings("rawtypes")
		@Override
        public PduResponse firePduRequestReceived(PduRequest pduRequest) {
            PduResponse response = pduRequest.createResponse();
            // do any logic here
            return response;
        }

    }
}
