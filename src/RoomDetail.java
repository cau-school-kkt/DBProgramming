public class RoomDetail{
	/* google style public member. anyway there must be getter or setter.*/
	public String regist_number;

	public String city_addr;
	public String district_addr;
	public String dong_addr;
	public String sub_etc_addr;
	public String broker_id;
	public String room_structure;
	public String main_picture_url;
	public String regist_date;

	public int total_number_of_stories;
	public int this_room_floor;
	public float square_measure;
	public String admission_available_date;
	public String option_description;
	public float utility_bill;

	public int monthly_rent_deposit;
	public int monthly_rent;
	public float contract_term;
	
	/* display a detail information of this room in a cmd */
	public void displayAll(){
		String roomDetailInfo = " -----방 상세 정보----- \n\n"+"사진 : " + main_picture_url + "\n" + "위치 : " + city_addr + " " + district_addr + " " + dong_addr + " "
			+ sub_etc_addr + " \n보증금 : " + Integer.toString(monthly_rent_deposit) + "원" +"    월세 : " + Integer.toString(monthly_rent) + "원" 
			+ "    관리비 : " + Float.toString(utility_bill) + "만원" + "\n입주 가능일 : " + admission_available_date + "\n계약기간 : " + Float.toString(contract_term)
		       	+ "\n판매자 : " + broker_id + "\n방 등록일 : " + regist_date + "\n해당 매물이 속한 건물의 총 층수 : "+ Integer.toString(total_number_of_stories)
		       	+ "\n이 매물은 " + Integer.toString(this_room_floor) + "층 입니다." + "\n평수 : " + Float.toString(square_measure) + "평"
		       	+ "\n옵션 : " + option_description + "\n방 구조 : " + room_structure;
		System.out.println(roomDetailInfo);
	}
}
