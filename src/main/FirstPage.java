package main;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class FirstPage {
	JLayeredPane JPFirstPage;
	JPanel PanelBG, PanelComponent;
	ImageIcon IamgeBG;
	JLabel JlbBG, JlbStart, JlbSetting, JlbExit;
	ImageIcon ImgStart, ImgSetting, ImgExit;
	static AudioPlay ap;
	File File;
	
	public FirstPage() {
		JPFirstPage = new JLayeredPane();
		JPFirstPage.setLayout(null);
        
        SetPanelBG();
        
        PanelComponent = new JPanel();
        PanelComponent.setLayout(null);
        PanelComponent.setBackground(null);
        PanelComponent.setOpaque(false);
        PanelComponent.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        SetPanelLeft();
        SetPanelRight();
        JPFirstPage.add(PanelComponent, JLayeredPane.PALETTE_LAYER);
        
        PlayBGM();
	}
	
	/*����I������*/
	public static void PlayBGM() {
		ap = new AudioPlay(new File("src\\main\\audio\\main.mid"));
        ap.playmidi(true); //���ּ���
	}
	
	/*�I��*/
	private void SetPanelBG() {
		PanelBG = new JPanel();
		PanelBG.setLayout(null);
		PanelBG.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
    	IamgeBG = new ImageIcon("src\\main\\image\\main_bg.jpg");
    	IamgeBG.setImage(IamgeBG.getImage()
    			.getScaledInstance(MainFrame.WIDTH, MainFrame.HEIGHT, Image.SCALE_DEFAULT));
    	JlbBG = new JLabel(IamgeBG);
    	JlbBG.setBounds(0, 0, IamgeBG.getIconWidth(), IamgeBG.getIconHeight());
    	PanelBG.add(JlbBG);
    	JPFirstPage.add(PanelBG, JLayeredPane.DEFAULT_LAYER);
	}
	
	/*���U��}�l���s*/
	private void SetPanelLeft() {
		ImgStart = new ImageIcon("src\\main\\image\\start.png");
		ImgStart.setImage(
				ImgStart.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
		JlbStart = new JLabel(ImgStart);
		JlbStart.setBounds(30, 500, ImgStart.getIconWidth(), ImgStart.getIconHeight());
		JlbStart.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						30, 500, ImgStart.getIconWidth(), ImgStart.getIconHeight());}
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						20, 490, ImgStart.getIconWidth(), ImgStart.getIconHeight());}
			public void mouseClicked(MouseEvent e) { //������C���e��
				ap.stopmidi();
				MainFrame.cards.show(MainFrame.content, "2");
				main.SecondPage.isFirstStart(true);
				main.SecondPage.LevelChanged(1);
			}
		});
		PanelComponent.add(JlbStart);
	}
	
	/*�k�W����s*/
	private void SetPanelRight() {
		//���}���s
		ImgExit = new ImageIcon("src\\main\\image\\main_exit.png");
		ImgExit.setImage(ImgExit.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));
		JlbExit = new JLabel(ImgExit);
		JlbExit.setBounds(865, 50, ImgExit.getIconWidth(), ImgExit.getIconHeight());
		JlbExit.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource())
					.setBounds(865, 50, ImgExit.getIconWidth(), ImgExit.getIconHeight());}
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource())
					.setBounds(870, 45, ImgExit.getIconWidth(), ImgExit.getIconHeight());}
			public void mouseClicked(MouseEvent e) {System.exit(0);}
		});
		PanelComponent.add(JlbExit);
		
		//�]�w���s
		ImgSetting = new ImageIcon("src\\main\\image\\main_setting.png");
		ImgSetting.setImage(
				ImgSetting.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));
		JlbSetting = new JLabel(ImgSetting);
		JlbSetting.setBounds(870, 180, ImgSetting.getIconWidth(), ImgSetting.getIconHeight());
		JlbSetting.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource())
					.setBounds(870, 180, ImgSetting.getIconWidth(), ImgSetting.getIconHeight());}
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource())
					.setBounds(875, 175, ImgSetting.getIconWidth(), ImgSetting.getIconHeight());}
			public void mouseClicked(MouseEvent e) {}
		});
		//component_panel.add(setting_jlb);
	}
}
