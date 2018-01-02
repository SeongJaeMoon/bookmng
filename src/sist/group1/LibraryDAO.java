package sist.group1;

import java.util.*;
import java.io.*;

public class LibraryDAO {

	/*
	 * 
	 * this.users.add(new User("","","","","")); ��ü ����� ���� ����� ����Ʈ�� �ִ� ���
	 * this.books.add(new Book("","","","")); ��ü å ���� ����� ����Ʈ�� �ִ� ���
	 * this.checkOuts.add(new CheckOut("B001","U001","2017-12-27")); ���� ��ü ���� ����Ʈ��
	 * �ִ� ��� for(CheckOut c : checkOuts) { ����Ʈ���� Ư�� �Ű����� ���� �°� ��ü ���� �������� ���
	 * if(c.getcBookNo().equals("B001")) {
	 * 
	 * } Set<String>key = TestClass.testBook.keySet(); Iterator it = key.iterator();
	 * while(it.hasNext()){ �ʿ��� Ư�� �Ű����� ���� �°� ��ü ���� �������� ��� String key =
	 * (String)it.next(); Book b = TestClass.testBook.get(key); }
	 * 
	 * }
	 */

	/*
	 * @Param ����� ����(���� ������ ����� ���̵�, å ����), ����� ��ü ����, å ��ü ����, ���� ��ü ����
	 */

	private Utils utils = Utils.getInstance();
	private Map<String, User> users = new HashMap<String, User>();
	private Map<String, Book> books = new HashMap<String, Book>();
	private List<CheckOut> checkOuts = new ArrayList<CheckOut>();

	private static final String USER_FILE = "D:\\users.data";
	private static final String BOOK_FILE = "D:\\books.data";
	private static final String CHECKOUT_FILE = "D:\\checkOuts.data";

	@SuppressWarnings("unchecked")
	public LibraryDAO() {
		File file = new File(USER_FILE);
		File file1 = new File(BOOK_FILE);
		File file2 = new File(CHECKOUT_FILE);
		Object obj = null;

		if (file.exists()) {
			obj = deSerialization(USER_FILE);
			this.users = (Map<String, User>) obj;
		}
		if (file1.exists()) {
			obj = deSerialization(BOOK_FILE);
			this.books = (Map<String, Book>) obj;
		}
		if (file2.exists()) {
			obj = deSerialization(CHECKOUT_FILE);
			this.checkOuts = (List<CheckOut>) obj;
		}
		/*
		 * �� ��� �׽�Ʈ
		 */
		StringBuilder sb = new StringBuilder();
		Set<String>keySet = this.users.keySet();
		List<String>temp = new ArrayList<String>(keySet);
		Iterator<String>it = temp.iterator();
		while(it.hasNext()) {
			String key = it.next();
			sb.append(String.format("%s%n",this.users.get(key)));
		}
		System.out.println(sb.toString());
	}

	/*
	 * -ȸ�������� �Ϸ� �Ǿ����ϴ�. 
	 * -�̹� �����ϴ� id �Դϴ�. �ٽ� �Է����ּ���. 
	 * -�߸��� ��й�ȣ �����Դϴ�. �ٽ� �Է����ּ���. 
	 * -�߸��� ��ȭ��ȣ �����Դϴ�. �ٽ� �Է����ּ���.
	 */

	public void register(String userId, String password, String name, String phone) {
		User user = null;
		String userNo = "U001";
		
		if (this.users.size() > 0) {
			Set<String> key = this.users.keySet();
			List<String> temp = new ArrayList<String>(key);

			Collections.sort(temp, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					return s1.compareTo(s2);
				}
			});
			String tempKey = temp.get(temp.size() - 1);
			user = this.users.get(tempKey);
			userNo = String.format("U%03d", Integer.parseInt(user.getUserNo().substring(1)) + 1);
		}
		
		User u = new User(userNo, userId, password, name, phone);
		this.users.put(userNo, u);
	}

	/*
	 * @Param ����� ���̵�, ����� ��й�ȣ
	 * ���������� ��� ������ �޼ҵ�. (���� ����� ������, ����� ���� ����)  
	 */
	public User getCurrentUser(String userId, String password) {
		//��� ������ �ѱ� User Ÿ�� ���� ����
		User user = null;
		//��ü users�� �ݺ����� ������, Ž���ϱ� ���� set, list ����
		Set<String>key = this.users.keySet();
		List<String>temp = new ArrayList<String>(key);
		//Iterator�� �ݺ��� ������
		Iterator<String> it = temp.iterator();
		while(it.hasNext()) {
			String keyTemp = (String)it.next();
			//User Ÿ���� �ӽ� ���� ���� �� users �� ��ü�� �� �Ҵ�
			User u = this.users.get(keyTemp);
			//�Ű������� �Է¹��� ����� ���̵� �� ��й�ȣ�� ��ġ�ϴ� ���� �ִٸ�
			if(u.getUserId().equals(userId)&&u.getPassword().equals(password)) {
				//��� ���� ������ ���� ������ �Ҵ�
				user = u;
				//��� ���� ã�����Ƿ�, �� �̻� �ݺ����� ���� �ʿ䰡 ����. break;
				break;
			}
		}
		//��� �� ���� -> ��� ����  null�̶��, ����ڰ� ���ٴ� ���̴�. ������ �� �� �� üũ �ʼ�!
		return user;
	}
	
	/*
	 * ����� ���̵� ���� ���� Ȯ�� 
	 * @Param ����� ���̵�, ����� ��й�ȣ
	 * ���̵�� ����������, ��й�ȣ�� ���� ���� ��� true�� �����Ѵ�.
	 */
	public boolean isWrongPassword(String userId, String password) {
		boolean result = false;
		Set<String>key = this.users.keySet();
		List<String>temp = new ArrayList<String>(key);
		Iterator<String> it = temp.iterator();
		while(it.hasNext()) {
			String keyTemp = (String)it.next();
			User u = this.users.get(keyTemp);
			if(u.getUserId().equals(userId)&&!u.getPassword().equals(password)) {
				result = true;
				break;
			}
		}
		return result;
	}
	/*
	 * @Param �����̸�  
	 * ���� ������ȭ
	 * ���α׷� ����� users, books, checkouts �ʱ�ȭ�� ���� �޼ҵ�
	 */
	public Object deSerialization(String fileName) {
		Object result = null;
		FileInputStream fs = null;
		ObjectInputStream os = null;
		try {
			fs = new FileInputStream(fileName);
			os = new ObjectInputStream(fs);
			result = os.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// ���α׷� ����� users, books, checkOuts ������ ����
	public void logout() {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			if (this.users.size() > 0) {
				fs = new FileOutputStream(USER_FILE);
				os = new ObjectOutputStream(fs);
				os.writeObject(this.users);
			}
			if (this.books.size() > 0) {
				fs = new FileOutputStream(BOOK_FILE);
				os = new ObjectOutputStream(fs);
				os.writeObject(this.books);
			}
			if (this.checkOuts.size() > 0) {
				fs = new FileOutputStream(CHECKOUT_FILE);
				os = new ObjectOutputStream(fs);
				os.writeObject(this.checkOuts);
			}
			// �÷��� ����ҿ� ����� ��� ������ ����ȭ �õ�
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
