package dkeep.gui;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;

final class GameWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6133340691884675448L;
	/**
	 * 
	 */
	private JFrame frame;
	private GameMapPanel gamePnl;
	private WindowController windowController;
	private JLabel gameContextLbl;
	private JButton newGameBtn;
	private JButton leftBtn;
	private JButton upBtn;
	private JButton rightBtn;
	private JButton downBtn;
	private JButton exitBtn;

	/**
	 * Create the application.
	 */
	GameWindow(WindowController windowController) {
		super();
		this.windowController = windowController;
		initializeWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void paintMap(ImageIcon[][] imgMap) {
		this.gamePnl.setMap(imgMap);
		resizeFrame(imgMap.length);
		gamePnl.requestFocus();
	}

	private void resizeFrame(int subSquareNumber) {
		int gamePanelSize = windowController.getEditPanelSubSquareLength() * subSquareNumber;

		frame.setBounds(100, 100, 300 + gamePanelSize, 128 + gamePanelSize);
		newGameBtn.setBounds(75 + gamePanelSize, 85, 120, 25);
		exitBtn.setBounds(75 + gamePanelSize, 300, 120, 25);
		downBtn.setBounds(95 + gamePanelSize, 230, 75, 30);
		rightBtn.setBounds(175 + gamePanelSize, 190, 75, 30);
		leftBtn.setBounds(15 + gamePanelSize, 190, 75, 30);
		upBtn.setBounds(95 + gamePanelSize, 150, 75, 30);
		downBtn.setBounds(95 + gamePanelSize, 230, 75, 30);
		gamePnl.setBounds(0, 41, gamePanelSize, gamePanelSize);
		gamePnl.repaint();
	}

	public void setLegend(String legend) {
		this.gameContextLbl.setText(legend);
	}

	private void initializeWindow() {

		frame = new JFrame();
		frame.setBounds(100, 100, 700, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		newGameBtn = new JButton("New Game");
		initializeNewButton(newGameBtn);
		frame.getContentPane().add(newGameBtn);

		upBtn = new JButton("Up");
		initializeUpButton(upBtn);
		frame.getContentPane().add(upBtn);

		leftBtn = new JButton("Left");
		initializeLeftBtn(leftBtn);
		frame.getContentPane().add(leftBtn);

		rightBtn = new JButton("Right");
		initializeRightBtn(rightBtn);
		frame.getContentPane().add(rightBtn);

		downBtn = new JButton("Down");
		initializeDownBtn(downBtn);
		frame.getContentPane().add(downBtn);

		gamePnl = new GameMapPanel();
		initializeGamePanel(gamePnl, windowController);
		frame.getContentPane().add(gamePnl);
		gamePnl.setVisible(true);

		exitBtn = new JButton("Exit");
		initializeExitBtn(exitBtn);
		frame.getContentPane().add(exitBtn);

		gameContextLbl = new JLabel("You can start now");
		gameContextLbl.setBounds(0, 9, 140, 16);
		frame.getContentPane().add(gameContextLbl);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuMn = new JMenu("Menu");
		menuBar.add(menuMn);

		JMenuItem gameSettingsMI = new JMenuItem("Game Settings");
		initializeJMenu(gameSettingsMI);
		menuMn.add(gameSettingsMI);

		JMenuItem editMapMI = new JMenuItem("Create Map");
		initializeJMenuItem(editMapMI);
		menuMn.add(editMapMI);

		JMenuItem saveGameMnItem = new JMenuItem("Save Game");
		initializeSaveGame(saveGameMnItem);
		menuMn.add(saveGameMnItem);

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

	private void initializeUpButton(JButton upBtn) {
		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				windowController.makeMove('w');
			}
		});
		upBtn.setBounds(495, 150, 75, 30);
	}

	private void initializeNewButton(JButton newGameBtn) {
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowController.newGame();
			}
		});
		newGameBtn.setBounds(475, 85, 120, 25);
	}

	private void initializeLeftBtn(JButton leftBtn) {
		leftBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowController.makeMove('a');
			}
		});
		leftBtn.setBounds(415, 190, 75, 30);
	}

	private void initializeRightBtn(JButton rightBtn) {
		rightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowController.makeMove('d');
			}
		});
		rightBtn.setBounds(575, 190, 75, 30);
	}

	private void initializeDownBtn(JButton downBtn) {
		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowController.makeMove('s');
			}
		});
		downBtn.setBounds(495, 230, 75, 30);
	}

	private void initializeExitBtn(JButton exitBtn) {
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit");
				System.exit(0);
			}
		});
		exitBtn.setBounds(475, 300, 120, 25);
	}

	private void initializeJMenu(JMenuItem gameSettingsMI) {
		gameSettingsMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				windowController.showWindowGameSettings();
			}
		});
	}

	private void initializeSaveGame(JMenuItem saveGameMnItem) {
		saveGameMnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				windowController.serialize();
			}
		});
	}

	private void initializeGamePanel(GameMapPanel gamePnl, WindowController windowController) {
		gamePnl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					windowController.makeMove('w');
					break;
				case KeyEvent.VK_DOWN:
					windowController.makeMove('s');
					break;
				case KeyEvent.VK_LEFT:
					windowController.makeMove('a');
					break;
				case KeyEvent.VK_RIGHT:
					windowController.makeMove('d');
					break;
				default:
					break;
				}
			}
		});

		gamePnl.setFont(new Font("Courier", Font.PLAIN, 18));
		gamePnl.setBounds(0, 41, 400, 400);
		gamePnl.setSubSquareLength(windowController.getEditPanelSubSquareLength());
	}

	private void initializeJMenuItem(JMenuItem editMapMI) {
		editMapMI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				windowController.showCreateGameWindow();
			}
		});
	}

	void closeWindow() {
		this.frame.dispose();
	}
}
