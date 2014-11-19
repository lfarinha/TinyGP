import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.script.*;

public class Interface {
	
	static int varnumber, fitnesscases;
	static double [][] targets;
	
	boolean variable;
	double data;
	
	public String formula;
	

	private JFrame frame;
	private JTextField functionArea;

	

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton solve = new JButton("Solve!");
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ScriptEngineManager factory = new ScriptEngineManager();
				ScriptEngine engine = factory.getEngineByName("JavaScript");
				
				formula = functionArea.getText();
				int dataSetSize = 63; // This is part of the settings, this is the number of fitness cases -- The bigger the number the better the fitness, but, the program takes more time...
				double minValue=0;
				
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
		     			
		        tiny_gp.main(new String[0]);
		        
			}
		});
		solve.setBounds(314, 34, 89, 23);
		frame.getContentPane().add(solve);
		
		functionArea = new JTextField();
		functionArea.setBounds(67, 35, 196, 20);
		frame.getContentPane().add(functionArea);
		functionArea.setColumns(10);
		
		
	}//end of initialize
	
}// EOF
