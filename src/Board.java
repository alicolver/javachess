package src;

public class Board {

  private int dim = 8;
  private Square[][] board = new Square[dim][dim];

  public Board(char wG, char bG) {
    for (int y = 0; y < dim; y++) {
      for (int x = 0; x < dim; x++) {
        if (y == 1 && x != (wG - 'a')) {
          board[y][x] = new Square(x, y);
          board[y][x].setOccupier(Colour.WHITE);
        }
        else if (y == 6 & x != (bG - 'a')) {
          board[y][x] = new Square(x, y);
          board[y][x].setOccupier(Colour.BLACK);
        }
        else {
          board[y][x] = new Square(x, y);
        }
      }
    }
  }

  public Square getSquare(int x, int y) {
    return board[x][y];
  }

  public void applyMove(Move move) {
    board[(move.getTo().getX())][move.getTo().getY()].setOccupier(move.getFrom().OccupiedBy());
    board[move.getFrom().getX()][move.getFrom().getY()].setOccupier(Colour.NONE);
    if (move.isEnPassantCapture()) {
      if (move.getFrom().OccupiedBy() == Colour.BLACK) {
        board[move.getTo().getX()][move.getTo().getY() + 1].setOccupier(Colour.NONE);
      } else {
        board[move.getTo().getX()][move.getTo().getY() - 1].setOccupier(Colour.NONE);
      }
    }
  }

  public void unapplyMove(Move move) {
    if (move.isEnPassantCapture()) {
      if (move.getFrom().OccupiedBy() == Colour.BLACK) {
        board[move.getTo().getX()][move.getTo().getY() + 1].setOccupier(Colour.WHITE);
      } else {
        board[move.getTo().getX()][move.getTo().getY() - 1].setOccupier(Colour.BLACK);
      }
    }
    board[move.getFrom().getX()][move.getFrom().getY()].setOccupier(move.getFrom().OccupiedBy());
    board[move.getTo().getX()][move.getTo().getY()].setOccupier(Colour.NONE);
  }

  public void display() {

    System.out.println("   A B C D E F G H");
    System.out.println("");

    for (int y = 0; y < 8; y++) {
      System.out.print((y+1) + "  ");
      for (int x = 0; x < 8; x++) {
        if (getSquare(y, x).OccupiedBy() == Colour.NONE) {
          System.out.print(". ");
        }
        else if (getSquare(y, x).OccupiedBy() == Colour.WHITE) {
          System.out.print((char) 9823 + " ");
        } else {
          System.out.print((char) 9817 + " ");
        }
      }
      System.out.println(" " + (y + 1));
    }
    System.out.println("");
    System.out.print("   A B C D E F G H");
  }

}