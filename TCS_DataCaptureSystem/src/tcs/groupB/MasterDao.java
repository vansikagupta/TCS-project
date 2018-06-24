package tcs.groupB;

import java.sql.*;
import java.util.ArrayList;

public class MasterDao 
{
	public ArrayList<Master> getMaster()
	{
		ArrayList<Master> MasterList = new ArrayList<>();
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XE";
	    String username = "SYSTEM";
	    String password = "gupta@18";
	    String sql="select * from Master";
	    try
	    {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	//System.out.println("Cannot load");
	    	Connection con= DriverManager.getConnection(jdbcUrl,username,password);
	    	//System.out.println("Cannot connect");
	    	Statement st=con.createStatement();
	    	ResultSet rs= st.executeQuery(sql);
	    	
	    	while(rs.next())
	    	{
	    		Master m=new Master(rs.getInt(1),rs.getString(2),
	    				rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
	    				rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getFloat(12),rs.getString(13));
	    		MasterList.add(m);
	    	}
	    }
	    catch(Exception e)
	    {
	    	System.out.println(e);
	    }
	    return MasterList;
	}

}
