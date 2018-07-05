package tcs.groupB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@WebServlet("/uploadFile")
public class UploadFileController extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try
		{
			ServletFileUpload servletfile=new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> files = servletfile.parseRequest(request);
			String filepath;
			for(FileItem item: files)
			{
				filepath="F:\\UploadedFiles\\" + item.getName();
				File uploadedfile=new File(filepath);
				item.write(uploadedfile);
				System.out.println(filepath+ " - file uploaded");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}


class ReadExcelFile
{
	protected void readExcel(String filepath)
	{
		ArrayList<Master> masterList=new ArrayList();
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
