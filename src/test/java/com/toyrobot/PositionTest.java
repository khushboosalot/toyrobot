package com.toyrobot;

import org.junit.Test;

import com.toyrobot.entities.Position;
import com.toyrobot.enums.Direction;
import com.toyrobot.exceptions.DirectionException;

import junit.framework.Assert;

/**
 * Unit test for Robot Position
 */
public class PositionTest {

	@Test
	public void testIsValidNewPosition() throws DirectionException {
		
		Position position = new Position(0,0,Direction.NORTH);
		
		
		Position newPosition = position.getNextPosition();
        Assert.assertEquals(newPosition.getX(), 0);
        Assert.assertEquals(newPosition.getY(), 1);
        Assert.assertEquals(newPosition.getDirection(), Direction.NORTH);

        newPosition = newPosition.getNextPosition();
        Position expected=new Position(0,2,Direction.NORTH);
        Position actual=new Position(0,1,Direction.EAST);
        Assert.assertNotSame(expected, actual);
        
        newPosition.setDirection(Direction.EAST);
        newPosition = newPosition.getNextPosition();
        Assert.assertEquals(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 2);
        Assert.assertEquals(newPosition.getDirection(), Direction.EAST);

	}

}
