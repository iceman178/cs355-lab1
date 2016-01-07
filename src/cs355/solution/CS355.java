package cs355.solution;

import java.awt.MouseInfo;

import cs355.GUIFunctions;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args) 
	{
		System.out.println("Starting Program...");
		
		
		
		
		// Fill in the parameters below with your controller and view.
		GUIFunctions.createCS355Frame(null, null);

		GUIFunctions.refresh();
	
		GUIFunctions.printf("%d", MouseInfo.getPointerInfo().getLocation().x);
		System.out.println("X=" +MouseInfo.getPointerInfo().getLocation().x + " Y=" + MouseInfo.getPointerInfo().getLocation().y);
	
		
		
	}
}
