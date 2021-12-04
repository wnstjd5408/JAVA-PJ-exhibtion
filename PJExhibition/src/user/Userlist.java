package user;

//User table getter, setter
public class Userlist {
	
	private String userId;
	private String userPW;
	private String userName;
	private String phoneNum;
	
	
	
	public Userlist() {
		// TODO Auto-generated constructor stub
	}
	
	public Userlist(String userId, String userPW, String userName, String phoneNum) {
		super();
		this.userId = userId;
		this.userPW = userPW;
		this.userName = userName;
		this.phoneNum = phoneNum;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
	
}
