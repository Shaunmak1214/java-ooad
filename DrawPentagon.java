import java.awt.Graphics;

public class DrawPentagon {

    public static int clickCount = 0;
    public static int xPoints[] = new int[5];
    public static int yPoints[] = new int[5];

    public DrawPentagon(Graphics g, boolean activeClick2Pnt, boolean activeClickMultiple, int xPoint, int yPoint) {
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
            if(clickCount < 4) {
                drawPoint(g);
            }
            else if(clickCount == 4) {
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
        g.fillOval(xPoints[0],yPoints[0], 2, 2);

        clickCount++;
    }

    public void draw2PntShape(Graphics g) {
        int storeX0 = xPoints[0];
        int storeX1 = xPoints[1];
        int storeY0 = yPoints[0];
        int storeY1 = yPoints[1];

        if(xPoints[0] < xPoints[1] && yPoints[0] < yPoints[1]) {
            System.out.println("METHOD 1");            

            xPoints[0] = storeX0;
            xPoints[1] = (storeX0 + storeX1) / 2;
            xPoints[2] = storeX1;
            xPoints[3] = ((storeX1 - storeX0) * 81 / 100) + storeX0;
            xPoints[4] = ((storeX1 - storeX0) * 19 / 100) + storeX0;

            yPoints[0] = ((storeY1 - storeY0) * 38 / 100) + storeY0;
            yPoints[1] = storeY0;
            yPoints[2] = ((storeY1 - storeY0) * 38 / 100) + storeY0;
            yPoints[3] = storeY1;
            yPoints[4] = storeY1;
        }

        else if(xPoints[0] > xPoints[1] && yPoints[0] > yPoints[1]) {
            System.out.println("METHOD 2");

            xPoints[0] = storeX1;
            xPoints[1] = (storeX0 + storeX1) / 2;
            xPoints[2] = storeX0;
            xPoints[3] = ((storeX0 - storeX1) * 81 / 100) + storeX1;
            xPoints[4] = ((storeX0 - storeX1) * 19 / 100) + storeX1;

            yPoints[0] = ((storeY0 - storeY1) * 38 / 100) + storeY1;
            yPoints[1] = storeY1;
            yPoints[2] = ((storeY0 - storeY1) * 38 / 100) + storeY1;
            yPoints[3] = storeY0;
            yPoints[4] = storeY0;       
        }

        else if(xPoints[0] < xPoints[1] && yPoints[0] > yPoints[1]) {
            System.out.println("METHOD 3");

            xPoints[0] = storeX0;
            xPoints[1] = (storeX0 + storeX1) / 2;
            xPoints[2] = storeX1;
            xPoints[3] = ((storeX1 - storeX0) * 81 / 100) + storeX0;
            xPoints[4] = ((storeX1 - storeX0) * 19 / 100) + storeX0;

            yPoints[0] = ((storeY0 - storeY1) * 38 / 100) + storeY1;
            yPoints[1] = storeY1;
            yPoints[2] = ((storeY0 - storeY1) * 38 / 100) + storeY1;
            yPoints[3] = storeY0;
            yPoints[4] = storeY0;
        }

        else if(xPoints[0] > xPoints[1] && yPoints[0] < yPoints[1]) {
            System.out.println("METHOD 4");

            xPoints[0] = storeX1;
            xPoints[1] = (storeX0 + storeX1) / 2;
            xPoints[2] = storeX0;
            xPoints[3] = ((storeX0 - storeX1) * 81 / 100) + storeX1;
            xPoints[4] = ((storeX0 - storeX1) * 19 / 100) + storeX1;

            yPoints[0] = ((storeY1 - storeY0) * 38 / 100) + storeY0;
            yPoints[1] = storeY0;
            yPoints[2] = ((storeY1 - storeY0) * 38 / 100) + storeY0;
            yPoints[3] = storeY1;
            yPoints[4] = storeY1;
        }

        else if(xPoints[0] == xPoints[1]) {
            ConstructGui.createPopUp("ErrorForXPlane");
        }

        else if(yPoints[0] == yPoints[1]) {
            ConstructGui.createPopUp("ErrorForYPlane");
        }

        g.drawPolygon(xPoints, yPoints, 5);
        clickCount = 0;
    }

    public void drawMultiplePntShape(Graphics g) {
        g.drawPolygon(xPoints, yPoints, 5);
        clickCount = 0;
    }
}
