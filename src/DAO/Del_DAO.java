package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Del_DTO;


public class Del_DAO  {
	private Connection conn = null;
	public Del_DAO(){   // ������
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
	
//	�α��� �� ���̵��� �ּ� �޾ƿ���
	
	public String getAddr(String id) {
		if(connect()) {
			ResultSet rs=null;
			
			String sql="select addr from delmember where id=?";
			try {
				PreparedStatement s=conn.prepareStatement(sql);
				s.setString(1, id);				
				rs=s.executeQuery();
				
				if(rs.next()) {
					String addr=rs.getString("addr");
					conn.close();
					return addr;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<Del_DTO> selAddr(String addr, String kind){ //�α��� �� ���̵�� ���� ������ �ִ� ���Ե鸸 ����ϱ�

			ResultSet rs=null;
			ArrayList<Del_DTO> wlist = new ArrayList<>();
			if(connect()) {
				try {
					String sql="select shop from food where addr=? and kind=?";
					PreparedStatement s=conn.prepareStatement(sql);
					s.setString(1, addr);	
					s.setString(2, kind);		
					

					rs=s.executeQuery();
					
					while(rs.next()) {
						Del_DTO w = new Del_DTO();
						w.setShop(rs.getString("shop"));
						wlist.add(w);
					}
					conn.close();
					

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return wlist;

	}
	

	public Del_DTO selectOne(String shop) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		Del_DTO w = null;
		if(connect()) {
			try {
				String sql="select * from food where shop=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, shop);
				rs = psmt.executeQuery();
				if(rs.next()) {
					w = new Del_DTO();
					w.setKind(rs.getString("kind"));
					w.setShop(rs.getString("shop"));
					w.setFname(rs.getString("fname"));
					w.setPrice(rs.getInt("price"));
					w.setAddr(rs.getString("addr"));
				}
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return w;
	}
	
	
	
	
	
	
}
