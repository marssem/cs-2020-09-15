package tester;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.cs.test.dao.UploadDAO;
import com.cs.test.dao.UploadDAOImpl;


@WebServlet("/file2")
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int ramSize = 100 * 1024;
	private static int onLoadSize = 10 * 1024 * 1024;
	private static int fileSize = 2 * 1024 * 1024;
	private UploadDAO udao = new UploadDAOImpl();
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextType = request.getContentType();
		DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
		dfiFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
		sfu.setSizeMax(onLoadSize);
		sfu.setFileSizeMax(fileSize);
		try {
			Map<String, String> upload = new HashMap<>();
			List<FileItem> fiList = sfu.parseRequest(new ServletRequestContext(request));
			for(FileItem fi : fiList) {
				if(fi.isFormField()) {
					System.out.println(fi);
					upload.put(fi.getFieldName(), fi.getString("utf-8"));
				}else {
					String fileName = fi.getName();
					String extName = fileName.substring(fileName.lastIndexOf("."));
					String path = "c:\\uploads\\" + System.nanoTime() + extName;
					File f = new File(path);
					fi.write(f);
					upload.put(fi.getFieldName(), fileName);
					upload.put(fi.getFieldName().substring(4), path);
				}
			}
			udao.insertUpload(upload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		doGet(request, response);
	}

}
