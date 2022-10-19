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
	public Member_DAO(){   // 생성자
		//예외.. 프로그램 실행 중에 발생하는 것.. 에러: 실행조차 안됨..
		try {
			// 1. 드라이버 로딩(필요한 클래스를 자바로 로드)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	//필요할때마다 커넥션 얻어는 메서드 정의
	public boolean connect() { // 
		try {
			// 커넥션을 시도하고 그 결과를 얻어 오는 코드... 
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
					return 1 ;//1이면 중복 아이디 있음
					
							
				}
				conn.close();
				return 0; //0이면 중복아이디 없음
				
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
		                
		                // Object[]을 만들어 저장하여 model에 추가하면 JTable에서 결과를 확인 가능
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
					addrSh.append("이름 : "+rs.getString("name")+"\n");
					addrSh.append("주소 : "+rs.getString("addr")+"\n");
					addrSh.append("---------------------------");
					
					while(rs.next()) {
						addrSh.append("\n");
						addrSh.append("ID : "+rs.getString("id")+"\n");
						addrSh.append("이름 : "+rs.getString("name")+"\n");
						addrSh.append("주소 : "+rs.getString("addr")+"\n");
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
