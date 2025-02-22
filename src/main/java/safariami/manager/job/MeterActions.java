package safariami.manager.job;

import java.util.ArrayList;
import java.util.Date;

import safariami.manager.model.ActionLog;

public class MeterActions {

    private int id;
    private int counter;
    private String phoneNumber;
    private  boolean isConnected;
    private Date firstEntry;
    private ArrayList<ActionLog> actionLogs = new ArrayList<>();
    private int total;
    private int fails;
    private String serialNo;

    MeterActions(int id, int counter, String phoneNumber, Date firstEntry, String serialNo) {
        this.id = id;
        this.counter = counter;
        this.phoneNumber = phoneNumber;
        this.firstEntry = firstEntry;
        fails = 0;
        total = 0;
        this.serialNo = serialNo;
        isConnected = false;
    }

    public  boolean isIsConnected() {
        return isConnected;
    }

    public  void setIsConnected(boolean isConnected) {
       this.isConnected = isConnected;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter += counter;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getFirstEntry() {
        return firstEntry;
    }

    public void setFirstEntry(Date firstEntry) {
        this.firstEntry = firstEntry;
    }

    public ArrayList<ActionLog> getActionLogs() {
        return actionLogs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public void setActionLogs(ArrayList<ActionLog> actionLogs) {
        this.actionLogs = actionLogs;
    }

    public void reset() {
        this.actionLogs = new ArrayList<>();
        this.counter = 0;
        this.firstEntry = new Date();
    }
}
