package reference.karthick.project.KarthickMCAProjectReferences;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	Scanner scan=new Scanner(System.in);
	Connection con;
	PreparedStatement pre;
	String qry;
	public void getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public App()
	{
		
	}
	public void insertNewRecord()
	{
		System.out.println("Tell us username: ");
		String user=scan.next();
		System.out.println("Tell us password: ");
		String pass=scan.next();
		System.out.println("Tell us email: ");
		String email=scan.next();
		getConnection();
		qry="insert into info(user,pass,email) values(?,?,?)";
		try {
			pre=con.prepareStatement(qry);
			pre.setString(1, user);pre.setString(2, pass);
			pre.setString(3, email);
			int ack=pre.executeUpdate();
			if(ack!=0)
			{
				System.out.println("Record inserted");
			}
			else {System.out.println("Record not inserted");}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static void main( String[] args )
    {
    	App app=new App();
        System.out.println("Profile Management");
        //app.insertNewRecord();
        //new ForBlob().upload();
        new ForBlob().download();
    }
}
