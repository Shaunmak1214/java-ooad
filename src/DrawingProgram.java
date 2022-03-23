import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.lang.Math;

public class DrawingProgram extends JFrame implements MouseMotionListener, MouseListener, ChangeListener, ActionListener {
    private Point mousePnt = new Point();
    public static Color penColor = new Color(0,0,0);
    private JSlider penSize = new JSlider(JSlider.HORIZONTAL,1,30,4);
    public static int pen = 4;
    public int currentX1, currentY1, currentX2, currentY2, clickCount;

    public DrawingProgram() {
        super("Painter");
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel jp = new JPanel();

        JButton circleBtn = new JButton();
        JLabel circleBtnLabel = new JLabel();
        //circleBtn.setPreferredSize(new Dimension(30, 30));
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
        //System.out.println("here");
        if(me.getModifiers()==MouseEvent.BUTTON3_MASK){
            penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
        }
    }

    public void mouseEntered(MouseEvent me){ }
    public void mouseExited(MouseEvent me){ }
    public void mousePressed(MouseEvent me){
        clickCount++;
        Graphics g = getGraphics();
        if(clickCount==1){
            currentX1 = me.getX();
            currentY1 = me.getY();
            System.out.println("currentX1: " + currentX1);
            System.out.println("currentY1: " + currentY1);
            g.fillOval(currentX1,currentY1,2,2);
        }
        else if(clickCount==2){
            currentX2 = me.getX();
            currentY2 = me.getY();
            System.out.println("currentX2: " + currentX2);
            System.out.println("currentY2: " + currentY2);
            g.fillOval(currentX2,currentY2,2,2);
            //Graphics g = getGraphics();
            int circWidth = (int)Math.sqrt(Math.pow(currentX2-currentX1,2)+Math.pow(currentY2-currentY1,2));
            int r = circWidth/2;
            System.out.println("circWidth: " + circWidth);
            int circMidptX = (int)((currentX1+currentX2)/2);
            int circMidptY = (int)((currentY1+currentY2)/2);
            System.out.println("circMidptX: " + circMidptX);
            System.out.println("circMidptY: " + circMidptY);
            System.out.println("width: " + circWidth);
            g.drawOval(circMidptX-r,circMidptY-r, circWidth, circWidth);
            clickCount=0;
        }
    }
    public void mouseReleased(MouseEvent me){ }

    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
            pen = (int)source.getValue();
        }
    }

    public void paint(Graphics g){
        g.setColor(penColor);
        g.fillOval(mousePnt.x,mousePnt.y,pen,pen);
        //g.drawOval(20,20,100,100);
    }

    public static void main(String[] a){
        new DrawingProgram();
    }
}
