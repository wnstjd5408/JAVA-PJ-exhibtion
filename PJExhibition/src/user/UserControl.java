package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.net.aso.i;

public class UserControl {
	private Connection conn;
	private Statement state;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	   String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	
	//DB랑 연동
	public UserControl() {

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
	
	
	//전체 손님리스트 출력
	public ArrayList<Userlist> userSelectAll(){
		ArrayList<Userlist> userlist = new ArrayList<Userlist>();
	
		try {
			state =conn.createStatement();
			rs = state.executeQuery("select * from 손님");
			System.out.println(rs);
			while(rs.next()) {
				Userlist user = new Userlist();
				user.setUserId(rs.getString("아이디"));
				user.setUserPW(rs.getString("비번"));
				user.setUserName(rs.getNString("이름"));
				user.setPhoneNum(rs.getNString("전화번호"));
				
				
				userlist.add(user);
				System.out.println("값 들어감");
			}
			return userlist;
		}catch(SQLException e){
			System.out.println("SQL구문 에러~!!!");
		}finally {
			Disconnect();
		
		}
		return null;
	}
	public Userlist userSelectOneAll(String userid) {
		
		
		String sql = "select * from 손님 where 아이디 = '%s'";
		sql = String.format(sql, userid);
		
		
		System.out.println("SQL 구문 : "  +  sql);
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				Userlist user = new Userlist();
				user.setUserId(rs.getString("아이디"));
				user.setUserPW(rs.getString("비번"));
				user.setUserName(rs.getNString("이름"));
				user.setPhoneNum(rs.getNString("전화번호"));
				
				return user;
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			Disconnect();
			
		}
		return null;
		
		
	}
	
	//로그인 확인 부분
	public Userlist userSelectOne(String userid, String password) {
		
		
		String userPW = String.valueOf(password);
		
		String sql = "select * from 손님 where 아이디 = '%s' and 비번 ='%s'";
		sql =String.format(sql, userid, userPW);
		System.out.println("SQL 구분  :" + sql);
		
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			
			while(rs.next()) {
				Userlist user = new Userlist();
				user.setUserId(rs.getString("아이디"));
				user.setUserPW(rs.getString("비번"));
				user.setUserName(rs.getNString("이름"));
				user.setPhoneNum(rs.getNString("전화번호"));
				
				return user;
				
				
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 에러~!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Disconnect();
			
		}
		return null;
	}
	
	//Main UI 이름 출력
	public String searchUserName(String id) {
		
		
		String sql = "select 이름 from 손님 where 아이디 = '%s'";
		sql = String.format(sql, id);
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			
			while(rs.next()) {
				return rs.getNString(1);
			
			}
		} catch (SQLException e) {
			System.out.println("SQL구문 에러~!!!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Disconnect();
			
		}
		return null;
	}
	
	//회원가입 부분
	public int insertUser(Userlist user) {
		String sql = "insert into 손님  values('%s', '%s', '%s', '%s')";
		
		sql =String.format(sql, user.getUserId(), user.getUserPW(), user.getUserName(), user.getPhoneNum());
		
		System.out.println("SQL 구문 :" + sql);
		
		try {
			state = conn.createStatement();
			int row = state.executeUpdate(sql);
			
			System.out.println("값 추가");
			return row;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL문 에러~~!!!");
			e.printStackTrace();
		}finally {
		Disconnect();
		}
		
		return 0;
	}
	
	
	//GUI에서 ID값을 받아서  알림창을 띄웁니다.
	public int idCheck(String id) {
		int rst = 0;
		String sql= "select * from user where 아이디 = " + id;
		
		System.out.println("아이디 확인  : " +sql );
		
		try {
			state= conn.createStatement();
			rs = state.executeQuery(sql);
			
			if(rs.next()) rst =1;
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQL 에러 입니다.");
		}
		return rst;
	}
	
	//DB 연결 끊기
	public void Disconnect() {
		try {
			if(rs != null)		rs.close();
			if(state != null)	state.close();
			if(conn != null)	conn.close();
			
		}catch(Exception e) {
						
		}
		
	}
	
	
	
}
