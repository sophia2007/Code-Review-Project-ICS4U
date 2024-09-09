import javax.swing.*;
import java.awt.*;

public class inputPanel extends JPanel {
    private final JTextField enterStudentsTextField = new JTextField();
    private final JButton enterButton = new JButton();

    public inputPanel(){
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));    // set the max height to 75 and the max width to (almost) unlimited
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));        // X_Axis will arrange the content horizontally
        this.add(enterStudentsTextField);    // textField added first and so is on the left
        this.add(enterButton);               // button is added second and so is on the right
    }
}
