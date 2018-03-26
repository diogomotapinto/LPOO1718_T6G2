package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dkeep.logic.WindowController;
import utilities.ImageLoader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;

public final class GameWindow extends JFrame {
	private JFrame frame;
	private GameMapPanel gamePanel;
	private WindowController wdwController;
	private JLabel contextLbl;
	private JButton btnNewGame;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private JButton btnExit;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmGameSettings;
	private JMenuItem mntmEditMap;

	/**
	 * Create the application.
	 */
	public GameWindow(WindowController windowController, ImageLoader imageLoader) {
		super();
		this.wdwController = windowController;
		initializeWindow(imageLoader);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void paintMap(ImageIcon[][] imgMap) {
		this.gamePanel.setMap(imgMap);
		resizeFrame(imgMap.length);
		gamePanel.repaint();

	}

	private void resizeFrame(int subSquareNumber) {
		int gamePanelSize = wdwController.getEditPanelSubSquareLength() * subSquareNumber;

		frame.setBounds(100, 100, 300 + gamePanelSize, 128 + gamePanelSize);
		btnNewGame.setBounds(75 + gamePanelSize, 85, 120, 25);
		btnExit.setBounds(75 + gamePanelSize, 300, 120, 25);
		btnDown.setBounds(95 + gamePanelSize, 230, 75, 30);
		btnRight.setBounds(175 + gamePanelSize, 190, 75, 30);
		btnLeft.setBounds(15 + gamePanelSize, 190, 75, 30);
		btnUp.setBounds(95 + gamePanelSize, 150, 75, 30);
		btnDown.setBounds(95 + gamePanelSize, 230, 75, 30);
		gamePanel.setBounds(0, 41, gamePanelSize, gamePanelSize);
		gamePanel.repaint();
	}

	public void setLegend(String legend) {
		this.contextLbl.setText(legend);
	}

	private void initializeWindow(ImageLoader imageLoader) {

		frame = new JFrame();
		frame.setBounds(100, 100, 700, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.newGame();
			}
		});
		btnNewGame.setBounds(475, 85, 120, 25);
		frame.getContentPane().add(btnNewGame);

		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wdwController.makeMove('w');
			}
		});
		btnUp.setBounds(495, 150, 75, 30);
		frame.getContentPane().add(btnUp);

		btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('a');
			}
		});
		btnLeft.setBounds(415, 190, 75, 30);
		frame.getContentPane().add(btnLeft);

		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('d');
			}
		});
		btnRight.setBounds(575, 190, 75, 30);
		frame.getContentPane().add(btnRight);

		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wdwController.makeMove('s');
			}
		});
		btnDown.setBounds(495, 230, 75, 30);
		frame.getContentPane().add(btnDown);

		gamePanel = new GameMapPanel(imageLoader);
		gamePanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("KEY PRESSED");
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

		gamePanel.setFont(new Font("Courier", Font.PLAIN, 18));
		gamePanel.setBounds(0, 41, 400, 400);
		gamePanel.setSubSquareLength(wdwController.getEditPanelSubSquareLength());
		frame.getContentPane().add(gamePanel);
		gamePanel.setVisible(true);

		btnExit = new JButton("Exit");
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

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		mntmGameSettings = new JMenuItem("Game Settings");
		mntmGameSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				wdwController.showWindowGameSettings();
			}
		});
		mnMenu.add(mntmGameSettings);

		mntmEditMap = new JMenuItem("Create Map");
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
