package tcs.groupB;

import java.io.IOException;
import java.util.List;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/uploadFile")
public class UploadFileController extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try
		{
			ServletFileUpload servletfile=new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> files = servletfile.parseRequest(request);
			String filepath=null;
			for(FileItem item: files)
			{
				filepath="F:\\UploadedFiles\\" + item.getName();
				File uploadedfile=new File(filepath);
				item.write(uploadedfile);
				System.out.println(filepath+ " - file uploaded");
			}
			MasterDao md = new MasterDao();
			md.readExcel(filepath);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
