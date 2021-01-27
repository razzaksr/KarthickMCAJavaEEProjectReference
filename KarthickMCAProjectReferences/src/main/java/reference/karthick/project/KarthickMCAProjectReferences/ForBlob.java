package reference.karthick.project.KarthickMCAProjectReferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ForBlob 
{
	Scanner scan=new Scanner(System.in);
	File file;
	InputStream is;
	public void upload()
	{
		try {
			System.out.println("Tell us user name: ");
			String user=scan.nextLine();
			System.out.println("Tell us location of file to upload for the "+user);
			file=new File(scan.nextLine());
			is=new FileInputStream(file);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String qry="update info set doc=? where user=?";
			PreparedStatement pre=con.prepareStatement(qry);
			pre.setBinaryStream(1, is);
			pre.setString(2, user);
			int ack=pre.executeUpdate();
			if(ack!=0)
			{
				System.out.println("Document uploaded");
			}
			else {System.out.println("Document Not uploaded");}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void download()
	{
		try {
			System.out.println("Tell us user name: ");
			String user=scan.nextLine();
			System.out.println("Tell us password: ");
			String pass=scan.next();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
			String qry="select doc from info where user=? and pass=?";
			PreparedStatement pre=con.prepareStatement(qry);
			pre.setString(1, user);
			pre.setString(2, pass);
			ResultSet res=pre.executeQuery();
			if(res.next())
			{
				is=(InputStream)res.getBinaryStream("doc");
				file=new File("C:\\Users\\SRDB\\Downloads\\karthick\\downloaded.jpg");
				byte[] tmp=new byte[is.available()];
				is.read(tmp);
				FileOutputStream fos=new FileOutputStream(file);
				fos.write(tmp);
				fos.close();
				is.close();
				System.out.println("File downloaded @ "+file.getAbsolutePath());
			}
			else {System.out.println("Document Not downloaded");}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
