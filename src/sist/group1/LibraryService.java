package sist.group1;

import java.util.Scanner;
import java.util.regex.Pattern;

public class LibraryService {
	
	private LibraryDAO dao = new LibraryDAO();
	private Utils utils = Utils.getInstance();
	
	public void login(Scanner sc) {
		System.out.println("�α����� �����մϴ�.");
		System.out.print("���̵�>");
		String userId = sc.next();
		System.out.print("��й�ȣ>");
		String password = sc.next();
		if(userId.equals(utils.getAdmin())&& password.equals(utils.getAdmin())) {
			this.adminMenu(sc);
		}else {
			try {
				this.isWrongUser(userId, password);
				if(this.dao.getCurrentUser(userId, password)!=null) {
					//���� ����� ����
					utils.setCurrentUser(this.dao.getCurrentUser(userId, password));
					this.userMenu(sc);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void adminMenu(Scanner sc) {
		while(true){
			System.out.println("�����ڷ� �α��� �߽��ϴ�.");
			System.out.println("1.���� ���� 2.ȸ�� ���� 0.�α� �ƿ�");
			int input = sc.nextInt();
			if(input == 0) break;
			switch(input) {
			case 1:/*���� ���� �޴� �޼ҵ� ȣ��*/break;
			case 2:/*ȸ�� ���� �޴� �޼ҵ� ȣ��*/break;
			default : System.out.println("�� �� ���� �Է��Դϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
	public void userMenu(Scanner sc) {
		System.out.println("["+utils.getCurrentUser().getName()+"]"+"������ �α��� �߽��ϴ�.");
		/*�����ڿ��� �� �޼����� [1]�� �ֽ��ϴ�. (�޼��� �޼ҵ� ȣ��)*/
		/*[1] ��ü���� ������ �ֽ��ϴ�. �ݳ� ���ּ���. (��ü �޼ҵ� ȣ��)*/
		while(true) {
			System.out.println("1.���� �˻�   2.���� ���   3.�ݳ� ���   4.�޽��� Ȯ��   0.�α׾ƿ�");
			System.out.print("����>");
			int input = sc.nextInt();
			if(input == 0)break;
			switch(input) {
			case 1: /*���� �˻� sub�޴� */break;
			case 2: /*���� ��� sub�޴�*/break;
			case 3: /*�ݳ� ��� sub�޴�*/break;
			case 4: /*�޼��� Ȯ�� sub�޴�*/break;
			default : System.out.println("�� �� ���� �Է��Դϴ�. �ٽ� �Է����ּ���.");
			}
		}
	}
	public void register(Scanner sc) {
		System.out.println("ȸ�������� �����մϴ�.");

		System.out.print("���̵�>");
		String userId = sc.next();
		System.out.print("��й�ȣ(����+���� ���� 8�� �̻�)>");
		String password = sc.next();
		while (true) {
			try {
				this.isPasswordPattern(password);
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.print("��й�ȣ(����+���� ���� 8�� �̻�)>");
				password = sc.next();
			}
		}
		System.out.print("�̸�>");
		String name = sc.next();
		System.out.print("��ȭ��ȣ(010-1234-5678)>");
		String phone = sc.next();
		while(true) {
			try {
				this.isPhonePattern(phone);
				break;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.print("��ȭ��ȣ(010-1234-5678)>");
				phone = sc.next();
			}
		}
		if (this.dao.getCurrentUser(userId, password)!=null) {
			System.out.println("�̹� �����ϴ� id �Դϴ�. �ٽ� �Է����ּ���.");
		} else {
			this.dao.register(userId, password, name, phone);
			System.out.println("ȸ�� ������ �Ϸ� �Ǿ����ϴ�.");
		}
	}
	
	//���α׷� ����� users, books, checkOuts ������ ����
	public void fileSave() {
		System.out.println("���α׷��� �����մϴ�.");
		this.dao.logout();
	}
	
	public void isPhonePattern(String password) throws PatternException{
		String temp = "(\\d{3}).*(\\d{3}).*(\\d{4})";
		Boolean bool = Pattern.matches(temp, password);
		if(!bool) {
			throw new PatternException("�߸��� ��ȭ��ȣ ���� �Դϴ�. �ٽ� �Է����ּ���.");
		}
	}
	public void isPasswordPattern(String phone) throws PatternException{
		String temp = "(?=.*\\d)(?=.*[a-z]).{8,15}";
		Boolean bool = Pattern.matches(temp, phone);
		if(!bool) {
			throw new PatternException("�߸��� ��й�ȣ ���� �Դϴ�. �ٽ� �Է����ּ���.");
		}
	}
	public void isWrongUser(String userId, String password) throws ExistUserException{
		if(this.dao.getCurrentUser(userId, password)==null && !userId.equals(utils.getAdmin())) {
			throw new ExistUserException("��ϵ��� ���� ����� �Դϴ�. �ٽ� �Է����ּ���.");
		}
		if(this.dao.isWrongPassword(userId, password)) {
			throw new ExistUserException("�߸��� ��й�ȣ �Դϴ�. �ٽ� �Է����ּ���.");
		}
	}
}
