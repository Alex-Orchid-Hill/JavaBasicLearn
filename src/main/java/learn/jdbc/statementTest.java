package learn.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * 使用statement对象执行静态sql语句
 * Created by Spring on 2016/8/23.
 */
public class statementTest {
	private String url="jdbc:mysql://localhost:3306/newtestdb";
	private String username="root";
	private String password="storm";

	@Test
	public static void test1(String password, String url, String username) {
		Statement stmt=null;
		Connection conn=null;
		try {
			//注册驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			//拿到jdbc连接对象
			conn=DriverManager.getConnection(url, username, password);

			//创建statement对象
			stmt=conn.createStatement();

			String sql="select * from student";

			ResultSet resultSet=stmt.executeQuery(sql);

			System.out.print(resultSet);

		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {

			if(stmt!=null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
}
