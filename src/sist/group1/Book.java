package sist.group1;

public class Book {

	/*
	 @Param
	 ������ȣ(ex. BOO1, BOO2...), ������, ����, ���ǻ�, ���� ���� ����
	 */
	private String bookNo;
	private String bookTitle;
	private String author;
	private String publisher;
	private int bookStatus; //0: ��ġ��, 1: ������, 2:��ü��
	
	public Book() {
		
	}
	
	public Book(String bookNo, String bookTitle, String author, String publisher) {
		
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.author = author;
		this.publisher = publisher;
	}
	public String getPublisher() {
		return publisher;
	}
	
	public String getBookNo() {
		return bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getAuthor() {
		return author;
	}
	
	public int getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}

}
