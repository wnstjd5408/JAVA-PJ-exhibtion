// mainUI.java
package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import exhibition.ExhibitionControl;
import exhibition.Exhibitionlist;
import user.UserControl;

public class MainUI extends JFrame {

	private static String userid = null;
	private JLabel label_userName;
	public MainUI(String usID) {
		MainUI.userid = usID ; 
		System.out.println("static 값 :"  +MainUI.userid);
		initialize(usID);
	}


	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI(userid);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize(String userid) {
		ExhibitionControl control = new ExhibitionControl();
		UserControl control1 = new UserControl();
		ArrayList<Exhibitionlist> list = control.exhibitionSelectAll();
		ArrayList<byte[]> imglist = control.image();
		ArrayList<String> titlelist= new ArrayList<String>();
		String name = control1.searchUserName(MainUI.userid);
		
		for (Exhibitionlist ei : list) {
			titlelist.add(ei.getExTitle());
		}


		setVisible(true);
		setSize(1500,900);
		Dimension frameSize = getSize();
		// 모니터 크기
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
		setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
		
		//사용자 이름
		if(name == null) {
		label_userName = new JLabel();
			
		}
		else {
		label_userName = new JLabel(name+ "님이 로그인");
		
		}
		label_userName.setBounds(1200, 30, 300, 60);
		getContentPane().add(label_userName);
		
		if(userid == null) {

			JButton btn_tologin = new JButton("LOGIN");
			btn_tologin.setBounds(1350, 30, 100, 50);
			getContentPane().add(btn_tologin);


			btn_tologin.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					LoginUI login = new LoginUI();
					login.setVisible(true);
					control.Disconnect();
					dispose();
				}
			});
		}
		else if(userid != null){
			JButton btn_tologin = new JButton("LOGOUT");
			btn_tologin.setBounds(1350, 30, 100, 50);
			getContentPane().add(btn_tologin);

		

			btn_tologin.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(null, "로그아웃을 하시겠습니까?",
							"Confirm", JOptionPane.YES_NO_OPTION);

					if(result == JOptionPane.CLOSED_OPTION) {
						//사용자가  "예" 아니오" 선택없이 다이얼로그 창을 닫을 경우
					}
					else if(result == JOptionPane.YES_OPTION) {
						MainUI.userid = null;
						MainUI mainUI = new MainUI(MainUI.userid );
						mainUI.setVisible(true);
						control.Disconnect();
						dispose();
					}
					else {
						
					}


	
				}
			});
			
			
			JButton btnMyPage = new JButton("MyPage");
			btnMyPage.setBounds(10, 30, 100 , 50);
			getContentPane().add(btnMyPage);
			btnMyPage.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MyPageUI UI  = new  MyPageUI(MainUI.userid);
					UI.setVisible(true);
				}
			});
			
		}

		//1번 버튼 관련 + 클릭이벤트
		JButton btnNewButton = new JButton(titlelist.get(0));
		btnNewButton.setBounds(50, 420, 300, 23);
		getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se = new SectionUI(list.get(0), imglist.get(0), MainUI.userid);
				se.setVisible(true);
//				setVisible(false);
			}
		});



		//2번 버튼 관련 + 클릭이벤트
		JButton btnNewButton1 = new JButton(titlelist.get(1));
		btnNewButton1.setBounds(600, 420, 300, 23);
		getContentPane().add(btnNewButton1);

		btnNewButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se = new SectionUI(list.get(1), imglist.get(1), MainUI.userid);
				se.setVisible(true);
				//  			setVisible(false);
			}
		});
		//3번 버튼 관련 + 클릭이벤트

		JButton btnNewButton2 = new JButton(titlelist.get(2));
		btnNewButton2.setBounds(1150, 420, 300, 23);
		getContentPane().add(btnNewButton2);

		btnNewButton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se1 = new SectionUI(list.get(2), imglist.get(2), MainUI.userid);
				se1.setVisible(true);
				//  			setVisible(false);
			}
		});
		//4번 버튼 관련 + 클릭이벤트

		JButton btnNewButton3 = new JButton(titlelist.get(3));
		btnNewButton3.setBounds(50, 820, 300 , 23);
		getContentPane().add(btnNewButton3);
		btnNewButton3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se = new SectionUI(list.get(3), imglist.get(3), MainUI.userid);
				se.setVisible(true);
				//  			setVisible(false);
			}
		});

		//5번 버튼 관련 + 클릭이벤트

		JButton btnNewButton4 = new JButton(titlelist.get(4));
		btnNewButton4.setBounds(600, 820, 300 , 23);
		getContentPane().add(btnNewButton4);
		btnNewButton4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se = new SectionUI(list.get(4), imglist.get(4), MainUI.userid);
				se.setVisible(true);
				//  			frame.setVisible(false);
			}
		});


		//6번 버튼 관련 + 클릭이벤트
		JButton btnNewButton5 = new JButton(titlelist.get(5));
		btnNewButton5.setBounds(1150, 820, 300 , 23);
		getContentPane().add(btnNewButton5);
		btnNewButton5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SectionUI se = new SectionUI(list.get(5), imglist.get(5), MainUI.userid);
				se.setVisible(true);
				//  			frame.setVisible(false);
			}
		});

		//Image 받아서 배치해주고 넣어주기
		ImageIcon img0 = new ImageIcon(imglist.get(0));
		ImageIcon img1 = new ImageIcon(imglist.get(1));
		ImageIcon img2 = new ImageIcon(imglist.get(2));
		ImageIcon img3 = new ImageIcon(imglist.get(3));
		ImageIcon img4 = new ImageIcon(imglist.get(4));
		ImageIcon img5 = new ImageIcon(imglist.get(5));
		JLabel img_label0 = new JLabel("");
		JLabel img_label1 = new JLabel("");
		JLabel img_label2 = new JLabel("");
		JLabel img_label3 = new JLabel("");
		JLabel img_label4 = new JLabel("");
		JLabel img_label5 = new JLabel("");
		img_label0.setIcon(img0);
		img_label1.setIcon(img1);
		img_label2.setIcon(img2);
		img_label3.setIcon(img3);
		img_label4.setIcon(img4);
		img_label5.setIcon(img5);      
		img_label0.setBounds(50, 100, 300, 300);
		img_label1.setBounds(600, 100, 300, 300);
		img_label2.setBounds(1150, 100, 300, 300);
		img_label3.setBounds(50, 500, 300, 300);
		img_label4.setBounds(600, 500, 300, 300);
		img_label5.setBounds(1150, 500, 300, 300);

		getContentPane().add(img_label0);
		getContentPane().add(img_label1);
		getContentPane().add(img_label2);
		getContentPane().add(img_label3);
		getContentPane().add(img_label4);
		getContentPane().add(img_label5);


	}
}