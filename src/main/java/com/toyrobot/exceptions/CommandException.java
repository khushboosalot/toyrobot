package com.toyrobot.exceptions;

public class CommandException extends Exception {

	private static final long serialVersionUID = -6121641562685785612L;
	public CommandException(String message) {
		super(message);		
	}
	
	public CommandException(String message,DirectionException directionException) {
		super(message, directionException);
	}
}
