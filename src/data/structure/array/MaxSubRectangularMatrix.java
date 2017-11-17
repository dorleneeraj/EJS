package data.structure.array;

/**
 * 
 * @author neeraj
 *
 *
 *         Given: A two-dimensional array b (M rows, N columns) of Boolean
 *         values ("0" and "1"). Required: Find the largest (most elements)
 *         rectangular subarray containing all ones.
 */
public class MaxSubRectangularMatrix {

	// integer 2-D array
	static int[][] array;
	static int rows;
	static int cols;
	static Coordinate maxLU;
	static Coordinate maxRL;
	static int maxRectangleSize = 0;

	public static void main(String[] args) {
		MaxSubRectangularMatrix mSRM = new MaxSubRectangularMatrix();
		mSRM.init();
		mSRM.printMatrix(array, rows, cols);
		mSRM.printAllRectanglesInMatrix();
		mSRM.printRectangle(maxLU, maxRL);
		mSRM.addingDirection();
	}

	public void init() {
		array = new int[][] { { 1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 },
				{ 1, 1, 1, 1 }, { 0, 1, 1, 1 } };
		rows = 5;
		cols = 4;
	}

	public void printAllRectanglesInMatrix() {
		// matrix is a 2-D array.
		// Rectangle has co-ordinates: lu : Left-upper, ll: Left-lower
		// ru: Right upper, rl: right-lower

		// pratyeek row madhun suru honara rectangle
		for (int i = 0; i < rows; i++) {
			// pratyeek column pasun suru honara
			for (int j = 0; j < cols; j++) {
				// So technically, i ,j will specify the "lu" coordinate(index)
				for (int k = i; k < rows; k++) {
					for (int l = j; l < cols; l++) {
						// so (k,l) will specify the rl coordinate(index)

						// print the rectangle between them.
						Coordinate leftUpper = new Coordinate(i, j);
						Coordinate rightLower = new Coordinate(k, l);
						printRectangle(leftUpper, rightLower);
					}
				}
			}
		}
	}

	// Prints the rectangle
	private void printRectangle(Coordinate leftUpper, Coordinate rightLower) {
		int i = leftUpper.getX();
		int j = leftUpper.getY();

		int k = rightLower.getX();
		int l = rightLower.getY();

		int r = k - i + 1;
		int c = l - j + 1;
		int[][] matrix = new int[r][c];
		int rowIndex = 0;
		int columnIndex = 0;
		for (int row = i; row <= k; row++) {
			for (int col = j; col <= l; col++) {
				matrix[rowIndex][columnIndex] = array[row][col];
				columnIndex++;
			}
			columnIndex = 0;
			rowIndex++;
		}

		printMatrix(matrix, r, c);
		isMaxSubRectangle(matrix, leftUpper, rightLower, r, c);
	}

	private void isMaxSubRectangle(int[][] matrix, Coordinate leftUpper,
			Coordinate rightLower, int r, int c) {
		boolean isAllOnes = isMatrixAllOnes(matrix, r, c);
		if (isAllOnes) {
			int size = getMatrixSize(leftUpper, rightLower);
			if (size > maxRectangleSize) {
				maxLU = leftUpper;
				maxRL = rightLower;
				maxRectangleSize = size;
			}
		}
	}

	private int getMatrixSize(Coordinate leftUpper, Coordinate rightLower) {
		int r = rightLower.getX() - leftUpper.getX() + 1;
		int c = rightLower.getY() - leftUpper.getY() + 1;
		return r * c;
	}

	private boolean isMatrixAllOnes(int[][] matrix, int r, int c) {
		boolean isAllOnes = true;
		for (int i = 0; i < r; i++) {
			if (isAllOnes) {
				for (int j = 0; j < c; j++) {
					if (matrix[i][j] == 0) {
						isAllOnes = false;
						break;
					}
				}
			} else {
				break;
			}
		}
		return isAllOnes;
	}

	public void printMatrix(int[][] array, int rows, int columns) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println("");
		}
		System.out.println();
	}

	/*************************************************************************************************************************/
	/**
	 * ADDING DIRECTION
	 * 
	 */

	public static int currentBestArea;
	public static Coordinate currentBestLUCoordinate;
	public static Coordinate currentBestRLCoordinate;
	int[][] b = new int[][] { { 1, 1, 0, 0 }, { 1, 1, 0, 0 }, { 1, 1, 0, 1 },
			{ 0, 0, 0, 0 } };

	public void addingDirection() {

		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				// These two loops indicate for each and every element of the
				// matrix

				Coordinate lu = new Coordinate(x, y);
				if (b[lu.getX()][lu.getY()] == 1) {
					Coordinate rl = growOnes(lu);
					int area = getMatrixSize(lu, rl);
					if (area > currentBestArea) {
						currentBestArea = area;
						currentBestLUCoordinate = lu;
						currentBestRLCoordinate = rl;
					}
				}
			}
		}

	}

	/**
	 * This function returns the best rectangle (with all 1s) it can form for
	 * any damn incoming coordinate.
	 * 
	 * 
	 * @param lu
	 * @return The Right lower coordinate, that the coordinate forms the max
	 *         rectangle with
	 */
	private Coordinate growOnes(Coordinate lu) {
		Coordinate rl = new Coordinate(lu.getX(), lu.getY());
		int x = lu.getX();
		int y = lu.getY();
		while (y + 1 < rows && b[y + 1][x] != 0) {
			while (x + 1 < cols && b[y][x + 1] != 0 && b[y + 1][x + 1] != 0) {
				x = x + 1;
			}
			y = y + 1;
		}
		rl = new Coordinate(x, y);
		return rl;
	}
}

class Coordinate {
	int X;
	int Y;

	public Coordinate(int x, int y) {
		this.X = x;
		this.Y = y;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	@Override
	public String toString() {
		return "Coordinate [X=" + X + ", Y=" + Y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (X != other.X)
			return false;
		if (Y != other.Y)
			return false;
		return true;
	}

}
