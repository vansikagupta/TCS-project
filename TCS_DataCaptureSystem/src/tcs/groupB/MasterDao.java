package tcs.groupB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	    				rs.getInt(8),rs.getString(9),rs.getString(10),rs.getFloat(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15));
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
	
	public void readExcel(String filepath)
	{
		
		File file=null;
		try 
		{
			file = new File(filepath);
			
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(file);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(filepath.toLowerCase().endsWith("xlsx"))
			{
				workbook = new XSSFWorkbook(fis);
			}
			else if(filepath.toLowerCase().endsWith("xls"))
			{
				workbook = new HSSFWorkbook(fis);
			}
			 //get the number of spreadsheets
			int noOfSheets=workbook.getNumberOfSheets();
			for(int index=0;index < noOfSheets;index++)
			{
				Sheet sheet=workbook.getSheetAt(index);
				//iterate through all rows
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
				{
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell=row.getCell(1);
				}
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	      
	      if(file.isFile() && file.exists()) {
	         System.out.println("file open successfully.");
	      } else {
	         System.out.println("Error to open file.");
	      }
	}
}
