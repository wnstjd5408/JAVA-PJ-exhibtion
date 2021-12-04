package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JCalendar;
import com.toedter.components.JSpinField;

import ticketing.TicketingControl;

public class TicketingUI extends JFrame {
	   /**
	    * Launch the application.
	    */
	   private static String userid  =null;
	   private static String exID = null;
		
	
	   public static void main(String[] args) {
	      EventQueue.invokeLater(new Runnable() {
	         public void run() {
	            try {
	               TicketingUI window = new TicketingUI(TicketingUI.exID, TicketingUI.userid);
	               window.setVisible(true);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      });
	   }

	   /**
	    * Create the application.
	    */
	   public TicketingUI(String exId, String userId) {
		  System.out.println(userId);
		  TicketingUI.exID = exId;
		  TicketingUI.userid = userId;
	      initialize();
	   }

	   /**
	    * Initialize the contents of the frame.
	    */
	   private void initialize() {

	      setBounds(100, 100, 600, 600);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      getContentPane().setLayout(null);
	      
	      JCalendar calendar = new JCalendar();   //Calendar 캘린더
	      calendar.setBounds(60, 100, 470, 400);
	      getContentPane().add(calendar);
	      
	      SpinnerModel value = new SpinnerNumberModel(1, 1, 100, 1);
	      JSpinner spinField = new JSpinner(value); 
	      spinField.setBounds(319, 53, 100, 40);
	      getContentPane().add(spinField);

	      
	      JLabel lblNewLabel = new JLabel("Ticketing");
	      lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
	      lblNewLabel.setBounds(12, 10, 96, 21);
	      getContentPane().add(lblNewLabel);
	      
	      JLabel lblNewLabel_1 = new JLabel("매수:");
	      lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
	      lblNewLabel_1.setBounds(270, 65, 38, 21);
	      getContentPane().add(lblNewLabel_1);
	      
	      
	      JButton btnNewButton = new JButton("예매");  //예매 버튼
	      btnNewButton.setFont(new Font("굴림", Font.PLAIN, 20));
	      btnNewButton.setBounds(429, 53, 100, 40);
	      btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TicketingControl control = new TicketingControl();
				String count = Integer.toString((int)spinField.getValue());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = sdf.format(calendar.getDate());
				int row= control.insertTickeing(date, count, TicketingUI.exID, TicketingUI.userid);
				
				if(row > 0) {
					MainUI ui = new MainUI(TicketingUI.userid);
					ui.setVisible(true);
		         	JOptionPane.showMessageDialog(null, "예약이 되었습니다!! 예매확인은 예매페이지에서 확인해주세요");
					control.Disconnect();
					dispose();
				}else {
		         	JOptionPane.showMessageDialog(null, "입력정보를 확인해주세요!!!");
				}
			}
		});
	      getContentPane().add(btnNewButton);
	   }

}
