package tcs.groupB;

import java.sql.*;
import java.util.ArrayList;

public class MasterDao 
{
	Connection getCon() throws ClassNotFoundException, SQLException
	{
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
	    String username = "SYSTEM";
	    String password = "gupta@18";
	    
	 
	    Class.forName("oracle.jdbc.driver.OracleDriver");
	   	Connection con= DriverManager.getConnection(jdbcUrl,username,password);
	    System.out.println("Connected");
	   	return con;
	    	
	}
	//called by getdatacontroller servlet
	public ArrayList<Master> getMaster()
	{
		ArrayList<Master> MasterList = new ArrayList<>();
		String sql="select * from Master";
		
	    try
	    {
	    	Connection con=getCon();
	    	Statement st=con.createStatement();
	    	ResultSet rs= st.executeQuery(sql);
	    	
	    	while(rs.next())
	    	{
	    		Master m=new Master(rs.getInt(1),rs.getString(2),
	    				rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
	    				rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getFloat(12),rs.getString(13));
	    		MasterList.add(m);
	    	}
	    	st.close();
			con.close();
			rs.close();
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    return MasterList;
	}
	
	//called by ProcessDataController
	public void processData()
	{
		String sql="{call Update_All}";
		System.out.println("In Process Data Method");
		CallableStatement storedProc = null;
		Connection con;
		try
		{
			con=getCon();
	        storedProc = con.prepareCall(sql);
	        storedProc.executeQuery();
	        System.out.println("Query executed");
	        storedProc.close();
	        
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println(e);
	    }
		catch (ClassNotFoundException e) 
	    {
	    	System.out.println(e);
	    }
		catch (Exception e) 
	    {
	    	System.out.println(e);
	    }
	}

}
