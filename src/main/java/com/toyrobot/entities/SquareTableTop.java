package com.toyrobot.entities;

public class SquareTableTop{

	int rows;
	int cols;


	public SquareTableTop(int rows,int cols){
		this.rows=rows;
		this.cols=cols;
	}

	
	public boolean isOutOfRange(Position position) {
		if(position.getX() > this.rows || position.getX() < 0 ||
			position.getY() > this.cols || position.getY() < 0){
			System.out.println("Out of range. Ignoring this command - "+position.getX()+","+position.getY()+","+position.getDirection());
			return false;
		}else{
			return true;
		}
		
	}
}
