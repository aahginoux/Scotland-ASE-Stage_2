package airportCheckIn;

import java.awt.event.*;

import javax.swing.JSlider;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;



public class AirportController 
{
	private AirportModel airport;
	private AirportGUIView view;
	
	public AirportController(AirportModel a, AirportGUIView v) 
	{
		airport = a;
		view = v;
		view.addProceedCIDListener(new ProceedCIDController());
		view.addSliderListener(new SliderController());
	}
		
    
    public class ProceedCIDController implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
        	System.out.println("\nProceed Button in GUI has been clicked -> Starting Simulation");
        	view.disableProceedButton();
    		Thread thread = new Thread (airport);
    		thread.start();
    		    		
    		//view.updateCurrentItem(i.getItemReport());
    		//airport.processNextBid();
    		//view.setAfterProcessing();
    		//view.display(airport.getItemReport());
    		//if (airport.isFinished() )
   			//view.setAfterProcessing();
        }
    }
    
    public class SliderController implements ChangeListener
    {
        	 public void stateChanged(ChangeEvent e) 
        	 {
        		 JSlider source = (JSlider)e.getSource();
        		 if (!source.getValueIsAdjusting()) {
                     int speed = (int)source.getValue();
                     airport.changeSpeedFactor(speed);
                     System.out.println("Slider value changed to: "+ speed);
        	}
            
//                 if (!e.getSource().getValueIsAdjusting()) {
//                     int speed = (int)e.getSource().getValue();
//                     System.out.println("Slider value: "+ speed);
        		 
       }         
    }
}