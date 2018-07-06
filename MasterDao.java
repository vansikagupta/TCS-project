package tcs.groupB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import oracle.jdbc.driver.OraclePreparedStatement;


public class MasterDao 
{
	Connection getCon() throws ClassNotFoundException, SQLException
	{
		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/orcl";
	    String username = "hr";
	    String password = "hr";
	    
	 
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
		Connection con;
		
        
		try 
		{
			PreparedStatement sql_statement = null;
			con =getCon();
			String jdbc_insert_sql = "INSERT INTO MASTER"
                    + "(TransactionID,TransactionDate,CustomerID,CustomerName,CustomerContact,CustomerEmail,CustomerAge,PlanID,PlanName,PlanTarrif,PlanValidity,Status,Remarks) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,null,null)";
			sql_statement = con.prepareStatement(jdbc_insert_sql);
			// we set batch size as 50. You should increase this 
            // depending on the number of rows in your Excel document
			((OraclePreparedStatement)sql_statement).setExecuteBatch(50);
            /* We should now load excel objects and loop through the worksheet data */
            //FileInputStream input_document = new FileInputStream(new File("C:\\Users\\tctch\\OneDrive\\Documents\\UploadedFiles\\transactionID.xlsx"));
            /* Load workbook */
			
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
			//int noOfSheets=workbook.getNumberOfSheets();
			//for(int index=0;index < noOfSheets;index++)
			//{
				Sheet sheet=workbook.getSheetAt(0);
				//iterate through all rows
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
				{
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch(cell.getCellTypeEnum()) { 
                        case STRING: //handle string columns
                                sql_statement.setString(1, cell.getStringCellValue());                                                                                     
                                break;
                        case NUMERIC: //handle double data
                                sql_statement.setDouble(2,cell.getNumericCellValue() );
                                break;
						case BLANK:
							break;
						case BOOLEAN:
							break;
						case ERROR:
							break;
						case FORMULA:
							break;
						case _NONE:
							break;
						default:
							break;
                        }
                       
                }
					 // though we call execute here, it is done only 
                    //when the batch size is reached.
                   try {
                           sql_statement.execute();
                    } catch(BatchUpdateException e) {
                    //you should handle exception here if required
                    }
				
			}
				// unprocessed batch would get processed anyway.
		        con.commit();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//fis.close();
        /* Close prepared statement */
        //sql_statement.close();
        /* COMMIT transaction */
        

	      if(file.isFile() && file.exists()) {
	         System.out.println("file open successfully.");
	      } else {
	         System.out.println("Error to open file.");
	      }
	}

}