package ticketing;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class RemainTicketingControl {

	
	private Connection conn;
	private CallableStatement csmt;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	
	public RemainTicketingControl() {
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
	
	
	public int showRemainTicket(String exID) {
		String sql = "{call 잔여티켓(?, ?)}";
		try {
			csmt = conn.prepareCall(sql);
			csmt.setString(1, exID);
			csmt.registerOutParameter(2, Types.INTEGER);
			csmt.executeQuery();
			return csmt.getInt(2);
		}
		catch(SQLException e){
			System.out.println("SQL 구문 에러");
		}
		finally {
			
			try {
				if(rs != null) 		rs.close();
				if(csmt != null)	csmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				System.out.println("FINALLY 에러");
			}
			
		}
		
		return 0;
		
		
	}
}
