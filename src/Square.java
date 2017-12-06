package src;

public class Square {

  private int x;
  private int y;
  private Colour colour;

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
    this.colour = Colour.NONE;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public char getAlpha() {
    return ((char) ('a' + x));
  }

  public Colour OccupiedBy() {
    return colour;
  }

  public void setOccupier(Colour c) {
    colour = c;
  }


}