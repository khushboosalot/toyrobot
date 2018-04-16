package com.toyrobot.controller;

import com.toyrobot.entities.Position;
import com.toyrobot.entities.SquareTableTop;
import com.toyrobot.entities.ToyRobot;
import com.toyrobot.enums.Command;
import com.toyrobot.enums.Direction;
import com.toyrobot.exceptions.CommandException;
import com.toyrobot.exceptions.DirectionException;
import com.toyrobot.exceptions.ToyRobotException;

public class CommandController {

	SquareTableTop squareTableTop;
	ToyRobot toyRobot;

	public CommandController(SquareTableTop squareTableTop, ToyRobot toyRobot) {
		super();
		this.squareTableTop = squareTableTop;
		this.toyRobot = toyRobot;
	}

	/**
	 * Place toy robot on the table top
	 * @param position
	 * @return boolean value - valid position
	 * @throws ToyRobotException
	 * @throws DirectionException
	 */
	public boolean placeToyRobot(Position position) throws ToyRobotException, DirectionException {

		if (squareTableTop == null)
			throw new ToyRobotException("Invalid squareTableTop object");

		if (position == null)
			throw new DirectionException("Invalid position object");

		//If new position is not valid, ignore it
		if (!squareTableTop.isOutOfRange(position)){			
			return false;
		}	
		// If position is valid then set robot at new position
		toyRobot.setPosition(position);
		return true;
	}

	public String report() {
		if (toyRobot.getPosition() != null){
			String strRobotPosition=toyRobot.getPosition().getX() + "," + toyRobot.getPosition().getY() + "," + toyRobot.getPosition().getDirection().toString();
			return strRobotPosition;
		}else{
			return null;
		}
	}

	/**
	 * Validate first command is PLACE
	 * @param inputString
	 * @return
	 * @throws CommandException
	 */
	public boolean validateFirstCommand(String inputString) throws CommandException{
		try{
			String[] args = inputString.split(" ");   
			Command command = Command.valueOf(args[0]);			
			if(command != Command.PLACE){
				throw new CommandException("Can not proceed further as Robot has not been PLACEd");
			}else{
				return true;
			}
		}catch(IllegalArgumentException e){
			throw new CommandException("Invalid command");
		}		
	}

	/**
	 * Validate last command is REPORT
	 * @param inputString
	 * @return
	 * @throws CommandException
	 */
	public boolean validateLastCommand(String inputString) throws CommandException{
		try{		
			String[] args = inputString.split(" ");  
			Command command = Command.valueOf(args[0]);
			if(command == Command.REPORT){			
				return true;
			}else{
				return false;
			}
		}catch(IllegalArgumentException e){
			throw new CommandException("Invalid command");
		}
	}
	
	/**
	 * Execute user's command
	 * @param inputString
	 * @return position of robot in string
	 * @throws CommandException
	 * @throws DirectionException
	 * @throws ToyRobotException
	 */
	public String executeCommand(String inputString) throws CommandException, DirectionException, ToyRobotException {
		String[] args = inputString.split(" ");        
		String output=null;

		try {
			Command command = Command.valueOf(args[0]);

			String[] params;
			int x = 0;
			int y = 0;
			Direction commandDirection = null;
			if (command == Command.PLACE) {
				params = args[1].split(",");
				try {
					x = Integer.parseInt(params[0]);
					y = Integer.parseInt(params[1]);
					commandDirection = Direction.valueOf(params[2]);
				} catch (Exception e) {
					throw new CommandException("Place command requires 3 parameters(eg. PLACE 1,1,EAST)");
				}
			}

			switch (command) {
			case PLACE:
				output=String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
				break;
			case MOVE:
				Position newPosition = toyRobot.getPosition().getNextPosition();
				if (squareTableTop.isOutOfRange(newPosition)){
					output=String.valueOf(toyRobot.move(newPosition));
				}
				break;
			case LEFT:
				output=String.valueOf(toyRobot.rotateLeft());
				break;
			case RIGHT:
				output=String.valueOf(toyRobot.rotateRight());
				break;
			case REPORT:
				output=report();
				break;
			default:
				throw new CommandException("Invalid command");
			}
			return output;
		} catch (IllegalArgumentException e) {
			throw new CommandException("Invalid command");
		}
	}

}
