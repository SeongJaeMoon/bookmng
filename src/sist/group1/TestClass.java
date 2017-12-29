package sist.group1;

import java.util.*;

public class TestClass {

	/*
	 @Param
	 testData
	 값 가져올 때  아래 형식으로 iterator 사용하시면 됩니다.
	 Set<String>key = TestClass.testBook.keySet();
	 Iterator it = key.iterator();
	 while(it.hasNext()){
	 	String key = (String)it.next();
	 	Book b = TestClass.testBook.get(key);
	 }
	 */
	public static final Map<String, Book> testBook = new HashMap<String, Book>(); 
	public static final Map<String, User> testUser = new HashMap<String, User>();
	
	static {
		/*
		 @Param
		 키: 등록번호(ex. B001, B002...), 등록번호, 도서명, 저자, 출판사
		 */
		testBook.put("B001", new Book("B001", "이것이자바다", "신종권","한빛미디어"));
		testBook.put("B002", new Book("B002", "C를 통해 배우는 자료구조", "윤성우","오렌지미디어"));
		testBook.put("B003", new Book("B003", "TCP/IP 윈도우 소켓프로그래밍", "김선우","한빛미디어"));
		testBook.put("B004", new Book("B004", "Oracle 2007","S H KIM", "Orange Media"));
		testBook.put("B005", new Book("B005", "MS Office 2010","D H Ahn", "Orange Media"));
		
		/*
		 @Param 
		 키: 회원번호(ex. U001, U002...), 값: 회원번호 ,아이디, 비밀번호, 이름, 전화번호, 받은 메세지, 유저 고유의 대출 정보
		 */
		testUser.put("U001", new User("U001","clear219","1234","SeongJaeMoon","010-4318-0221"));
		testUser.put("U002", new User("U002","sjmoon0221","1234","SeongjaeMoon","010-4318-0221"));
		testUser.put("U003", new User("U003","minjong12","1234","Minjong","010-1234-1234"));
		testUser.put("U004", new User("U004","asdf123","1234","DonghyunAhn","010-2233-0121"));
		testUser.put("U005", new User("U005","choi123","1234","YumiChoi","010-1234-4567"));
		
	}
}
