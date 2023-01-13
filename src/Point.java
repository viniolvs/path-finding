import java.util.LinkedList;

public class Point {
  private int x;
  private int y;
  private double fCost;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
    fCost = 0;
  }

  public Point() {
    x = 0;
    y = 0;
    fCost = 0;
  }

  public LinkedList<Point> getNeighbors() {
    return getNeighbors(this.x, this.y);
  }

  public static LinkedList<Point> getNeighbors(int x, int y) {
    // Adiciona todas as possibilidades de movimentaçao em uma Lista
    LinkedList<Point> next_pos = new LinkedList<Point>();
    for (int i = 0; i < 4; i++) {
      Point xy = new Point(x, y);
      switch (i) {
        // TOP
        case 0:
          xy.setX(x - 1);
          break;
        // RIGHT
        case 1:
          xy.setY(y + 1);
          break;
        // BOTTOM
        case 2:
          xy.setX(x + 1);
          break;
        // LEFT
        case 3:
          xy.setY(y - 1);
          break;
      }
      next_pos.push(xy);
    }
    return next_pos;
  }

  public static LinkedList<Point> getNeighbors(Point p) {
    return getNeighbors(p.getX(), p.getY());
  }

  public void setfCost(double fCost) {
    this.fCost = fCost;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public double getfCost() {
    return fCost;
  }
}
