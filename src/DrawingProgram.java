import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Graphics;
import java.lang.Math;

public class DrawingProgram extends JFrame implements MouseMotionListener, MouseListener, ChangeListener {
    private Point mousePnt = new Point();
    public static Color penColor = new Color(0,0,0);
    private JSlider penSize = new JSlider(JSlider.HORIZONTAL,1,30,4);
    public static int pen = 4;
    public int currentX1, currentY1, currentX2, currentY2, clickCount;

    public int [] xPoints = new int[5];
    public int [] yPoints = new int[5];

    //Icon button
    public JButton rectBtn = new JButton();
    public JButton circleBtn = new JButton();
    public JButton triangleBtn = new JButton();
    public JButton pentagonBtn = new JButton();
    public JButton penBtn = new JButton();
    public JButton resetBtn = new JButton();
    public JButton setClrBtn = new JButton();

    //Button active status
    public boolean activeRectBtn=false, activeCircleBtn=false,
            activeTriangleBtn=false, activePentagonBtn=false, activePen=false;

    public DrawingProgram() {
        super("Painter");

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1,2));

        JPanel jp = new JPanel();

        //JPanel btnWrapper = new JPanel();
        JLabel btnLabel = new JLabel();
        //circleBtn.setPreferredSize(new Dimension(30, 30));

        toolbar.add(btnLabel);
        btnLabel.setLayout(new GridLayout(1,7));
        rectBtn.setSize(30,30);
        circleBtn.setSize(30,30);
        triangleBtn.setSize(30,30);
        pentagonBtn.setSize(30,30);
        penBtn.setSize(30,30);
        resetBtn.setSize(30,30);
        setClrBtn.setSize(30,30);

        String[] imageName = {"assets/rectangle.png","assets/circle.png","assets/triangle.png","assets/pentagon.png","assets/pencil.png","assets/reset.png","assets/setColor.png"};
        Image shapeIcon;
        for(int i=0; i<7; i++){
            try {
                shapeIcon = ImageIO.read(new File(imageName[i]));
                Image scaledIcon = shapeIcon.getScaledInstance(30,30,Image.SCALE_SMOOTH);
                if(i==0){
                    rectBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==1){
                    circleBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==2){
                    triangleBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==3){
                    pentagonBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==4){
                    penBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==5){
                    resetBtn.setIcon(new ImageIcon(scaledIcon));
                }
                else if(i==6){
                    setClrBtn.setIcon(new ImageIcon(scaledIcon));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //btnWrapper.add(btnLabel);
        //btnWrapper.setBackground(Color.green);
        toolbar.setSize(500,50);
        //toolbar.setIgnoreRepaint(true);
        btnLabel.setPreferredSize(new Dimension(200, 50));
        //btnLabel.setBackground(Color.RED);
        //toolbar.add(btnWrapper);

        btnLabel.add(rectBtn);
        btnLabel.add(circleBtn);
        btnLabel.add(triangleBtn);
        btnLabel.add(pentagonBtn);
        btnLabel.add(penBtn);
        btnLabel.add(resetBtn);
        btnLabel.add(setClrBtn);

        //toolbar.add(new Label("Drag mouse to draw"));
        toolbar.add(penSize);
        //toolbar.setBackground(Color.pink);
        this.add(toolbar,BorderLayout.SOUTH);
        this.add(jp,BorderLayout.CENTER);
        jp.addMouseMotionListener(this);
        jp.addMouseListener(this);
        penSize.addChangeListener(this);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        rectBtn.addActionListener(this::buttonAction);
        circleBtn.addActionListener(this::buttonAction);
        triangleBtn.addActionListener(this::buttonAction);
        pentagonBtn.addActionListener(this::buttonAction);
        penBtn.addActionListener(this::buttonAction);
        resetBtn.addActionListener(this::buttonAction);
        setClrBtn.addActionListener(this::buttonAction);

    }

    public void buttonAction(ActionEvent me){


        if(me.getSource() != setClrBtn) {
            activeRectBtn = false;
            activeCircleBtn = false;
            activeTriangleBtn = false;
            activeTriangleBtn = false;
            activePentagonBtn = false;
            activePen = false;

            rectBtn.setEnabled(true);
            circleBtn.setEnabled(true);
            triangleBtn.setEnabled(true);
            pentagonBtn.setEnabled(true);
            penBtn.setEnabled(true);

            if (me.getSource() == rectBtn) {
                activeRectBtn = true;
                rectBtn.setEnabled(false);
                System.out.println("activeRectBtn");
            } else if (me.getSource() == circleBtn) {
                activeCircleBtn = true;
                circleBtn.setEnabled(false);
                System.out.println("activeCircleBtn");
            } else if (me.getSource() == triangleBtn) {
                activeTriangleBtn = true;
                triangleBtn.setEnabled(false);
                System.out.println("activeTriangleBtn");
            } else if (me.getSource() == pentagonBtn) {
                activePentagonBtn = true;
                pentagonBtn.setEnabled(false);
                System.out.println("activePentagonBtn");
            } else if (me.getSource() == penBtn) {
                activePen = true;
                penBtn.setEnabled(false);
                System.out.println("activePenBtn");
            }else if(me.getSource() == resetBtn){
                System.out.println("activeResetBtn");
            }
        }
        else if(me.getSource() == setClrBtn){
            penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
            System.out.println("activeClrBtn");
        }
    }

    public void mouseMoved(MouseEvent me){

    }

    public void mouseDragged(MouseEvent me){
        Graphics g = getGraphics();
        mousePnt = me.getPoint();
        g.setColor(penColor);

        //repaint();
        if(activePen){
            g.fillOval(mousePnt.x,mousePnt.y+30,pen,pen);
        }
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

        Graphics g = getGraphics();

        if(activeCircleBtn){
            clickCount++;
            if(clickCount==1){
                currentX1 = me.getX();
                currentY1 = me.getY();
                System.out.println("currentX1: " + currentX1);
                System.out.println("currentY1: " + currentY1);
                g.setColor(penColor);
                g.fillOval(currentX1,currentY1+30,2,2);
            }
            else if(clickCount==2){
                currentX2 = me.getX();
                currentY2 = me.getY();
                System.out.println("currentX2: " + currentX2);
                System.out.println("currentY2: " + currentY2);
                g.setColor(penColor);
                g.fillOval(currentX2,currentY2+30,2,2);
                //Graphics g = getGraphics();
                int circWidth = (int)Math.sqrt(Math.pow(currentX2-currentX1,2)+Math.pow(currentY2-currentY1,2));
                int r = circWidth/2;
                System.out.println("circWidth: " + circWidth);
                int circMidptX = (int)((currentX1+currentX2)/2);
                int circMidptY = (int)((currentY1+currentY2)/2)+30;
                System.out.println("circMidptX: " + circMidptX);
                System.out.println("circMidptY: " + circMidptY);
                System.out.println("width: " + circWidth);
                g.drawOval(circMidptX-r,circMidptY-r, circWidth, circWidth);
                clickCount=0;
            }
        }
        else if(activePentagonBtn){

            currentX1 = me.getX();
            currentY1 = me.getY()+30;

            xPoints[clickCount] = currentX1;
            yPoints[clickCount] = currentY1;
            clickCount++;
            g.setColor(penColor);
            g.fillOval(currentX1, currentY1, 2, 2);

            if (clickCount > 4) {
                g.drawPolygon(xPoints, yPoints, 5);
                clickCount=0;
            }
        }

    }
    public void mouseReleased(MouseEvent me){

//        if(activePentagonBtn) {
//            Graphics g = getGraphics();
//            currentX1 = me.getX();
//            currentY1 = me.getY();
//
//            xPoints[clickCount] = currentX1;
//            yPoints[clickCount] = currentY1;
//            g.fillOval(currentX1, currentY1, 2, 2);
//
//            clickCount++;
//
//            if (clickCount > 4) {
//                System.out.println("Drawing pentagon");
//                g.drawPolygon(xPoints, yPoints, 5);
//            }
//        }
    }

    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if(!source.getValueIsAdjusting()){
            pen = (int)source.getValue();
        }
    }

//    public void paint(Graphics g){
//        super.paint(g);
//        g.setColor(penColor);
//        activePen=true;
//        if(activePen){
//            g.fillOval(mousePnt.x,mousePnt.y,pen,pen);
//        }
//
//
//        //g.drawOval(20,20,100,100);
//    }

    public static void main(String[] a){
        new DrawingProgram();
    }
}
