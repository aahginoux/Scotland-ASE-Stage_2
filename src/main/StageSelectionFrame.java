/**
 * 
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

/**
 * It appears a frame which asks the user to choose which stage he wants to proceed on.
 * @author Panagiotis Tsamoutalis (pt45)
 *
 */
public class StageSelectionFrame extends JFrame implements ActionListener {
	
	JLabel title,display;
	JButton close,stage1,stage2,info;
	GridBagConstraints c;

	/**
	 * @throws HeadlessException
	 */
	public StageSelectionFrame() throws HeadlessException {
		// TODO Auto-generated constructor stub
		setTitle("DISPLAY");
    	setSize(400,400);
    	setResizable(false);
		setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		
		setupSouthPanel();
		setupNorthPanel();
		setupCenterPanel();
	
        //pack and set visible
        //pack();
        setVisible(true);
	}

	/**
	 * 
	 */
	private void setupNorthPanel() {
		// TODO Auto-generated method stub
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridBagLayout());
		title=new JLabel("ASE  COURSEWORK");
		title.setFont( new Font(Font.MONOSPACED, Font.BOLD,30));
		c = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.ipadx=20;
		c.insets=new Insets(20,0,0,0);
		c.fill= GridBagConstraints.VERTICAL;
		northPanel.add(title,c);
		this.add(northPanel,BorderLayout.NORTH);
	}

	/**
	 * 
	 */
	private void setupSouthPanel() {
		// TODO Auto-generated method stub
		JPanel southPanel=new JPanel();
		southPanel.setLayout(new GridBagLayout());
		close=new JButton("Close");
		close.addActionListener(this);
		c = new GridBagConstraints();
		c.gridx=1;
		c.gridy=0;
		c.ipadx=20;
		c.insets=new Insets(0,232,0,0);
		c.fill= GridBagConstraints.HORIZONTAL;
		southPanel.add(close,c);
		info=new JButton("Info");
		info.addActionListener(this);
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.ipadx=20;
		c.fill= GridBagConstraints.HORIZONTAL;
		southPanel.add(info,c);
		this.add(southPanel,BorderLayout.PAGE_END);
		
	}

	/**
	 * 
	 */
	private void setupCenterPanel() {
		// TODO Auto-generated method stub
		JPanel stagePanel=new JPanel();
		stagePanel.setLayout(new GridBagLayout());
		
		display=new JLabel("Pick the stage that you want to proceed:");
		//display.setFont( new Font(Font.MONOSPACED, Font.BOLD,15));
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		//c.weighty=2;
		//c.ipady=40;
		c.insets=new Insets(20,0,0,0);
		c.fill= GridBagConstraints.VERTICAL;
		stagePanel.add(display,c);
		
		stage1=new JButton("Stage1");
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=1;
		//c.weighty=2;
		//c.ipady=40;
		c.insets=new Insets(60,0,0,0);
		c.fill= GridBagConstraints.VERTICAL;
		stage1.addActionListener(this);
		stagePanel.add(stage1,c);
		
		
		stage2=new JButton("Stage2");
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=2;
		//c.weighty=2;
		//c.ipady=40;
		c.insets=new Insets(25,0,0,0);
		c.fill= GridBagConstraints.VERTICAL;
		stage2.addActionListener(this);
		stagePanel.add(stage2,c);
		
		
		JPanel setupCenterPanel=new JPanel();
		setupCenterPanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(70,200,100,200);	
		c.fill= GridBagConstraints.BASELINE;
		setupCenterPanel.add(stagePanel,c);
		this.add(setupCenterPanel,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == close) {
    		JOptionPane.showMessageDialog(this, 
    				 " thank you ");
    		System.exit(0);
		}
		else if (e.getSource() == stage1) {
			KioskLogic kiosk = new KioskLogic();
			
			kiosk.ReadyKiosk();
			kiosk.StartUpKiosk();
    		this.dispose();
		}
		else if (e.getSource() == stage2) {
			CheckinController controller = new CheckinController();
			controller.StartCheckin();
    		this.dispose();
		}
		else if (e.getSource() == info) {
    		JOptionPane.showMessageDialog(this, 
    				 " thank you ");
    		System.exit(0);
		}
	}

}
