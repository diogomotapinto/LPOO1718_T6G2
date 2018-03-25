package dkeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dkeep.logic.Controller;
import dkeep.logic.Map;
import dkeep.logic.WindowController;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;

public final class GameWindow extends JFrame {
	private JFrame frame;
	private JTextField ogreTextField;
	private String map;
	private String legend;
	private JTextArea textArea;
	private WindowController wdwController;
	private JLabel contextLbl;

	/**
	 * Create the application.
	 */
	public GameWindow(WindowController windowController) {
		super();
		this.wdwController = windowController;
		initializeWindow();
		textArea.requestFocus();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void setMap(String map) {
		this.textArea.setText(map);
	}

	public void setLegend(String legend) {
		this.contextLbl.setText(legend);
	}

	private void initializeWindow() {

		frame = new JFrame();
		frame.setBounds(100, 100, 700, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// // JOption pane
				wdwController.newGame();
			}
		});
		btnNewGame.setBounds(475, 85, 120, 25);
		frame.getContentPane().add(btnNewGame);

		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wdwController.makeMove('w');
			}
		});
		btnUp.setBounds(495, 150, 75, 30);
		frame.getContentPane().add(btnUp);

		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('a');
			}
		});
		btnLeft.setBounds(415, 190, 75, 30);
		frame.getContentPane().add(btnLeft);

		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('d');
			}
		});
		btnRight.setBounds(575, 190, 75, 30);
		frame.getContentPane().add(btnRight);

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('s');
			}
		});
		btnDown.setBounds(495, 230, 75, 30);
		frame.getContentPane().add(btnDown);

		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					wdwController.makeMove('w');
					break;
				case KeyEvent.VK_DOWN:
					wdwController.makeMove('s');
					break;
				case KeyEvent.VK_LEFT:
					wdwController.makeMove('a');
					break;
				case KeyEvent.VK_RIGHT:
					wdwController.makeMove('d');
					break;
				}
			}
		});
		textArea.setFont(new Font("Courier", Font.PLAIN, 18));
		textArea.setBounds(0, 41, 400, 400);
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
		btnExit.setBounds(475, 300, 120, 25);
		frame.getContentPane().add(btnExit);

		contextLbl = new JLabel("You can start now");
		contextLbl.setBounds(0, 9, 140, 16);
		frame.getContentPane().add(contextLbl);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmGameSettings = new JMenuItem("Game Settings");
		mntmGameSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wdwController.showWindowGameSettings();
			}
		});
		mnMenu.add(mntmGameSettings);

		JMenuItem mntmEditMap = new JMenuItem("Create Map");
		mntmEditMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wdwController.showCreateGameWindow();
			}
		});
		mnMenu.add(mntmEditMap);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		frame.setVisible(true);
	}

}
