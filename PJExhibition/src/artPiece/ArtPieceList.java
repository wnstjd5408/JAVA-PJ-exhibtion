package artPiece;

import java.sql.Timestamp;

public class ArtPieceList {

	private int artNum;
	private String aTitle;
	private Timestamp year;
	private String exID;
	public int getArtNum() {
		return artNum;
	}

	public void setArtNum(int artNum) {
		this.artNum = artNum;
	}

	public String getaTitle() {
		return aTitle;
	}


	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}


	public Timestamp getYear() {
		return year;
	}
	public void setYear(Timestamp year) {
		this.year = year;
	}
	
	public String getExID() {
		return exID;
	}
	
	public void setExID(String exID) {
		this.exID = exID;
	}
	
	
	
	
	
	
}
