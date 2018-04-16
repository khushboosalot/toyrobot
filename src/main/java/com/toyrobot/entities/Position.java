package com.toyrobot.entities;

import com.toyrobot.enums.Direction;
import com.toyrobot.exceptions.DirectionException;

public class Position {


	int x;
	int y;
	Direction direction;


	public Position(Position position) {
		this.x = position.getX();
		this.y = position.getY();
		this.direction = position.getDirection();
	}
	public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void changePosition(int x, int y) {
		this.x = this.x + x;
		this.y = this.y + y;
	}


	public Position getNextPosition() throws DirectionException
	{
		if (this.direction == null)		
		 throw new DirectionException("Invalid robot direction");

		Position newPosition = new Position(this);
		switch (this.direction) {
		case NORTH:
			newPosition.changePosition(0, 1);
			break;
		case EAST:
			newPosition.changePosition(1, 0);
			break;
		case SOUTH:
			newPosition.changePosition(0, -1);
			break;
		case WEST:
			newPosition.changePosition(-1, 0);
			break;
		}
		return newPosition;
	}

}
