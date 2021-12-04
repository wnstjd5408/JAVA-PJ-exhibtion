package artPiece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtPieceControl {

	private Connection conn;
	private Statement state;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	   String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	
	
	public ArtPieceControl() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB연결");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("클래스를 찾을 수 없습니다");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL 접속예외");
			e.printStackTrace();
		}
	}
	public ArrayList<ArtPieceList> manageArtPieceSelectAll(String exid){
		ArrayList<ArtPieceList> artpiecelist = new ArrayList<ArtPieceList>();
		
		String sql = "select * from 작품 where  전시회코드 = (select 전시회코드 from 전시회 where 전시회코드 in (select 전시회코드 from 직원 where 직원번호 = '%s'))";
		sql = String.format(sql, exid);
		
		System.out.println("SQL구문 : " + sql);
		
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				ArtPieceList art = new ArtPieceList();
				art.setArtNum(rs.getInt("소장번호"));
				art.setaTitle(rs.getNString("작품명"));
				art.setYear(rs.getTimestamp("연도"));
				art.setExID(rs.getString("전시회코드"));
				
				artpiecelist.add(art);
			}
			return artpiecelist;
		} catch (SQLException e) {
			System.out.println("SQL구문 에러");
			e.printStackTrace();
		}
		return null;
	}
	public int insertArtPiece(String aTitle, String year, String exID) {
		
		
		String sql = "insert into 작품"
				+ " values(UPCOUNTARTPIECE.NEXTVAL,'%s',"
				+ "TO_TIMESTAMP('%s', 'yyyy-MM-dd'), '%s')";
		sql = String.format(sql, aTitle, year, exID);
		System.out.println(sql);
		try {
			state = conn.createStatement();
			
			int row = state.executeUpdate(sql);
			
			
			return row;
		} catch (SQLException e){
			System.out.println("SQL 예외" + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	public int deleteArtPiece(String idx) {
		String sql = "delete from 작품 where 소장번호  = %s";
		
		sql = String.format(sql, idx);
		System.out.println(sql);
		
		try {
			state = conn.createStatement();
			int row = state.executeUpdate(sql);
			
			return row;
		}catch(SQLException e) {
			System.out.println("SQL 구문 에러");
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateArtPiece(String aTitle, String year, String num) {
		String sql = "update 작품 set  작품명= '%s', 연도 = TO_TIMESTAMP('%s', 'YYYY-DD-MM') where 소장번호 = %s";
		sql = String.format(sql, aTitle, year, num);
		System.out.println("SQL구문 : " + sql);

		try {
			
			state = conn.createStatement();
			
			int row = state.executeUpdate(sql);
			
			return row;
			
		}catch(SQLException e) {
			System.out.println("SQL 구문 예외 : " + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public ArtPieceList artPieceDetail(String id) {
		String sql = "select * from 작품 where 소장번호 = %s";
		sql = String.format(sql, id);
		
		System.out.println("SQL구문 : " + sql);
	
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				ArtPieceList art = new ArtPieceList();
				art.setArtNum(rs.getInt("소장번호"));
				art.setaTitle(rs.getNString("작품명"));
				art.setYear(rs.getTimestamp("연도"));
				art.setExID(rs.getString("전시회코드"));
				
				return art;
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL구문 에러~!!!!");
			e.printStackTrace();
			Disconnect();
		}
		
		return null;
	}
	
	
	
	public ArrayList<ArtPieceList> artPieceSelectAll(String exid){
		ArrayList<ArtPieceList> artpiecelist = new ArrayList<ArtPieceList>();
		
		
		String sql = "select 소장번호, 작품명, 연도  from 작품 where 전시회코드 = '" + exid + "'";
		System.out.println(sql);
		try {
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			
			while(rs.next()) {
				ArtPieceList art = new ArtPieceList();
				art.setArtNum(rs.getInt("소장번호"));
				art.setaTitle(rs.getNString("작품명"));
				art.setYear(rs.getTimestamp("연도"));
				
				artpiecelist.add(art);
			}
			return artpiecelist;
		} catch (SQLException e) {
			System.out.println("SQL구문 에러~!!!!");			e.printStackTrace();
		}
		
		return null;
	}
	
	public void Disconnect() {
		
		try {
			if(rs != null)		rs.close();
			if(state != null)	state.close();
			if(conn != null)	conn.close();
			
		}catch(Exception e) {
			 
			System.out.println("naming 에러~!!!");
		}
	}
}
