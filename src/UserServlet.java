

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;


@WebServlet("/ajax/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String str;
		StringBuffer sb = new StringBuffer();
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		Gson g = new Gson();
		UserInfoVO user = g.fromJson(sb.toString(), UserInfoVO.class);
		Map<String,Object> result = new HashMap<>();
		if("login".equals(user.getCmd()) && "test".equals(user.getUiId()) && "test".equals(user.getUiPwd())) {
			result.put("res", true);
		}else {
			result.put("res", false);
		}
		String json = g.toJson(result);
		PrintWriter pw = response.getWriter();
		pw.println(json);
	}

}
