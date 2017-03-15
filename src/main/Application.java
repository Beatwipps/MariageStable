package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application {

	private JFrame frmMariageStableApplication;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmMariageStableApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMariageStableApplication = new JFrame();
		frmMariageStableApplication.setTitle("Mariage Stable Application");
		frmMariageStableApplication.setBounds(100, 100, 450, 300);
		frmMariageStableApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
