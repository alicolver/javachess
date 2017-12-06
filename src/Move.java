package src;

public class Move {

  private Square oldPos;
  private Square newPos;
  private boolean isCapture;
  private boolean isEnPassantCapture;

  public Move (Square from, Square to, boolean isCapture, boolean isEnPassantCapture) {
    this.oldPos = from;
    this.newPos = to;
    this.isCapture = isCapture;
    this.isEnPassantCapture = isEnPassantCapture;
  }

  public Square getFrom() {
    return oldPos;
  }

  public Square getTo() {
    return newPos;
  }

  public Boolean isCapture() {
    return isCapture;
  }

  public Boolean isEnPassantCapture() {
    return isEnPassantCapture;
  }

  public String getSAN() {
    String iSAN = oldPos.getAlpha() + Integer.toString(oldPos.getY()+1);
    String nSAN = newPos.getAlpha() + Integer.toString(newPos.getY()+1);
    if (!isCapture && !isEnPassantCapture) {
      return iSAN + '-' + nSAN;
    }
    return iSAN + 'x' + nSAN;
  }
}