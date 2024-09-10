import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class paintPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    public paintPanel(){
        setBackground(new Color(50, 72, 56));
        setBorder(BorderFactory.createLineBorder(new Color(117, 88, 38), 7));

        JButton settingsBtn = new JButton("Settings");
        settingsBtn.addActionListener(this);
        add(settingsBtn);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Dimension getPreferredSize(){
        return new Dimension(900, 600);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (s.isClicked()){
                moveStudent(s, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (s.isClicked()){
                moveStudent(s, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (
                    e.getX() > s.getX() &&
                    e.getX() < (s.getX() + s.getWidth()) &&
                    e.getY() > s.getY() &&
                    e.getY() < (s.getY() + s.getHeight())
            ){
                s.hasBeenClicked();
            } else {
                s.hasBeenUnclicked();
            }
        }
    }

    private void moveStudent(Student student, int x, int y) {
        // Student position prior to being moved, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = student.getX();
        final int CURR_Y = student.getY();
        final int CURR_W = student.getWidth();
        final int CURR_H = student.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) { // If true, the student is moving
            // Repaint background over the old student location.
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            student.setX(x);
            student.setY(y);

            // Repaint the student at the new location.
            repaint(student.getX(), student.getY(),
                    student.getWidth()+OFFSET,
                    student.getHeight()+OFFSET);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] roomLayout = { "Singles", "Duos", "Cubes", "Hollow Square" };

        Object layout = JOptionPane.showInputDialog(null,
                "What is the layout of your classroom's seating?", "Classroom Layout",
                JOptionPane.INFORMATION_MESSAGE, null,
                roomLayout, roomLayout[0]);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            System.out.println("Painted "+s);
            s.paint(g);
        }
    }

    // Unused methods from MouseListener and MouseMotionListener:
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}

}
