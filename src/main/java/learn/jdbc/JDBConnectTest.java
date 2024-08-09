package learn.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Spring on 2016/8/22.
 */
public class JDBConnectTest {
	private String url="jdbc:mysql://localhost:3306/newtestdb";
	private String userName="root";
    private String password="storm";

	@Test
	public void test1() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, userName, password);
		System.out.println(conn);

	}
}
