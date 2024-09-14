import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static Point[] getTopLeftCorners(
          Block container,
          Block[] children
  ) {
    double newY = 0;
    double newX = 0;
    double columnWidth = 0;
    Point[] topLeftCorners = new Point[children.length];
    double containerWidth = container.width;
    double containerHeight = container.height;

    for (int c = 0; c < children.length; c++) {
      Block child = children[c];
      double childWidth = child.width;
      double childHeight = child.height;

      if (newY + childHeight <= containerHeight) {
        if (newX + childWidth <= containerWidth) {
          topLeftCorners[c] = new Point(newX, newY);
          newY += childHeight;

          if (childWidth > columnWidth) {
            columnWidth = childWidth;
          }
        } else {
          throwError();
        }
      } else {
        newY = 0;
        newX += columnWidth;
        columnWidth = 0;

        if (childHeight <= containerHeight) {
          if (newX + childWidth <= containerWidth) {
            topLeftCorners[c] = new Point(newX, newY);
            newY += childHeight;

            if (childWidth > columnWidth) {
              columnWidth = childWidth;
            }
          } else {
            throwError();
          }
        } else {
          throwError();
        }
      }
    }

    return topLeftCorners;
  }

  private static void throwError() {
    String errorMessage = "Child is out of container.";

    throw new IllegalArgumentException(errorMessage);
  }

  public static void main(String[] args) {
    Block parent = new Block(20, 10);
    Block[] children = new Block[5];
    children[0] = new Block(2.5, 2.5);
    children[1] = new Block(5, 2.5);
    children[2] = new Block(4.5, 1.5);
    children[3] = new Block(3, 5);
    children[4] = new Block(4.5, 1.5);
    System.out.println(Arrays.toString(getTopLeftCorners(parent, children)));
  }
}