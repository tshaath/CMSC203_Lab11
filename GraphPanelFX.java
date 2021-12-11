package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * GraphPanelFX creates the Canvas and draws the graph
 * @author ralexander
 *
 */
public class GraphPanelFX {
    double gridWidth;
    double gridHeight;
    double xLeft, xRight, yTop, yBottom;
    OptimizerManager graphMgr;
    Canvas graphCanvas;
    GraphicsContext gc;

    GraphPanelFX(OptimizerManager graphManager, double CANVAS_WIDTH, double CANVAS_HEIGHT) {
        graphMgr = graphManager;
        graphCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = graphCanvas.getGraphicsContext2D();
    }

    public Canvas getGraphCanvas(GraphPanelFX graphPanel2) {
        return graphCanvas;
    }

    /**
     * drawGraph is called when the "Graph a Function" button is selected
     */
    public void drawGraph() {
        gridWidth = gc.getCanvas().getWidth();
        gridHeight = gc.getCanvas().getHeight();
        gc.clearRect(0,0,gridWidth,gridHeight);
        System.out.println("in paintComponent(); width="+gridWidth+"; height="+gridHeight);

        drawGraph(gridWidth, gridHeight-10, gc);
    }

    /**
     * Draws line segments from left extent to right extent, pixel by pixel, transforming points
     * to the coordinate system of the panel.
     * @param gridWidth2 the width of the panel in pixels
     * @param gridHeight the height of the panel in pixels
     * @param g2 the Graphics2D instance generated by Java library classes and sent as a argument of paintComponent
     */
    public void drawGraph(double gridWidth2, double gridHeight, GraphicsContext gc) {
        double x0=xLeft, y0, x1=0;
        double x1Draw, x0Draw, y1Draw, y0Draw;
        int functionChoice = graphMgr.getFunctionChoice();
        Function fn = graphMgr.getFunction(functionChoice);

        //check to make sure a function choice has been made before drawing
        if(functionChoice>0) {

            xLeft = graphMgr.getLeftExtent();
            xRight = graphMgr.getRightExtent();

            graphMgr.setExtents(xLeft, xRight, gridWidth2);

            yTop = graphMgr.getTopExtent();
            yBottom = graphMgr.getBottomExtent();

            //draw a gray horizontal line at y=0
            gc.setStroke(Paint.valueOf("Gray"));
            y1Draw = fn.originToPlot(gridHeight, yBottom, yTop);
            gc.strokeLine(0,y1Draw,gridWidth2,y1Draw);

            //set the graphing color and width
            gc.setStroke(Paint.valueOf("BLUE"));
            gc.setLineWidth(2);
            x0=xLeft;
            y0 = graphMgr.getFnValue(functionChoice, x0);
            //loop pixel by pixel, drawing the function between each value of x
            for (int i=1; i<gridWidth2; i++) {
                x1 = x0+((xRight-xLeft)/gridWidth2);
                x0Draw = i;
                x1Draw = i+1;
                y1Draw = fn.fnValueToPlot(x1, gridHeight, yBottom, yTop);
                y0Draw = fn.fnValueToPlot(x0, gridHeight, yBottom, yTop);
                System.out.println("x0="+x0+"  y0="+graphMgr.getFnValue(functionChoice, x0)+";  x0Draw="+x0Draw+"  y0Draw="+y0Draw);
                gc.strokeLine(x1Draw,y1Draw,x0Draw,y0Draw);
                x0=x1;
            }
        }
    }
}