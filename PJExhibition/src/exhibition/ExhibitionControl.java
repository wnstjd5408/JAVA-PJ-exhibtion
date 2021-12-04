package exhibition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExhibitionControl {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;



	private String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";

	private String user = "EXHIBITION";
	private String password = "1234";


	public ExhibitionControl() {
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

	//이미지만 출력을 해서 돌림
	public ArrayList<byte[]> image() {
		ArrayList<byte[]> imagelist = new ArrayList<byte[]>();

		String sql = "select 이미지 from 전시회";


		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {
				byte[] image = null;
				image = rs.getBytes("이미지");
				imagelist.add(image);
			}
			return imagelist;
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQL문 에러~!!!");
		}

		return null;
	}

	//전시회코드, 전시명, 시작날, 마지막날 리스트 불러오기
	public ArrayList<Exhibitionlist>  exhibitionSelectAll(){
		ArrayList<Exhibitionlist> exhibitonlist = new ArrayList<Exhibitionlist>();

		String sql = "select 전시회코드, 전시명, 시작날, 마지막날 from 전시회";

		System.out.println(sql);
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while(rs.next()) {
				Exhibitionlist exhibition = new Exhibitionlist();
				exhibition.setExID(rs.getString("전시회코드"));
				exhibition.setExTitle(rs.getString("전시명"));
				exhibition.setsDate(rs.getTimestamp("시작날"));
				exhibition.setlDate(rs.getTimestamp("마지막날"));
				exhibitonlist.add(exhibition);
			}
			return exhibitonlist;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL구문 에러~!!!!");
			e.printStackTrace();
		}
		return null;

	}

	//DB 연결 취
	public void Disconnect() {
		try {
			if(rs != null) 		rs.close();
			if(psmt != null)	psmt.close();
			if(conn != null)	conn.close();
		}catch(Exception e) {

		}

	}

	public Exhibitionlist exhibitionDetail(String id){
		String sql = "select 전시회코드, 전시명, 시작날, 마지막날 from 전시회 where 전시회코드 = '?'";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setNString(1, id);
			rs = psmt.executeQuery();

			while(rs.next()) {
				Exhibitionlist exhibition = new Exhibitionlist();
				exhibition.setExID(rs.getString("전시회코드"));
				exhibition.setExTitle(rs.getString("전시명"));
				exhibition.setsDate(rs.getTimestamp("시작날"));
				exhibition.setlDate(rs.getTimestamp("마지막날"));
				return exhibition;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL구문 에러~!!!!");
			e.printStackTrace();
		}


		return null;
	}



	//DB에 데이터베이스 정보 넣어주기
	public void ins(String filepath) {
		try {
			File image = new File(filepath);
			FileInputStream input = new FileInputStream(image);
			String sql = "update 전시회  set 이미지= ? where 전시회코드 = '106'";


			psmt = conn.prepareStatement(sql);
			psmt.setBinaryStream(1,input, (int)(image.length()));

			int s = psmt.executeUpdate();

			if(s > 0) {
				System.out.println("Succesfully");
			}
			else {
				System.out.println("Unsuccessfully to upload image");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 INPUTSTREAM 부분에 이상이 있습니다." + e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) 		rs.close();
				if(psmt != null)	psmt.close();
				if(conn != null)	conn.close();
			}catch(Exception e) {

			}

		}


	}


}
