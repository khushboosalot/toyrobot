package com.toyrobot.exceptions;

import com.toyrobot.entities.Position;

public class DirectionException extends Exception{

	private static final long serialVersionUID = -6122644562685785612L;
	
	private Position newPosition;

	
	public DirectionException(String message) {
		super(message);		
	}
	
	public DirectionException(Position newPosition,String message) {
		super(message);
		this.newPosition = newPosition;
	}
	
	public Position getNewDirection() {
		return newPosition;
	}
}
