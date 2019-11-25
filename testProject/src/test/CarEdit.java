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

public class CarEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfCompany;
	private JTextField tfName;
	private JTextField tfYear;
	private JTextField tfEfficiency;
	private JTextField tfMoney;
	//추가
	private CarList list;
	private CarDAO dao;
	private CarDTO dto;
	private String name;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CarEdit frame = new CarEdit();
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
	public CarEdit(CarList list, String name) {
		this.list = list;
		this.name =name;
		System.out.println(list);
		System.out.println(dto);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 302, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("제조사");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(110, 7, 116, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("차량이름");
		lblNewLabel_1.setBounds(12, 60, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(110, 57, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("연도");
		lblNewLabel_2.setBounds(12, 108, 57, 15);
		contentPane.add(lblNewLabel_2);
		
		tfYear = new JTextField();
		tfYear.setBounds(110, 105, 116, 21);
		contentPane.add(tfYear);
		tfYear.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("연비");
		lblNewLabel_3.setBounds(12, 161, 57, 15);
		contentPane.add(lblNewLabel_3);
		
		tfEfficiency = new JTextField();
		tfEfficiency.setBounds(110, 158, 116, 21);
		contentPane.add(tfEfficiency);
		tfEfficiency.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("가격");
		lblNewLabel_4.setBounds(12, 209, 57, 15);
		contentPane.add(lblNewLabel_4);
		
		tfMoney = new JTextField();
		tfMoney.setBounds(110, 203, 116, 21);
		contentPane.add(tfMoney);
		tfMoney.setColumns(10);
		
	    dao = new CarDAO();
	    dto = dao.viewCar(name);
	    tfName.setText(dto.getName());
	    tfCompany.setText(dto.getCompany());
	    tfYear.setText(dto.getYear());
	    tfEfficiency.setText(dto.getEfficiency()+"");
	    tfMoney.setText(dto.getMoney()+"");
	    
		
		JButton btnNewButton = new JButton("수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name =tfName.getText();
				String company = tfCompany.getText();
				String year = tfYear.getText();
				int efficiency = Integer.parseInt(tfEfficiency.getText());
				int money = Integer.parseInt(tfMoney.getText());
				CarDTO dto=new CarDTO(company, name, year, efficiency, money);
				int result = dao.updateCar(dto);
				if(result == 1) {
					JOptionPane.showMessageDialog(CarEdit.this, "수정되었습니다.");
					list.refreshTable();
					dispose();
				}
			}
		});
		btnNewButton.setBounds(22, 284, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				int result = 0;
				int response=JOptionPane.showConfirmDialog(CarEdit.this, "삭제하시겠습니까?");
				if(response == JOptionPane.YES_OPTION) {
					result = dao.deleteCar(name);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(CarEdit.this, "삭제되었습니다.");
					list.refreshTable();
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(156, 284, 97, 23);
		contentPane.add(btnNewButton_1);
	}
}
