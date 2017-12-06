package src;

import java.util.List;
import java.util.ArrayList;

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
    return (opponent.computerPlayer);
  }

  public Square[] getAllPawns() {
    ArrayList<Square> allPawns = new ArrayList<Square>();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        if (board.getSquare(x, y).OccupiedBy() == colour) {
          allPawns.add(board.getSquare(x, y));
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
    return new Move[10];
  }

}