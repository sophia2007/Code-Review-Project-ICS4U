import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

public class GUIPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    Student student = new Student("Sophia");

    public GUIPanel(){
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
        moveSquare(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        moveSquare(e.getX(), e.getY());
    }

    private void moveSquare(int x, int y) {
        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int CURR_X = student.getX();
        final int CURR_Y = student.getY();
        final int CURR_W = student.getWidth();
        final int CURR_H = student.getHeight();
        final int OFFSET = 1;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background
            // over the old square location.
            repaint(CURR_X,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            student.setX(x);
            student.setY(y);

            // Repaint the square at the new location.
            repaint(student.getX(), student.getY(),
                    student.getWidth()+OFFSET,
                    student.getHeight()+OFFSET);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] possibleValues = { "First", "Second", "Third" };

        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        student.paint(g);
    }

    // Unused methods from MouseListener and MouseMotionListener:
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
