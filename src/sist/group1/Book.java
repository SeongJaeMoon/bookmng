package sist.group1;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book>{

	/**
	 * ����ȭ UID
	 */
	private static final long serialVersionUID = 2L;
	/*
	 @Param
	 ������ȣ(ex. BOO1, BOO2...), ������, ����, ���ǻ�, ���� ���� ����
	 */
	private String bookNo;
	private String bookTitle;
	private String author;
	private String publisher;
	private int bookStatus; //0: ��ġ��, 1: ������, 2:��ü��
	//bookStatus ���°��� ���·� ���� ���� ����
	private String bookStatusString;
	
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
	
	public void setBookStatusString(String bookStatusString) {
		this.bookStatusString = bookStatusString;
	}
	
	public String getBookStatusString() {
		return bookStatusString;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-10s%-20s%-20s%-10s%-10s%n",this.bookNo,this.bookTitle,this.author,this.publisher,this.bookStatus));
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Book) {
			Book book = (Book)obj;
			if(this.bookNo.equals(book.getBookNo())){
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public int compareTo(Book book) {
		return this.bookNo.compareTo(book.getBookNo());
	}
}
