package ticketing;

import java.sql.Timestamp;

public class TicketingList {

	private int txID;
	private Timestamp date;
	private int count;
	private String userid;
	private String exTitle;
	
	
	public int getTxID() {
		return txID;
	}
	public void setTxID(int txID) {
		this.txID = txID;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getExTitle() {
		return exTitle;
	}
	public void setExTitle(String exTitle) {
		this.exTitle = exTitle;
	}
	
	
	
	
}
