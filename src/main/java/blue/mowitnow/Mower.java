package blue.mowitnow;

import org.apache.log4j.Logger;

/**
 * The <code>Mower</code> class represents a lawn mower. A <code>Mower</code>
 * object has a position {@link Mower#position }, a direction
 * {@link Mower#direction } and can move inside a grid {@link Mower#gridBoundary }
 * 
 * @author Atef ZAYATI
 */
public class Mower {
	private static final Logger logger = Logger.getLogger(Mower.class);

	/**
	 * The position of this <code>Mower</code>.
	 * 
	 * @see Point
	 */
	private Point position;

	/**
	 * The direction of this <code>Mower</code>.
	 * 
	 * @see Direction
	 */
	private Direction direction;

	/**
	 * The top right position of the grid where this <code>Mower</code> can
	 * move.
	 * 
	 * @see Point
	 */
	private final Point gridBoundary;

	/**
	 * Constructs a <code>Mower</code> with the specified initial position,
	 * direction and gird boundary.
	 * 
	 * @param position
	 *            the initial position of the mower
	 * @param direction
	 *            the initial direction of the mower
	 * @param gridBoundary
	 *            the top right position of the grid
	 */
	public Mower(Point position, Direction direction, final Point gridBoundary) {
		this.position = new Point(position);
		this.direction = direction;
		this.gridBoundary = gridBoundary;
	}

	/**
	 * Returns the current position of the mower.
	 * 
	 * @return the current position of the mower
	 */
	public Point getPosition() {
		return this.position;
	}

	/**
	 * Returns the current direction of the mower.
	 * 
	 * @return the current direction of the mower
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * Returns a copy of the top right position of the grid.
	 * 
	 * @return a copy of the top right position of the grid
	 */
	public Point getGridBoundary() {
		return new Point(this.gridBoundary);
	}

	/**
	 * Executes a sequence of commands.
	 * 
	 * @param sequence
	 *            the sequence of commands to be executed by the mower
	 * @see #executeCommand(char)
	 */
	public void executeSequence(String sequence) {
		logger.debug("Executing sequence: '" + sequence + "'");

		for (int i = 0, l = sequence.length(); i < l; i++) {
			this.executeCommand(sequence.charAt(i));
		}
	}

	/**
	 * Executes a simple command.
	 * 
	 * @param command
	 *            the command to be executed by the mower
	 */
	private void executeCommand(char command) {
		logger.debug("Executing command: '" + command + "'");

		switch (command) {
		case 'D':
			this.rotateRight();
			break;
		case 'G':
			this.rotateLeft();
			break;
		case 'A':
			this.move();
			break;
		default:
			// do nothing
			logger.debug("Unknown command. The mower will do nothing.");
			break;
		}
	}

	/**
	 * Moves the mower according to its current position and current direction.
	 * If the final position of the mower will be out of the grid, then it will
	 * not move.
	 * 
	 * @see Point#translate
	 */
	private void move() {
		logger.debug("Initial position : " + this.position.getX() + " "
				+ this.position.getY());

		boolean outOfBoundaries = (this.position.getX() + this.direction.getA() > this.gridBoundary
				.getX())
				|| (this.position.getY() + this.direction.getB() > this.gridBoundary
						.getY());

		if (outOfBoundaries) {
			logger.debug("Final position is out of the grid. The mower will not move.");
		} else {
			logger.debug("Final position is within the grid. The mower will move.");
			this.position.translate(this.direction.getA(),
					this.direction.getB());
		}

		logger.debug("Final position : " + this.position.getX() + " "
				+ this.position.getY());
	}

	/**
	 * Rotates the mower 90° anticlockwise.
	 */
	private void rotateLeft() {
		logger.debug("The mower will rotate to the left.");

		logger.debug("Initial direction : " + this.direction);

		switch (this.direction) {
		case N:
			this.direction = Direction.W;
			break;
		case E:
			this.direction = Direction.N;
			break;
		case W:
			this.direction = Direction.S;
			break;
		case S:
			this.direction = Direction.E;
			break;
		default:
			break;
		}

		logger.debug("Final direction : " + this.direction);
	}

	/**
	 * Rotates the mower 90° clockwise.
	 */
	private void rotateRight() {
		logger.debug("The mower will rotate to the right.");

		logger.debug("Initial direction : " + this.direction);

		switch (this.direction) {
		case N:
			this.direction = Direction.E;
			break;
		case E:
			this.direction = Direction.S;
			break;
		case W:
			this.direction = Direction.N;
			break;
		case S:
			this.direction = Direction.W;
			break;
		default:
			break;
		}

		logger.debug("Final direction : " + this.direction);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((gridBoundary == null) ? 0 : gridBoundary.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/**
	 * Determines whether or not two mowers are equal. Two instances of
	 * <code>Mower</code> are equal if their <code>position</code> are equal
	 * {@link Point#equals(Object)} , their <code>direction</code> are the same
	 * and they share the same <code>gridBoundary</code> object.
	 * 
	 * @param obj
	 *            an object to be compared with this <code>Mower</code>
	 * @return <code>true</code> if the object to be compared is an instance of
	 *         <code>Mower</code> and has the same <code>position</code> values,
	 *         the same <code>direction</code> values and shares the same
	 *         <code>gridBoundary</code> object; <code>false</code> otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Mower)) {
			return false;
		}
		Mower other = (Mower) obj;
		if (position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!position.equals(other.position)) {
			return false;
		}
		if (direction != other.direction) {
			return false;
		}
		if (gridBoundary == null) {
			if (other.gridBoundary != null) {
				return false;
			}
		} else if (gridBoundary != other.gridBoundary) {
			return false;
		}
		return true;
	}

	/**
	 * A point representing a location in {@code (x,y)} coordinate space,
	 * specified in integer precision. This class is inspired by
	 * {@link java.awt.Point}
	 * 
	 * @author Atef ZAYATI
	 */
	public static class Point {
		/**
		 * The X coordinate of this <code>Point</code>. If no X coordinate is
		 * set it will default to 0.
		 *
		 * @see #getLocation()
		 */
		private int x;

		/**
		 * The Y coordinate of this <code>Point</code>. If no Y coordinate is
		 * set it will default to 0.
		 *
		 * @see #getLocation()
		 */
		private int y;

		/**
		 * Constructs and initializes a point at the origin (0,&nbsp;0) of the
		 * coordinate space.
		 */
		public Point() {
			this(0, 0);
		}

		/**
		 * Constructs and initializes a point at the specified {@code (x,y)}
		 * location in the coordinate space.
		 * 
		 * @param x
		 *            the X coordinate of the newly constructed
		 *            <code>Point</code>
		 * @param y
		 *            the Y coordinate of the newly constructed
		 *            <code>Point</code>
		 */
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Constructs and initializes a point with the same location as the
		 * specified <code>Point</code> object.
		 * 
		 * @param p
		 *            a point
		 */
		public Point(Point p) {
			this(p.x, p.y);
		}

		/**
		 * Returns the X coordinate of this <code>Point</code> in integer
		 * precision.
		 * 
		 * @return the X coordinate of this <code>Point</code>.
		 */
		public int getX() {
			return this.x;
		}

		/**
		 * Returns the Y coordinate of this <code>Point</code> in integer
		 * precision.
		 * 
		 * @return the Y coordinate of this <code>Point</code>.
		 */
		public int getY() {
			return this.y;
		}

		/**
		 * Returns the location of this point.
		 * 
		 * @return a copy of this point, at the same location
		 * @see #setLocation(Point)
		 * @see #setLocation(int, int)
		 */
		public Point getLocation() {
			return new Point(this.x, this.y);
		}

		/**
		 * Sets the location of the point to the specified location.
		 * 
		 * @param p
		 *            a point, the new location for this point
		 * @see #getLocation
		 */
		public void setLocation(Point p) {
			setLocation(p.x, p.y);
		}

		/**
		 * Changes the point to have the specified location.
		 * 
		 * @param x
		 *            the X coordinate of the new location
		 * @param y
		 *            the Y coordinate of the new location
		 * @see #getLocation
		 */
		public void setLocation(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * Translates this point, at location {@code (x,y)}, by {@code dx} along
		 * the {@code x} axis and {@code dy} along the {@code y} axis so that it
		 * now represents the point {@code (x+dx,y+dy)}.
		 *
		 * @param dx
		 *            the distance to move this point along the X axis
		 * @param dy
		 *            the distance to move this point along the Y axis
		 */
		public void translate(int dx, int dy) {
			this.x += dx;
			this.y += dy;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		/**
		 * Determines whether or not two points are equal. Two instances of
		 * <code>Point</code> are equal if the values of their <code>x</code>
		 * and <code>y</code> member fields, representing their position in the
		 * coordinate space, are the same.
		 * 
		 * @param obj
		 *            an object to be compared with this <code>Point</code>
		 * @return <code>true</code> if the object to be compared is an instance
		 *         of <code>Point</code> and has the same values;
		 *         <code>false</code> otherwise.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Point)) {
				return false;
			}
			Point other = (Point) obj;
			if (x != other.x) {
				return false;
			}
			if (y != other.y) {
				return false;
			}
			return true;
		}
	}

	/**
	 * A direction is a complex number that can be identified with points in the
	 * complex plane, namely the number {@code a + bi} is identified with the
	 * point {@code (a,b)}.
	 * {@link https://en.wikipedia.org/wiki/Complex_number }
	 * 
	 * <p>
	 * Every direction is an element of the unit circle in the complex plane
	 * {@link https://en.wikipedia.org/wiki/Circle_group }.
	 * The complex plane is a geometric representation of the complex numbers 
	 * {@link https://en.wikipedia.org/wiki/Complex_plane }.
	 * </p>
	 * 
	 * <p>
	 * Examples: {@link https://goo.gl/1ZhTMW }.
	 * </p>
	 * 
	 * @author Atef ZAYATI
	 */
	public enum Direction {
		/**
		 * North direction
		 */
		N(0, 1),

		/**
		 * East direction
		 */
		E(1, 0),

		/**
		 * West direction
		 */
		W(-1, 0),

		/**
		 * South direction
		 */
		S(0, -1);

		/**
		 * The A coordinate of this <code>Direction</code>.
		 */
		private int a;

		/**
		 * The B coordinate of this <code>Direction</code>.
		 */
		private int b;

		/**
		 * Constructs and initializes a direction at the specified {@code (a,b)}
		 * location in the complex plane.
		 * 
		 * @param a
		 *            the A coordinate of the <code>Direction</code>
		 * @param b
		 *            the B coordinate of the <code>Direction</code>
		 */
		Direction(int a, int b) {
			this.a = a;
			this.b = b;
		}

		/**
		 * Returns the A coordinate of this <code>Direction</code>.
		 * 
		 * @return the A coordinate of this <code>Direction</code>.
		 */
		public int getA() {
			return a;
		}

		/**
		 * Returns the B coordinate of this <code>Direction</code>.
		 * 
		 * @return the B coordinate of this <code>Direction</code>.
		 */
		public int getB() {
			return b;
		}
	}
}
