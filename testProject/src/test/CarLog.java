package test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CarLog extends JFrame {

	private JPanel contentPane;
	private JTextField userid;
	private JPasswordField pwd;
	//추가
	private CarDAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarLog frame = new CarLog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CarLog() {
		setTitle("관리자");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(12, 42, 57, 15);
		contentPane.add(lblNewLabel);
		
		userid = new JTextField();
		userid.setBounds(127, 39, 116, 21);
		contentPane.add(userid);
		userid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(12, 107, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		pwd = new JPasswordField();
		pwd.setBounds(127, 104, 116, 18);
		contentPane.add(pwd);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ids = userid.getText();
				String strpwd = String.valueOf(pwd.getPassword());
				Connection co=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				CarList frm = new CarList(CarLog.this);
				
				try {
					FileInputStream fis = new FileInputStream("d:\\oracle계정.prop");
					Properties prop =new Properties();
					prop.load(fis);
					String url = prop.getProperty("url");
					String id = prop.getProperty("id");
					String password = prop.getProperty("password");
					//
					co = DriverManager.getConnection(url, id, password);
					String sql = "select * from carid "
							+ " where id=? and pwd=?";
					ps = co.prepareStatement(sql);
					ps.setString(1, ids);
					ps.setString(2, strpwd);
					rs = ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(CarLog.this, "환영합니다.");
					}else {
						JOptionPane.showMessageDialog(CarLog.this, "아이디.비밀번호를 확인해주세요.");
						System.exit(0);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}finally {
					try {
						if(rs!=null) rs.close();
					} catch (Exception e3) {
						e3.printStackTrace();
					}
					try {
						if(ps!=null) ps.close();
					} catch (Exception e3) {
						e3.printStackTrace();
					}
					try {
						if(co!=null) co.close();
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}//finally
				frm.setVisible(true);
				frm.setLocation(200,300);
			}
		});
		btnLogin.setBounds(100, 183, 97, 23);
		contentPane.add(btnLogin);
	}
}
