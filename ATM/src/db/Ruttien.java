package db;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Ruttien extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1772021687833513864L;
	private JPanel contentPane;
	private JTextField tfSoTienRut;
	Statement stmt;
	Connection conn;
	String IdRutTien;
	String PinRutTien;
	ResultSet kq;
	public void connectdb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bt", "root","");
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
					Ruttien frame = new Ruttien();
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
	public Ruttien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSoTienRut = new JLabel("S\u1ED1 ti\u1EC1n c\u1EA7n rut");
		lblSoTienRut.setBounds(21, 68, 83, 14);
		contentPane.add(lblSoTienRut);
		
		tfSoTienRut = new JTextField();
		tfSoTienRut.setBounds(126, 65, 86, 20);
		contentPane.add(tfSoTienRut);
		tfSoTienRut.setColumns(10);
		
		JButton btnRut = new JButton("Rut");
		btnRut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connectdb();
					stmt = conn.createStatement();
					kq = stmt.executeQuery(" Select * from atm where ID='"+IdRutTien +"' and Pin='"+PinRutTien+"'");
					
					if (kq != null) {
					while(kq.next())
					{
						Float check = kq.getFloat(4);
						if (check >0)
						{
							if ( Float.parseFloat(tfSoTienRut.getText()) > kq.getFloat(4))
								{
								    JOptionPane.showMessageDialog(lblSoTienRut, "Tai khoan khong du", "Thong bao",JOptionPane.INFORMATION_MESSAGE);
								    
								}
							else
								{
									Float SoTruoc = kq.getFloat(4);
									Float SoSau = SoTruoc - Float.parseFloat(tfSoTienRut.getText());
									stmt.executeUpdate("Update atm set Sotien='"+SoSau+"' where ID='"+IdRutTien+"' and Pin='"+PinRutTien+"'");
									JOptionPane.showMessageDialog(lblSoTienRut, "Thanh Cong", "Thanh cong",JOptionPane.INFORMATION_MESSAGE);
									break;
								}
						}
					
					}
					
					}
					
					else JOptionPane.showMessageDialog(lblSoTienRut, "Chua nhap so tien rut", "Thong bao",JOptionPane.INFORMATION_MESSAGE);
										
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnRut.setBounds(253, 64, 89, 23);
		contentPane.add(btnRut);
	}
}
