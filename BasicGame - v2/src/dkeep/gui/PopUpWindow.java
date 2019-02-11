package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

final class PopUpWindow extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2900963691202547995L;
/**
	 * 
	 */
private JFrame frame;

  /**
   * Class constructor
   */
  public PopUpWindow() {
    super();
  }

  void printErrorMessageDialog(String message) {
    JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  
  void printWarningMessageDialog(String message) {
    JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
  }

}
