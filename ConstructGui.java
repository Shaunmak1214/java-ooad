import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ConstructGui {
    public static JButton createButton(String iconLocation) {
        JButton button = new JButton();
        button.setSize(50,50);

        try {
            Image shapeIcon;
            shapeIcon = ImageIO.read(new File(iconLocation));
            Image scaledIcon = shapeIcon.getScaledInstance(30,30,Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return button;
    }

    public static void createPopUp(String prompt) {
        if(prompt.equals("ErrorForXPlane"))
            JOptionPane.showMessageDialog(null, "The X dimensions of both points are parallel, please try again", "Error", JOptionPane.ERROR_MESSAGE);
        else if(prompt.equals("ErrorForYPlane")) 
            JOptionPane.showMessageDialog(null, "The Y dimensions of both points are parallel, please try again", "Error", JOptionPane.ERROR_MESSAGE);        
    }
}
