package exhibition;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class Exhibitionlist {
	private String exID;
	private String exTitle;
	private Byte[] img;
	
	
	public Byte[] getImg() {
		return img;
	}
	public void setImg(Byte[] img) {
		this.img = img;
	}
	private Timestamp sDate;
	public Timestamp getsDate() {
		return sDate;
	}
	public void setsDate(Timestamp sDate) {
		this.sDate = sDate;
	}
	public Timestamp getlDate() {
		return lDate;
	}
	public void setlDate(Timestamp lDate) {
		this.lDate = lDate;
	}
	private Timestamp lDate;
	private int tArtPiece;
	private int tTicketing;
	
	
	public String getExID() {
		return exID;
	}
	public void setExID(String exID) {
		this.exID = exID;
	}
	public String getExTitle() {
		return exTitle;
	}
	public void setExTitle(String exTitle) {
		this.exTitle = exTitle;
	}


	public int gettArtPiece() {
		return tArtPiece;
	}
	public void settArtPiece(int tArtPiece) {
		this.tArtPiece = tArtPiece;
	}
	public int gettTicketing() {
		return tTicketing;
	}
	public void settTicketing(int tTicketing) {
		this.tTicketing = tTicketing;
	}
	
	
}
