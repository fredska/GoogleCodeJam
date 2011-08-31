package test.year2009.round1C.AllYourBase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import year2009.round1c.AllYourBase;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AllYourBaseTest {
	
	@Mock private AllYourBase ayb;
	@Before
	public void setup()
	{
		ayb = new AllYourBase();
	}
	
	@Test
	public void findUniqueCharactersTest()
	{
		String input1 = "bbabbbbaaaaababbabbababbaabbbabaabbbabbaabaaaaaaaaaaaaaaaaaa";
		String input2 = "cats";
		String input3 = "zig";
		assertEquals(1000000000000000000l, ayb.findMinimumValue(input1));
		assertEquals(75, ayb.findMinimumValue(input2));
		assertEquals(11, ayb.findMinimumValue(input3));
	}
}
