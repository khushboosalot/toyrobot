package com.toyrobot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.toyrobot.controller.CommandController;
import com.toyrobot.entities.SquareTableTop;
import com.toyrobot.entities.ToyRobot;
import com.toyrobot.exceptions.CommandException;
import com.toyrobot.exceptions.DirectionException;
import com.toyrobot.exceptions.ToyRobotException;

public class ToyRobotSimulator{
	
	public static void main( String[] args){
		
		boolean hasCommandInput=true;
        int cnt=0;
        SquareTableTop squareTableTop = new SquareTableTop(5, 5);
        ToyRobot toyRobot = new ToyRobot();
        CommandController commandController = new CommandController(squareTableTop, toyRobot);

        System.out.println("Toy Robot");
        System.out.println("Please enter a command. Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT");        
        
		try {
			
			while(hasCommandInput){
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String commandStr;
				commandStr = reader.readLine();
				if (commandStr != null) {
					cnt++;
					if(cnt==1){ 
						commandController.validateFirstCommand(commandStr);
					}					
					if(commandController.validateLastCommand(commandStr)){		
						hasCommandInput=false;
						
					}
					String output=commandController.executeCommand(commandStr);
					if(!hasCommandInput){
						System.out.println("Final position of Robot is:"+output);
					}else{
						System.out.println("Current position of Robot is:"+commandController.report());
					}
					
				}
			}
		} catch (DirectionException e) {
            System.out.println(e.getMessage());
        }catch (CommandException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
        	System.out.println("I/O Error");
			e.printStackTrace();
		} catch (ToyRobotException e) {
			System.out.println(e.getMessage());			
		}

	}
}
