package com.toyrobot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.toyrobot.controller.CommandController;
import com.toyrobot.entities.Position;
import com.toyrobot.entities.SquareTableTop;
import com.toyrobot.entities.ToyRobot;
import com.toyrobot.enums.Direction;
import com.toyrobot.exceptions.CommandException;
import com.toyrobot.exceptions.DirectionException;
import com.toyrobot.exceptions.ToyRobotException;

import junit.framework.Assert;

/**
 * Unit test for Command Controller class
 */
public class CommandControllerTest {
	
	SquareTableTop squareTableTop = new SquareTableTop(5, 5);
    ToyRobot toyRobot = new ToyRobot();
    CommandController commandController = new CommandController(squareTableTop, toyRobot);
    @Rule
    public org.junit.rules.ExpectedException ex = ExpectedException.none();

	@Test
	public void testFirstCommand() throws CommandException {
		Assert.assertTrue(commandController.validateFirstCommand("PLACE 1,1,NORTH"));
		ex.expect(CommandException.class);
		Assert.assertEquals("Can not proceed further as Robot has not been PLACEd", commandController.validateFirstCommand("LEFT"));
	}

	@Test
	public void testLastCommand() throws CommandException {
		Assert.assertTrue(commandController.validateLastCommand("REPORT"));
		Assert.assertFalse(commandController.validateLastCommand("MOVE"));
	}
	
	@Test
	public void testPlaceToyRobot() throws ToyRobotException, DirectionException {
		
		Assert.assertTrue(commandController.placeToyRobot(new Position(0, 1, Direction.NORTH)));
		Assert.assertFalse(commandController.placeToyRobot(new Position(9, 8, Direction.EAST)));
		Assert.assertFalse(commandController.placeToyRobot(new Position(-5, 1, Direction.WEST)));
		Assert.assertTrue(commandController.placeToyRobot(new Position(3, 4, Direction.SOUTH)));
        
	}
	
	@Test
	public void testExecuteCommand() throws CommandException, DirectionException, ToyRobotException {
		commandController.executeCommand("PLACE 0,0,NORTH");
		Assert.assertEquals("0,0,NORTH", commandController.report());
		
		commandController.executeCommand("MOVE");
		commandController.executeCommand("RIGHT");
		commandController.executeCommand("MOVE");
		Assert.assertEquals("1,1,EAST", commandController.report());
		//Ignore command if outside the Table top
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		commandController.executeCommand("MOVE");
		Assert.assertEquals("5,1,EAST", commandController.report());
		commandController.executeCommand("RIGHT");
		commandController.executeCommand("MOVE");		
		Assert.assertEquals("5,0,SOUTH", commandController.report());
		
		
	}
	
	@Test
	public void testInvalidCommand() throws CommandException, DirectionException, ToyRobotException {
		commandController.executeCommand("PLACE 0,0,NORTH");
		Assert.assertEquals("0,0,NORTH", commandController.report());
		ex.expect(CommandException.class);
		Assert.assertEquals("Invalid command", commandController.executeCommand("XXXX"));
		ex.expect(CommandException.class);
		Assert.assertEquals("Invalid command", commandController.executeCommand("Move"));
		ex.expect(CommandException.class);
		Assert.assertEquals("Place command requires 3 parameters(eg. PLACE 1,1,EAST)", commandController.executeCommand("PLACE 1,2"));
	}
	
	
}
