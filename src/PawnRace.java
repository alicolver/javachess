package src;

import java.util.Random;
import java.util.Objects;

public class PawnRace {

  public static void main(String[] args) {

    Player player1 = null;
    Player player2 = null;

    int randomWhiteGap = new Random().nextInt(7);
    int randomBlackGap = new Random().nextInt(7);

    char whiteGap = (char) ('a' + randomWhiteGap);
    char blackGap = (char) ('a' + randomBlackGap);

    Board board = new Board(whiteGap, blackGap);
    Game game = new Game(board);

    System.out.println("CHESS PAWN RACE");
    System.out.println(
        "Player v Player or Player v Computer or Computer v Computer?");
    System.out.print("Enter PvP or PvC or CvC");

    String humanOrComp = IOUtil.readString();

    board.display();

    if (Objects.equals("PvP", humanOrComp)) {
      player1 = new Player(game, board, Colour.WHITE, false);
      player2 = new Player(game, board, Colour.BLACK, false);

      System.out.println("Player 1 - You are white!");
      System.out.println("Player 2 - You are black!");

      do {
        if (player1.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        System.out.print("Please Input your move -> ");
        String move1 = IOUtil.readString();
        Move parsedMove1 = game.parseMove(move1);
        board.applyMove(parsedMove1);
        if (player2.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        System.out.print("Please Input your move -> ");
        String move2 = IOUtil.readString();
        Move parsedMove2 = game.parseMove(move2);
        board.applyMove(parsedMove2);
        board.display();
      } while (game.isFinished() == false);

    }

    else if (Objects.equals("PvC", humanOrComp)) {
      player1 = new Player(game, board, Colour.WHITE, false);
      player2 = new Player(game, board, Colour.BLACK, true);

      System.out.println("You are white!");

      do {
        if (player1.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        System.out.print("Please Input your move -> ");
        String move = IOUtil.readString();
        Move parsedMove = game.parseMove(move);
        board.applyMove(parsedMove);
        if (player2.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        player2.makeMove();
        board.display();
      } while (game.isFinished() == false);
    }

    else if (Objects.equals("CvC", humanOrComp)) {
      player1 = new Player(game, board, Colour.WHITE, true);
      player2 = new Player(game, board, Colour.BLACK, true);

      do {
        if (player1.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        player1.makeMove();
        board.display();
        if (game.isFinished() == true) {
          System.out.println(Colour.WHITE + " wins!");
          System.exit(0);
        }
        if (player2.getAllValidMoves().length == 0) {
          System.out.println("STALEMATE");
          System.exit(0);
        }
        player2.makeMove();
        board.display();
      } while (game.isFinished() == false);

      System.out.println(Colour.BLACK + " wins!");

    }

  }

}