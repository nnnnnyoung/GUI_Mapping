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
	public Del_DAO(){   // 생성자
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
	
//	로그인 한 아이디의 주소 받아오기
	
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
	
	public ArrayList<Del_DTO> selAddr(String addr, String kind){ //로그인 한 아이디와 같은 지역에 있는 가게들만 출력하기

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
