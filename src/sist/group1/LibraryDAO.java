package sist.group1;

import java.util.*;
import java.io.*;

public class LibraryDAO {

	/*
	 * 
	 * this.users.add(new User("","","","","")); 전체 사용자 정보 저장용 리스트에 넣는 방법
	 * this.books.add(new Book("","","","")); 전체 책 정보 저장용 리스트에 넣는 방법
	 * this.checkOuts.add(new CheckOut("B001","U001","2017-12-27")); 대출 전체 정보 리스트에
	 * 넣는 방법 for(CheckOut c : checkOuts) { 리스트에서 특정 매개변수 값에 맞게 객체 정보 꺼내오는 방법
	 * if(c.getcBookNo().equals("B001")) {
	 * 
	 * } Set<String>key = TestClass.testBook.keySet(); Iterator it = key.iterator();
	 * while(it.hasNext()){ 맵에서 특정 매개변수 값에 맞게 객체 정보 꺼내오는 방법 String key =
	 * (String)it.next(); Book b = TestClass.testBook.get(key); }
	 * 
	 * }
	 */

	/*
	 * @Param 사용자 정보(현재 접속한 사용자 아이디, 책 정보), 사용자 전체 정보, 책 전체 정보, 대출 전체 정보
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
		 * 값 출력 테스트
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
	 * -회원가입이 완료 되었습니다. 
	 * -이미 존재하는 id 입니다. 다시 입력해주세요. 
	 * -잘못된 비밀번호 형식입니다. 다시 입력해주세요. 
	 * -잘못된 전화번호 형식입니다. 다시 입력해주세요.
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
	 * @Param 사용자 아이디, 사용자 비밀번호
	 * 범용적으로 사용 가능한 메소드. (현재 사용자 설정용, 사용자 존재 여부)  
	 */
	public User getCurrentUser(String userId, String password) {
		//결과 값으로 넘길 User 타입 변수 생성
		User user = null;
		//전체 users의 반복문을 돌리며, 탐색하기 위해 set, list 생성
		Set<String>key = this.users.keySet();
		List<String>temp = new ArrayList<String>(key);
		//Iterator로 반복문 돌리기
		Iterator<String> it = temp.iterator();
		while(it.hasNext()) {
			String keyTemp = (String)it.next();
			//User 타입의 임시 변수 선언 후 users 맵 객체의 값 할당
			User u = this.users.get(keyTemp);
			//매개변수로 입력받은 사용자 아이디 및 비밀번호가 일치하는 값이 있다면
			if(u.getUserId().equals(userId)&&u.getPassword().equals(password)) {
				//결과 값을 리턴할 유저 변수에 할당
				user = u;
				//결과 값을 찾았으므로, 더 이상 반복문을 돌릴 필요가 없다. break;
				break;
			}
		}
		//결과 값 리턴 -> 결과 값이  null이라면, 사용자가 없다는 뜻이다. 가져다 쓸 때 널 체크 필수!
		return user;
	}
	
	/*
	 * 사용자 아이디 존재 여부 확인 
	 * @Param 사용자 아이디, 사용자 비밀번호
	 * 아이디는 존재하지만, 비밀번호가 맞지 않을 경우 true를 리턴한다.
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
	 * @Param 파일이름  
	 * 파일 역직렬화
	 * 프로그램 실행시 users, books, checkouts 초기화를 위한 메소드
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

	// 프로그램 종료시 users, books, checkOuts 데이터 저장
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
			// 컬렉션 저장소에 저장된 모든 정보를 직렬화 시도
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
