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

    //Icon button
    public JButton rectBtn = new JButton();
    public JButton circleBtn = new JButton();
    public JButton triangleBtn = new JButton();
    public JButton pentagonBtn = new JButton();
    public JButton penBtn = new JButton();
    public JButton resetBtn = new JButton();

    //Button active status
    public boolean activeRectBtn=false, activeCircleBtn=false,
            activeTriangleBtn=false, activePentagonBtn=false, activePen=false, activeReset=false;

    public DrawingProgram() {
        super("Painter");

        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BorderLayout());

        JPanel jp = new JPanel();

        //JPanel btnWrapper = new JPanel();
        JLabel btnLabel = new JLabel();
        //circleBtn.setPreferredSize(new Dimension(30, 30));

        toolbar.add(btnLabel, BorderLayout.WEST);
        btnLabel.setLayout(new GridLayout(1,4));
        rectBtn.setSize(30,30);
        circleBtn.setSize(30,30);
        triangleBtn.setSize(30,30);
        pentagonBtn.setSize(30,30);
        penBtn.setSize(30,30);
        resetBtn.setSize(30,30);

        String[] imageName = {"assets/rectangle.png","assets/circle.png","assets/triangle.png","assets/pentagon.png","assets/pencil.png"};
        Image shapeIcon;
        for(int i=0; i<5; i++){
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //btnWrapper.add(btnLabel);
        //btnWrapper.setBackground(Color.green);
        toolbar.setSize(500,50);
        btnLabel.setPreferredSize(new Dimension(200, 50));
        btnLabel.setBackground(Color.RED);
        //toolbar.add(btnWrapper);

        btnLabel.add(rectBtn);
        btnLabel.add(circleBtn);
        btnLabel.add(triangleBtn);
        btnLabel.add(pentagonBtn);
        btnLabel.add(penBtn);
        btnLabel.add(resetBtn);

        //toolbar.add(new Label("Drag mouse to draw"));
        toolbar.add(penSize);
        toolbar.setBackground(Color.pink);
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
    }

    public void buttonAction(ActionEvent me){

        activeRectBtn = false;
        activeCircleBtn = false;
        activeTriangleBtn = false;
        activeTriangleBtn = false;
        activePentagonBtn = false;
        activePen = false;
        activeReset = false;

        if(me.getSource() == rectBtn){
            activeRectBtn = true;
            System.out.println("activeRectBtn");
        }
        else if(me.getSource() == circleBtn){
            activeCircleBtn = true;
            System.out.println("activeCircleBtn");
        }
        else if(me.getSource() == triangleBtn){
            activeTriangleBtn = true;
            System.out.println("activeTriangleBtn");
        }
        else if(me.getSource() == pentagonBtn){
            activePentagonBtn = true;
            System.out.println("activePentagonBtn");
        }
        else if(me.getSource() == penBtn){
            activePen = true;
            System.out.println("activePenBtn");
        }
        else if(me.getSource() == resetBtn){
            activeReset = true;
            System.out.println("activeResetBtn");
        }
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

        if(activeCircleBtn){
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
        activePen=true;
        if(activePen){
            g.fillOval(mousePnt.x,mousePnt.y,pen,pen);
        }

        //g.drawOval(20,20,100,100);
    }

    public static void main(String[] a){
        new DrawingProgram();
    }
}
