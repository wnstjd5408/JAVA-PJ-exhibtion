package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ticketing.TicketingControl;
import ticketing.TicketingList;
import user.UserControl;
import user.Userlist;

public class MyPageUI extends JFrame{
	private static String userid = null;
	
	private JLabel txtUserID;
	private JLabel txtUserIDValue;
	private JLabel txtName;
	private JLabel txtNameValue;
	private JLabel txtPhoneNum;
	private JLabel txtPhoneNumValue;
	private JLabel txtTicketing;
	private UserControl control;
	private Userlist user;
	private TicketingControl control1;
    private String colName[] = { "예매번호", "예약날짜", "예매수량" ,"전시명","아이디"};

	public MyPageUI(String userid) {
		
		MyPageUI.userid = userid;
		control = new UserControl();
		user  = control.userSelectOneAll(userid);
		
		setSize(800,950);
		Dimension frameSize = getSize();
	      // 모니터 크기
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	    setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		getContentPane().setLayout(null);

		txtUserID = new JLabel("아이디 :");
		txtUserID.setFont(new Font("굴림", Font.BOLD, 30));
		txtUserID.setBounds(100, 150, 120, 100);
		getContentPane().add(txtUserID);
		
		txtName = new JLabel("이름 :");
		txtName.setFont(new Font("굴림", Font.BOLD, 30));
		txtName.setBounds(100, 300, 120, 100);
		getContentPane().add(txtName);
		
		txtPhoneNum = new JLabel("전화번호 :");
		txtPhoneNum.setFont(new Font("굴림", Font.BOLD, 30));
		txtPhoneNum.setBounds(100, 450, 450, 100);
		getContentPane().add(txtPhoneNum);
		
		txtTicketing = new JLabel("예약정보");						
		txtTicketing.setBounds(50, 500, 200, 100);
		txtTicketing.setFont(new Font("굴림", Font.BOLD, 20));
		getContentPane().add(txtTicketing);
		
		txtUserIDValue = new JLabel(user.getUserId());						// ID Value 값
		txtUserIDValue.setBounds(550, 150, 300, 100);
		txtUserIDValue.setFont(new Font("굴림", Font.PLAIN, 30));
		getContentPane().add(txtUserIDValue);
		
		txtNameValue = new JLabel(user.getUserName());						// Name Value 값
		txtNameValue.setBounds(550, 300, 300, 100);
		txtNameValue.setFont(new Font("굴림", Font.PLAIN, 30));
		getContentPane().add(txtNameValue);
		
		txtPhoneNumValue = new JLabel(user.getPhoneNum());						// PhoneNum Value 값
		txtPhoneNumValue.setBounds(500, 450, 300, 100);
		txtPhoneNumValue.setFont(new Font("굴림", Font.PLAIN, 30));
		getContentPane().add(txtPhoneNumValue);


		control1 = new TicketingControl();
		
		ArrayList<TicketingList> ticketList = control1.showticketingAll(user.getUserId());
		
		
		String row[] = new String[5];
		
		DefaultTableModel model = new DefaultTableModel(colName, 0) {
			public boolean isCellEditable(int i, int c) {
                return false;
            }
		};
		
		for(TicketingList ti : ticketList) {
			row[0] = Integer.toString(ti.getTxID());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			row[1] = sdf.format(new Date(ti.getDate().getTime()));
			row[2] = Integer.toString(ti.getCount());
			row[3] = ti.getExTitle();
			row[4] = ti.getUserid();
			model.addRow(row);
		}
		
		JTable ticketTable = new  JTable(model);
		ticketTable.setLocation(45, 600);
		ticketTable.setSize(700, 50);
		getContentPane().add(ticketTable);

		
	
	
	
	}
	
}
