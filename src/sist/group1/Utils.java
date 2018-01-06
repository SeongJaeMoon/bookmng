package sist.group1;

public class Utils {
	
	/*
	 @Param
	 ������ ID,PW -> admin123
	Singleton -> �ν��Ͻ��� Utils utils = Utils.getInstance(); �������� �������� �˴ϴ�.
	 Ŭ���� ���� �ƹ� �������� utils.�޼ҵ��(); ���� �� �ڵ鸵 ����
	 */
	//������ ���̵�, ��й�ȣ
	private final String ADMIN ="admin123";
	//Utils static ��ü ����
	private static Utils utils = null;
	//���� ����� ���� ����� ���� ����
	private User user;
	
	private Utils() {
		
	}
	
	public static Utils getInstance() {
		if(utils == null) {
			utils = new Utils();
		}
		return Utils.utils;
	}
	
	public String getAdmin() {
		return ADMIN;
	}
	
	public void setCurrentUser(User user) {
		this.user = user;
	}
	
	public User getCurrentUser() {
		return user;
	}

}
