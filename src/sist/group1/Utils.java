package sist.group1;

public class Utils {
	
	/*
	 @Param
	 관리자 ID,PW -> admin123
	 Singleton -> 인스턴스로 Utils utils = Utils.getInstance(); 형식으로 가져오면 됩니다.
	 클래스 내의 아무 곳에서나 utils.메소드명(); 으로 값 핸들링 가능
	 */
	private static final String ADMIN ="admin123";
	private static Utils utils = null;
	private String uid;
	
	private Utils() {
		
	}
	
	public static Utils getInstance() {
		
		if(utils == null) {
			utils = new Utils();
		}
		return Utils.utils;
	}
	
	public static String getAdmin() {
		return Utils.ADMIN;
	}
	
	public void setCurrentUser(String uid) {
		this.uid = uid;
	}
	
	public String getCurrentUser() {
		return uid;
	}

}
