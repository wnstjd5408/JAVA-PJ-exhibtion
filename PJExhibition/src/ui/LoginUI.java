package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import user.UserControl;
import user.Userlist;

public class LoginUI extends JFrame {
   public LoginUI() {
	   
	  setSize(400,250);
	  Dimension frameSize =getSize();
	      // 모니터 크기
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	  setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  getContentPane().setLayout(null);
   
      JLabel Label_login = new JLabel("LOGIN");
      Label_login.setBounds(160, 35, 37, 32);
      getContentPane().add(Label_login);

      JLabel Label_id = new JLabel("ID");
      Label_id.setBackground(Color.WHITE);
      Label_id.setBounds(80, 92, 57, 15);
      getContentPane().add(Label_id);

      JLabel Label_pw = new JLabel("PW");
      Label_pw.setBounds(80, 123, 57, 15);
      getContentPane().add(Label_pw);

      JButton btn_tomanager = new JButton("Admin Login");
      btn_tomanager.addActionListener(new ActionListener() {
    	  
         public void actionPerformed(ActionEvent e) {
        	 EmployeeLoginUI management =  new EmployeeLoginUI();
        	 setVisible(false);
        	 management.setVisible(true);
         }
      });
      btn_tomanager.setBounds(220, 10, 150, 23);
      getContentPane().add(btn_tomanager);

      JTextField TextField_id = new JTextField();
      TextField_id.setBounds(155, 89, 116, 21);
      getContentPane().add(TextField_id);
      TextField_id.setColumns(10);

      JPasswordField passwordField = new JPasswordField();
      passwordField.setBounds(155, 120, 116, 21);
      getContentPane().add(passwordField);

      JButton btn_login = new JButton("LOGIN");
      btn_login.setBounds(155, 179, 97, 23);
      getContentPane().add(btn_login);
      btn_login.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 String id = TextField_id.getText().trim();
        	 char[] pw = passwordField.getPassword();
        	 String userPW = String.valueOf(pw);
     		
        	 
        	 if(id.length()==0 || userPW.length()==0) {
        		 JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
        		 return;
        		 } 
            UserControl control = new UserControl();
            
            try {
	            Userlist list = control.userSelectOne(id, userPW);
	            new MainUI(list.getUserId());
	            dispose();
            }
            catch(NullPointerException ms) {
            	JOptionPane.showMessageDialog(null, "로그인이 일치하지않습니다..");
            }
            
            
         }
      });

   }
}