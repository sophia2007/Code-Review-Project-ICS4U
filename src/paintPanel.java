import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class paintPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
    Object chosenLayout = "";
    Image[] layouts = new Image[4];
    String[] layoutNames = { "Singles", "Duos", "Cubes", "Hollow Square" };

    public paintPanel(){
        setBackground(new Color(50, 72, 56));
        setBorder(BorderFactory.createLineBorder(new Color(117, 88, 38), 7));

        JButton settingsBtn = new JButton("Settings");
        settingsBtn.addActionListener(this);
        add(settingsBtn);

        addMouseListener(this);
        addMouseMotionListener(this);

        try{
            layouts[0] = ImageIO.read(new File("./layouts/singles.png"));
            layouts[1] = ImageIO.read(new File("./layouts/doubles.png"));
            layouts[2] = ImageIO.read(new File("./layouts/cubes.png"));
            layouts[3] = ImageIO.read(new File("./layouts/hollow square.png"));
        } catch (IOException e) {
            // Catches the error thrown if and when any of the images files can't be found
            System.out.println("Failed to find image");
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(900, 600);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (s.getShape().contains(e.getX(), e.getY()) && s.isClicked()){
                repaint();
                s.moveStudent(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (s.isClicked()){
                repaint();
                s.moveStudent(e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
            if (s.getShape().contains(e.getX(), e.getY())) {
                s.hasBeenClicked();
            } else {
                s.hasBeenUnclicked();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chosenLayout = JOptionPane.showInputDialog(null,
                "What is the layout of your classroom's seating?", "Classroom Layout",
                JOptionPane.INFORMATION_MESSAGE, null,
                layoutNames, layoutNames[0]);
        SeatingPlanTool.listModel.clear();
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if (!chosenLayout.equals("")){
            int i = Arrays.asList(layoutNames).indexOf(chosenLayout);
            g.drawImage(layouts[i], 0, 0, this);
        }

        for (int i = 0; i < SeatingPlanTool.listModel.getSize(); i++){
            Student s = SeatingPlanTool.listModel.getElementAt(i);
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
