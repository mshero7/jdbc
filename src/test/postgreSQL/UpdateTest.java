package test.postgreSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {
	
	public static void main(String[] args) {
		boolean result = update( 1, "문상수" );
		if( result ) {
			System.out.println( "업데이트 성공!" );
		}
	}
	
	public static boolean update(Integer no, String name) {
		boolean result = false;
		
		Connection conn = null;
		Statement stmt = null;
		try {
			//1. JDBC Driver(MariaDB) 로딩
			Class.forName("org.postgresql.Driver");
			
			//2. 연결하기
			String url = "jdbc:postgresql://192.168.1.199:5432/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. statement 객체 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = 
				" update author" +
				"    set name='" + name + "'" +
				"  where no=" + no;
			int count = stmt.executeUpdate(sql);
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
 			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
