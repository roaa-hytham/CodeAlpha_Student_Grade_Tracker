package exceptions;

public class NotEnoughBalanceException extends Exception{

	public NotEnoughBalanceException(){
		super();
	}
	
	public NotEnoughBalanceException(String e) {
		super(e);
	}
		
}
