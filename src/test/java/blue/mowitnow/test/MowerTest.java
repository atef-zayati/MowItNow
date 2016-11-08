package blue.mowitnow.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import blue.mowitnow.Mower;

@RunWith(Parameterized.class)
public class MowerTest {
	private final static Mower.Point GRID_BOUNDARY = new Mower.Point(5, 5); // 5 5
	
	private final Mower.Point initialPosition;
	private final Mower.Direction initialDirection;
	private final String sequence;
	private final Mower.Point expectedFinalPosition;
	private final Mower.Direction expectedFinalDirection;

	public MowerTest(final Mower.Point initialPosition,
			final Mower.Direction initialDirection, final String sequence,
			final Mower.Point expectedFinalPosition,
			final Mower.Direction expectedFinalDirection) {
		this.initialPosition = initialPosition;
		this.initialDirection = initialDirection;
		this.sequence = sequence;
		this.expectedFinalPosition = expectedFinalPosition;
		this.expectedFinalDirection = expectedFinalDirection;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ new Mower.Point(1, 2), Mower.Direction.N, "GAGAGAGAA",
						new Mower.Point(1, 3), Mower.Direction.N }, // 1 2 N
																	// GAGAGAGAA
																	// 1 3 N ?
				{ new Mower.Point(3, 3), Mower.Direction.E, "AADAADADDA",
						new Mower.Point(5, 1), Mower.Direction.E } // 3 3 E
																	// AADAADADDA
																	// 5 1 E ?
		};
		return Arrays.asList(data);
	}

	@Test
	public void testMower() {
		Mower mower = new Mower(initialPosition, initialDirection,
				GRID_BOUNDARY);
		mower.executeSequence(sequence);

		assertEquals("Result", new Mower(expectedFinalPosition,
				expectedFinalDirection, GRID_BOUNDARY), mower);
	}
}
