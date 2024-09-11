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

        shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, HEIGHT);
        shape.addPoint(WIDTH, HEIGHT);
        shape.addPoint(WIDTH, 0);

        shape.translate(xPos, yPos);
    }

    public String getName(){return name;}
    public boolean isClicked(){return active;}
    public void hasBeenClicked(){active = true;}
    public void hasBeenUnclicked(){active = false;}

    public Polygon getShape(){
        return shape;
    }

    public String toString(){return name;}

    public void paint(Graphics g){
        if (isClicked()){
            g.setColor(new Color(133, 220, 46));
        } else {
            g.setColor(new Color(208, 182, 146));
        }
        g.drawPolygon(shape);
        g.fillPolygon(shape);

        // The space occupied by 1 character is 6 px wide
        int offsetX = (WIDTH - 6*name.length())/2;
        int offsetY = 30;
        g.setColor(new Color(0,0,0));
        g.drawString(name, xPos + offsetX, yPos + offsetY);
    }

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
