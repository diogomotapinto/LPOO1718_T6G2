package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.controller.Controller;
import dkeep.logic.model.Guard;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public final class GameSettingsWindow extends JFrame {

	private final JPanel contentPane;
	// private static final String[] guardTypes = { "", "Rookie", "Drunken",
	// "Suspicious" };

	private final JLabel ogreNumberLbl;
	private final JTextField ogreNumberTxtFld;
	private final JLabel guardPersonalityLbl;
	private final JComboBox guardPersonalityCmbBox;
	private final JComboBox gameAmbientCmbBox;
	private final JButton applyBtn;
	private final JButton closeBtn;

	private String ogreNumber;
	private String guardPersonality;
	private String gameAmbient;

	/**
	 * Create the frame.
	 */
	public GameSettingsWindow() {
		super();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ogreNumberLbl = new JLabel("Number of Ogres");
		ogreNumberLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		ogreNumberLbl.setBounds(15, 85, 100, 16);
		contentPane.add(ogreNumberLbl);

		ogreNumberTxtFld = new JTextField();
		ogreNumberTxtFld.setColumns(10);
		ogreNumberTxtFld.setBounds(125, 80, 45, 25);
		contentPane.add(ogreNumberTxtFld);

		guardPersonalityLbl = new JLabel("Guard personality");
		guardPersonalityLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		guardPersonalityLbl.setBounds(15, 55, 100, 16);
		contentPane.add(guardPersonalityLbl);

		guardPersonalityCmbBox = new JComboBox();
		guardPersonalityCmbBox
				.setModel(new DefaultComboBoxModel(new String[] { "", "Rookie", "Drunken", "Suspicious" }));
		guardPersonalityCmbBox.setBounds(125, 50, 120, 25);
		contentPane.add(guardPersonalityCmbBox);

		applyBtn = new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogreNumber = ogreNumberTxtFld.getText();
				guardPersonality = (String) guardPersonalityCmbBox.getSelectedItem();
				gameAmbient = (String) gameAmbientCmbBox.getSelectedItem();
				setVisible(false);
			}
		});
		applyBtn.setBounds(15, 172, 100, 20);
		contentPane.add(applyBtn);

		closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		closeBtn.setBounds(15, 208, 100, 20);
		contentPane.add(closeBtn);

		JLabel gameAmbientLbl = new JLabel("Game Ambient");
		gameAmbientLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		gameAmbientLbl.setBounds(15, 19, 100, 16);
		contentPane.add(gameAmbientLbl);

		gameAmbientCmbBox = new JComboBox();
		gameAmbientCmbBox.setModel(new DefaultComboBoxModel(new String[] { "", "GUI", "Console" }));
		gameAmbientCmbBox.setBounds(125, 13, 120, 25);
		contentPane.add(gameAmbientCmbBox);

	}

	public final String getOgreNumber() {
		return ogreNumber;
	}

	public String getGameAmbient() {
		return this.gameAmbient;
	}

	public final String getGuardPersonality() {
		return guardPersonality;
	}
}
