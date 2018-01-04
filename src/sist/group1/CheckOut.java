package sist.group1;

import java.io.Serializable;

public class CheckOut implements Serializable, Comparable<CheckOut> {
	
	/**
	 * 직렬화 UID
	 */
	private static final long serialVersionUID = 3L;
	/*
	 @Param
	  책의 등록번호, 대출자 번호, 대출일, 반납입, 반납예정일, 책 상태, 반납상태(보류), 연체일수  
	 */
	private String bookNo;
	private String userNo;
	private String checkOutDate;
	private String returnDate;
	private String dueDate="";
	//private int checkOutbookStatus; //0: 반납완료, 1: 대출중, 2: 연체중
	private int overdueDays;

	public CheckOut() {
		
	}
	
	public CheckOut(String bookNo, String userNo, String checkOutDate) {
		this.bookNo = bookNo;
		this.userNo = userNo;
		this.checkOutDate = checkOutDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public String getBookNo() {
		return bookNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public String getDueDate() {
		return dueDate;
	}
	
	//dueDate(반납 예정일)은 생성자 호출시점에 cDate + 7
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(int overdueDays) {
		this.overdueDays = overdueDays;
	}	
	
	@Override
	public int compareTo(CheckOut obj) {
		CheckOut checkOut = null;
		if (obj instanceof CheckOut) {
			checkOut = (CheckOut) obj;
		}
		return this.checkOutDate.compareTo(checkOut.getCheckOutDate());
	}
	
}
