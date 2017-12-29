package sist.group1;

public class CheckOut {
	
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

	public String getReturnDate() {
		return returnDate;
	}

	public String getcBookNo() {
		return bookNo;
	}

	public String getcUserNo() {
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
	
	/*public int getcheckOutbookStatus() {
	return checkOutbookStatus;
	}*/
	
	/*public void setccheckOutbookStatus(int checkOutbookStatus) {
	this.checkOutbookStatus = checkOutbookStatus;
	}*/
	
}
