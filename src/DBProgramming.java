import java.sql.*;

public class DBProgramming{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}

		catch(ClassNotFoundException e){
			System.out.println("드라이버 연결 에러.");
		}

		catch(Exception etc){
			System.out.println(etc.getMessage());
		}

		try{
			String url = "jdbc:mysql://gamehaeduo-db.c8xdbny5rkis.ap-northeast-2.rds.amazonaws.com";
			String userId = "gamehaeduo";
			String userPwd = "caugamehaeduo";

			conn = DriverManager.getConnection(url, userId, userPwd);
			stmt = conn.createStatement();
			ResultSet resultSet = null;

			if(stmt.execute("SHOW DATABASES")){
					resultSet = stmt.getResultSet();
			}

			while(resultSet.next()){
				String str = resultSet.getNString(1);
				System.out.println(str);
			}

			System.out.println("연결됨");
			stmt.close();
			conn.close();
		}

		catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}
	}
}
