package safariami.manager.service.impl;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import safariami.manager.service.STSTokenGeneratorService;
import java.math.RoundingMode;

import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.*;


import safariami.manager.prismtoken1.MeterConfigIn;
import safariami.manager.prismtoken1.SessionOptions;
import safariami.manager.prismtoken1.SignInResult;
import safariami.manager.prismtoken1.Token;
import safariami.manager.prismtoken1.TokenApi;

@Service
public class STSTokenGeneratorServiceImpl implements STSTokenGeneratorService {

	@Value("${sts.token.port}")
	private short port;

	@Value("${sts.token.tct}")
	private short tct;

	@Value("${sts.token.ti}")
	private short ti;

	@Value("${sts.token.ea}")
	private short ea;

	@Value("${sts.token.host}")
	private String host;

	@Value("${sts.token.password}")
	private String password;

	@Value("${sts.token.username}")
	private String username;

	@Value("${sts.token.deviceId}")
	private String deviceId;

	@Value("${sts.token.sgc}")
	private int sgc;

	@Value("${sts.token.keyRevisionNumber}")
	private short keyRevisionNumber;

	@Value("${sts.token.keyRegisterNumber}")
	private String keyRegisterNumber;

	@Value("${sts.token.keyExpiryNumber}")
	private short keyExpiryNumber;

	@Value("${sts.token.managementFunction}")
	private byte managementFunction;

	@Value("${sts.tls.trustStore}")
	private String trustStore;

	@Value("${sts.tls.trustStorePassword}")
	private String trustStorePassword;

	@Override
	public String generateToken(BigDecimal amount, String serialNo) throws Exception {

		// Create params
		TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
		params.setTrustStore(trustStore, trustStorePassword);

		// Create transport
		TTransport transport = TSSLTransportFactory.getClientSocket(host, port, 0, params);
		transport = new TFramedTransport(transport);

		// Create protocol
		org.apache.thrift.protocol.TProtocol proto = new org.apache.thrift.protocol.TBinaryProtocol(transport);
		TokenApi.Client ptoken = new TokenApi.Client(proto);

		// Sign in
		SignInResult result1 = ptoken.signInWithPassword(java.util.UUID.randomUUID().toString(),
				"local", username, password, new SessionOptions());
		String accessToken = result1.getAccessToken();

		// Issue a 100kW Electricity token to a meter
		MeterConfigIn meter = new MeterConfigIn(serialNo, ea, tct, sgc, keyRevisionNumber, ti,keyExpiryNumber);
		meter.setAllowKrnUpdate(false);

		// Multiply amount by 10 and round it by STS
		BigDecimal newAmount = amount.multiply(BigDecimal.TEN).setScale(3, RoundingMode.HALF_UP);
		java.util.List<Token> tokens = ptoken.issueCreditToken(java.util.UUID.randomUUID().toString(),
				accessToken, meter, (short)0, newAmount.doubleValue(), 0, 0);

		// Close connection
		transport.close();

		if(tokens == null || tokens.size() < 0) {
			throw new Exception("Unable to generate token for meter:"+serialNo+". Error: empty reponse");
		}

		Token token = tokens.get(0);

		return token.tokenDec;	
	}

	@Override
	public String generateTamperToken(String serialNo) throws Exception {

		// Create params
		TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
		params.setTrustStore(trustStore, trustStorePassword);

		// Create transport
		TTransport transport = TSSLTransportFactory.getClientSocket(host, port, 0, params);
		transport = new TFramedTransport(transport);

		// Create protocol
		org.apache.thrift.protocol.TProtocol proto = new org.apache.thrift.protocol.TBinaryProtocol(transport);
		TokenApi.Client ptoken = new TokenApi.Client(proto);

		// Sign in
		SignInResult result1 = ptoken.signInWithPassword(java.util.UUID.randomUUID().toString(),
				"local", username, password, new SessionOptions());
		String accessToken = result1.getAccessToken();

		// Issue a 100kW Electricity token to a meter
		MeterConfigIn meter = new MeterConfigIn(serialNo, ea, tct, sgc, keyRevisionNumber, ti,keyExpiryNumber);
		meter.setAllowKrnUpdate(false);

		// Generate clear tamper token
		java.util.List<Token> tokens = ptoken.issueMseToken(java.util.UUID.randomUUID().toString(),
				accessToken, meter, (short)5, 0, 0, 0);

		// Close connection
		transport.close();

		if(tokens == null || tokens.size() < 0) {
			throw new Exception("Unable to generate token for meter:"+serialNo+". Error: empty reponse");
		}

		Token token = tokens.get(0);

		return token.tokenDec;	
	}

}
