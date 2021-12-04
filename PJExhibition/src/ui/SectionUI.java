package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import artPiece.ArtPieceControl;
import artPiece.ArtPieceList;
import exhibition.Exhibitionlist;
import ticketing.RemainTicketingControl;

public class SectionUI extends JFrame {
	private JLabel title;
	private JButton ticketingBtn;
	private JLabel exhibitionname;
	private JLabel txtStart;
	private JLabel txtStartValue;
	private JLabel txtlastValue;
	private JLabel txtlast;
	private JLabel remaintxt;
	private JLabel remainTicketValue;
	private JTable piecetable;
	private JLabel soldOut;
	
	
	public DefaultTableModel model;
	private JScrollPane jsp;
	private ArtPieceControl control;
	private ArrayList<ArtPieceList> artPieceList;
	private int num;
    private String colName[] = { "소장번호", "작품명", "연도"};
    private SimpleDateFormat sdf;
    private RemainTicketingControl control1;
    private int remainticket;
    
    private static String userid  = null;

	public SectionUI() {
		// TODO Auto-generated constructor stub
	}
	
	public SectionUI(Exhibitionlist exhibition, byte[] image, String id) {
		
		SectionUI.userid = id;
		control = new ArtPieceControl();

		artPieceList = control.artPieceSelectAll(exhibition.getExID());
		num = 0;
		initialize(exhibition, image, artPieceList);
	}

	private void initialize(Exhibitionlist exhibition, byte[] image, ArrayList<ArtPieceList> artPieceList) {								// 창 크기
		setSize(800,950);
		Dimension frameSize = getSize();
	      // 모니터 크기
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	      // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
	    setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		System.out.println("SectionUI에서 출력 : " + exhibition.getExID());
		control1 = new RemainTicketingControl();
		try {
			
		remainticket = control1.showRemainTicket(exhibition.getExID());
		}
		catch(NullPointerException e) {
			remainticket = -1;
			System.out.println("에러~!!!" + e.getMessage());
		}
		ImageIcon img0 = new ImageIcon(image);
		JLabel img_label0 = new JLabel("");
		img_label0.setIcon(img0);
		img_label0.setBounds(45, 45, 300, 300);
		getContentPane().add(img_label0);
		title = new JLabel("제목 : ");						// 타이틀 글씨
		title.setFont(new Font("굴림", Font.BOLD, 20));
		title.setBounds(360, 45, 70, 100);
		getContentPane().add(title);
		
		exhibitionname = new JLabel(exhibition.getExTitle());			// 작품 이름
		exhibitionname.setFont(new Font("굴림", Font.PLAIN, 20));
		exhibitionname.setBounds(430, 45, 400, 100);
		getContentPane().add(exhibitionname);
		
		txtStart = new JLabel("시작날 : ");
		txtStart.setFont(new Font("굴림", Font.BOLD, 20));
		txtStart.setBounds(360, 123, 150, 100);
		getContentPane().add(txtStart);
		
		txtlast = new JLabel("마지막날 : ");
		txtlast.setFont(new Font("굴림", Font.BOLD, 20));
		txtlast.setBounds(360, 200, 150, 100);
		getContentPane().add(txtlast);
		
		remaintxt = new JLabel("남은 티켓 : ");
		remaintxt.setFont(new Font("굴림", Font.BOLD, 20));
		remaintxt.setBounds(360, 250, 150, 100);
		getContentPane().add(remaintxt);

		if(remainticket < 3 && remainticket > 0) {
			soldOut = new JLabel("마감임박 ");
			soldOut.setFont(new Font("굴림", Font.BOLD, 20));
			soldOut.setBounds(550, 310, 150, 100);
			getContentPane().add(soldOut);
			if(userid != null) {
				ticketingBtn = new JButton("예매");			// 티케팅 버튼
				ticketingBtn.setBounds(360, 350, 97, 23);
				getContentPane().add(ticketingBtn);
				ticketingBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TicketingUI ui = new TicketingUI(exhibition.getExID(), SectionUI.userid);
						ui.setVisible(true);
						dispose();
					}
				});
				}
			
		}
		else if(remainticket == 0){
			soldOut = new JLabel("매진");
			soldOut.setFont(new Font("굴림", Font.BOLD, 20));
			soldOut.setBounds(550, 310, 150, 100);
			getContentPane().add(soldOut);
		}else {
			if(userid != null) {
				ticketingBtn = new JButton("예매");			// 티케팅 버튼
				ticketingBtn.setBounds(360, 350, 97, 23);
				getContentPane().add(ticketingBtn);
				ticketingBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TicketingUI ui = new TicketingUI(exhibition.getExID(), SectionUI.userid);
						ui.setVisible(true);
						dispose();
					}
				});
				}
		}
		
		sdf= new SimpleDateFormat("yyyy-MM-dd");
		
		txtStartValue = new JLabel(sdf.format(new Date(exhibition.getsDate().getTime())));			// 작품 이름
		txtStartValue.setFont(new Font("굴림", Font.PLAIN, 20));
		txtStartValue.setBounds(480, 123, 400, 100);
		getContentPane().add(txtStartValue);
		
		txtlastValue = new JLabel(sdf.format(new Date(exhibition.getlDate().getTime())));			// 작품 이름
		txtlastValue.setFont(new Font("굴림", Font.PLAIN, 20));
		txtlastValue.setBounds(480, 200, 400, 100);
		getContentPane().add(txtlastValue);
		
		remainTicketValue = new JLabel(Integer.toString(remainticket));
		remainTicketValue.setFont(new Font("굴림", Font.PLAIN, 20));
		remainTicketValue.setBounds(480, 250, 400, 100);
		getContentPane().add(remainTicketValue);


	
		
		

		String row[] = new String[3];

		model = new DefaultTableModel(colName, 0) {

			public boolean isCellEditable(int i, int c) {
	                return false;
	            }
		};
		for(ArtPieceList ar : artPieceList) {
			num++;
			row[0] = Integer.toString(num);
			row[1] = ar.getaTitle();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			row[2] = sdf.format(new Date(ar.getYear().getTime()));
			model.addRow(row);
		}
		
		piecetable = new JTable(model);							// J테이블
		piecetable.setLocation(45, 400);
		piecetable.setSize(700, 250);
		jsp = new JScrollPane(piecetable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
    			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		getContentPane().add(piecetable);
	}
}
