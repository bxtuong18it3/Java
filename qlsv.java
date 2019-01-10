package thu;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class qlsv extends JFrame implements  ActionListener,MouseListener {

	Connection conn;
	Statement stm;
	ResultSet rst;
	
	Vector vData = new Vector();
	Vector vTitle = new Vector();
	JScrollPane tableResult;
	DefaultTableModel model;
	JTable tb = new JTable();
	JButton  btnsua, btnxoa, btnthem, btntim;
	JTextField tftim;
	int selectedrow = 0;
	
	public qlsv(String s)
	{
		super(s);
		try
		{
			tftim = new JTextField("20");
			JPanel p = new JPanel();
			btnsua = new JButton("Chinh Sua");
			btnsua.addActionListener(this);
			btnxoa = new JButton("Xoa");
			btnxoa.addActionListener(this);
			btnthem = new JButton("Them");
			btnthem.addActionListener(this);
			btntim = new JButton("Tim Kiem");
			btntim.addActionListener(this);
			
			btntim.addActionListener(this);
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=quanlydiem;integratedSecurity=TRUE");
			stm = conn.createStatement();
			p.add(btnsua);
			p.add(btnxoa);
			p.add(btnthem);
			p.add(btntim);
			
			getContentPane().add(p, "South");
			reload();
			model = new DefaultTableModel(vData,vTitle);
			tb = new JTable(model);
			tb.addMouseListener(this);
			tableResult = new JScrollPane(tb);
			this.getContentPane().add(tableResult, "Center");
			this.setSize(1300, 700);
			this.setVisible(true);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void reload()
	{
		try
		{
			vTitle.clear();
			vData.clear();
			ResultSet rst = stm.executeQuery("select * from students");
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			vTitle.add("STT");
			for (int i=1;i<=num_column;i++)
			{
				vTitle.add(rstmeta.getColumnLabel(i));
			}
			int stt=0;
			while (rst.next())
			{
				stt++;
				Vector row = new Vector(num_column);
				row.add(String.valueOf(stt));
				for (int i=1;i<=num_column;i++)
					row.add(rst.getString(i));
					vData.add(row);
			}
			rst.close();
		}
		catch (Exception e1)
		{
			System.out.println(e1.getMessage());
		}
	}
	public void xoa()
	{
		try
		{
			Vector st = (Vector)vData.elementAt(selectedrow);
			stm.executeUpdate("Delete from students where ID = '"+st.elementAt(1)+"'");
			vData.remove(selectedrow);
			model.fireTableDataChanged();
		}
		catch (Exception e2)
		{
			e2.printStackTrace();
		}
	}
	public void actionPerformed1(ActionEvent e)
	{
		if(e.getActionCommand().equals("xong"))
		{
			
		}
	}
	public void Tim(String tim)
	{
		try {
			vTitle.clear();
			vData.clear();
			String sql = "select * from students where name like '%"+tim+"%'";
			ResultSet rst = stm.executeQuery(sql);
			ResultSetMetaData rstmeta = rst.getMetaData();
			int num_column = rstmeta.getColumnCount();
			int STT=0;
			while (rst.next())
			{
				STT++;
				Vector row = new Vector(num_column);
				row.add(String.valueOf(STT));
				for (int i=1;i<=num_column;i++)
					row.add(rst.getString(i));
				vData.add(row);
			}
			rst.close();
			model.fireTableDataChanged();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Chinh Sua"))
		{
			Vector st = (Vector)vData.elementAt(selectedrow);
			new Them(" Chinh sua thong tin sinh vien:",(String)st.elementAt(1),(String)st.elementAt(2),(String)st.elementAt(3),(String)st.elementAt(4),(String)st.elementAt(5),(String)st.elementAt(6),(String)st.elementAt(7),(String)st.elementAt(8),this);
		}
		if(e.getActionCommand().equals("Tim Kiem"))
		{
			new timkiem(this);
		}
		if(e.getActionCommand().equals("Xoa"))
		{
			xoa();
		}
		if(e.getActionCommand().equals("Them"))
		{
			new Them("Them thong tin sinh vien","","","","","","","","",this);
		}
		}
	
	public void mouseClicked(MouseEvent e)
	{
		selectedrow = tb.getSelectedRow();
	}
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e)
	{}
	public void mousePressed(MouseEvent e)
	{}
	public void mouseReleased(MouseEvent e)
	{}
	public static void main(String[] args)
	{
			new qlsv("quan ly sinh vien");
		
	}
}
class timkiem extends JFrame implements  ActionListener
{   JLabel lbltim;
	JTextField tftim;
	JButton btnok;
	JButton btncanel;
	qlsv msv;

	
	
	public timkiem(qlsv sv)
	{
		
		Container cont = this.getContentPane();
		cont.setLayout(new GridLayout(2,2));
		msv=sv;
		lbltim = new JLabel("Tim Kiem");
		tftim = new JTextField();
		btnok = new JButton("ok");
		btnok.addActionListener(this);
		btncanel = new JButton("canel");
		btncanel.addActionListener(this);
		cont.add(lbltim);
		cont.add(tftim);
		cont.add(btnok);
		cont.add(btncanel);
		this.setSize(100, 100);
		this.setLocation(250, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("ok"))
		{
			String tim = tftim.getText();
			msv.Tim(tim);
		}
		else this.dispose();
		
	}
	
}


