package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public final class GameSettingsWindow extends JFrame {

  private final JPanel contentPane;
  private static final String[] guardTypes = { "", "Rookie", "Drunken", "Suspicious" };

  private final JLabel ogreNumberLbl;
  private final JTextField ogreNumberTxtFld;
  private final JLabel guardPersonalityLbl;
  private final JComboBox guardPersonalityCmbBox;
  private final JButton applyBtn;
  private final JButton closeBtn;

  private String ogreNumber;
  private String guardPersonality;

  /**
   * Create the frame.
   */
  public GameSettingsWindow() {
    super();
    this.ogreNumber = "";
    this.guardPersonality = "";

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    ogreNumberLbl = new JLabel("Number of Ogres");
    ogreNumberLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    ogreNumberLbl.setBounds(15, 63, 100, 16);
    contentPane.add(ogreNumberLbl);

    ogreNumberTxtFld = new JTextField();
    ogreNumberTxtFld.setColumns(10);
    ogreNumberTxtFld.setBounds(125, 57, 45, 25);
    contentPane.add(ogreNumberTxtFld);

    guardPersonalityLbl = new JLabel("Guard personality");
    guardPersonalityLbl.setFont(new Font("Arial", Font.PLAIN, 12));
    guardPersonalityLbl.setBounds(15, 31, 100, 16);
    contentPane.add(guardPersonalityLbl);

    guardPersonalityCmbBox = new JComboBox(guardTypes);
    guardPersonalityCmbBox.setBounds(125, 25, 120, 25);
    contentPane.add(guardPersonalityCmbBox);

    applyBtn = new JButton("Apply");
    applyBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ogreNumber = ogreNumberTxtFld.getText();
        guardPersonality = (String) guardPersonalityCmbBox.getSelectedItem();
        setVisible(false);
      }
    });
    applyBtn.setBounds(15, 160, 100, 20);
    contentPane.add(applyBtn);

    closeBtn = new JButton("Close");
    closeBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
      }
    });
    closeBtn.setBounds(15, 199, 100, 20);
    contentPane.add(closeBtn);

  }

  public final String getOgreNumber() {
    return ogreNumber;
  }

  public final String getGuardPersonality() {
    return guardPersonality;
  }
}
