package cs355.controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Iterator;

import cs355.GUIFunctions;
import cs355.model.drawing.*;

public class Controller implements CS355Controller {

	private boolean shapeSelected = false;
	
	private int count = 0;
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		System.out.println("Controller:mouseClicked  X=" + arg0.getX() + " Y=" + arg0.getY());
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		//System.out.println("Controller:mousePressed  X=" + arg0.getX() + " Y=" + arg0.getY());

		switch(Model.instance().getCurrentMode())
		{
		case LINE:
			Point2D.Double start_line = new Point2D.Double(arg0.getX(), arg0.getY());		
			Point2D.Double end_line = new Point2D.Double(arg0.getX(), arg0.getY());
			Line line = new Line(Model.instance().getSelectedColor(), start_line, end_line);
			line.setShapeType(Shape.type.LINE);
			Model.instance().addShape(line);
			shapeSelected = true;
			break;
		case CIRCLE:
			Point2D.Double center_circle = new Point2D.Double(arg0.getX(), arg0.getY());
			Circle circle = new Circle(Model.instance().getSelectedColor(), center_circle, 0);
			circle.setShapeType(Shape.type.CIRCLE);
			Model.instance().addShape(circle);
			shapeSelected = true;			
			break;
		case ELLIPSE:
			Point2D.Double center_ellipse = new Point2D.Double(arg0.getX(), arg0.getY());
			Ellipse ellipse = new Ellipse(Model.instance().getSelectedColor(), center_ellipse, 0, 0);
			ellipse.setShapeType(Shape.type.ELLIPSE);
			Model.instance().addShape(ellipse);
			shapeSelected = true;
			break;
		case RECTANGLE:
			Point2D.Double start_rectangle = new Point2D.Double(arg0.getX(), arg0.getY());
			Rectangle rectangle = new Rectangle(Model.instance().getSelectedColor(), start_rectangle, 0, 0);
			rectangle.setShapeType(Shape.type.RECTANGLE);
			Model.instance().addShape(rectangle);
			shapeSelected = true;
			break;
		case SQUARE:
			Point2D.Double start_square = new Point2D.Double(arg0.getX(), arg0.getY());
			Square square = new Square(Model.instance().getSelectedColor(), start_square, 0);
			square.setShapeType(Shape.type.SQUARE);
			Model.instance().addShape(square);
			shapeSelected = true;
			break;
		case TRIANGLE:	
			Point2D.Double triangle_a = new Point2D.Double(arg0.getX(), arg0.getY());
			Triangle triangle = new Triangle(Model.instance().getSelectedColor(), triangle_a, null, null);
			triangle.setShapeType(Shape.type.TRIANGLE);
			Model.instance().addShape(triangle);
			shapeSelected = true;
			break;
		default:
			break;
		}
		

	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		//System.out.println("Controller:mouseReleased  X=" + arg0.getX() + " Y=" + arg0.getY());
		shapeSelected = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		//System.out.println("Controller:mouseDragged  X=" + arg0.getX() + " Y=" + arg0.getY());
		
		if(shapeSelected) 
		{
			Shape currentShape = Model.instance().getLastShape();
			if (currentShape == null)
			{
				System.out.println("currentShape is null");
				return;
			}
			switch(currentShape.getShapeType())
			{
			case LINE:
				updateCurrentLine(currentShape, arg0);
				break;
			case CIRCLE:
				updateCurrentCircle(currentShape, arg0);		
				break;
			case ELLIPSE:
				updateCurrentEllipse(currentShape, arg0);
				break;
			case RECTANGLE:
				updateCurrentRectangle(currentShape, arg0);
				break;
			case SQUARE:
				updateCurrentSquare(currentShape, arg0);
				break;
			case TRIANGLE:	
				updateCurrentTriangle(currentShape, arg0);
				break;
			default:
				break;
			}
		}
		GUIFunctions.refresh();
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("# of shapes=" + Model.instance().getShapes().size());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//System.out.println("Controller:mouseEntered  X=" + arg0.getX() + " Y=" + arg0.getY());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//System.out.println("Controller:mouseExited  X=" + arg0.getX() + " Y=" + arg0.getY());
	}
	
	private void updateCurrentLine(Shape currentShape, MouseEvent arg0) 
	{
		Line line = (Line) currentShape;
		Point2D.Double end_line = new Point2D.Double(arg0.getX(), arg0.getY());
		line.setEnd(end_line);
		
		Model.instance().updateLastShape(line);
	}

	private void updateCurrentCircle(Shape currentShape, MouseEvent arg0) 
	{
		Circle circle = (Circle) currentShape;
		
	}
	
	private void updateCurrentEllipse(Shape currentShape, MouseEvent arg0) 
	{
		Ellipse ellipse = (Ellipse) currentShape;
		
		
		
		
	}
		
	private void updateCurrentRectangle(Shape currentShape, MouseEvent arg0) 
	{
		Rectangle rectangle = (Rectangle) currentShape;
		Point2D.Double opposite_corner = new Point2D.Double(arg0.getX(), arg0.getY());

		// Left side of origin point
		if (opposite_corner.getX() < rectangle.getOrigin().getX())
		{
			// Above origin point
			if (opposite_corner.getY() < rectangle.getOrigin().getY())
			{
				rectangle.setUpperLeft(new Point2D.Double(opposite_corner.getX(), opposite_corner.getY()));
			}
			else // Below origin point
			{
				rectangle.setUpperLeft(new Point2D.Double(opposite_corner.getX(), rectangle.getOrigin().getY()));
			}
		}
		else // Right side of origin point
		{
			// Above origin point
			if (opposite_corner.getY() < rectangle.getOrigin().getY())
			{
				rectangle.setUpperLeft(new Point2D.Double(rectangle.getOrigin().getX(), opposite_corner.getY()));
			}
			else // Below origin point
			{
				rectangle.setUpperLeft(new Point2D.Double(rectangle.getOrigin().getX(), rectangle.getOrigin().getY()));
			}
		}
		
		double width = Math.abs(rectangle.getOrigin().getX() - opposite_corner.getX());
		double height = Math.abs(rectangle.getOrigin().getY() - opposite_corner.getY());
		
		rectangle.setWidth(width);
		rectangle.setHeight(height);
		
		Model.instance().updateLastShape(rectangle);		
	}

	private void updateCurrentSquare(Shape currentShape, MouseEvent arg0) 
	{
		Square square = (Square) currentShape;
		Point2D.Double opposite_corner = new Point2D.Double(arg0.getX(), arg0.getY());


		double side_length = Math.min(Math.abs(square.getOrigin().getX() - opposite_corner.getX()), 
									  Math.abs(square.getOrigin().getY() - opposite_corner.getY()));
		square.setSize(side_length);
		
		// Left side of origin point
		if (opposite_corner.getX() < square.getOrigin().getX())
		{
			// Above origin point
			if (opposite_corner.getY() < square.getOrigin().getY())
			{
				square.setUpperLeft(new Point2D.Double(square.getOrigin().getX() - side_length, 
													   square.getOrigin().getY() ));
			}
			else // Below origin point
			{
				square.setUpperLeft(new Point2D.Double(opposite_corner.getX(), square.getOrigin().getY()));
			}
		}
		else // Right side of origin point
		{
			// Above origin point
			if (opposite_corner.getY() < square.getOrigin().getY())
			{
				square.setUpperLeft(new Point2D.Double(square.getOrigin().getX(), opposite_corner.getY()));
			}
			else // Below origin point
			{
				square.setUpperLeft(new Point2D.Double(square.getOrigin().getX(), square.getOrigin().getY()));
			}
		}
		
		
		Model.instance().updateLastShape(square);
	}

	private void updateCurrentTriangle(Shape currentShape, MouseEvent arg0) 
	{
		Triangle triangle = (Triangle) currentShape;
		
		
	}
	
	@Override
	public void colorButtonHit(Color c) 
	{
		System.out.println("Controller:colorButtonHit");
		System.out.println("Color=" + c.toString());
		Model.instance().setSelectedColor(c);
		GUIFunctions.changeSelectedColor(c);
	}

	@Override
	public void lineButtonHit() 
	{
		System.out.println("Controller:lineButtonHit");
		Model.instance().setCurrentMode(Shape.type.LINE);
	}

	@Override
	public void squareButtonHit() 
	{
		System.out.println("Controller:squareButtonHit");
		Model.instance().setCurrentMode(Shape.type.SQUARE);
	}

	@Override
	public void rectangleButtonHit() 
	{
		System.out.println("Controller:rectangleButtonHit");
		Model.instance().setCurrentMode(Shape.type.RECTANGLE);
	}

	@Override
	public void circleButtonHit() 
	{
		System.out.println("Controller:circleButtonHit");
		Model.instance().setCurrentMode(Shape.type.CIRCLE);
	}

	@Override
	public void ellipseButtonHit() 
	{
		System.out.println("Controller:ellipseButtonHit");
		Model.instance().setCurrentMode(Shape.type.ELLIPSE);
	}

	@Override
	public void triangleButtonHit() 
	{
		System.out.println("Controller:triangelButtonHit");
		Model.instance().setCurrentMode(Shape.type.TRIANGLE);
	}

	@Override
	public void selectButtonHit() 
	{
		System.out.println("Controller:selectButtonHit");
	}

	@Override
	public void zoomInButtonHit() 
	{
		System.out.println("Controller:zoomInButtonHit");
	}

	@Override
	public void zoomOutButtonHit() 
	{
		System.out.println("Controller:zoomOutButtonHit");
	}

	@Override
	public void hScrollbarChanged(int value) 
	{
		System.out.println("Controller:hScrollbarChanged  Value=" + value);
	}

	@Override
	public void vScrollbarChanged(int value) 
	{
		System.out.println("Controller:vScrollbarChanged  Value=" + value);
	}

	@Override
	public void openScene(File file) 
	{
		

	}

	@Override
	public void toggle3DModelDisplay() 
	{
		

	}

	@Override
	public void keyPressed(Iterator<Integer> iterator) 
	{
		

	}

	@Override
	public void openImage(File file) 
	{
		

	}

	@Override
	public void saveImage(File file) 
	{
		

	}

	@Override
	public void toggleBackgroundDisplay() 
	{
		

	}

	@Override
	public void saveDrawing(File file) 
	{
		

	}

	@Override
	public void openDrawing(File file) 
	{
		

	}

	@Override
	public void doDeleteShape() 
	{
		

	}

	@Override
	public void doEdgeDetection() 
	{
		

	}

	@Override
	public void doSharpen() 
	{
		

	}

	@Override
	public void doMedianBlur() 
	{
		

	}

	@Override
	public void doUniformBlur() 
	{
		

	}

	@Override
	public void doGrayscale() 
	{
		

	}

	@Override
	public void doChangeContrast(int contrastAmountNum) 
	{
		

	}

	@Override
	public void doChangeBrightness(int brightnessAmountNum) 
	{
		

	}

	@Override
	public void doMoveForward() 
	{
		

	}

	@Override
	public void doMoveBackward() 
	{
		

	}

	@Override
	public void doSendToFront() 
	{
		

	}

	@Override
	public void doSendtoBack() 
	{
		

	}

}