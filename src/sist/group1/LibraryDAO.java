package sist.group1;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	private User currentUser = this.utils.getCurrentUser();
	private Map<String, User> users = new HashMap<String, User>();
	private Map<String, Book> books = new HashMap<String, Book>();
	private List<CheckOut> checkOuts = new ArrayList<CheckOut>();

	private static final String USER_FILE = "D:\\users.data";
	private static final String BOOK_FILE = "D:\\books.data";
	private static final String CHECKOUT_FILE = "D:\\checkOuts.data";

	private LocalDate now = LocalDate.now();
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private String nowDate = this.now.format(dateFormat);
	
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
	/*@Param  
	word : Book의 getter 종류 선택할 때
	key : 값 비교할 때
	검색어와 일치하는 도서를 출력하게 만드는 메소드
	*/
	public String searchForBooks(String word, String key) {

		StringBuilder sb = new StringBuilder();
		
		//검색어에 해당하는 도서 안내 메세지
		sb.append(String.format("[%s]에 해당하는 도서목록 입니다.%n", key));
		sb.append(String.format("--------------------------------------------%%n"));
		sb.append(String.format("등록번호   도서명     저자    출판사    대출현황%n"));
		sb.append(String.format("--------------------------------------------%%n"));
		
			Book s = this.books.get(key);
			if (word.equals("등록번호") && s.getBookNo().contains(key)) {
				sb.append(String.format("%-5s%-7s%-7s%-7s%-7d%n", s.getBookNo(), s.getBookTitle(), s.getPublisher(),
						s.getAuthor(), s.getBookStatus()));
			} else if (word.equals("책제목") && s.getBookTitle().contains(key)) {
				sb.append(String.format("%-5s%-7s%-7s%-7s%-7d%n", s.getBookNo(), s.getBookTitle(), s.getPublisher(),
						s.getAuthor(), s.getBookStatus()));
			} else if (word.equals("출판사") && s.getAuthor().contains(key)) {
				sb.append(String.format("%-5s%-7s%-7s%-7s%-7d%n", s.getBookNo(), s.getBookTitle(), s.getPublisher(),
						s.getAuthor(), s.getBookStatus()));
			} else if (word.equals("저자") && s.getPublisher().contains(key)) {
				sb.append(String.format("%-5s%-7s%-7s%-7s%-7d%n", s.getBookNo(), s.getBookTitle(), s.getPublisher(),
						s.getAuthor(), s.getBookStatus()));
			}
		
		sb.append(String.format("------------------------------------------%n"));
		return sb.toString();
	}
	
	public String viewCheckedOutBooks() {

		StringBuilder sb = new StringBuilder();
		//임시 카운트 변수
		int a = 0;
		// 퍼센트 계산용 books 사이즈 변수
		double d = this.books.size();
		// 오늘날짜 출력
		sb.append(String.format("오늘 날짜 : %s%n", this.nowDate));
		sb.append(String.format("-------------------------------------------------------------------------------------------------%n"));
		sb.append(String.format("등록번호    도서명        저자     출판사       대출일       반납예정일        대출인     회원번호%n"));

			for (CheckOut c : checkOuts) {
				Book b = this.books.get(c.getBookNo());
				User s = this.users.get(c.getBookNo());

				if (b.getBookStatus() == 1 && b.getBookStatus() == 2) {
					++a;
					sb.append(String.format("%-5s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%n", b.getBookNo(),
							b.getBookTitle(), b.getAuthor(), b.getPublisher(), c.getCheckOutDate(), c.getDueDate(),
							s.getName(), c.getUserNo()));
				}
			}
		
		//퍼센트 단위 바꿔주는 변수
		double e = (a / d) * 100.0;
		//강제 형변환
		sb.insert(0, String.format("도서관내 ['%d%'] 책이 대출중 입니다.%n", (int) e));
		return sb.toString();

	}
	/*
	@Param  
	word : Book의 getter 종류 선택할 때
	key : 값 비교할 때
	검색어와 일치하는 유저를 출력하게 만드는 메소드
	 */	
	public String serachForUsers(String word, String key) {

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("[%s]에 해당하는 회원입니다.%n", key));
		sb.append(String.format("--------------------------------------------%%n"));
		sb.append(String.format("회원번호  아이디   이름   전화번호 %n"));
		sb.append(String.format("--------------------------------------------%%n"));

			User s = this.users.get(key);

			// User toString 수정해야함
			if (word.equals("회원번호") && s.getUserNo().contains(key)) {
				sb.append(String.format("%s%n", s.toString()));
			} else if (word.equals("이름") && s.getName().contains(key)) {
				sb.append(String.format("%s%n", s.toString()));
			} else if (word.equals("아이디") && s.getUserId().contains(key)) {
				sb.append(String.format("%s%n", s.toString()));
			} else if (word.equals("전화번호") && s.getphone().contains(key)) {
				sb.append(String.format("%s%n", s.toString()));
			}

		sb.append(String.format("------------------------------------------%n"));
		return sb.toString();
	}

	/*
	@Param
	유저 상세보기.
	*/
		public String viewUserInDetail(String userNo) {
		StringBuilder sb = new StringBuilder();
		// count용 임시변수, 회차
		int count = 0;
		// 값 입력받는 변수
		
		sb.append(String.format("[회원번호/이름/아이디/이메일/연락처]%n"));
		sb.append(String.format("%s%n", this.users.get(userNo)));
		sb.append(String.format("오늘 날짜 : %s%n", this.nowDate));
		sb.append(String.format("--------------------------------------------%%n"));
		sb.append(String.format("회차  도서명    대출일   반납일   반납예정일    연체일수%n"));
		sb.append(String.format("--------------------------------------------%%n"));

			for (CheckOut checkout : checkOuts) {
				++count;
				Book book = this.books.get(checkout.getBookNo());
				sb.append(String.format("%-5d%-10s%-10s%-10s%-10s%-5s%n", count, book.getBookTitle(),
						checkout.getCheckOutDate(), checkout.getReturnDate(), checkout.getDueDate(),
						checkout.getOverdueDays()));
			}
		return sb.toString();
	}

		// 전체 메세지 보기
		public String viewAllMessages() {
			StringBuilder sb = new StringBuilder();

			if(this.currentUser.getMessages().size() == 0 ) {
				System.out.println("메시지가 없습니다.");
			}else {
				sb.append(String.format("[%s/%s]님에게 온 메세지가 [%d]개 있습니다.%n", currentUser.getUserNo(), currentUser.getName(),
						this.currentUser.getMessages().size()));
				sb.append(this.currentUser.getMessages());
			}
			return sb.toString();

		}

		// deleteOneMessage (메시지 한개씩 삭제하는 메소드)
		public void deleteOneMessage(int messageNumber) {
			// 가져와야 할 것 : 메세지의 번호(messageNumber)
			this.currentUser.getMessages().remove(messageNumber);
		}

		// 메세지 전체 삭제
		public void deleteAllMessages() {
			this.currentUser.getMessages().clear();
		}

		// 오늘 날짜 구하는 메소드
		public String getToday() {
			String today = this.now.format(DateTimeFormatter.ISO_LOCAL_DATE).toString();
			return today;
		}
		
		public String getDueday() {
			String dueday = this.now.plusDays(7).format(DateTimeFormatter.ISO_LOCAL_DATE).toString();
			return dueday;
		}
		
		public String viewBookInDetail(String bookNo) {
			StringBuilder sb = new StringBuilder();

			Book book = this.books.get(bookNo);

			sb.append(String.format("[%s/ %s/ %s/ &s]%n", book.getBookNo(), book.getBookTitle(), book.getPublisher(),
					book.getAuthor()));
			sb.append(String.format("오늘 날짜 : %s", this.getToday()));
			sb.append(String.format(
					"------------------------------------------------------------------------------------------------"));
			sb.append(String.format("회차         대출일            반납일             반납예정일       연체일수   대출인    회원번호"));
			sb.append(String.format(
					"------------------------------------------------------------------------------------------------"));

			// 모든 체크아웃 객체들 중에
			// 지금 내가 보려는 책넘버랑 똑같은 책넘버를 가지고 있는 객체
			int count = 0;
			for (CheckOut checkOut : checkOuts) {
				if (checkOut.getBookNo().equals(book.getBookNo())) {
					String key = checkOut.getUserNo();
					++count;
					sb.append(String.format("%d %s %s %s %d %s %s %n", count, checkOut.getCheckOutDate(), checkOut.getReturnDate(), checkOut.getDueDate(),
							checkOut.getOverdueDays(), checkOut.getUserNo(), this.users.get(key).getName()));
				}
			}
			return sb.toString();
		}
}
