package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Rank;

/**
 *
 * @author Tzuhsien Liu
 * 
 *         Test Rank.java see if rank values are working
 */

public class RankTest {

	@Test
	public void testgetValues() {
		 assertEquals(2, Rank.DEUCE.getValue());
	}

}