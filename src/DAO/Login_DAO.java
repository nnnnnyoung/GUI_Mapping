package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_DAO {
	
	private Connection conn = null;
	public Login_DAO(){   // 생성자
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
	public int Login(String id, String pw) {
		if(connect()) {
			ResultSet rs=null;
			String sql="select id from delmember where id=? and pw=?";
			try {
				PreparedStatement ptmt=conn.prepareStatement(sql);
				ptmt.setString(1, id);
				ptmt.setString(2, pw);
				rs=ptmt.executeQuery();
				//select문을 쓸때에는 결과값을 담을 ResultSet 객체가 필요하다.
				while(rs.next()) { //.next() 메소드는 while문이 다 돌고 나면 더이상 다음이 없다. 다시 쓸 수 없다.
					conn.close();
					return 1 ;//로그인 성공시 1을 리턴
				}
				conn.close();
				return 0; //로그인 실패시 0을 리턴
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 2;
		
	}
	
	
	
	
}
