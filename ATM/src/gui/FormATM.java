package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import gui.CKATM;
import gui.Ruttien;
public class FormATM extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conn;
	Statement stmt;
	private JTextField tfPin;	
	private JTextField tfID;
	ResultSetMetaData rsm;
	JScrollPane scrollPane = new JScrollPane();
	// lay du lieu tren xampp
	private JTable table;
	Object[] titles = {"ID","Pin","Ten","Sotien"};
	public void connectdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bt", "root", "");
			System.out.println("Connect Sucess");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { 
			public void run() {
				try {
					FormATM frame = new FormATM();
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
	public FormATM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnRutTien = new JButton("Rut Tien ");
		btnRutTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Ruttien obj= new Ruttien();
				obj.IdRutTien = new String(tfID.getText());
				obj.PinRutTien = new String(tfPin.getText());
				obj.setVisible(true);
			}
		});
		btnRutTien.setBounds(0, 182, 128, 78);
		contentPane.add(btnRutTien);
		
		JButton btnKiemTraSoDu = new JButton("Kiem tra so du");
		btnKiemTraSoDu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connectdb();
					stmt = conn.createStatement();
					ResultSet res = conn.createStatement().executeQuery(" Select * from atm where ID = '" +tfID.getText() +"' and '"+ tfPin.getText() +"'");
					rsm=res.getMetaData();
					
					DefaultTableModel model = new DefaultTableModel(titles,0);
					while(res.next())
					{
						String ID = res.getString("ID");
						String Pin= res.getString("Pin");
						String Ten= res.getString("Ten");
						String SoTien= res.getString("SoTien");
						Object[] titles = {ID,Pin,Ten,SoTien};
						model.addRow(titles);
	
					}
					
					table.setModel(model);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnKiemTraSoDu.setBounds(0, 309, 128, 84);
		contentPane.add(btnKiemTraSoDu);
		JButton btnChuyenKhoan = new JButton("Chuyen Khoan");
		btnChuyenKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CKATM obj = new CKATM();
				obj.setVisible(true);
			}
		});
		btnChuyenKhoan.setBounds(818, 309, 138, 84);
		contentPane.add(btnChuyenKhoan);
		
		JLabel lblAtm = new JLabel("ATM");
		lblAtm.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblAtm.setBounds(432, 11, 102, 43);
		contentPane.add(lblAtm);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(165, 77, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblPin = new JLabel("Pin");
		lblPin.setBounds(165, 113, 46, 14);
		contentPane.add(lblPin);
		
		tfPin = new JTextField();
		tfPin.setBounds(214, 110, 86, 20);
		contentPane.add(tfPin);
		tfPin.setColumns(10);
		
		tfID = new JTextField();
		tfID.setBounds(214, 74, 86, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		JButton btnDangNhap = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( tfID.getText().equals("") || new String (tfPin.getText()).equals(""))
				{
					JOptionPane.showMessageDialog(tfID, " khong de trong", "thong bao",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					connectdb();
					ResultSet res = conn.createStatement().executeQuery(String.format("Select * from atm where ID = '" +tfID.getText()+"' and '"+ tfPin.getText() +"'"));
					if ( res !=null) 
					{
							
						if (res.next())
						{
							if ( res.getString("Pin").equals(new String(tfPin.getText()))) 
								{
									JOptionPane.showMessageDialog(tfPin, " Dang nhap thanh cong ", "Thanh cong",JOptionPane.INFORMATION_MESSAGE);
								
								}
							else {
								JOptionPane.showMessageDialog(tfID, " Sai Pin", "That bai",JOptionPane.ERROR_MESSAGE);
								 }
						}
						else
							{
								JOptionPane.showMessageDialog(tfID, " Sai ID hoac Pin", "thong bao",JOptionPane.ERROR_MESSAGE);
							}
					}
					}
			
				 catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
				
		 });
		btnDangNhap.setBounds(343, 73, 121, 23);
		contentPane.add(btnDangNhap);
		
		table = new JTable();
		table.setBounds(183, 182, 588, 489);
		contentPane.add(table);
	
		
	}
}
