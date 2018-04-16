package com.toyrobot;

import org.junit.Assert;
import org.junit.Test;

import com.toyrobot.entities.Position;
import com.toyrobot.entities.ToyRobot;
import com.toyrobot.enums.Direction;
import com.toyrobot.exceptions.DirectionException;

/**
 * Unit test for Robot movement on the table top
 */
public class ToyRobotTest {

	@Test
	public void testToyRobot() throws DirectionException {
		Position position = new Position(1,1,Direction.EAST);
		ToyRobot toyRobot = new ToyRobot(position);
		toyRobot.rotateLeft();
		toyRobot.move();
		Assert.assertEquals(1, toyRobot.getPosition().getX());
		Assert.assertEquals(2, toyRobot.getPosition().getY());
		Assert.assertEquals(Direction.NORTH, toyRobot.getPosition().getDirection());
		
		toyRobot.rotateRight();		
		toyRobot.move();
		toyRobot.rotateRight();
		Assert.assertEquals(2, toyRobot.getPosition().getX());
		Assert.assertEquals(2, toyRobot.getPosition().getY());
		Assert.assertEquals(Direction.SOUTH, toyRobot.getPosition().getDirection());
		
		toyRobot.setPosition(new Position(0,0,Direction.NORTH));
		toyRobot.move();
		toyRobot.rotateRight();
		
		Assert.assertEquals(0, toyRobot.getPosition().getX());
		Assert.assertEquals(1, toyRobot.getPosition().getY());
		Assert.assertEquals(Direction.EAST, toyRobot.getPosition().getDirection());
		
	}

}
