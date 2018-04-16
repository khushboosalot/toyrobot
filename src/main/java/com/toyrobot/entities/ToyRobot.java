package com.toyrobot.entities;

import com.toyrobot.exceptions.DirectionException;

public class ToyRobot {
	private Position position;
	

    public ToyRobot() {
    }

    public ToyRobot(Position position) {
        this.position = position;
    }
    
    public Position getPosition() {
        return this.position;
    }
    
    public boolean setPosition(Position position) {
        if (position!= null){
        	this.position = position;
            return true;
        }else{
        	return false;
        }        
    }
    
    /**
     * Move robot to new position
     * @return
     * @throws DirectionException
     */
    public boolean move() throws DirectionException {
    	return move(position.getNextPosition());
    }
    
    
    /**
     * Move robot to new position
     * @param newPosition
     * @return
     */
    public boolean move(Position newPosition) {
        if (newPosition!= null){
        	this.position = newPosition;
            return true;
        }else{
        	return false;
        }        
    }
    
    /**
     * Rotate Left
     * @return
     */
    public boolean rotateLeft() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.leftDirection();
        return true;
    }
    
    /**
     * Rotate Right
     * @return
     */
    public boolean rotateRight() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.rightDirection();
        return true;
    }
}
