package thu;

import java.sql.Connection;
import java.sql.DriverManager;

public class thu {
	public static void main (String args[])
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			DriverManager.getConnection("jdbc:sqlserver://DESKTOP-EJPNPAF;databaseName=qlktx;integratedSecurity=TRUE");
			System.out.println("ket noi thanh cong");
		}
		catch(Exception e)
		{
			e.getStackTrace();
			e.printStackTrace();
		
		}
		}
}
