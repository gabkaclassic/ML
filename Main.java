package Graphics;

import java.awt.*;

public class Main {

	private Frame frame;

	private Main() {

		frame = new Frame();
		frame.initialize();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					main.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}