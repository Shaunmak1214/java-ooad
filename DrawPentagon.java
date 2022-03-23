import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.lang.Math;

public class DrawPentagon extends JFrame implements MouseMotionListener, MouseListener, ChangeListener, ActionListener {
    private Point mousePnt = new Point();
    public static Color penColor = new Color(0,0,0);
    private JSlider penSize = new JSlider(JSlider.HORIZONTAL,1,30,4);
    public static int pen = 4;
    public int currentX, currentY;
    public int clickCount = 0;
    public int [] xPoints = new int[5];
    public int [] yPoints = new int[5];

    public DrawPentagon() {
        super("Painter");
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jp = new JPanel();

        JButton circleBtn = new JButton();
        JLabel circleBtnLabel = new JLabel();
        circleBtn.setSize(30,30);

        Image circleIcon;
        try {
            circleIcon = ImageIO.read(new File("assets/circle.png"));
            Image scaledIcon = circleIcon.getScaledInstance(30,30,Image.SCALE_SMOOTH);
            circleBtn.setIcon(new ImageIcon(scaledIcon));
        } catch (IOException e) {
            e.printStackTrace();
        }

        circleBtnLabel.setPreferredSize(new Dimension(200, 40));
        circleBtnLabel.add(circleBtn);
        toolbar.add(circleBtnLabel,BorderLayout.SOUTH);
        toolbar.add(new Label("Drag mouse to draw"));
        toolbar.add(penSize);
        this.add(toolbar,BorderLayout.SOUTH);
        this.add(jp,BorderLayout.CENTER);
        jp.addMouseMotionListener(this);
        jp.addMouseListener(this);
        penSize.addChangeListener(this);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent me){
        repaint();
    }

    public void mouseMoved(MouseEvent me){

    }

    public void mouseDragged(MouseEvent me){
        mousePnt = me.getPoint();
        repaint();
    }

    public void mouseClicked(MouseEvent me){
        if(me.getModifiers()==MouseEvent.BUTTON3_MASK){
            penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
        }
    }

    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){
      
      Graphics g = getGraphics();
      currentX = me.getX();
      currentY = me.getY();
      
      xPoints[clickCount] = currentX;
      yPoints[clickCount] = currentY;
      g.fillOval(currentX,currentY,2,2);

      clickCount++;

      if(clickCount > 4){
        System.out.println("Drawing pentagon");
        g.drawPolygon(xPoints, yPoints, 5);
      }
    }

    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
            pen = (int)source.getValue();
        }
    }

    public void paint(Graphics g){
        g.setColor(penColor);
        g.fillOval(mousePnt.x,mousePnt.y,pen,pen);
    }

    public static void main(String[] a){
        new DrawPentagon();
    }
}
