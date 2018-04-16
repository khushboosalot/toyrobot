package com.toyrobot;

import org.junit.Test;

import com.toyrobot.enums.Direction;

import junit.framework.Assert;

/**
 * Unit test for Robot Direction
 */
public class ToyRobotDirectionTest {

	@Test
	public void testDirection() {
		
		Direction dir = Direction.NORTH;
		
		Assert.assertEquals(dir.leftDirection(), Direction.WEST);		
		Assert.assertSame(dir.rightDirection(), Direction.EAST);		
		Assert.assertNotSame(dir.rightDirection(), Direction.WEST);
		Assert.assertNotSame(dir.rightDirection(), Direction.SOUTH);
		Assert.assertNotSame(dir.rightDirection(), Direction.NORTH);
		
		
	}

}
