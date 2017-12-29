package sist.group1;

import java.util.*;
import java.io.*;

public class LibraryDAO {

	/*
	
	this.users.add(new User("","","","","")); ��ü ����� ���� ����� ����Ʈ�� �ִ� ���
	this.books.add(new Book("","","","")); ��ü å ���� ����� ����Ʈ�� �ִ� ���
	this.checkOuts.add(new CheckOut("B001","U001","2017-12-27")); ���� ��ü ���� ����Ʈ�� �ִ� ���
	for(CheckOut c : checkOuts) { ����Ʈ���� Ư�� �Ű����� ���� �°� ��ü ���� �������� ���
		if(c.getcBookNo().equals("B001")) {
		
		}
	 Set<String>key = TestClass.testBook.keySet();
	 Iterator it = key.iterator();
	 while(it.hasNext()){ �ʿ��� Ư�� �Ű����� ���� �°� ��ü ���� �������� ���
	 	String key = (String)it.next();
	 	Book b = TestClass.testBook.get(key);
	 }
		
	}*/
	
	/*
	 @Param
	 ����� ����(���� ������ ����� ���̵�, å ����), ����� ��ü ����, å ��ü ����, ���� ��ü ���� 
	 */
	
	private Utils utils = Utils.getInstance();
	private Map<String, User> users = new HashMap<String, User>();
	private Map<String, Book> books = new HashMap<String, Book>();
	private List<CheckOut>checkOuts = new ArrayList<CheckOut>();
	
	
	
	private static final String USER_FILE = "users.data";
	private static final String BOOK_FILE = "books.data";
	private static final String CHECKOUT_FILE = "checkOuts.data";

	public LibraryDAO() {
		File file = new File(USER_FILE);
		File file1 = new File(BOOK_FILE);
		File file2 = new File(CHECKOUT_FILE);
		Object obj = null;
		
		if (file.exists()) {
			obj = deSerialization(USER_FILE);
			this.users = (Map<String, User>)obj;
			obj = null;
		}
		if(file1.exists()) {
			obj = deSerialization(BOOK_FILE);
			this.books = (Map<String, Book>)obj;
			obj = null;
		}
		if(file2.exists()) {
			obj = deSerialization(CHECKOUT_FILE);
			this.checkOuts = (List<CheckOut>)obj;
		}
	}
	
	
	/*
	-ȸ�������� �Ϸ� �Ǿ����ϴ�.
	-�̹� �����ϴ� id �Դϴ�. �ٽ� �Է����ּ���.
	-�߸��� ��й�ȣ �����Դϴ�. �ٽ� �Է����ּ���.
	-�߸��� ��ȭ��ȣ �����Դϴ�. �ٽ� �Է����ּ���.*/
	
	public void register(String userId, String password, String name, String phoneNumber) {
		
		User user = null;
		String userNo = "U001";
		
		Set<String>key = this.users.keySet();
		List<String>temp = new ArrayList<String>(key);
				
		if(this.users.size()>0){
			user = this.users.get("");
			userNo = String.format("U%03d", Integer.parseInt(user.getUserNo().substring(1)) + 1); 
		}
		
		User u = new User(userNo, userId, password, name, phoneNumber);
		this.users.put(userNo, u);
	}
	
	/*
	 @param
	 ���̵�, ��й�ȣ �Է� ��
	 ����� �α��ο� �޼ҵ�, ���� ������� ���� ��ȣ ����
	 */
	public void login(String userId, String password) {
		
	}
	
	/*
	 ����� ���̵� �̹� ���� ���� Ȯ�� ����ó��
	 */
	public void isExistUser(String userId) throws ExistUserException{
		
	}
	
	/*
	 @Param 
	 �����̸�
	 ������ȭ
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
	
	//���α׷� ����� users, books, checkOuts ������ ����
	public ObjectOutputStream fileSave() {
		FileOutputStream fs = null;
		ObjectOutputStream os = null;
		try {
			fs = new FileOutputStream(USER_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.users);
			fs = new FileOutputStream(BOOK_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.books);
			fs = new FileOutputStream(CHECKOUT_FILE);
			os = new ObjectOutputStream(fs);
			os.writeObject(this.checkOuts);
			//�÷��� ����ҿ� ����� ��� ������ ����ȭ �õ�
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
		return os;
	}
}
