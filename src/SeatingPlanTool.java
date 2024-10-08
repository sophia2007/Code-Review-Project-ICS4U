import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatingPlanTool extends JFrame implements ActionListener{
    private JPanel topPanel, bottomPanel, inputPanel;
    private JSplitPane splitPane;

    private JScrollPane scrollPane;

    public static DefaultListModel<Student> listModel;
    private JList<Student> studentList;
    private JTextField textField;
    private JButton deleteBtn;

    public void init(String nameOfClassRoom){
        this.setTitle(nameOfClassRoom);

        // create a JSplitPane that shows a top and bottom component
        splitPane = new JSplitPane();
        topPanel = new paintPanel(); // top component
        bottomPanel = new JPanel(); // bottom component

        // in bottom component:
        listModel = new DefaultListModel<>();
        studentList = new JList<>(listModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentList.setSelectedIndex(0);
        studentList.setVisibleRowCount(5);             // Set the list to have 10 rows visible at a time
        scrollPane = new JScrollPane(studentList);     // allow the list to be scrollable

        inputPanel = new JPanel();              // separate panel to form the layout of the input section
        textField = new JTextField();           // allow user to add the student name
        textField.addActionListener(this);
        textField.setActionCommand("add");
        deleteBtn = new JButton("Delete"); // allow user to delete any student name
        deleteBtn.addActionListener(this);
        deleteBtn.setActionCommand("delete");

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
        this.getContentPane().add(splitPane);              // splitPane is the only component added to the window itself

        // configuring splitPane
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window vertically
        splitPane.setDividerLocation(600);                    // the initial position of the divider
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);

        // topPanel's configuration is done in paintPanel
        // bottomPanel's configuration:
        bottomPanel.setLayout(new BoxLayout(bottomPanel,
                BoxLayout.Y_AXIS));                         // BoxLayout.Y_AXIS will arrange the content vertically
        JLabel label = new JLabel("Your Students");
        //label.setMinimumSize(new Dimension(900, 20));
        bottomPanel.add(label);  // this JLabel is added first, making it on the top
        bottomPanel.add(scrollPane);
        bottomPanel.add(inputPanel);

        // configuring inputPanel
        inputPanel.setMaximumSize(new Dimension(900, 75));    // set the max height to 75 and the max width to (almost) unlimited
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));         // X_Axis will arrange the content horizontally
        inputPanel.add(textField);                                                 // textField added first and so is on the left
        textField.setMinimumSize(new Dimension(100, 30));
        inputPanel.add(deleteBtn);                                                 // button is added second and so is on the right

        this.pack(); // pack() applies all the layout and sizes that has been set before they are displayed
    }

    // takes care of what happens after the user interact with the textfield or the delete button
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("add")){ // triggers when the user presses ENTER in the text field
            String text = textField.getText();
            if (!hasName(text)){                         // if the newly added student name isn't always in the list
                listModel.addElement(new Student(text)); // add the student
                textField.setText("");                   // clears the text field
            } else { // tells the user they can't add two identical names
                JOptionPane.showOptionDialog(new JFrame(), "That student is already added!" +
                        "\n(If there's two students with the same name, use their initials.)", "Oops!",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
            }
        }

        if (e.getActionCommand().equals("delete")){
            listModel.remove(studentList.getSelectedIndex());
        }

        studentList.ensureIndexIsVisible(listModel.size());
        studentList.repaint();
        topPanel.repaint();
    }

    private boolean hasName(String newName){
        for (int i = 0; i < listModel.getSize(); i++){
            Student s = listModel.elementAt(i);
            if (s.getName().equals(newName)){ // finds the name of the task whose asteroid got destroyed
                return true;
            }
        }
        return false;
    }
}
