package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarList extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private JTable table;
	//추가
	private CarDAO dao;
	private Vector cols;
//	private DefaultTableModel model;
	private CarLog frm;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarList frame = new CarList();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public CarList(CarLog frm) {
		this();
		this.frm=frm;
	}
	
	public CarList() {
		setTitle("차량검색");
		dao = new CarDAO();
		cols=new Vector();
		cols.add("제조사");
		cols.add("차량이름");
		cols.add("연도");
		cols.add("연비");
		cols.add("가격");
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 498, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refreshTable();
			}
		});
		panel.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
				
				JOptionPane.showMessageDialog(CarList.this, "편집후 새로 검색해주세요");

//				refreshTable();
			}
		});
		panel.add(btnSearch);
		
		JButton btnInsert = new JButton("추가");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarSave frm = new CarSave(CarList.this);
				frm.setVisible(true);
				frm.setLocation(580, 100);
			}
		});
		panel.add(btnInsert);
		
		JButton btnEdit = new JButton("편집");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx=table.getSelectedRow();
				System.out.println(idx);
				if(idx == -1) {
					JOptionPane.showMessageDialog(CarList.this, "차량을선택해주세요.");
				return;
				}else {
					String name = String.valueOf(table.getValueAt(idx, 0));
					CarEdit frm=
							new CarEdit(CarList.this,name);
					frm.setVisible(true);
					frm.setLocation(550,100);
				}
			}
		});
		panel.add(btnEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		refreshTable();
	}
	
	public void refreshTable() {
		DefaultTableModel model=
				new DefaultTableModel(dao.listcar(),cols);
		table.setModel(model);
	}
	public void search() {
		String name = tfSearch.getText();
		DefaultTableModel model= new DefaultTableModel(dao.searchCar(name),cols);
		table.setModel(model);
		
	}


}
