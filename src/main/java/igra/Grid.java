package igra;

public class Grid {

    private int rows;
    private int columns;
    private int[][] matrix;

    public Grid() {

    }

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initGrid();
    }

    public void initGrid() {
        this.matrix = new int[rows][columns];
        for (var r = 0; r < this.rows; r++) {
            for (var c = 0; c < this.columns; c++) {
                this.matrix[r][c] = GridPosition.EMPTY.ordinal();
            }
        }
    }

    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getColumnsCount() {
        return this.columns;
    }

    public int palcePieces(int column, GridPosition piece) {
        if (column < 0 || column > this.columns) {
            throw new Error("Invalid column");
        }
        if (piece == GridPosition.EMPTY) {
            throw new Error("Invalid piece");
        }
        for (var r = this.rows - 1; r >= 0; r--) {
            if (matrix[r][column] == GridPosition.EMPTY.ordinal()) {
                this.matrix[r][column] = piece.ordinal();
                return r;
            }
        }
        return -1;
    }

    public boolean checkWin(int connectN, int row, int col, GridPosition piece) {
        // horizontal
        int count = 0;
        for (var c = 0; c < this.columns; c++) {
            if (this.matrix[row][c] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // vertical
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            if (this.matrix[r][col] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // diagonal
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            int d = row + col - r;
            if (d >= 0 && d < this.columns && this.matrix[r][d] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        // anti-diagonal
        count = 0;
        for (var r = 0; r < this.rows; r++) {
            int ad = col - row + r;
            if (ad >= 0 && ad < this.columns && this.matrix[r][ad] == piece.ordinal()) {
                count++;
            } else {
                count = 0;
            }
            if (count == connectN) {
                return true;
            }
        }
        return false;
    }
}
