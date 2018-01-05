package sist.group1;

import java.io.Serializable;

public class CheckOut implements Serializable, Comparable<CheckOut> {
	
	/**
	 * ����ȭ UID
	 */
	private static final long serialVersionUID = 3L;
	/*
	 @Param
	  å�� ��Ϲ�ȣ, ������ ��ȣ, ������, �ݳ���, �ݳ�������, å ����, �ݳ�����(����), ��ü�ϼ�  
	 */
	private String bookNo;
	private String userNo;
	private String checkOutDate;
	private String returnDate;
	private String dueDate;
	//private int checkOutbookStatus; //0: �ݳ��Ϸ�, 1: ������, 2: ��ü��
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
	
	//dueDate(�ݳ� ������)�� ������ ȣ������� cDate + 7
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
	public int compareTo(CheckOut checkOut) {
		return this.checkOutDate.compareTo(checkOut.getCheckOutDate());
	}
	
}
