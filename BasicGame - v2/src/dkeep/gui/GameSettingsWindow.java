package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dkeep.controller.Controller.GameAmbient;
import dkeep.logic.Guard.Personality;
import dkeep.logic.OgreNumber;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

final class GameSettingsWindow extends JFrame {

	private static final long serialVersionUID = -7222564576740430316L;

	private final JPanel contentPane;
	private final JLabel ogreNumberLbl;
	private final JLabel guardPersonalityLbl;
	private final JComboBox<Personality> guardPersonalityCmbBox;
	private final JComboBox<GameAmbient> gameAmbientCmbBox;
	private final JComboBox<OgreNumber> ogreNumberCmbBox;
	private final JButton applyBtn;
	private final JButton closeBtn;

	private OgreNumber ogreNumber;
	private Personality guardPersonality;
	private GameAmbient gameAmbient;

	/**
	 * Create the frame.
	 */
	public GameSettingsWindow() {
		super();
		guardPersonality = Personality.ROOKIE;
		ogreNumber = OgreNumber.ONE;
		gameAmbient = GameAmbient.GUI;

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

		guardPersonalityLbl = new JLabel("Guard personality");
		guardPersonalityLbl.setFont(new Font("Arial", Font.PLAIN, 12));
		guardPersonalityLbl.setBounds(15, 55, 100, 16);
		contentPane.add(guardPersonalityLbl);

		guardPersonalityCmbBox = new JComboBox<Personality>(Personality.values());
		guardPersonalityCmbBox.setBounds(125, 50, 120, 25);
		contentPane.add(guardPersonalityCmbBox);

		applyBtn = new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ogreNumber = (OgreNumber) ogreNumberCmbBox.getSelectedItem();
				guardPersonality = (Personality) guardPersonalityCmbBox.getSelectedItem();
				gameAmbient = (GameAmbient) gameAmbientCmbBox.getSelectedItem();
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
		
		ogreNumberCmbBox = new JComboBox<OgreNumber>(OgreNumber.values());
		ogreNumberCmbBox.setBounds(122, 79, 70, 26);
		contentPane.add(ogreNumberCmbBox);

		gameAmbientCmbBox = new JComboBox<GameAmbient>(GameAmbient.values());
		gameAmbientCmbBox.setBounds(125, 13, 120, 25);
		contentPane.add(gameAmbientCmbBox);

	}

	public final OgreNumber getOgreNumber() {
		return ogreNumber;
	}

	public GameAmbient getGameAmbient() {
		return this.gameAmbient;
	}

	public final Personality getGuardPersonality() {
		return guardPersonality;
	}

	public void setOgreNumber(OgreNumber ogreNumber) {
		this.ogreNumber = ogreNumber;
	}
}
