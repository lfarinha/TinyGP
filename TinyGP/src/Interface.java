import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;



import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.StringTokenizer;

import javax.script.*;
import javax.swing.JTextArea;

import com.sun.java_cup.internal.runtime.Scanner;

import java.awt.Font;
import javax.swing.JTextPane;


public class Interface {
	
	static int varnumber, fitnesscases;
	static double [][] targets;
	
	boolean variable;
	double data;
	
	public String formula, Solution;
	

	JFrame frame;
	private JTextField functionArea;
	private JLabel lblSettings;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		  EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Interface window = new Interface();
						window.frame.setVisible(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
										
				}
			});


	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
				
		frame = new JFrame();
		frame.setBounds(100, 100, 395, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton solve = new JButton("Solve!");
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ScriptEngineManager factory = new ScriptEngineManager();
				ScriptEngine engine = factory.getEngineByName("JavaScript");
				
				formula = functionArea.getText();
				int dataSetSize = 63; // This is part of the settings, this is the number of fitness cases -- The bigger the number the better the fitness, but, the program takes more time...
				
				try {
				
				FileOutputStream outputStream = null;

				outputStream = new FileOutputStream("problem.dat");
		        
		        DataOutputStream out = new DataOutputStream(outputStream);
		        
		        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

		        bw.write("1 100 -5 5 "+dataSetSize+"\n");
				
				for(double x=0; x<=dataSetSize; x++){
					String adjustedFormula = formula.replace("x", Double.toString(x));

						double y = (double) engine.eval(adjustedFormula);
						//System.out.println("x = "+x+" ==> "+formula+" = "+result);
				           
						bw.write("" + x + " " + y + "\n");
					}
				
				bw.flush();

				bw.close();
				}catch(Exception E){
				}
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
							Thread.sleep(10); // time until the thread goes to sleep
							tiny_gp.main(new String[0]);
						
						return null;	
					}
										
				};		     		
				worker.execute();					
			}
		});
		
		solve.setBounds(273, 34, 89, 23);
		frame.getContentPane().add(solve);
		
		functionArea = new JTextField();
		functionArea.setBounds(67, 35, 196, 20);
		frame.getContentPane().add(functionArea);
		functionArea.setColumns(10);
		
		/* This is the JTextField in which the settings are displayed --displaySettings-- */
		
		JTextArea displaySettings = new JTextArea();
		displaySettings.setToolTipText("Setting values for Tiny GP");
		displaySettings.setEditable(false);
		displaySettings.setBounds(10, 132, 324, 225);
		frame.getContentPane().add(displaySettings);
					
		String settings = ("            -- TINY GP (Java version) --\n\n"+"Seed: "+tiny_gp.DEPTH +"\r\nDepth: "+tiny_gp.DEPTH +"\r\nMax Lenght: "+tiny_gp.MAX_LEN+"\r\nPopulation Size: "+tiny_gp.POPSIZE+"\r\nCrossover probability: "+tiny_gp.CROSSOVER_PROB+"\r\nMutation probability "+tiny_gp.PMUT_PER_NODE+"\r\nmin random: "+tiny_gp.minrandom+"\r\nmax random "+tiny_gp.maxrandom+"\r\nGenerations: "+tiny_gp.GENERATIONS+"\r\nTree size: "+tiny_gp.TSIZE);
		
	    displaySettings.setText(settings);
	    
		lblSettings = new JLabel("Settings");
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSettings.setBounds(10, 76, 200, 50);
		frame.getContentPane().add(lblSettings);
		
		
		


	}//end of initialize
}// EOF
