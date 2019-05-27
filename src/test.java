import java.sql.*;

class test{
	public static void main(String[] args) throws SQLException{

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}

		catch(ClassNotFoundException e){
				System.out.println("Driver Connection Error.");
		}

		catch(Exception etc){
			System.out.println(etc.getMessage());
		}
		
		String url = "jdbc:mysql://gamehaeduo-db.c8xdbny5rkis.ap-northeast-2.rds.amazonaws.com:3306/db_programming";
		String userId = "gamehaeduo";
		String userPwd = "caugamehaeduo";

		Connection conn = DriverManager.getConnection(url, userId, userPwd);

		Statement stmt = conn.createStatement();
		
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
}
