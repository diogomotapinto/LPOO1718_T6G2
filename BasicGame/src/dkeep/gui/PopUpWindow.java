package dkeep.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class PopUpWindow extends JFrame {
	private JFrame frame;

	public PopUpWindow() {
		super();
	}

	public void printErrorMessageDialog(String message) {
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void printWarningMessageDialog(String message) {
		JOptionPane.showMessageDialog(frame, message, "Warning", JOptionPane.WARNING_MESSAGE);
	}

}
