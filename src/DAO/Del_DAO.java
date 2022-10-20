package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			//회원의 주소를 가져오기 위한 쿼리문
			try {
				PreparedStatement s=conn.prepareStatement(sql);
				s.setString(1, id);				
				rs=s.executeQuery();
				
				if(rs.next()) { //id는 pk이므로 결과값은 최대 1개이다. 따라서 while문이 아닌 if문을 사용
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
	
	public ArrayList<Del_DTO> selAddr(String addr, String kind){ 		
			ResultSet rs=null;
			ArrayList<Del_DTO> wlist = new ArrayList<>();
			if(connect()) {
				try {
					String sql="select shop from food where addr=? and kind=?";
					//로그인 한 아이디와 같은 지역에 있는 가게들만 출력하기
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
	public ArrayList<Del_DTO> getShop(){ //로그인 한 아이디와 같은 지역에 있는 가게들만 출력하기

		ResultSet rs=null;
		ArrayList<Del_DTO> wlist = new ArrayList<>();
		if(connect()) {
			try {
				String sql="select shop from food";
				PreparedStatement s=conn.prepareStatement(sql);
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
				//가게의 정보를 가져오기위한 메서드 
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, shop);
				rs = psmt.executeQuery();
				if(rs.next()) { //가게 이름은 pk이기때문에 while문이 아닌if문 사용
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
	public void modiShop(String sName, String fname, int price) {
		//가게 내용을 수정하기 위한 메서드, 주소와 가게이름은 수정하기 못하고 음식이름과 가격만 수정가능하게 설정함
		if(connect()) {
			String sql="update food set fname=? , price=? where shop=?";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1,fname);
				p.setInt(2,price);
				p.setString(3,sName);
				int r=p.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
	public void inputShop(Del_DTO newShop) {
		if(connect()) {
			//가게를 새로 등록하기 위한 메서드 입력한 정보가 food 테이블에 insert 된다.
			String sql="insert into food values(?,?,?,?,?)";
			try {
				PreparedStatement p=conn.prepareStatement(sql);
				p.setString(1, newShop.getKind());
				p.setString(2, newShop.getShop());
				p.setString(3, newShop.getFname());
				p.setInt(4, newShop.getPrice());
				p.setString(5,newShop.getAddr() );
				
				int r=p.executeUpdate();
				
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
