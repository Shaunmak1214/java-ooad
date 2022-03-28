import java.awt.Graphics;

public class DrawRectangle {

    public static int clickCount = 0;
    public static int xPoints[] = new int[4];
    public static int yPoints[] = new int[4];

    public DrawRectangle(Graphics g, boolean activeClick2Pnt, boolean activeClickMultiple, int xPoint, int yPoint) {
        
        xPoints[clickCount] = xPoint;
        yPoints[clickCount] = yPoint;

        if(activeClick2Pnt) {
            if(clickCount == 0) {
                drawPoint(g);
            }
            else if (clickCount == 1){
                draw2PntShape(g);
            }
        }
        else if(activeClickMultiple) {
            if(clickCount < 3) {
                drawPoint(g);
            }
            else if(clickCount == 3) {
                if(activeClick2Pnt) {
                    draw2PntShape(g);
                }
    
                else if(activeClickMultiple) {
                    drawMultiplePntShape(g);
                }
            }
        }
    }

    public void drawPoint(Graphics g) {
        g.fillOval(xPoints[clickCount],yPoints[clickCount], 2, 2);

        clickCount++;
    }

    public void draw2PntShape(Graphics g) {

        if(xPoints[0] < xPoints[1] && yPoints[0] < yPoints[1]) {
            g.drawRect(xPoints[0], yPoints[0], xPoints[1] - xPoints[0], yPoints[1] - yPoints[0]);
        }

        else if(xPoints[0] > xPoints[1] && yPoints[0] > yPoints[1]) {
            g.drawRect(xPoints[1], yPoints[1], xPoints[0] - xPoints[1], yPoints[0] - yPoints[1]);
        }

        else if(xPoints[0] < xPoints[1] && yPoints[0] > yPoints[1]) {
            g.drawRect(xPoints[0], yPoints[1], xPoints[1] - xPoints[0], yPoints[0] - yPoints[1]);
        }

        else if(xPoints[0] > xPoints[1] && yPoints[0] < yPoints[1]) {
            g.drawRect(xPoints[1], yPoints[0], xPoints[0] - xPoints[1], yPoints[1] - yPoints[0]);
        }

        else if(xPoints[0] == xPoints[1]) {
            ConstructGui.createPopUp("ErrorForXPlane");
        }

        else if(yPoints[0] == yPoints[1]) {
            ConstructGui.createPopUp("ErrorForYPlane");
        }

        clickCount = 0;
    }

    public void drawMultiplePntShape(Graphics g) {
        g.drawPolygon(xPoints, yPoints, 4);

        clickCount = 0;
    }
}
