package main;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedPane extends JPanel {
	Image player, monster;
	Graphics g;
	JLabel JlbPlayer, JlbMonster;
	final int PlayerX = 230, MonsterX = 620, direction = 55;
	int xPosPlayer = PlayerX, xPosMonster = MonsterX;
	boolean isplayer = false, ismonster = false, back = false, end = false;
	
	public AnimatedPane(Image img, boolean isWhat, JLabel Jlb) {
		//isWhat: true=>player false=>monster
		this.setBackground(null);
		this.setOpaque(false);
		this.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT/2);
		if(isWhat) {
			isplayer = true;
			player = img;
			JlbPlayer = Jlb;
			JlbPlayer.setVisible(false);
		} else {
			ismonster = true;
			monster = img;
			JlbMonster = Jlb;
			JlbMonster.setVisible(false);
		}
		
		Timer timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isplayer) { //玩家
					if(back != true) { //前進
						if(xPosPlayer == 450) back = true;
						xPosPlayer += direction;
					} else if(back && end != true) { //後退
						if(xPosPlayer == 230) {
							end = true;
							JlbPlayer.setVisible(true);
						} xPosPlayer -= direction;
					}
				}
				else if(ismonster) { //敵人
					if(back != true) { //前進
						if(xPosMonster == 400) {back = true;}
						xPosMonster -= direction;
					} else if(back && end != true) { //後退
						if(xPosMonster == 620) {
							end = true;
							JlbMonster.setVisible(true);
						} xPosMonster += direction;
					}
				} repaint();
			}
		});
		timer.setRepeats(true);
		if(end) timer.setRepeats(false); //如果來回一次 則不重複
		timer.setCoalesce(true);
		timer.start();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(player != null || monster != null) {
			if(isplayer && end != true) g.drawImage(player, xPosPlayer, 180, 200, 200, this);
			else if(ismonster && end != true) g.drawImage(monster, xPosMonster, 200, 150, 150, this);
			else if(end) {
				if(isplayer) g.drawImage(player, 0, 0, 0, 0, this);
				else g.drawImage(monster, 0, 0, 0, 0, this);
			}
		} else System.out.println("Not Found Image!");
	}
}
