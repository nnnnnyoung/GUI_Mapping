package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DTO.Join_DTO;

public class Join_DAO {
	
	private Connection conn = null;
	public Join_DAO(){   // ������
		//����.. ���α׷� ���� �߿� �߻��ϴ� ��.. ����: �������� �ȵ�..
		try {
			// 1. ����̹� �ε�(�ʿ��� Ŭ������ �ڹٷ� �ε�)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//�ʿ��Ҷ����� Ŀ�ؼ� ���� �޼��� ����
	public boolean connect() { // 
		try {
			// Ŀ�ؼ��� �õ��ϰ� �� ����� ��� ���� �ڵ�... 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public void newmember(Join_DTO j) {
		if(connect()) {
			String sql="insert into delmember values(?,?,?,?)";
			try {
				PreparedStatement ptmt=conn.prepareStatement(sql);
				ptmt.setString(1, j.getId());
				ptmt.setString(2, j.getPw());
				ptmt.setString(3, j.getName());
				ptmt.setString(4, j.getAddr());
				int r=ptmt.executeUpdate();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public int checkId(String id) {
		if(connect()) {
			ResultSet rs=null;
			String sql="select id from delmember where id=?";
			try {
				PreparedStatement ptmt=conn.prepareStatement(sql);
				ptmt.setString(1, id);
				rs=ptmt.executeQuery();
				
				while(rs.next()) {
					conn.close();
					return 1 ;//1�̸� �ߺ� ���̵� ����
					
							
				}
				conn.close();
				return 0; //0�̸� �ߺ����̵� ����
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 2;
		
	}
	
	
}
