package dkeep.gui;

import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dkeep.logic.WindowController;
import dkeep.logic.model.Position;
import utilities.ImageLoader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class CreateMapWindow extends JFrame {

	private WindowController wdwController;
	private EditMapPanel editPanel;
	private JLabel heroLbl;
	private JButton applyBtn;
	private JButton exitBtn;
	private JLabel mapSizeLbl;
	private JLabel wallLbl;
	private JLabel ogreLbl;
	private JLabel keyLbl;
	private JLabel doorLbl;
	private JLabel blankSpaceLbl;
	private boolean iconClicked;
	private JSlider slider;

	/**
	 * Create the frame.
	 */
	public CreateMapWindow(WindowController wdwController, ImageLoader imageLoader) {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.wdwController = wdwController;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 525);
		getContentPane().setLayout(null);

		editPanel = new EditMapPanel(imageLoader);
		editPanel.setBackground(Color.WHITE);
		editPanel.setBounds(0, 69, 400, 400);
		getContentPane().add(editPanel);
		editPanel.setLayout(null);
		editPanel.setMatrixSize(10);
		editPanel.setPanelLength(editPanel.getWidth());
		editPanel.setSubSquareLength(editPanel.getWidth() / 10);

		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		exitBtn.setBounds(559, 424, 104, 29);
		getContentPane().add(exitBtn);

		applyBtn = new JButton("Apply");
		applyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// wdwController.generateMap(editMap);
			}
		});
		applyBtn.setBounds(433, 424, 104, 29);
		getContentPane().add(applyBtn);

		mapSizeLbl = new JLabel("Map Size");
		mapSizeLbl.setBounds(0, 26, 69, 27);
		getContentPane().add(mapSizeLbl);

		heroLbl = new JLabel();
		heroLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getHeroImg());
				editPanel.setPaintIcon(iconClicked);
				// editPanel.setMatrixCoordinates(e.getX(), e.getY());
			}
		});
		heroLbl.setBounds(459, 65, 40, 40);
		heroLbl.setIcon(imageLoader.getHeroImg());
		getContentPane().add(heroLbl);

		ogreLbl = new JLabel();
		ogreLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getOgreImg());
				editPanel.setPaintIcon(iconClicked);
			}
		});
		ogreLbl.setBounds(575, 65, 40, 40);
		ogreLbl.setIcon(imageLoader.getOgreImg());
		getContentPane().add(ogreLbl);

		doorLbl = new JLabel();
		doorLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getDoorImg());
				editPanel.setPaintIcon(iconClicked);
			}
		});
		doorLbl.setBounds(459, 120, 40, 40);
		doorLbl.setIcon(imageLoader.getDoorImg());
		getContentPane().add(doorLbl);

		keyLbl = new JLabel();
		keyLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getKeyImg());
				editPanel.setPaintIcon(iconClicked);
			}
		});
		keyLbl.setBounds(575, 120, 40, 40);
		keyLbl.setIcon(imageLoader.getKeyImg());
		getContentPane().add(keyLbl);

		wallLbl = new JLabel();
		wallLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getWallImg());
				editPanel.setPaintIcon(iconClicked);
			}
		});
		wallLbl.setBounds(459, 175, 40, 40);
		wallLbl.setIcon(imageLoader.getWallImg());
		getContentPane().add(wallLbl);

		blankSpaceLbl = new JLabel();
		blankSpaceLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				iconClicked = true;
				editPanel.setImage(imageLoader.getBlankSpaceImg());
				editPanel.setPaintIcon(iconClicked);
				// editPanel.setMatrixCoordinates(e.getX(), e.getY());
			}
		});
		blankSpaceLbl.setBounds(575, 175, 40, 40);
		blankSpaceLbl.setIcon(imageLoader.getBlankSpaceImg());
		getContentPane().add(blankSpaceLbl);

		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = slider.getValue();
				int editPanelSize = editPanel.getSubSquareLength() * slider.getValue();
				setBounds(100, 100, 300 + editPanelSize, 125 + editPanelSize);

				editPanel.setBounds(0, 69, editPanelSize, editPanelSize);
				editPanel.setEditMapSize(slider.getValue());
				// if(value!=10) {
				// editPanel.setPaintIcon(true);
				// }
				// editPanel.repaint();

				heroLbl.setBounds(59 + editPanelSize, 65, 40, 40);
				ogreLbl.setBounds(175 + editPanelSize, 65, 40, 40);

				doorLbl.setBounds(59 + editPanelSize, 120, 40, 40);
				keyLbl.setBounds(175 + editPanelSize, 120, 40, 40);
				wallLbl.setBounds(59 + editPanelSize, 175, 40, 40);
				blankSpaceLbl.setBounds(175 + editPanelSize, 175, 40, 40);

				applyBtn.setBounds(33 + editPanelSize, 24 + editPanelSize, 104, 29);
				exitBtn.setBounds(159 + editPanelSize, 24 + editPanelSize, 104, 29);
			}
		});
		slider.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(5);
		slider.setToolTipText("");
		slider.setPaintLabels(true);
		slider.setValue(10);
		slider.setSnapToTicks(true);
		slider.setMaximum(15);
		slider.setMinimum(5);
		slider.setBounds(68, 26, 200, 35);
		getContentPane().add(slider);

	}

	public void setFrameVisible() {
		setVisible(true);
	}
}
