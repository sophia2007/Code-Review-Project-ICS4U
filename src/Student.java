import java.awt.*;

public class Student {
    private final String name;
    private int xPos, yPos;
    private final int WIDTH = 85;
    private final int HEIGHT = 50;
    private boolean active;

    private final Polygon shape;

    public Student(String studentName){
        xPos = 50;
        yPos = 50;
        name = studentName;

        shape = new Polygon(); // make the rectangle that represents the student
        shape.addPoint(0, 0);
        shape.addPoint(0, HEIGHT);
        shape.addPoint(WIDTH, HEIGHT);
        shape.addPoint(WIDTH, 0);

        shape.translate(xPos, yPos); // moves shape to the initial position
    }

    // GET and SET Methods
    public String getName(){return name;}
    public boolean isClicked(){return active;}
    public void hasBeenClicked(){active = true;}
    public void hasBeenUnclicked(){active = false;}
    public Polygon getShape(){return shape;}
    public String toString(){return name;}

    public void paint(Graphics g){
        if (isClicked()){ // if the student is selected, it's colored in green
            g.setColor(new Color(133, 220, 46));
        } else { // if not, the student is colored in brown
            g.setColor(new Color(208, 182, 146));
        }
        g.drawPolygon(shape); // draws the outline of the rectangular Student shape
        g.fillPolygon(shape); // fills the rectangle with the same color

        // The space occupied by 1 character is 6 px wide
        // Depending on the length of name of the student,
        // offsetX and offsetY are supposed to position the String name roughly in the middle of the rectangle
        int offsetX = (WIDTH - 6*name.length())/2;
        int offsetY = 30;
        g.setColor(new Color(0,0,0));
        g.drawString(name, xPos + offsetX, yPos + offsetY);
    }

    // moves the rectangular student to the coordinate x and y
    public void moveStudent(int x, int y){
        xPos = x;
        yPos = y;

        shape.xpoints[0] = xPos;
        shape.xpoints[1] = xPos;
        shape.xpoints[2] = xPos + WIDTH;
        shape.xpoints[3] = xPos + WIDTH;

        shape.ypoints[0] = yPos;
        shape.ypoints[1] = yPos + HEIGHT;
        shape.ypoints[2] = yPos + HEIGHT;
        shape.ypoints[3] = yPos;
    }
}
