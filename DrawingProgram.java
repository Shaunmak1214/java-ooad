import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics;

public class DrawingProgram extends JFrame implements MouseMotionListener, MouseListener, ChangeListener {
    private Point mousePnt = new Point();
    public static Color penColor = new Color(0,0,0);
    final private Color eraserColor = new Color(255, 255, 255);
    private JSlider penSize = new JSlider(JSlider.HORIZONTAL,1,30,4);
    public static int pen = 4;
    public int currentX1, currentY1, currentX2, currentY2, clickCount;

    public int [] xPoints = new int[5];
    public int [] yPoints = new int[5];

    public JButton rectBtn;
    public JButton circleBtn;
    public JButton triangleBtn;
    public JButton pentagonBtn;
    public JButton penBtn;
    public JButton eraserBtn;
    public JButton resetBtn;
    public JButton setClrBtn;
    public JButton click2PointBtn;
    public JButton clickMultipleBtn;

    //Button active status
    public boolean activeRectBtn=false, activeCircleBtn=false,
            activeTriangleBtn=false, activePentagonBtn=false, activePen=false, activeEraser = false, activeClick2Pnt=true, activeClickMultiple=false;

    public DrawingProgram() {
        super("Painter");

        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.PINK);
        toolbar.setLayout(new GridLayout(1,3));

        JPanel jp = new JPanel();
        
        Label dragMouseLabel = new Label("Drag mouse to draw"); //
        dragMouseLabel.setFont(new Font("Calibri", Font.BOLD, 15)); //
        //toolbar.add(dragMouseLabel); //
        
        JLabel btnLabel = new JLabel();
        toolbar.add(btnLabel);
        toolbar.add(dragMouseLabel); //
        btnLabel.setLayout(new GridLayout(1,7));

        rectBtn             = ConstructGui.createButton("assets/rectangle.png");
        circleBtn           = ConstructGui.createButton("assets/circle.png");
        triangleBtn         = ConstructGui.createButton("assets/triangle.png");
        pentagonBtn         = ConstructGui.createButton("assets/pentagon.png");
        penBtn              = ConstructGui.createButton("assets/pencil.png");
        eraserBtn           = ConstructGui.createButton("assets/eraser.png");
        resetBtn            = ConstructGui.createButton("assets/reset.png");
        setClrBtn           = ConstructGui.createButton("assets/setColor.png");
        click2PointBtn      = ConstructGui.createButton("assets/click2Point.png");
        clickMultipleBtn    = ConstructGui.createButton("assets/clickMultiple.png");

        click2PointBtn.setEnabled(false);

        toolbar.setSize(500,50);
        
        btnLabel.setPreferredSize(new Dimension(200, 50));
        btnLabel.add(rectBtn);
        btnLabel.add(circleBtn);
        btnLabel.add(triangleBtn);
        btnLabel.add(pentagonBtn);
        btnLabel.add(penBtn);
        btnLabel.add(eraserBtn);
        btnLabel.add(resetBtn);
        btnLabel.add(setClrBtn);
        btnLabel.add(click2PointBtn);
        btnLabel.add(clickMultipleBtn);
        btnLabel.setOpaque(true);

        //toolbar.add(new Label("Drag mouse to draw"));
        toolbar.add(penSize);
        
        this.add(toolbar,BorderLayout.SOUTH);
        this.add(jp,BorderLayout.CENTER);
        jp.addMouseMotionListener(this);
        jp.addMouseListener(this);
        penSize.addChangeListener(this);
        jp.setBackground(Color.WHITE);
        setResizable(false); //
        setSize(1500,800);
        //setSize(1000,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        rectBtn.addActionListener(this::buttonAction);
        circleBtn.addActionListener(this::buttonAction);
        triangleBtn.addActionListener(this::buttonAction);
        pentagonBtn.addActionListener(this::buttonAction);
        penBtn.addActionListener(this::buttonAction);
        eraserBtn.addActionListener(this::buttonAction);
        resetBtn.addActionListener(this::buttonAction);
        setClrBtn.addActionListener(this::buttonAction);
        click2PointBtn.addActionListener(this::buttonAction);
        clickMultipleBtn.addActionListener(this::buttonAction);
    }

    public void buttonAction(ActionEvent me){

        if(me.getSource() != setClrBtn && me.getSource() != click2PointBtn && me.getSource() != clickMultipleBtn) {
            activeRectBtn       = false;
            activeCircleBtn     = false;
            activeTriangleBtn   = false;
            activeTriangleBtn   = false;
            activePentagonBtn   = false;
            activePen           = false;
            activeEraser        = false;
            activeClick2Pnt     = true;
            activeClickMultiple = false;

            rectBtn.setEnabled(true);
            circleBtn.setEnabled(true);
            triangleBtn.setEnabled(true);
            pentagonBtn.setEnabled(true);
            penBtn.setEnabled(true);
            eraserBtn.setEnabled(true);
            click2PointBtn.setEnabled(false);
            clickMultipleBtn.setEnabled(true);

            if (me.getSource() == rectBtn) {
                activeRectBtn = true;
                rectBtn.setEnabled(false);
                System.out.println("activeRectBtn");
            } 
            
            else if (me.getSource() == circleBtn) {
                activeCircleBtn = true;
                click2PointBtn.setEnabled(false);
                clickMultipleBtn.setEnabled(false);
                circleBtn.setEnabled(false);
                System.out.println("activeCircleBtn");
            } 
            
            else if (me.getSource() == triangleBtn) {
                activeTriangleBtn = true;
                triangleBtn.setEnabled(false);
                System.out.println("activeTriangleBtn");
            } 
            
            else if (me.getSource() == pentagonBtn) {
                activePentagonBtn = true;
                pentagonBtn.setEnabled(false);
                System.out.println("activePentagonBtn");
            } 
            
            else if (me.getSource() == penBtn) {
                activePen = true;
                penBtn.setEnabled(false);
                System.out.println("activePenBtn");
            } 

            else if (me.getSource() == eraserBtn) {
                activeEraser = true;
                eraserBtn.setEnabled(false);
                System.out.println("activeEraserBtn");
            }
            
            else if(me.getSource() == resetBtn){
                repaint();
                System.out.println("activeResetBtn");
            }
        } 
        
        else if (me.getSource() == click2PointBtn) {
            activeClick2Pnt = true;
            activeClickMultiple = false;
            click2PointBtn.setEnabled(false);
            clickMultipleBtn.setEnabled(true);
            System.out.println("activeClick2Point");
        } 
        
        else if (me.getSource() == clickMultipleBtn) {
            activeClickMultiple = true;
            activeClick2Pnt = false;
            clickMultipleBtn.setEnabled(false);
            click2PointBtn.setEnabled(true);
            System.out.println("activeClickMultiple");
        }

        else if (me.getSource() == setClrBtn){
            penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
            System.out.println("activeClrBtn");
        }
    }

    public void mouseMoved(MouseEvent me){}  

    public void mouseDragged(MouseEvent me){

        if (me.getPoint().y > 705) {
            return;
        }

        Graphics g = getGraphics();
        mousePnt = me.getPoint();

        if (activePen){
            g.setColor(penColor);
            g.fillOval(mousePnt.x,mousePnt.y+30,pen,pen);
        }

        else if (activeEraser) {
            g.setColor(eraserColor);
            g.fillOval(mousePnt.x,mousePnt.y+30,pen,pen);
        }
    }

    public void mouseClicked(MouseEvent me){
        if(me.getModifiers()==MouseEvent.BUTTON3_MASK){
            penColor = JColorChooser.showDialog(null, "Change pen colour", penColor);
        }
    }

    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me)  {}
    public void mousePressed(MouseEvent me) {

        Graphics g = getGraphics();
        g.setColor(penColor);

        if(activeRectBtn)
            new DrawRectangle(g, activeClick2Pnt, activeClickMultiple, me.getX(), me.getY() + 30);
        
        else if(activeCircleBtn)
            new DrawCircle(g, me.getX(), me.getY());

        else if(activeTriangleBtn) 
            new DrawTriangle(g, activeClick2Pnt, activeClickMultiple, me.getX(), me.getY() + 30);

        else if(activePentagonBtn)
            new DrawPentagon(g, activeClick2Pnt, activeClickMultiple, me.getX(), me.getY() + 30);
    }

    public void mouseReleased(MouseEvent me) {}

    public void stateChanged(ChangeEvent me) {

        JSlider source = (JSlider)me.getSource();
        if (!source.getValueIsAdjusting()) {
            pen = (int)source.getValue();
        }
    }

    public static void main(String[] a){
        new DrawingProgram();
    }
}