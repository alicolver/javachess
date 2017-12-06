package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;

public class Game {

  private Board gameboard;
  private ArrayList<Move> moveList;
  private int index;
  private Colour player;

  public Game(Board board) {
    this.gameboard = board;
    ArrayList<Move> moveList = new ArrayList<Move>();
    int index = 0;
    Colour player = Colour.WHITE;
  }

  public Colour getCurrentPlayer() {
    if (index % 2 == 0) {
      return Colour.WHITE;
    }
    return Colour.BLACK;
  }

  public Move getLastMove() {
    if (index == 0) {
      return null;
    }
    return (moveList.get(index - 1));
  }

  public void applyMove(Move move) {
    moveList.add(index, move);
    index++;
    gameboard.applyMove(move);
    if (player == Colour.WHITE) {
      player = Colour.BLACK;
    }
    player = Colour.WHITE;
  }

  public void unapplyMove(Move move) {
    moveList.remove(index);
    index--;
    gameboard.unapplyMove(move);
    if (player == Colour.WHITE) {
      player = Colour.BLACK;
    }
    player = Colour.WHITE;
  }

  public Boolean isFinished() {
    Boolean whites = false;
    Boolean blacks = false;
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        Colour condition = gameboard.getSquare(x, y).OccupiedBy();
        if (y == 0) {
          if (condition == Colour.WHITE) {
            return true;
          }
        }
        if (y == 7) {
          if (condition == Colour.BLACK) {
            return true;
          }
        }
        if (condition == Colour.WHITE) {
          whites = true;
        }
        if (condition == Colour.BLACK) {
          blacks = true;
        }
      }
    }
    return (whites && blacks);
  }

  /*public Boolean isStalemate() {
    return (Player.getAllValidMoves.length == 0);
  }*/


  public Colour getGameResult() {
    if (isFinished()) {
      return player;
    }
    /*if (isStalemate()) {
      return Colour.NONE;
    }*/
    return null;
  }

  public Move parseMove(String san) {
    String moveType = san.charAt(2) + "";
    Square from = new Square(Character.getNumericValue(getInt(san.charAt(0))), Character.getNumericValue(san.charAt(1))-1);
    Square to = new Square(Character.getNumericValue(getInt(san.charAt(3))), Character.getNumericValue(san.charAt(4))-1);
    if (moveType == "-") {
      return new Move(from, to, false, false);
    }
    if (gameboard.getSquare(to.getX(), to.getY()).OccupiedBy() == Colour.NONE) {
      return new Move(from, to, false, true);
    }
    return new Move(from, to, true, false);
  }

  private int getInt(char xRef) {
    return xRef - 'a';
  }

}


