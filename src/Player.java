package src;

import java.util.ArrayList;
import java.util.Random;

public class Player {

  private Game game;
  private Board board;
  private Colour colour;
  private Player opponent;
  private boolean computerPlayer;

  public Player(Game game, Board board, Colour colour,
      boolean isComputerPlayer) {
    this.game = game;
    this.board = board;
    this.colour = colour;
    this.computerPlayer = isComputerPlayer;
  }

  public void setOpponent(Player opponent) {
    this.opponent = opponent;
  }

  public Colour getColor() {
    colour = this.colour;
    if (colour == Colour.NONE) {
      return null;
    }
    return colour;
  }

  public boolean isComputerPlayer() {
    return (computerPlayer);
  }

  public Square[] getAllPawns() {
    ArrayList<Square> allPawns = new ArrayList<Square>();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (board.getSquare(y, x).OccupiedBy() == colour) {
          allPawns.add(board.getSquare(y, x));
        }
      }
    }
    Square[] arrayAllPawns = new Square[allPawns.size()];
    for (int i = 0; i < allPawns.size(); i++) {
      arrayAllPawns[i] = allPawns.get(i);
    }
    return arrayAllPawns;
  }

  public Move[] getAllValidMoves() {
    ArrayList<Move> validMoves = new ArrayList<Move>();
    Square[] playersPawns = getAllPawns();
    int change = colour == Colour.WHITE ? 1 : -1;
    int y1 = colour == Colour.WHITE ? 1 : 6;
    int x1 = colour == Colour.WHITE ? 0 : 7;
    int y2 = colour == Colour.WHITE ? 7 : 0;
    int enPass = colour == Colour.WHITE ? 4 : 5;
    Colour opposite = colour == Colour.WHITE ? Colour.BLACK : Colour.WHITE;

    for (Square pawn : playersPawns) {
      Square currentSquare = board.getSquare(pawn.getX(),pawn.getY());
      int x = currentSquare.getX();
      int y = currentSquare.getY();

      if (currentSquare.getY() == y1) {
        Square two = board.getSquare(x, y + (2 * change));
        if (two.OccupiedBy() == Colour.NONE) {
          validMoves.add(new Move(currentSquare, two, false, false));
        }
      }

      if (currentSquare.getY() != y2) {
        Square one = board.getSquare(x, y + change);
        if (one.OccupiedBy() == Colour.NONE) {
        validMoves.add(new Move(currentSquare, one, false, false));
        }
      }

      if (currentSquare.getX() != x1) {
        if (currentSquare.getY() != y2) {
        Square rightFront = board.getSquare(x - change,y + change);
        Square right = board.getSquare(x - change, y);
          if (rightFront.OccupiedBy() == opposite) {
          validMoves.add(new Move(currentSquare, rightFront, true, false));
          }
          if (right.OccupiedBy() == opposite
            && rightFront.OccupiedBy() == Colour.NONE
            && currentSquare.getY() == enPass) {
            validMoves.add(new Move(currentSquare, rightFront, false, true));
          }
        }
      }

      if (currentSquare.getY() != y2) {
        if (currentSquare.getX() != y2) {
          Square leftFront = board.getSquare(x + change, y + change);
          Square left = board.getSquare(x + change, y);

          if (leftFront.OccupiedBy() == opposite) {
            validMoves.add(new Move(currentSquare, leftFront, true, false));
          }
          if (left.OccupiedBy() == opposite
              && leftFront.OccupiedBy() == Colour.NONE
              && currentSquare.getY() == enPass) {
            validMoves.add(new Move(currentSquare, leftFront, false, true));
          }
        }
      }
    }

    Move[] arrayValidMoves = new Move[validMoves.size()];
    for (int element = 0; element < validMoves.size(); element++) {
      arrayValidMoves[element] = validMoves.get(element);
    }

    return arrayValidMoves;
  }

  public boolean isPassedPawn(Square square) {
    Square[] allPawns = getAllPawns();

    for (int i = 0; i < allPawns.length; i++) {
      if (colour == Colour.WHITE && allPawns[i].getY() < square.getY()
          && allPawns[i].getX() == square.getX()) {
        return true;
      }
      if (colour == Colour.BLACK && allPawns[i].getY() > square.getY()
          && allPawns[i].getX() == square.getX()) {
        return true;
      }
    }
    return false;
  }

  public void makeMove() {
    if (isComputerPlayer() == true) {
      int n = getAllValidMoves().length - 1;
      int randomMove = new Random().nextInt(n);
      board.applyMove(getAllValidMoves()[randomMove]);
    }
  }

}