package comq.common;

import java.io.File;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet
public class FileUpload extends HttpServlet {
	
	public List<FileItem> userProfilePicture(HttpServletRequest request) throws Exception{
					System.out.println("file uploadfile checking...");
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	List<FileItem> items = upload.parseRequest(request);

	for (FileItem i : items) {
		System.out.println(i);
	}
	
	System.out.println(items);
	System.out.println("================");
	String name = null;
	String photo = null;
	
  	for (FileItem item : items) {
  		if(item.isFormField()) {
  			name = item.getName();
  		} else {
  			photo = item.getName();
  			
  			String realUploadPath = this.getServletContext().getRealPath("/upload");
  			
  			File newPath = new File(realUploadPath +"/"+photo);
  			item.write(newPath);
  		}
  	}
  	
  	return items;
	}
	
}
