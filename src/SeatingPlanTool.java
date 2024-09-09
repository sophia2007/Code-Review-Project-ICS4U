import javax.swing.*;
import java.awt.*;


public class SeatingPlanTool extends JFrame{
    private JPanel topPanel, bottomPanel;

    private JSplitPane splitPane;
    private JScrollPane scrollPane;
    private JTextArea studentAddedTextArea;

    public void init(String nameOfClassRoom){
        this.setTitle(nameOfClassRoom);

        // create a JSplitPane that shows a top and bottom component
        splitPane = new JSplitPane();
        topPanel = new paintPanel(); // top component
        bottomPanel = new JPanel(); // bottom component

        // in bottom component:
        scrollPane = new JScrollPane(); // allow text to be scrollable
        studentAddedTextArea = new JTextArea(); // allow text to be displayed

        createAndShowGUI();
    }

    public void createAndShowGUI(){
        // set the size and default layout of the window
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setForeground(Color.GRAY);

        // the contentPane is the container that holds all of our components
        this.getContentPane().setLayout(new GridLayout()); // default GridLayout is just a grid with one row and one column
        this.getContentPane().add(splitPane); // splitPane is the only component added to the window itself

        // configuring splitPane
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window verticaly
        splitPane.setDividerLocation(200);                    // the initial position of the divider is 200 (our window is 400 pixels high)
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);

        // topPanel's configuration is done in paintPanel
        // bottomPanel's configuration:
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS)); // BoxLayout.Y_AXIS will arrange the content vertically
        bottomPanel.add(scrollPane);                         // add the scrollPane first, making it on the top
        scrollPane.setViewportView(studentAddedTextArea);    // make the scrollPane scrolls studentAddedTextArea specifically
        bottomPanel.add(new inputPanel());                   // the inputPanel is added second, so it under the studentAddedTextArea

        this.pack(); // pack() applies all the layout and sizes that has been set before they are displayed
    }
}
