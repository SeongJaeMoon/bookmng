package sist.group1;

public class ExistUserException extends Exception {

	//사용자가 이미 존재할 경우, 발생하는 예외
	public ExistUserException() {
		super();
	}

	public ExistUserException(String arg0) {
		super(arg0);
	}

}
