package com.toyrobot;

import org.junit.Test;

import com.toyrobot.entities.Position;
import com.toyrobot.entities.SquareTableTop;
import com.toyrobot.enums.Direction;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Unit test for Square Table Top.
 */
public class SquareTableTopTest 
    extends TestCase
{
	@Test
    public void testIsValidPosition(){
    	SquareTableTop squareTableTop = new SquareTableTop(5,5);  
    	Assert.assertTrue(squareTableTop.isOutOfRange(new Position(1,1,Direction.EAST)));
    	Assert.assertFalse(squareTableTop.isOutOfRange(new Position(-1,1,Direction.NORTH)));
    	Assert.assertFalse(squareTableTop.isOutOfRange(new Position(10,0,Direction.WEST)));
    	Assert.assertFalse(squareTableTop.isOutOfRange(new Position(6,7,Direction.SOUTH)));
    	Assert.assertTrue(squareTableTop.isOutOfRange(new Position(2,4,Direction.EAST)));
    	Assert.assertTrue(squareTableTop.isOutOfRange(new Position(3,2,Direction.SOUTH)));
    }
}
