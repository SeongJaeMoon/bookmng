package sist.group1;

import java.util.Scanner;

public class LibraryService {
	
	private LibraryDAO dao = new LibraryDAO();
	private Utils utils = Utils.getInstance();
	

	
	public void signIn(Scanner sc) {
		
	}
	
	public void signUp(Scanner sc) {
		System.out.println("ȸ�������� �����մϴ�.");
		
		System.out.print("���̵�>");
		String uid = sc.next();
		System.out.print("��й�ȣ()>");
		String password = sc.next();
		System.out.print("�̸��� �Է��ϼ���.");
		String name = sc.next();
		System.out.print("��ȭ��ȣ�� �Է��ϼ���.");
		String phoneNumber = sc.next();
		
		System.out.println("ȸ�� ����� �Ϸ� �Ǿ����ϴ�.");
	}
	
	//���α׷� ����� users, books, checkOuts ������ ����
	public void fileSave() {
		
	}
	
	public void isPasswordOK() throws PasswordException {
		
	}
	
	public void isPatternOK() throws PatternException{
		
	}
}
