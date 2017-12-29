package sist.group1;

import java.util.Scanner;

public class Library {	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		
		try {
			while(true) {
			System.out.println("<쌍용 도서관>");
			System.out.println("로그인이 필요합니다. 회원가입 또는 로그인을 진행 해주세요.");
			System.out.println("1.회원가입 2.로그인 0.종료");
			System.out.print("선택>");
			
			int input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0) break;
				switch(input) {
				case 0: break;
				case 1: break;
				case 2: break;
				default : System.out.println("알 수 업는 입력입니다. 다시 시도하세요.");break;
				}
			}
			sc.close();
			System.out.println("프로그램을 종료합니다.");
		}catch(Exception e) {
			
		}
	}
	
}
