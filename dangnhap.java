
package thu;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class dangnhap extends JFrame
{
	Connection conn;
	Statement stm;
	JLabel lbltaikhoan;
	JTextField tftaikhoan;
	JLabel lblmatkhau;
	JPasswordField tfmatkhau;
	JButton btndangnhap;
	public dangnhap(String s)
	{
		super(s);
		lbltaikhoan = new JLabel("Tai Khoan: ");
		tftaikhoan = new JTextField(10);
		lblmatkhau = new JLabel("Mat Khau: ");
		tfmatkhau = new JPasswordField(10);
		btndangnhap = new JButton("Dang Nhap");
		btndangnhap.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					Connection conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=quanlydiem;integratedSecurity=TRUE");
					if (tftaikhoan.getText().equals("")||tfmatkhau.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Nhap lai...");
					else
					{
					PreparedStatement st = conn.prepareStatement("Select * from accounts where email=? and pass=?");
					st.setString(1, tftaikhoan.getText());
					st.setString(2, tfmatkhau.getText());
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");
						new qlsv("Quan Ly Sinh Vien ");
						}
					else JOptionPane.showMessageDialog(null, "Dang nhap that bai");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		Container cont = this.getContentPane();
		setSize(250, 150);
		setLocationRelativeTo(null);
		cont.setLayout(new FlowLayout());
		cont.add(lbltaikhoan);
		cont.add(tftaikhoan);
		cont.add(lblmatkhau);
		cont.add(tfmatkhau);
		cont.add(tfmatkhau);
		cont.add(btndangnhap);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args)
	{
			new dangnhap("Dang nhap");
		
	}
}
