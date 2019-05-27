import java.sql.*;
import java.util.Scanner;

public class DBProgramming{
	public static void main(String[] args){
		String user_id = "bluesalt752";
		String regist_number = "b34c2ed3-7f7c-11e9-8136-0a9562b69530";
			
		System.out.println("fs");
		displayRoomDetail(regist_number, user_id);
	}


	public static void displayRoomDetail(String regist_number, String user_id){
		try{
		/* DB connect */
		DBConnection dbConn = new DBConnection();
		dbConn.settingDB();


		System.out.println("fs");

		ResultSet resultSet = null;
		
		/*
		Statement stmt = dbConn.conn.createStatement();
		if(stmt.execute("SHOW DATABASES")){
			resultSet = stmt.getResultSet();
		}

		while(resultSet.next()){
			String str = resultSet.getNString(1);
			System.out.println(str);
		}
		*/

		/* Room 테이블에 있는 정보 가져옴. */
		RoomDetail roomDetailInfo = new RoomDetail();

		String sql_sel_all_from_Room = "select * from Room where regist_number = ?";
			
		PreparedStatement pstmt = dbConn.conn.prepareStatement(sql_sel_all_from_Room);

		System.out.println("ks");
		
		pstmt.setString(1, regist_number);
		resultSet = pstmt.executeQuery();

//		resultSet.next();
//		System.out.println(resultSet.getString(1));
		
		while(resultSet.next()){
			System.out.println("kss");
			
			roomDetailInfo.regist_number = resultSet.getString(1);
			roomDetailInfo.city_addr = resultSet.getString(2);
			roomDetailInfo.district_addr = resultSet.getString(3);
			roomDetailInfo.dong_addr = resultSet.getString(4);
			roomDetailInfo.sub_etc_addr = resultSet.getString(5);
			roomDetailInfo.broker_id = resultSet.getString(6);
			roomDetailInfo.room_structure = resultSet.getString(8);
			roomDetailInfo.main_picture_url = resultSet.getString(9);
			roomDetailInfo.regist_date = resultSet.getString(10);

			
		}

		resultSet = null;



		/* MonthlyRentBuilding 테이블에 있는 정보를 가져옴. */
		String sql_sel_all_from_monthlyRent =
			"select * from MonthlyRentBuilding where regist_number = ?";
		
		pstmt = 
			dbConn.conn.prepareStatement(sql_sel_all_from_monthlyRent);
			
		pstmt.setString(1, regist_number);
		resultSet = pstmt.executeQuery();
			
		while(resultSet.next()){
				
			System.out.println("kss");

			roomDetailInfo.monthly_rent_deposit = resultSet.getInt(2);
			roomDetailInfo.monthly_rent = resultSet.getInt(3);
			roomDetailInfo.contract_term = resultSet.getFloat(4);
			
		}
			



		/* RoomDetail 테이블에 있는 정보를 가져옴. */
		String sql_sel_all_from_RoomDetail =
			"select * from RoomDetail where regist_number = ?";
			
		pstmt = 
			dbConn.conn.prepareStatement(sql_sel_all_from_RoomDetail);
			
		pstmt.setString(1, regist_number);
		resultSet = pstmt.executeQuery();
			
		while(resultSet.next()){
				
			System.out.println("kss");
			
			roomDetailInfo.total_number_of_stories = resultSet.getInt(2);
			roomDetailInfo.this_room_floor = resultSet.getInt(3);
			roomDetailInfo.square_measure = resultSet.getFloat(4);
			roomDetailInfo.admission_available_date = resultSet.getString(5);
			roomDetailInfo.option_description = resultSet.getString(6);
			roomDetailInfo.utility_bill = resultSet.getFloat(7);				
		}
		
		/* DB disconnect */
		dbConn.closeDB();
		dbConn = null;
		
		/* display all in a CMD window */
		roomDetailInfo.displayAll();

		/* insertest-room insert. */
		addInterestList(roomDetailInfo.regist_number, user_id);

		}
		catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}
	}
	
	/* 관심매물에 등록할 것인지 물어보고,
	 * 등록한다고 답하면 DB 관심매물 테이블에 해당 매물의 정보를 insert. */
	public static void addInterestList(String regist_number, String user_id){
		
		System.out.println("관심매물에 등록하시겠습니까? (번호 선택)");
		System.out.println("1. yes        2. no");
		
		Scanner scan = new Scanner(System.in);

		/* if yes_or_no is 1, insert this room into Interest Table.
		 * if yes_or_no is 2, don't anything.*/
		int yes_or_no_regist= scan.nextInt();

		if(yes_or_no_regist ==2)
			return;

		/* insert this room into Interest Table. */

		try{
			/* DB connect */
			DBConnection dbConn = new DBConnection();
			dbConn.settingDB();

			ResultSet resultSet = null;
			
			String sql_insert_into_Interest = "insert into Interest values(?, ?, ?, ?)";
			PreparedStatement pstmt = null;

			pstmt = dbConn.conn.prepareStatement(sql_insert_into_Interest);

			pstmt.setString(1, user_id);
			pstmt.setString(2, regist_number);
			pstmt.setString(3, "월세");
			pstmt.setString(4, "방");
			
			/* number of rows updated. */
			int r_num = pstmt.executeUpdate();

			System.out.println("변경된 row 수 : " + Integer.toString(r_num));

			dbConn.closeDB();
			dbConn = null;
		}
		catch(SQLException e){
			System.out.println("SQLException : " + e.getMessage());
		}

	}
}
