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

	public ArrayList<BlistDTO> prtAll(String id) {
		// TODO Auto-generated method stub
		if(connect()) {
			ArrayList<BlistDTO> blist =new ArrayList<>();
			
			ResultSet rs=null;
			String sql="select bnum from blist where id=? order by bnum desc";
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
	
	public ArrayList<BlistDTO> prtWeek(String id) {
		// TODO Auto-generated method stub
		if(connect()) {
			ArrayList<BlistDTO> blist =new ArrayList<>();
			
			ResultSet rs=null;
			String sql="select bnum from blist where indate >= to_char(sysdate-7,'yyyymmdd') "
					+ "and id=? order by bnum desc";
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
	
	public ArrayList<BlistDTO> prtMonth(String id) {
		// TODO Auto-generated method stub
		if(connect()) {
			ArrayList<BlistDTO> blist =new ArrayList<>();
			
			ResultSet rs=null;
			String sql="select bnum from blist where indate >= to_char(sysdate-30,'yyyymmdd') "
					+ "and id=? order by bnum desc";
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

			try {
				String sql="select shop,fname,price,addr,to_char(indate,'mm-dd-yyyy hh24:mi') "
						+ "from blist where bnum=?";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, Integer.decode(bnum));
				rs = psmt.executeQuery();
				if(rs.next()) {

					w = new BlistDTO();

					w.setShop(rs.getString("shop"));
					w.setFname(rs.getString("fname"));
					w.setPrice(rs.getInt("price"));
					w.setAddr(rs.getString("addr"));
					w.setIndate(rs.getString("TO_CHAR(INDATE,'MM-DD-YYYYHH24:MI')"));
				}
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return w;
	}
	public boolean fiveMi(String id, int bno ) {
		if(connect()) {
			ResultSet rs =null;
			String sql="SELECT bnum FROM blist WHERE indate >= TO_CHAR(SYSDATE-5/(24*60),"
					+ "'YYYYMMDDHH24:MI:SS') and id=? and bnum=?";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1, id);
				p.setInt(2, bno);
				rs=p.executeQuery();
				if(rs.next()) {
					conn.close();
					return true;
				}

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public void del(int bno) {
		if(connect()) {
			String sql="delete from blist where bnum=?";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setInt(1, bno);
				int r=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public int time(String id, String bnum, int time) {
		if(connect()) {
			int bno=Integer.parseInt(bnum);
			ResultSet rs=null;
			String sql="SELECT to_char(SYSDATE,'MI')- to_char(indate,'MI') "
					+ "FROM blist WHERE indate >= TO_CHAR(SYSDATE-40/(24*60),'YYYYMMDDHH24:MI:SS') "
					+ "and id=? and bnum=?";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1, id);
				p.setInt(2, bno);
				rs=p.executeQuery();
				if(rs.next()) {
					time=40-rs.getInt("TO_CHAR(SYSDATE,'MI')-TO_CHAR(INDATE,'MI')");
					if(time>=60) {
						time-=60;
					}
					return time;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return time;
	}
	
	
}
