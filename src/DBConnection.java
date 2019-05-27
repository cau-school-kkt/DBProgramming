import java.sql.*;

/* initial setting for using DB API
 * connect a driver and a Database etc. */

public class DBConnection {
	/* google style. */
	public Connection conn = null;

	public void settingDB(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}

		catch(ClassNotFoundException e){
				System.out.println("Driver Connection Error.");
		}

		catch(Exception etc){
			System.out.println(etc.getMessage());
		}

		try{
			String url = "jdbc:mysql://gamehaeduo-db.c8xdbny5rkis.ap-northeast-2.rds.amazonaws.com:3306/db_programming";
			String userId = "gamehaeduo";
			String userPwd = "caugamehaeduo";

			conn = DriverManager.getConnection(url, userId, userPwd);
		}

		catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}
	}

	public void closeDB(){
		try{
			conn.close();
		}
		catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}
	}
}
