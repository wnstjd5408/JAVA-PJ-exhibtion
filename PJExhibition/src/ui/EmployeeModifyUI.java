package ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import artPiece.ArtPieceControl;
import artPiece.ArtPieceList;


public class EmployeeModifyUI extends JFrame {
	private static String eid = null;
	private JLabel title;
	private JButton modifyBtn;
	private JLabel exhibitionname;
	private JTextField txtTitle;
	private JTextField txtDate;
	private ArtPieceControl control;
	private ArtPieceList art;

	public EmployeeModifyUI(String id, String eid) {
		EmployeeModifyUI.eid = eid;
		control = new ArtPieceControl();
		try {
		art = control.artPieceDetail(id);
		initialize(art);
		}
		catch(NullPointerException e) {
    		JOptionPane.showMessageDialog(null, "값을 선택해주세요!!.", "수정", JOptionPane.DEFAULT_OPTION);
    		EmployeeMangeUI ui = new EmployeeMangeUI(eid);
    		
    		ui.setVisible(true);
    		setVisible(false);
		}
		
	
	}

	private void initialize(ArtPieceList art) {
		setBounds(100, 100, 600, 700);
		getContentPane().setLayout(null);
		
		title = new JLabel("작품명");						// 타이틀 글씨
		title.setFont(new Font("굴림", Font.BOLD, 20));
		title.setBounds(100, 150, 70, 45);
		getContentPane().add(title);
		
		exhibitionname = new JLabel("날짜");			// 작품 이름
		exhibitionname.setFont(new Font("굴림", Font.BOLD, 20));
		exhibitionname.setBounds(100, 300, 201, 45);
		getContentPane().add(exhibitionname);
		
		txtTitle = new JTextField(art.getaTitle());						// ID 텍스트필드
		txtTitle.setBounds(250, 150, 300, 45);
		getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(art.getYear().getTime()));

		
		txtDate = new JTextField(date);						// ID 텍스트필드
		txtDate.setBounds(250, 300, 300, 45);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		modifyBtn = new JButton("Modify");			// 티케팅 버튼
		modifyBtn.setBounds(110, 500, 400, 23);
		getContentPane().add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = control.updateArtPiece(txtTitle.getText(), txtDate.getText(), Integer.toString(art.getArtNum()) );
				if(row > 0) {
					EmployeeMangeUI employeeMangeUI = new EmployeeMangeUI(EmployeeModifyUI.eid);
					
	        		JOptionPane.showMessageDialog(null, "수정", "수정되었습니다.", JOptionPane.DEFAULT_OPTION);
					employeeMangeUI.setVisible(true);
					control.Disconnect();
					dispose();
				}
				else {
	        		JOptionPane.showMessageDialog(null, "수정", "값을 선택해주세요!!.", JOptionPane.DEFAULT_OPTION);

				}
				
			}
		});
	}
}
