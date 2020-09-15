

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int ramSize = 100*1024;
    private static int maxtotal = 10*1024*1024;
    private static int maxfile = 2*1024*1024;
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
		dfiFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
		sfu.setFileSizeMax(maxtotal);
		sfu.setSizeMax(maxfile);
		try {
			List<FileItem> fileList = sfu.parseRequest(new ServletRequestContext(request));
			for(FileItem file : fileList) {
				if(file.isFormField()) {
					System.out.println(file.getFieldName());
					System.out.println(file.getString("utf-8"));
				}else {
					String fileName = file.getName();
					File img = new File("C:\\Users\\Administrator\\Desktop\\uploads\\"+fileName);
					file.write(img);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doGet(request, response);
	}

}
