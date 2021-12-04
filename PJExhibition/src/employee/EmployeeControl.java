package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeControl {
	private Connection conn;
	private Statement state;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	   String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	
	public EmployeeControl() {
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
	
	public EmployeeList employeeSelectone(String id, String pw) {
		String sql = "select * from 직원 where 아이디 = '%s' and 비번 ='%s'";

		sql = String.format(sql, id, pw);
		
		System.out.println("SQL 구문  : " + sql );
		
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				EmployeeList employee = new EmployeeList();
				employee.seteID(rs.getNString("직원번호"));
				employee.seteName(rs.getNString("이름"));
				employee.setId(rs.getNString("아이디"));
				employee.setPw(rs.getNString("비번"));
				employee.setExID(rs.getNString("전시회코드"));			
				
				return employee;
			}
		} catch (SQLException e) {
			System.out.println("SQL 구문 에러~!!!" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) 		rs.close();
				if(state != null)	state.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {
				
			}
			
		}
		return null;
		
	}
	
	public void disconnect() {
		
		try {
			if(rs != null) 		rs.close();
			if(state != null)	state.close();
			if(conn != null)	conn.close();
		}catch(Exception e) {
			
		}
		
	}
	
}
