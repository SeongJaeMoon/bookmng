package sist.group1;

import java.util.Scanner;

public class Library {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LibraryService service = new LibraryService();
		try {
			while (true) {
				System.out.println();
				System.out.println("<<<�ֿ� ������>>>>");
				System.out.println("�α����� �ʿ��մϴ�. ȸ������ �Ǵ� �α����� ���� ���ּ���.\n");
				System.out.println("1.ȸ������   2.�α���   0.����");
				System.out.print("����> ");
				int input = sc.nextInt();
				sc.nextLine();
				if (input == 0)
					break;
				switch (input) {
				case 1:
					service.register(sc);
					break;
				case 2:
					service.login(sc);
					break;
				default:
					System.out.println("�� �� ���� �Է��Դϴ�. �ٽ� �õ��ϼ���.");
					break;
				}
			}
			sc.close();
			service.fileSave();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
