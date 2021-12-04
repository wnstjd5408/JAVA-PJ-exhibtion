package user;

import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class DisplayImage extends JFrame {
	
	private Connection conn;
	private Statement state;
	private ResultSet rs;
	
	
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	   
	private String user = "EXHIBITION";
	private String password = "1234";
	
	public Connection getConnection() {
	    Connection connection = null;

	    try {
	        Class.forName(driver);
	        connection = DriverManager.getConnection(
	              url,user,password);;
	    } catch (Exception e) {
	        System.out.println("Error Occured While Getting the Connection: - "
	                + e);
	    }
	    return connection;
	}
	
	public DisplayImage() {
		super("Image Display");
	    this.setSize(600,600);
	    conn = getConnection();
	    try {
	        state = conn.createStatement();
	        rs =state.executeQuery("select 이미지 from 전시회");

	            byte[] image = null;
	            while(rs.next()) {
	                image = rs.getBytes("이미지");
	            }
	            Image img = Toolkit.getDefaultToolkit().createImage(image);
	            ImageIcon icon =new ImageIcon(img);
	            JLabel lPhoto = new JLabel();
	            lPhoto.setIcon(icon);
	            add(lPhoto);

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new DisplayImage();
		
	}
}
