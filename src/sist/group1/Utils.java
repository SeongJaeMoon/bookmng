package sist.group1;

public class Utils {
	
	/*
	 @Param
	 ������ ID,PW -> admin123
	 Singleton -> �ν��Ͻ��� Utils utils = Utils.getInstance(); �������� �������� �˴ϴ�.
	 Ŭ���� ���� �ƹ� �������� utils.�޼ҵ��(); ���� �� �ڵ鸵 ����
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
