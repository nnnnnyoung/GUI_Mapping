package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_DAO {
	
	private Connection conn = null;
	public Login_DAO(){   // ������
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
	public int Login(String id, String pw) {
		if(connect()) {
			ResultSet rs=null;
			String sql="select id from delmember where id=? and pw=?";
			try {
				PreparedStatement ptmt=conn.prepareStatement(sql);
				ptmt.setString(1, id);
				ptmt.setString(2, pw);
				rs=ptmt.executeQuery();
				//select���� �������� ������� ���� ResultSet ��ü�� �ʿ��ϴ�.
				while(rs.next()) { //.next() �޼ҵ�� while���� �� ���� ���� ���̻� ������ ����. �ٽ� �� �� ����.
					conn.close();
					return 1 ;//�α��� ������ 1�� ����
				}
				conn.close();
				return 0; //�α��� ���н� 0�� ����
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 2;
		
	}
	
	
	
	
}
