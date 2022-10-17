package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.BlistDTO;



public class Blist_DAO {
	private Connection conn = null;
	public Blist_DAO(){   // 생성자
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
	
	
	public void buy(String shop, String id) {
		ResultSet rs=null;
		if(connect()) {
			try {
				String sql="select * from food where shop=?";
				
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1, shop);
				rs=p.executeQuery();
				

								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql2="insert into blist values(BN_SEQ.NEXTVAL,?,?,?,?,?, default)";
			try {
				PreparedStatement p1=conn.prepareStatement(sql2);
				while(rs.next()) {
					p1.setString(1, id);
					p1.setString(2, shop);
					p1.setString(3, rs.getString("fname"));
					p1.setInt(4, rs.getInt("price"));
					p1.setString(5, rs.getString("addr"));
					int  r=p1.executeUpdate();											
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<BlistDTO> prt(String id) {
		// TODO Auto-generated method stub
		if(connect()) {
			ArrayList<BlistDTO> blist =new ArrayList<>();
			
			ResultSet rs=null;
			String sql="select * from blist where id=?";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1, id);
				rs=p.executeQuery();
				while(rs.next()) {
					BlistDTO bdto=new BlistDTO();
					bdto.setBnum(rs.getInt("bnum"));

					blist.add(bdto);
				}
				conn.close();
				return blist;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
		
		
	}
	
	
	public BlistDTO selectOne(String bnum) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		BlistDTO w = null;

		if(connect()) {
			System.out.println("ccc");
			try {
				String sql="select * from blist where bnum=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, Integer.decode(bnum));
				rs = psmt.executeQuery();
				if(rs.next()) {
					System.out.println("aa");
					w = new BlistDTO();

					w.setShop(rs.getString("shop"));
					w.setFname(rs.getString("fname"));
					w.setPrice(rs.getInt("price"));
					w.setAddr(rs.getString("addr"));
					w.setIndate(rs.getInt("indate"));
				}else {
					System.out.println("bbb");
				}
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("ddd");
		return w;
	}
	
	
}
