package ui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import artPiece.ArtPieceControl;
import artPiece.ArtPieceList;

public class EmployeeMangeUI extends JFrame {

	private static String eid = null;
	private static String artPieceIDX = null;
	private static String exID = null;
	
	private JTable artPiecetable;
	public DefaultTableModel model;
	private JScrollPane jsp;
	private int num;
	private TableModel tmodel;
	private ArtPieceControl control;
	private ArrayList<ArtPieceList> artPieceList;
	private Vector<String> colName;
	public EmployeeMangeUI() {
		// TODO Auto-generated constructor stub
	}
	public void dataSetting() {
		colName.add("순서번호");
		colName.add("소장번호");
		colName.add("작품명");
		colName.add("연도");
		
	}
	
	public EmployeeMangeUI(String exid) {
		System.out.println(exid);
		EmployeeMangeUI.eid = exid;
		System.out.println(EmployeeMangeUI.eid);
		control = new ArtPieceControl();
		System.out.println(control);
		artPieceList = control.manageArtPieceSelectAll(exid);
		System.out.println(artPieceList);
		EmployeeMangeUI.exID = artPieceList.get(1).getExID();
		num = 0;
		initialize(artPieceList, EmployeeMangeUI.eid);
	}


	private void initialize(ArrayList<ArtPieceList> artPieceList, String exid) {
		
		setSize(705, 475);
		Dimension frameSize = getSize();
		// 모니터 크기
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
		setLocation((screenSize.width - frameSize.width) /2, (screenSize.height - frameSize.height) /2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel MexhibitionName = new JLabel("관리자 계정 관리");
		MexhibitionName.setFont(new Font("굴림", Font.CENTER_BASELINE, 20));
		MexhibitionName.setBounds(15, 10, 188, 36);
		getContentPane().add(MexhibitionName);
		
		colName = new Vector<String>();
		dataSetting();
	
		String row[] = new String[4];
		model = new DefaultTableModel(colName,0) {
			public boolean isCellEditable(int i, int c) {
                return false;
            }
			
		};
		for(ArtPieceList ar : artPieceList) {
			num++;
			row[0] = Integer.toString(num);
			row[1] = Integer.toString(ar.getArtNum());
			row[2] = ar.getaTitle();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			row[3] = sdf.format(new Date(ar.getYear().getTime()));
			model.addRow(row);
		}
	
		artPiecetable = new JTable(model);
		artPiecetable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				tmodel = (TableModel)artPiecetable.getModel();
				int col = artPiecetable.getSelectedColumn();
				int row  = artPiecetable.getSelectedRow();
				System.out.println(artPiecetable.getModel().getValueAt(row, 1).toString());
				EmployeeMangeUI.artPieceIDX = artPiecetable.getModel().getValueAt(row, 1).toString();
			}
		});
		jsp = new JScrollPane(artPiecetable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 									
    			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		artPiecetable.setBounds(25, 150, 530, 250);
		getContentPane().add(artPiecetable);
		
		JLabel artPieceComment = new JLabel("작품추가");
		artPieceComment.setBounds(25, 70, 110, 50);;
		getContentPane().add(artPieceComment);
		
		
		JTextField txInsert = new JTextField();
		txInsert.setBounds(100, 70, 200, 50);
		getContentPane().add(txInsert);
		txInsert.setColumns(10);
		
		JLabel yearComment = new JLabel("날짜 입력");
		yearComment.setBounds(305, 70, 100, 50);;
		getContentPane().add(yearComment);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		JFormattedTextField txtDATE = new JFormattedTextField(df);
		txtDATE.setBounds(380, 70, 110, 50);
		getContentPane().add(txtDATE);
		txtDATE.setColumns(10);
		
		JButton insertBtn = new JButton("Insert");
		insertBtn.setBounds(572, 56, 97, 23);
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = control.insertArtPiece(txInsert.getText(), txtDATE.getText(), EmployeeMangeUI.exID);
				if(row > 0) {
					EmployeeMangeUI employeeMangeUI = new EmployeeMangeUI(EmployeeMangeUI.eid);
					
	        		JOptionPane.showMessageDialog(null, "추가되었습니다.", "추가", JOptionPane.DEFAULT_OPTION);

					employeeMangeUI.setVisible(true);
					control.Disconnect();
					dispose();
				}
				else {
					EmployeeMangeUI employeeMangeUI = new EmployeeMangeUI(EmployeeMangeUI.eid);
					
	        		JOptionPane.showMessageDialog(null,"추가오류", "추가", JOptionPane.DEFAULT_OPTION);
					employeeMangeUI.setVisible(true);
					control.Disconnect();
					dispose();
				}
			}
		});
		getContentPane().add(insertBtn);
		
		JButton modifyBtn = new JButton("Modify");
		modifyBtn.setBounds(572, 89, 97, 23);
		getContentPane().add(modifyBtn);
		modifyBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EmployeeModifyUI ui = new EmployeeModifyUI(EmployeeMangeUI.artPieceIDX, EmployeeMangeUI.eid);
				ui.setVisible(true);
				dispose();
				
			}
		});
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(572, 122, 97, 23);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "작품을 삭제 하시겠습니까?",
						"Confirm", JOptionPane.YES_NO_OPTION);

				if(result == JOptionPane.CLOSED_OPTION) {
					//사용자가  "예" 아니오" 선택없이 다이얼로그 창을 닫을 경우
				}
				else if(result == JOptionPane.YES_OPTION) {
					int re = control.deleteArtPiece(EmployeeMangeUI.artPieceIDX);
					if(re == 0 ) {
		        		 JOptionPane.showMessageDialog(null, "삭제오류", "데이터베이스오류!!", JOptionPane.DEFAULT_OPTION);
					}
					else if(re == 1) {
						EmployeeMangeUI.artPieceIDX = null;
						EmployeeMangeUI employeeMangeUI = new EmployeeMangeUI(EmployeeMangeUI.eid);
		        		JOptionPane.showMessageDialog(null, "삭제", "삭제되었습니다.", JOptionPane.DEFAULT_OPTION);

						employeeMangeUI.setVisible(true);
						control.Disconnect();
						dispose();
					}
				}
				else {
					
				}
			}
		});
		getContentPane().add(deleteBtn);
	}
}
