package Exceptions;

public class EmptyFieldException extends Exception {

	private static final long serialVersionUID = 8893592563343051780L;

	public EmptyFieldException(String msg) {
		super(msg);
	}
}
