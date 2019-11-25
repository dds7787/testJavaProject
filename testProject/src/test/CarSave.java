package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CarSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfCompany;
	private JTextField tfName;
	private JTextField tfYear;
	private JTextField tfEfficiency;
	private JTextField tfMoney;
	//추가
	private CarList list;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarSave frame = new CarSave();
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
	public CarSave(CarList list) {
		this();
		this.list=list;
		
	}
	
	public CarSave() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 282, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("제조사");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(103, 7, 116, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("차량이름");
		lblNewLabel_1.setBounds(12, 67, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(103, 64, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("연도");
		lblNewLabel_2.setBounds(12, 116, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfYear = new JTextField();
		tfYear.setBounds(103, 116, 116, 21);
		contentPane.add(tfYear);
		tfYear.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("연비");
		lblNewLabel_3.setBounds(12, 178, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfEfficiency = new JTextField();
		tfEfficiency.setBounds(103, 175, 116, 21);
		contentPane.add(tfEfficiency);
		tfEfficiency.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("가격");
		lblNewLabel_4.setBounds(12, 233, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfMoney = new JTextField();
		tfMoney.setBounds(103, 230, 116, 21);
		contentPane.add(tfMoney);
		tfMoney.setColumns(10);
		
		JButton btnNewButton = new JButton("저장");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =tfName.getText();
				String company = tfCompany.getText();
				String year =tfYear.getText();
				int efficiency = Integer.parseInt(tfEfficiency.getText());
				int money = Integer.parseInt(tfMoney.getText());
				CarDTO dto=new CarDTO(company, name, year, efficiency, money);
				CarDAO dao=new CarDAO();
				int result=dao.insertCar(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(CarSave.this, "저장되었습니다.");
					list.refreshTable();
					dispose();
				}
			}
		});
		btnNewButton.setBounds(83, 297, 97, 23);
		contentPane.add(btnNewButton);
	}
}
