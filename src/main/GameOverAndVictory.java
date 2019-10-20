package main;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOverAndVictory {
	static JPanel JPGOV;
	ImageIcon ImgOver, ImgVictory;
	JLabel JlbOver;
	JLabel JlbVictory;
	
	public GameOverAndVictory() {
		JPGOV = new JPanel();
		JPGOV.setLayout(null);
		JPGOV.setBackground(null);
		JPGOV.setOpaque(false);
		JPGOV.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
		gameOver();
		victory();
	}
	
	public void gameOver() {
		ImgOver = new ImageIcon("src\\main\\image\\gameover.png");
		ImgOver.setImage(
				ImgOver.getImage().getScaledInstance(650, 450, Image.SCALE_DEFAULT));
		JlbOver = new JLabel(ImgOver);
		JlbOver.setBounds(200, 0, ImgOver.getIconWidth(), ImgOver.getIconHeight());
		JPGOV.add(JlbOver);
		JlbOver.setVisible(false);
	}
	
	public void victory() {
		ImgVictory = new ImageIcon("src\\main\\image\\victory.png");
		ImgVictory.setImage(
				ImgVictory.getImage().getScaledInstance(650, 450, Image.SCALE_DEFAULT));
		JlbVictory = new JLabel(ImgVictory);
		JlbVictory.setBounds(200, 0, ImgVictory.getIconWidth(), ImgVictory.getIconHeight());
		JPGOV.add(JlbVictory);
		JlbVictory.setVisible(false);
	}
}
