package ticketing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TicketingControl {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	
	public TicketingControl() {
		// TODO Auto-generated constructor stub
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스를 찾을 수 없습니다");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL 접속예외");
			e.printStackTrace();
		}
	}
	
	//selectMaxIdx는 최고값을 가져와서 값을 반환 
	/*
	 * public int selectMaxIdx() { String sql = "select max(예매번호) from 예매";
	 * 
	 * 
	 * try { csmt = conn.createStatement(); rs = csmt.executeQuery(sql);
	 * 
	 * 
	 * while(rs.next()) { return rs.getInt(1); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block System.out.println("예매번호 최고값 에러~!!!" +
	 * e.getMessage()); e.printStackTrace(); } return 0;
	 * 
	 * }
	 */
	
	public int insertTickeing(String date, String count, String exId, String userid ) {
		//예매번호, 예약날짜, 예매수량, 구매시간, 전시회코드, 아이디
		String sql = "insert into 예매 values(upcount.NEXTVAL, '%s', '%s', SYSTIMESTAMP, '%s', '%s')";
		System.out.println(userid);
		
		sql = String.format(sql, date, count, exId, userid);
		System.out.println(sql);
		try {
			psmt = conn.prepareStatement(sql);
			int a = psmt.executeUpdate();	
						
			
			return a;
		} catch (SQLException e) {
			System.out.println("SQL 구문 에러 입니다~!!");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<TicketingList> showticketingAll(String id){
		ArrayList<TicketingList> ticketList = new ArrayList<TicketingList>();
		
		String sql ="select 예매.예매번호, 예매.예약날짜, 예매.예매수량, 전시회.전시명, 예매.아이디"
				+ " from 예매,전시회 where 전시회.전시회코드 = 예매.전시회코드 and 예매.아이디 ='" + id +"'"; 		
	
		System.out.println(sql);
		try {
			psmt = conn.prepareStatement(sql);
			
	
			rs = psmt.executeQuery();
			
			
			
			while(rs.next()) {
				TicketingList ticket = new TicketingList();
				ticket.setTxID(rs.getInt("예매번호"));
				
				ticket.setDate(rs.getTimestamp("예약날짜"));
				ticket.setCount(rs.getInt("예매수량"));
				ticket.setExTitle(rs.getNString("전시명"));
				ticket.setUserid(rs.getNString("아이디"));
				
				ticketList.add(ticket);
			}
			return ticketList;
		} catch (SQLException e) {
			System.out.println("SQL구문 에러~!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Disconnect();
			
		}
		return null;
		
		
	}
	
	public void Disconnect() {
		try {
			if(rs != null) 		rs.close();
			if(psmt != null)	psmt.close();
			if(conn != null)	conn.close();
		}catch(Exception e) {
			System.out.println("FINALLY 에러");
		}
		
	}
	
}
