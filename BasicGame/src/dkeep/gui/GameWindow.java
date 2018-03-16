package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import dkeep.logic.Controller;
import dkeep.logic.Map;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GameWindow {
	private JFrame frame;
	private JTextField ogreTextField;
	private String map;
	private String legend;
	private JTextArea textArea;
	private Controller controller;
	private JLabel lblYouCanStart;

	/**
	 * Create the application.
	 */
	public GameWindow(Controller windowController) {
		this.controller = windowController;
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setMap(String map) {
		this.textArea.setText(map);
	}

	public void setLegend(String legend) {
		this.lblYouCanStart.setText(legend);
	}
	
	

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 551, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Number of Ogres");
		lblNewLabel.setFont(new Font("Helvetica", Font.PLAIN, 13));
		lblNewLabel.setBounds(20, 20, 116, 16);
		frame.getContentPane().add(lblNewLabel);

		ogreTextField = new JTextField();
		ogreTextField.setBounds(143, 14, 43, 26);
		frame.getContentPane().add(ogreTextField);
		ogreTextField.setColumns(10);
		//numberOfOgres = ogreTextField.getText();

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(20, 47, 122, 16);
		frame.getContentPane().add(lblGuardPersonality);

		String[] guardTypes = {"","Rookie","Drunken","Suspicious"};
		JComboBox comboBox = new JComboBox(guardTypes);
		comboBox.setBounds(143, 43, 147, 27);
		frame.getContentPane().add(comboBox);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOption pane
				 String numberOfOgres = ogreTextField.getText();
				 String personality = comboBox.getSelectedItem().toString();
				 if(numberOfOgres.equals("") || personality.equals("")) {
					 JOptionPane.showMessageDialog(frame, "Missing information", "Error", JOptionPane.ERROR_MESSAGE);
				 }else {		
				controller.newGame(numberOfOgres, personality);
				 }
			}
		});
		btnNewGame.setBounds(367, 42, 117, 29);
		frame.getContentPane().add(btnNewGame);

		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.makeMove('w');
			}
		});
		btnUp.setBounds(367, 159, 117, 29);
		frame.getContentPane().add(btnUp);

		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.makeMove('a');
			}
		});
		btnLeft.setBounds(293, 192, 117, 29);
		frame.getContentPane().add(btnLeft);

		
		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.makeMove('d');
			}
		});
		btnRight.setBounds(427, 192, 117, 29);
		frame.getContentPane().add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.makeMove('s');
			}
		});
		btnDown.setBounds(367, 224, 117, 29);
		frame.getContentPane().add(btnDown);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier", Font.PLAIN, 18));
		textArea.setBounds(20, 82, 270, 263);
		textArea.setVisible(true);
		textArea.setEditable(false);

		frame.getContentPane().add(textArea);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
				System.exit(0);
			}
		});
		btnExit.setBounds(367, 298, 117, 29);
		frame.getContentPane().add(btnExit);

		lblYouCanStart = new JLabel("You can start now");
		lblYouCanStart.setBounds(20, 357, 275, 16);
		frame.getContentPane().add(lblYouCanStart);
		

		frame.setVisible(true);
	}
}

