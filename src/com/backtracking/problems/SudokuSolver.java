package com.backtracking.problems;

/**
 * 
 * @author neeraj
 *
 */
public class SudokuSolver {
	private static final Integer UNASSIGNED = 0;
	private static final Integer ROWS = 9;
	private static final Integer COLS = 9;
	private static final Integer SMALL_GRID_ROW = 3;
	private static final Integer SMALL_GRID_COL = 3;
	private static final Integer NUM_RANGE = 9;

	public static void main(String[] args) {
		int[][] grid = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

		System.out
				.println("**************************************************");
		printGrid(grid);

		System.out
				.println("**************************************************");

		if (solveSudoku(grid)) {
			System.out.println("Soduku is solvable");
			printGrid(grid);
		} else {
			System.out.println("Soduku is un - solvable");
		}
	}

	public static boolean solveSudoku(int[][] grid) {
		boolean isSolvable = false;
		Cell cell = new Cell(-1, -1);
		if (!findUnAssignedLocation(grid, cell)) {
			// all cells are filled
			return true;
		}

		// for all the numbers
		for (int num = 1; num <= NUM_RANGE; num++) {
			if (safeToPlace(grid, cell, num)) {
				grid[cell.getI()][cell.getJ()] = num;
				if (solveSudoku(grid)) {
					return true;
				} else {
					grid[cell.getI()][cell.getJ()] = UNASSIGNED;
				}
			}
		}

		return isSolvable;
	}

	public static boolean findUnAssignedLocation(int[][] grid, Cell cell) {
		boolean found = false;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (grid[i][j] == UNASSIGNED) {
					cell.setI(i);
					cell.setJ(j);
					found = true;
					break;
				}
			}
		}
		return found;
	}

	public static boolean isUsedInRow(int[][] grid, int row, int num) {
		boolean isUsed = false;
		for (int col = 0; col < COLS; col++) {
			if (grid[row][col] == num) {
				isUsed = true;
				break;
			}
		}
		return isUsed;
	}

	public static boolean isUsedInCol(int[][] grid, int col, int num) {
		boolean isUsed = false;
		for (int row = 0; row < ROWS; row++) {
			if (grid[row][col] == num) {
				isUsed = true;
				break;
			}
		}
		return isUsed;
	}

	public static boolean isUsedInSmallGrid(int[][] grid, int row, int col,
			int num) {
		boolean isUsed = false;
		int startingRow = row - (row % 3);
		int startingCol = col - (col % 3);
		for (int i = 0; i < SMALL_GRID_ROW; i++) {
			for (int j = 0; j < SMALL_GRID_COL; j++) {
				if (grid[i + startingRow][j + startingCol] == num) {
					isUsed = true;
					break;
				}
			}
			if (isUsed) {
				break;
			}
		}

		return isUsed;
	}

	public static boolean safeToPlace(int[][] grid, Cell cell, int num) {
		boolean isSafe = false;

		if (!isUsedInCol(grid, cell.getJ(), num)
				&& !isUsedInRow(grid, cell.getI(), num)
				&& !isUsedInSmallGrid(grid, cell.getI(), cell.getJ(), num)) {
			isSafe = true;
		}
		return isSafe;

	}

	public static void printGrid(int[][] grid) {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				System.out.print(grid[row][col] + " ");
			}
			System.out.println("");
		}
	}

}

class Cell {
	int i = -1;
	int j = -1;

	public Cell(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

}