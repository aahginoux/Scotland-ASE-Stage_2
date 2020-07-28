/**
 * 
 */
package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class displays the events which took place.
 * @author Panagiotis Tsamoutalis (pt45)
 *
 */
public class SummaryFrame extends JFrame  implements ActionListener{
	
	JButton close;
	JScrollPane SummaryScroll;
	JTextArea SummaryInfo;
	JLabel SummaryLabel;
	
	public SummaryFrame() {
		setTitle("DISPLAY");
    	setSize(800,800);
    	setResizable(true);
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
	private void setupCenterPanel() {
		// TODO Auto-generated method stub
		SummaryInfo = new JTextArea(35,30);
        SummaryInfo.setFont(new Font (Font.MONOSPACED, Font.PLAIN,12));
        SummaryInfo.setEditable(false);
        SummaryScroll = new JScrollPane(SummaryInfo);
        this.add(SummaryScroll,BorderLayout.CENTER);
	
	}

	/**
	 * 
	 */
	private void setupNorthPanel() {
		// TODO Auto-generated method stub
		JPanel titlePanel = new JPanel();
		SummaryLabel = new JLabel("Display Events");
		SummaryLabel.setFont( new Font(Font.MONOSPACED, Font.BOLD,30));
        titlePanel.add(SummaryLabel);   
        
        //set up north panel containing the title and add it to the frame
        JPanel northPanel = new JPanel();
        northPanel.add(titlePanel);
        this.add(northPanel, BorderLayout.NORTH); 
	}

	/**
	 * 
	 */
	private void setupSouthPanel() {
		// TODO Auto-generated method stub
		close=new JButton("Close");
		close.setPreferredSize(new Dimension(45,30));
		close.addActionListener(this);
		this.add(close,BorderLayout.SOUTH);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == close) {
    		JOptionPane.showMessageDialog(this, 
    				 " thank you ");
    		System.exit(0);
		}
	}
	public void setevents(String events) {
		SummaryInfo.setText(events);
		
	}

}
