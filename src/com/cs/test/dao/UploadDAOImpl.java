package com.cs.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class UploadDAOImpl implements UploadDAO {

	@Override
	public int insertUpload(Map<String, String> upload) {
		try{
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","test","test");
			String sql = "insert into upload(up_num, org_file_name1, file_path1, org_file_name2, file_path2, up_name)";
			sql += "values(seq_up_num.nextval,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, upload.get("org_file_path1"));
			ps.setString(2, upload.get("file_path1"));
			ps.setString(3, upload.get("org_file_path2"));
			ps.setString(4, upload.get("file_path2"));
			ps.setString(5, upload.get("up_name"));
			System.out.println("up_name :"+upload.get("up_name"));
			return ps.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
