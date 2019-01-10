package thu;
	import java.awt.*;
	import java.awt.event.*;
	import java.sql.*;
	import java.util.*;

	import javax.swing.*;

	public class Them extends JFrame implements ActionListener
	{
		Connection conn;
		Statement stm;
		ResultSet rst;
		JLabel lblid;
		JTextField tfid;
		JLabel lblname;
		JTextField tfname;
		JLabel lbllophoc;
		JTextField tflophoc;
		JLabel lblGT;
		ButtonGroup btngGT = new ButtonGroup();
		JRadioButton rdbtnNam;
		JRadioButton rdbtnNu;
		JTextField tfGT;
		JLabel lablmath;
		JTextField tfmath;
		JLabel lblphys;
		JTextField tfphys;
		JLabel lablchem;
		JTextField tfchem;
		JLabel lablaver;
		JTextField tfaver;
		JLabel errorlb;
		JLabel errordetails;
		JButton btnxong;
		JButton btnthoat;
		qlsv mst;
		public Them(String s, String id,  String name, String lophoc, String GT,String math, String phys, String chem,String aver, qlsv st)
		{
			super(s);
			try
			{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=quanlydiem;integratedSecurity=TRUE");
			stm = conn.createStatement();
			mst = st;
			Container cont = this.getContentPane();
			cont.setLayout(new GridLayout(10,1));
			
			JPanel pid = new JPanel();
			lblid = new JLabel("Ma sinh vien:");
			tfid = new JTextField(id,15);
			pid.add(lblid);
			pid.add(tfid);
			cont.add(pid);
			
			JPanel pten = new JPanel();
			lblname = new JLabel("Ho & ten: ");
			tfname = new JTextField(name,15);
			pten.add(lblname);
			pten.add(tfname);
			cont.add(pten);
			
			JPanel plophoc = new JPanel();
			lbllophoc = new JLabel("lop hoc  :");
			tflophoc = new JTextField(lophoc,15);
			plophoc.add(lbllophoc);
			plophoc.add(tflophoc);
			cont.add(plophoc);
			
			JPanel pGT = new JPanel();
			lblGT = new JLabel("Gioi tinh:");
			rdbtnNam = new JRadioButton("Nam:");
			btngGT.add(rdbtnNam);
			rdbtnNu = new JRadioButton("Nu:");
			btngGT.add(rdbtnNu);
			pGT.add(lblGT);
			pGT.add(rdbtnNam);
			pGT.add(rdbtnNu);
			cont.add(pGT);
			if (GT.equals("Nam")) rdbtnNam.setSelected(true);
			else if (GT.equals("Nu")) rdbtnNu.setSelected(true);
			
			JPanel pmath = new JPanel();
			JLabel lblmath = new JLabel("math:");
			tfmath = new JTextField(15);
			pmath.add(lblmath);
			pmath.add(tfmath);
			cont.add(pmath);
			
			JPanel pphys = new JPanel();
			lblphys = new JLabel("phys:");
			tfphys = new JTextField(15);
			pphys.add(lblphys);
			pphys.add(tfphys);
			cont.add(pphys);
			
			JPanel pchem = new JPanel();
			JLabel lblchem = new JLabel("chem:");
			tfchem = new JTextField(15);
			pchem.add(lblchem);
			pchem.add(tfchem);
			cont.add(pchem);
			
			JPanel paver = new JPanel();
			JLabel lblaver = new JLabel("aver:");
			tfaver = new JTextField(15);
			paver.add(lblaver);
			paver.add(tfaver);
			
			errorlb = new JLabel("");
			errordetails = new JLabel("");
			errorlb.setVisible(false);
			errordetails.setVisible(false);
			cont.add(errorlb);
			cont.add(errordetails);
			
			JPanel pbtn = new JPanel();
			btnxong = new JButton("Xong");
			btnxong.addActionListener(this);
			btnthoat = new JButton("Thoat");
			btnthoat.addActionListener(this);
			pbtn.add(btnxong);
			pbtn.add(btnthoat);
			cont.add(pbtn);
			
			this.setSize(500, 400);
			this.setVisible(true);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Xong"))
			{
				//Gọi hàm cập nhật dữ liệu
				themdl();
			}
			else this.dispose();
			//Tắt cửa sổ khi ấn Cancel
		}
		
		public void themdl()
		{
			
			
				try
				{
					//Lấy nội dung đã nhập ở giao diện
					String id = tfid.getText();
					String name = tfname.getText();
					String lophpoc = tflophoc.getText();
					String GT = "";
					if (rdbtnNu.isSelected()) GT = "Nu";
					else if (rdbtnNam.isSelected()) GT = "Nam";
					float math = Float.parseFloat(tfmath.getText());
					float phys = Float.parseFloat(tfphys.getText());
					float chem = Float.parseFloat(tfchem.getText());
					String sql ="";
					//Nếu là nhập mới
					
					 
					if (this.getTitle().equals("Them thong tin sinh vien"))
						sql ="insert into students(id,Name,lophoc,gioitinh,Math,Phys,Chem,Aver) "
								+ "values('"+id+"','"+name+"','"+lophpoc+"','"+GT+"',"+math+","+phys+","+chem+","+(math+phys+chem)/3+")";
					else sql ="update students set Name='"+name+"',lophoc='"+lophpoc+"',gioitinh='"+GT+"', Math="+math+", Phys="+phys+", Chem="+chem+", Aver="+(math+phys+chem)/3+"where ID="+tfid.getText()+"";
					//Cập nhật vào csdl
					mst.stm.executeUpdate(sql);
					//Cập nhật giao diện cửa sổ chính
					mst.reload();
					mst.model.fireTableDataChanged();
					//Tắt cửa sổ
					this.dispose();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
		}
	}