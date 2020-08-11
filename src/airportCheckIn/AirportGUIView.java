//This View doesn't know about the Controller, 
//except that it provides methods for registering a Controller's listeners. 
//Other organizations are possible
package airportCheckIn;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.util.*;

/**
 * This Class is used to set up the GUI.
 * 
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class AirportGUIView extends JFrame implements ActionListener, Observer
{
	private static final long serialVersionUID = 1L;
	private AirportModel airport;
	private CheckInDeskList cidList;				//List of checkIns Desks
	private int numDesks;
	private GridLayout gr;
	private JPanel cidPanel;
	private int count;
	private int flag=0;
	//GUI Components
	JButton proceed, close;
	JScrollPane scrollList, scrollNorth, scrollQueue, scrollFlight, scrollSouth;
	JTextArea [] checkInDesks;
	JTextArea displayQueue, displayFlight;
	JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
	Font myFont;


	/**
	 * The Constructor which creates the full GUI Layout of the frame and it's panels.
	 * @param list The List of CheckIns to be searched
	 */
	public AirportGUIView(AirportModel airport)
	{
		//super((Window)null);	//Calling constructor of JDialog initialised with No owner (NULL)
		//setModal(true);		 	//Blocks input to others so that there will be no concurrency issues

		this.airport=airport;
		count=0;
		numDesks=2;
		airport.addObserver(this);
		cidList=airport.getListOfCheckInDesks();
		numDesks=cidList.getSize();
		
		myFont=new Font (Font.SANS_SERIF,Font.PLAIN,16);	//myFont will be the main font of the GUI
		//this.setLayout(new BorderLayout(5,5));
		setTitle(" HeriotWatt - CheckIn GUI for Airport");	//Title of Main GUI Window
		setLocation (20,10);								//(20,10) should be near the Top-Left Corner 
		setDefaultCloseOperation(AirportGUIView.DO_NOTHING_ON_CLOSE);	//disable default close action
		setupNorthPanel();
		setupCenterPanel(2);
		setupSouthPanel();
		pack();				//pack contents to fit
		setVisible(true);	//Set it as Visible

	} 

	/**
	 * Method to set up the Center panel of the GUI, which contains live information about CheckIn Desks
	 */
	private void setupCenterPanel(int nc)
	{
		gr=new GridLayout (1,nc);
		cidPanel = new JPanel(gr);
		checkInDesks = new JTextArea [nc];
		for (int i = 0; i < nc; i++) 
		{
			checkInDesks[i]= new JTextArea(16,30);
			checkInDesks[i].setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
			checkInDesks[i].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
			checkInDesks[i].setEditable(false);
			cidPanel.add(checkInDesks[i]);
		}
		scrollList = new JScrollPane(cidPanel);	//The display area can be scrolled.
		scrollList.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));	//To create a small border 
		this.add(scrollList,BorderLayout.CENTER);
	}

	/**
	 * Method to set up the North Panel of the GUI, which contains the queue information
	 * The firstPanel has the  a "Close" button.
	 * The 2nd titlePanel contains a title.
	 */
	private void setupNorthPanel() 
	{

		//The firstPanel has one buttons: a "Close" button.
		JPanel firstPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel titleLabel = new JLabel("Heriot Watt Airport CheckIn       ");
		titleLabel.setFont(new Font (Font.SANS_SERIF,Font.ITALIC,20));
		firstPanel.add(titleLabel);
		proceed = new JButton("Proceed with Simulation");
		proceed.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		proceed.setBackground(Color.GREEN);
		proceed.setForeground(Color.BLACK);
		firstPanel.setBackground(Color.YELLOW);
		firstPanel.add(proceed);
		
		close = new JButton("Close & Write Log File");
		close.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		close.setBackground(Color.RED);
		close.setForeground(Color.WHITE);
		close.addActionListener(this);
		firstPanel.setBackground(Color.YELLOW);
		firstPanel.add(close);

		//The sliderPanel
		JPanel sliderPanel = new JPanel();
		JLabel sLabel = new JLabel("Slider to Alter Speed of Simulation (1-Fast, 3-Normal, 5-Slow)  ");
		sliderPanel.setBackground(Color.PINK);
		sLabel.setFont(new Font (Font.SANS_SERIF,Font.PLAIN,16));
		sliderPanel.add(sLabel);
	    //slider.setMinorTickSpacing(0.2);
	    slider.setMajorTickSpacing(1);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    slider.setLabelTable(slider.createStandardLabels(1));
	    sliderPanel.add(slider);
	    
		//The queuePanel is used to display the queue information
		JPanel queuePanel = new JPanel();	
		displayQueue = new JTextArea(5,120);		//10 row cells and 80 column cells
		displayQueue.setFont(new Font (Font.MONOSPACED,Font.PLAIN,16));		//Monospaced font for good formatting
		displayQueue.setEditable(false);
		scrollQueue = new JScrollPane(displayQueue);	//The display area can be scrolled.
		scrollQueue.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));	//To create a small border 
		queuePanel.setBackground(Color.ORANGE);
		queuePanel.add(scrollQueue);

		//Set up the northPanel which contains the previous two panels
		JPanel topPanel = new JPanel(new GridLayout(3,1));
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		topPanel.add(firstPanel);
		topPanel.add(sliderPanel);
		JPanel qPanel=new JPanel();
		JLabel queueLabel = new JLabel("Queue Of Waiting Passengers");
		qPanel.setBackground(Color.ORANGE);
		queueLabel.setFont(new Font (Font.SANS_SERIF,Font.BOLD,16));
		qPanel.add(queueLabel);
		topPanel.add(qPanel);
		northPanel.add(topPanel);
		northPanel.add(queuePanel);
		scrollNorth = new JScrollPane(northPanel);
		scrollNorth.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));	//To create a small border 
		this.add(scrollNorth,BorderLayout.NORTH);	//add northPanel to the North position of this Frame
		
	}

	/**
	 * Method to set up the SouthPanel consisting of Flight Information
	 */
	private void setupSouthPanel() 
	{ 

		//The flightPanel is used to display the queue information
		JPanel flightPanel  = new JPanel();	
		displayFlight = new JTextArea(7,120);		//10 row cells and 80 column cells 
		displayFlight.setFont(new Font (Font.MONOSPACED,Font.PLAIN,16));		//Monospaced font for good formatting
		displayFlight.setEditable(false);
		scrollFlight = new JScrollPane(displayFlight);	//The display area can be scrolled.
		scrollFlight.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));	//To create a small border 

		flightPanel.add(scrollFlight);
		flightPanel.setBackground(Color.CYAN);
		scrollSouth = new JScrollPane(flightPanel);
		scrollSouth.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));	//To create a small border 
		this.add(scrollSouth,BorderLayout.SOUTH);	//add to the South position of this Frame
	}


	//MVC pattern - allows listeners to be added
	public void addProceedCIDListener(ActionListener al) 
	{
		proceed.addActionListener(al);
	}
	
	public void addSliderListener(ChangeListener cl) 
	{
		slider.addChangeListener(cl);
		System.out.println("Slider value at: "+ slider.getValue());
	}

	public void disableProceedButton() 
	{
		proceed.setEnabled(false);
	}

	/**
	 * Implementation of the abstract actionPerformed Method of the ActionListener Interface
	 * Event Handling is performed here when a button is clicked.
	 * For each type of button, the appropriate action must be done accordingly
	 */ 
	public void actionPerformed(ActionEvent e) 
	{ 

		if (e.getSource() == close) 
		{	
			JLabel closeLabel;
			closeLabel= new JLabel("The Log File of all Events can be found after this GUI is closed.");
			closeLabel.setFont(myFont);
			closeLabel.setForeground(Color.RED);
			JOptionPane.showMessageDialog(this,closeLabel,"GoodBye From S H S Airport!",JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);
			this.dispose();
			LogSingleton ls=LogSingleton.getInstance();
			System.out.println(ls.getLog());
			String logReport=ls.getLog();
			ls.writeToFile("LogReport.txt",logReport);
			System.exit(0);
		}
	}  


	//OBSERVER pattern - must provide update methods
	//synchronized blocks access to sync methods of the same object until finished
	public synchronized void update(Observable o, Object args) 
	{		
		System.out.println("Invoked Update method of Observer -> AirportGUIView");
		displayQueue.setText(airport.getQ());

		displayFlight.setText(airport.printFlightDetails());
		if(airport.getNumDesks()==3&&flag==0)
		{
			flag=1;
			System.out.println("Splitting GUI-> From 2 CheckIn Desks to 3");
			cidPanel.removeAll();
			validate();
			setupCenterPanel(3);
			validate();
			numDesks=3;
		}
		if(numDesks==2)	
		{
			for (int i = 0; i < 2; i++) 
			{
				String report = cidList.get(i).getReport() ;
				this.checkInDesks[i].setText(report);	
				if (report.contains("CLOSING CHECK-IN DESK NUMBER"))
				{
					checkInDesks[i].setBackground(Color.RED);
					checkInDesks[i].setForeground(Color.WHITE);
				}
				else
					checkInDesks[i].setForeground(Color.BLACK);
			}
		}
		else
		{
			for (int i = 0; i < 3; i++) 
			{
				String report = cidList.get(i).getReport() ;
				this.checkInDesks[i].setText(report);	
				if (report.contains("CLOSING CHECK-IN DESK NUMBER"))
				{
					checkInDesks[i].setBackground(Color.RED);
					checkInDesks[i].setForeground(Color.WHITE);
				}
				else
					checkInDesks[i].setForeground(Color.BLACK);
			}
		}
	}

	public CheckInDeskList getUpdatedCheckInList()
	{
		return cidList;
	}

	public int getCount() 
	{
		return count;
	}


	/**
	 * Method to clear and reset all the GUI Components back to their default values.
	*/
	
/*	private void resetCheckIn()
	{

	}
*/
}