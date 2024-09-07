import java.awt.Graphics;
import java.awt.Color;

public class Student {
    private final String name;
    private int xPos, yPos;
    private final int WIDTH = 85;
    private final int HEIGHT = 50;

    public Student(String studentName){
        xPos = 50;
        yPos = 50;
        name = studentName;
    }

    public void setX(int xPos){
        this.xPos = xPos;
    }

    public int getX(){
        return xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public int getY(){
        return yPos;
    }

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    public void paint(Graphics g){
        g.setColor(new Color(208, 182, 146));
        g.fillRect(xPos,yPos,WIDTH,HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(xPos,yPos,WIDTH,HEIGHT);

        // The space occupied by 1 character is 6 px wide by 8 px high
        int offsetX = (WIDTH - 6*name.length())/2;
        int offsetY = 30;
        g.drawString(name, xPos + offsetX, yPos + offsetY);
    }
}
