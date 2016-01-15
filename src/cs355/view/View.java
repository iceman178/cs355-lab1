package cs355.view;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Observable;

import cs355.GUIFunctions;
import cs355.model.drawing.*;
import cs355.model.scene.Instance;

public class View implements ViewRefresher {

	@Override
	public void update(Observable arg0, Object arg1) {
		GUIFunctions.refresh();
	}

	@Override
	public void refreshView(Graphics2D g2d) {
		ArrayList<Shape> shapes = (ArrayList<Shape>) Model.instance().getShapes();
		
		for(int i = 0; i < shapes.size(); i++) {
			Shape currentShape = shapes.get(i);
			
			g2d.setColor(currentShape.getColor());
			g2d.fill(shapeFactory(currentShape)); //Uses the factory to determine the current shape to set the fill
			g2d.draw(shapeFactory(currentShape)); //Uses the factory to determine the current shape to draw the image
		}
	}
	
	//Use a factory to determine what type is being dealt with
	//TODO Needs to add logic for the rest of the shapes
	public java.awt.Shape shapeFactory(Shape currentShape)
	{
		if (currentShape.getShapeType() == Shape.type.LINE)
		{
			Line line = (Line)currentShape;
			
			Point2D.Double start = new Point2D.Double(line.getStart().x, line.getStart().y);		
			Point2D.Double end = new Point2D.Double(line.getEnd().x, line.getEnd().y);
			
			return new Line2D.Double(start.x, start.y, end.x, end.y);
		}
		else if (currentShape.getShapeType() == Shape.type.CIRCLE)
		{
			Circle circle = (Circle)currentShape;
						
			double x = circle.getOrigin().getX();
			double y = circle.getOrigin().getY();
			double width = circle.getRadius();
			double height = circle.getRadius();
						
			return new Ellipse2D.Double(x, y, width, height);
		}
		else if (currentShape.getShapeType() == Shape.type.ELLIPSE)
		{
			Ellipse ellipse = (Ellipse)currentShape;
			
			double x = ellipse.getOrigin().getX();
			double y = ellipse.getOrigin().getY();
			double width = ellipse.getWidth();
			double height = ellipse.getHeight();
						
			return new Ellipse2D.Double(x, y, width, height);
		}
		else if (currentShape.getShapeType() == Shape.type.RECTANGLE)
		{
			Rectangle rectangle = (Rectangle)currentShape;
			
			double x = rectangle.getOrigin().getX();
			double y = rectangle.getOrigin().getY();
			double width = rectangle.getWidth();
			double height = rectangle.getHeight();
			System.out.println("SFRx=" + rectangle.getUpperLeft().getX() + "  SFRy=" + rectangle.getUpperLeft().getY());		
			return new Rectangle2D.Double(x, y, width, height);
		}
		else if (currentShape.getShapeType() == Shape.type.SQUARE)
		{
			Square square = (Square)currentShape;
			double x = square.getOrigin().getX();
			double y = square.getOrigin().getY();
			double width = square.getSize();
			double height = square.getSize();
						
			return new Rectangle2D.Double(x, y, width, height);
		}
		else if (currentShape.getShapeType() == Shape.type.TRIANGLE)
		{
			System.out.println("Triangle");

		}
		
		return null;
	}
	
	
	
	
	
	
	
	
}

