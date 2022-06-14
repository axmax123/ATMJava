package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CKATM extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIDNhan;
	private JTextField tfSoTienChuyen;
	Connection conn;
	Statement stmtc, stmtn;
	ResultSet rsc, rsn;
	ResultSetMetaData rsmdc, rsmdn;
	String IDCtien, PinCtien;
	public void connectDB()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/bt", "root", "");
			System.out.println("Connect Success");
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
					CKATM frame = new CKATM();
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
	public CKATM() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChuynTin = new JLabel("Chuy\u1EC3n Ti\u1EC1n");
		lblChuynTin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblChuynTin.setBounds(232, 41, 138, 32);
		contentPane.add(lblChuynTin);
		
		JLabel lblIDnhan = new JLabel("S\u1ED1 ID nh\u1EADn ti\u1EC1n:");
		lblIDnhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIDnhan.setBounds(34, 115, 100, 23);
		contentPane.add(lblIDnhan);
		
		JLabel lblSoTienChuyen = new JLabel("S\u1ED1 ti\u1EC1n chuy\u1EC3n:");
		lblSoTienChuyen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSoTienChuyen.setBounds(34, 170, 100, 23);
		contentPane.add(lblSoTienChuyen);
		
		tfIDNhan = new JTextField();
		tfIDNhan.setBounds(232, 115, 138, 26);
		contentPane.add(tfIDNhan);
		tfIDNhan.setColumns(10);
		
		tfSoTienChuyen = new JTextField();
		tfSoTienChuyen.setBounds(232, 170, 138, 26);
		contentPane.add(tfSoTienChuyen);
		tfSoTienChuyen.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connectDB();
					stmtc = conn.createStatement();
					rsc = stmtc.executeQuery("Select * from atm where ID='"+IDCtien+"' and Pin='"+PinCtien+"'");
					rsmdc = rsc.getMetaData();
					stmtn = conn.createStatement();
					rsn = stmtn.executeQuery("Select * from atm where ID='"+tfIDNhan.getText()+"'");
					rsmdn = rsn.getMetaData();
					if (rsn != null)
					{
						if (rsc.next() && rsn.next())
						{
							float checkBalIDgui = rsc.getFloat("Balance");
							float checkBalIDsaukhigui = 0;
							checkBalIDsaukhigui = checkBalIDgui - Float.parseFloat(tfSoTienChuyen.getText());
							float tgn=0, tgc=0;
							if (checkBalIDgui>0 && checkBalIDsaukhigui>0)
							{
								if (tfIDNhan.getText().equals(rsn.getObject(1)))
								{
									tgn = rsn.getFloat("Balance") + Float.parseFloat(tfSoTienChuyen.getText());
									tgc = rsc.getFloat("Balance") - Float.parseFloat(tfSoTienChuyen.getText());
									int n = stmtn.executeUpdate("Update atm set Balance='"+tgn+"' where ID='"+tfIDNhan.getText()+"'");
									int c = stmtc.executeUpdate("Update atm set Balance='"+tgc+"' where ID='"+IDCtien+"'");
									if (n>0 && c>0)
									{
										JOptionPane.showMessageDialog(tfIDNhan, "Chuyen tien thanh cong.", "Thong Bao", JOptionPane.INFORMATION_MESSAGE);
									}
								
								}
							
							}
							else JOptionPane.showMessageDialog(tfSoTienChuyen, "So du khong du.", "Thong Bao", JOptionPane.ERROR_MESSAGE);
						}
						else JOptionPane.showMessageDialog(tfIDNhan, "Kiem tra lai tai khoan nhan.", "Thong Bao", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnOk.setBounds(232, 237, 89, 23);
		contentPane.add(btnOk);
	}
}