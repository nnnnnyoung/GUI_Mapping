package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import DTO.Member_DTO;

public class Member_DAO extends JFrame{
	
	private Connection conn = null;
	public Member_DAO(){   // ������
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
	
	public void newmember(Member_DTO j) {
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
	
	public void prtM(DefaultTableModel model) {
		if(connect()) {
			String sql ="select * from delmember";
			ResultSet rs=null;
			try {
				Statement s=conn.createStatement();
				rs=s.executeQuery(sql);
				while(rs.next()) {
					String id=rs.getString("id");
					String name=rs.getString("name");
					String pw=rs.getString("pw");
		        	String addr=rs.getString("addr");
		                
		                // Object[]�� ����� �����Ͽ� model�� �߰��ϸ� JTable���� ����� Ȯ�� ����
		        	Object data[]= {id,pw,name,addr};
		            model.addRow(data); 
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean searsh(String sh, JTextArea addrSh) {
		if(connect()) {
			
			ResultSet rs=null;
			String sql="select id,name,addr from delmember where addr=?";
			try {
				PreparedStatement s=conn.prepareStatement(sql);
				s.setString(1, sh);
				rs=s.executeQuery();
				
				if (rs.next()){
					addrSh.append("\n");
					addrSh.append("ID : "+rs.getString("id")+"\n");
					addrSh.append("�̸� : "+rs.getString("name")+"\n");
					addrSh.append("�ּ� : "+rs.getString("addr")+"\n");
					addrSh.append("---------------------------");
					
					while(rs.next()) {
						addrSh.append("\n");
						addrSh.append("ID : "+rs.getString("id")+"\n");
						addrSh.append("�̸� : "+rs.getString("name")+"\n");
						addrSh.append("�ּ� : "+rs.getString("addr")+"\n");
						addrSh.append("---------------------------");
						
					}
					return true;
				}else {
					return false;
				}

			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	
}
