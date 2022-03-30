import java.awt.Graphics;

public class DrawCircle implements DrawShape {
    public static int clickCount = 0;
    public static int xPoints[] = new int[2];
    public static int yPoints[] = new int[2];

    public DrawCircle(Graphics g, int xPoint, int yPoint) {
        
        xPoints[clickCount] = xPoint;
        yPoints[clickCount] = yPoint;

        if(clickCount == 0) {
            drawPoint(g);
        }
        else if(clickCount == 1) {
            draw2PntShape(g);
        }
    }

    public void drawPoint(Graphics g) {
        g.fillOval(xPoints[clickCount],yPoints[clickCount]+30, 2, 2);

        clickCount++;
    }

    public void draw2PntShape(Graphics g) {
        g.fillOval(xPoints[1],yPoints[1]+30,2,2);

        int circWidth = (int)Math.sqrt(Math.pow(xPoints[1]-xPoints[0],2)+Math.pow(yPoints[1]-yPoints[0],2));
        int r = circWidth/2;
        int circMidptX = (int)((xPoints[0]+xPoints[1])/2);
        int circMidptY = (int)((yPoints[0]+yPoints[1])/2)+30;

        System.out.println("circWidth: " + circWidth);
        System.out.println("circMidptX: " + circMidptX);
        System.out.println("circMidptY: " + circMidptY);
        System.out.println("width: " + circWidth);
        
        g.drawOval(circMidptX-r,circMidptY-r, circWidth, circWidth);
        
        clickCount = 0;
    }
}