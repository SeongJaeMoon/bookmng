package sist.group1;

import java.util.Scanner;

public class LibraryService {
	
	private LibraryDAO dao = new LibraryDAO();
	private Utils utils = Utils.getInstance();
	

	
	public void signIn(Scanner sc) {
		
	}
	
	public void signUp(Scanner sc) {
		System.out.println("회원가입을 진행합니다.");
		
		System.out.print("아이디>");
		String uid = sc.next();
		System.out.print("비밀번호()>");
		String password = sc.next();
		System.out.print("이름을 입력하세요.");
		String name = sc.next();
		System.out.print("전화번호를 입력하세요.");
		String phoneNumber = sc.next();
		
		System.out.println("회원 등록이 완료 되었습니다.");
	}
	
	//프로그램 종료시 users, books, checkOuts 데이터 저장
	public void fileSave() {
		
	}
	
	public void isPasswordOK() throws PasswordException {
		
	}
	
	public void isPatternOK() throws PatternException{
		
	}
}
