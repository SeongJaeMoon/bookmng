package sist.group1;

import java.util.Scanner;

public class Library {	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		
		try {
			while(true) {
			System.out.println("<�ֿ� ������>");
			System.out.println("�α����� �ʿ��մϴ�. ȸ������ �Ǵ� �α����� ���� ���ּ���.");
			System.out.println("1.ȸ������ 2.�α��� 0.����");
			System.out.print("����>");
			
			int input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0) break;
				switch(input) {
				case 0: break;
				case 1: break;
				case 2: break;
				default : System.out.println("�� �� ���� �Է��Դϴ�. �ٽ� �õ��ϼ���.");break;
				}
			}
			sc.close();
			System.out.println("���α׷��� �����մϴ�.");
		}catch(Exception e) {
			
		}
	}
	
}
