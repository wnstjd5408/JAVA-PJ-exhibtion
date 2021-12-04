package ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import employee.EmployeeControl;
import employee.EmployeeList;

public class EmployeeLoginUI extends JFrame {

	private JTextField TextField_Mid;
	private JPasswordField passwordField_M;
	Connection con = null;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLoginUI window = new EmployeeLoginUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployeeLoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {							// 창 크기
		setSize(450, 300);
		Dimension frameSize = getSize();
		// 모니터 크기
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
		setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel Label_Mlogin = new JLabel("LOGIN");				// 로그인 라벨
		Label_Mlogin.setBounds(191, 41, 37, 32);
		getContentPane().add(Label_Mlogin);
		
		TextField_Mid = new JTextField();						// ID 텍스트필드
		TextField_Mid.setBounds(191, 95, 116, 21);
		getContentPane().add(TextField_Mid);
		TextField_Mid.setColumns(10);
		
		JLabel Label_Mid = new JLabel("ID");					// ID 라벨
		Label_Mid.setBackground(Color.WHITE);
		Label_Mid.setBounds(122, 98, 57, 15);
		getContentPane().add(Label_Mid);
		
		JLabel Label_Mpw = new JLabel("PW");					// PW 라벨
		Label_Mpw.setBounds(122, 129, 57, 15);
		getContentPane().add(Label_Mpw);
		
		passwordField_M = new JPasswordField();					// PW 텍스트필드
		passwordField_M.setBounds(191, 126, 116, 21);
		getContentPane().add(passwordField_M);
		
		
		JButton btn_Mlogin = new JButton("LOGIN");				// Login 버튼
		btn_Mlogin.setBounds(167, 185, 97, 23);
		getContentPane().add(btn_Mlogin);
		btn_Mlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String managerID = TextField_Mid.getText();
				String managerPW = String.copyValueOf(passwordField_M.getPassword());
				
				 if(managerID.length()==0 || managerPW.length()==0) {
	        		 JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
	        		 return;
				 }
				 EmployeeControl control = new EmployeeControl();
				 
				 try {
					 EmployeeList list = control.employeeSelectone(managerID, managerPW);
					 System.out.println(list.geteName());
					 EmployeeMangeUI employeeUI = new EmployeeMangeUI(list.geteID());
					 employeeUI.setVisible(true);
					 control.disconnect();
					 JOptionPane.showMessageDialog(null, "로그인 성공했습니다...");
					 dispose();
				 }
				 catch(NullPointerException ms) {
		           JOptionPane.showMessageDialog(null, "로그인이 일치하지않습니다..");
				 }
			}
		});;
			

	}
}
