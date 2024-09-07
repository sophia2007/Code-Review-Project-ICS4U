import javax.swing.*;
import java.awt.Color;


public class SeatingPlanTool{
    JFrame mainFrame;

    public void init(String nameOfClassRoom){
        mainFrame = new JFrame(nameOfClassRoom);
        createAndShowGUI();
    }

    public void createAndShowGUI(){
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setForeground(Color.GRAY);

        mainFrame.add(new GUIPanel());
        mainFrame.pack();

    }
}
