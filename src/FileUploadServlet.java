

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.cs.test.dao.UploadDAO;
import com.cs.test.dao.UploadDAOImpl;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int ramSize = 100*1024;
    private static int maxTotalSize = 300 * 1024 * 1024;//업로드 최대용량
    private static int maxFileSize = 300 * 1024 * 1024;//업로드시 파일 하나당 최대 용량
    private UploadDAO upDAO = new UploadDAOImpl();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contentType = request.getContentType();
		DiskFileItemFactory dfiFactory = new DiskFileItemFactory();//jsp에서 파일을 받아오기 위한 것.
		dfiFactory.setSizeThreshold(ramSize);
		
		ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
		sfu.setFileSizeMax(maxFileSize);
		sfu.setSizeMax(maxTotalSize);//당연히 기본적으로 해줘야 하는 것이다.
		try {
			Map<String, String> upload = new HashMap<>();
			List<FileItem> fiList = sfu.parseRequest(new ServletRequestContext(request));
			for(FileItem fi:fiList) {
				if(fi.isFormField()) {
					upload.put(fi.getFieldName(), fi.getString("utf-8"));
					System.out.println("fi : "+fi);
					/*
					 * System.out.println(fi.getFieldName());
					 * System.out.println(fi.getString("utf-8"));
					 */
				}else {
					System.out.println(fi.getName());
					String fileName = fi.getName();
					String extName = fileName.substring(fileName.lastIndexOf("."));
					String path = "C:\\uploads\\"+ System.nanoTime() + extName;
					File f = new File(path);
					fi.write(f);
					upload.put(fi.getFieldName(), fileName);
					upload.put(fi.getFieldName().substring(4), path);
					}
			}
			upDAO.insertUpload(upload);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(contentType);
		//doGet(request, response);
	}

}
