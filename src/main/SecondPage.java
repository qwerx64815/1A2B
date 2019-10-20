package main;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SecondPage extends MouseAdapter implements ActionListener {
	private static GameOverAndVictory GOV;
	private Toolkit Kit = Toolkit.getDefaultToolkit();
	static boolean isFirstStart = true;
	
	/*�W���O*/
	private static JLayeredPane PanelTop;
	private static JPanel[] PanelBGArr = new JPanel[5];
	private JLabel RoundCurrent, RoundSurplus, JlbClock, TextReplay, TextSettings, JlbPlayer, RoundCurrentBG, 
		RoundSurplusBG, ClockBG, PlayerHealthBG, EnemyHealthBG;
	private static JLabel RoundCurrentNum, RoundSurplusNum, TextTime, PlayerHealthText, EnemyHealthText;
	private JLabel[] JlbBGArr = new JLabel[5], JlbLevelSelect;
	private static JLabel[] JlbEnemyArr = new JLabel[5];
	private static JProgressBar[] JbarHealthArr = new JProgressBar[2];
	private static DecimalFormat df = new DecimalFormat("00"); //�Ʀr�榡
	private static int min, sec, BGImgCurrentNum, EnemyImgCurrentNum, BGMCurrentNum;
	private static TimeBlock tb; static AudioPlay ap; File file;
	private static String[] FileNameArr = new String[5];
	private static Image ImgPlayer, ImgEnemy;
	private static Image[] ImgEnemyArr = new Image[5];
	private ImageIcon IconClock, IconReplay, IconSettings, IconPlayer, IconTopTextBG;
	private ImageIcon[] IconBGArr = new ImageIcon[5], IconEnemyArr = new ImageIcon[5];
	
	/*�U���O*/
	public JPanel JPSecondPage, JPBottom, JPBottomLeft, JPBottomRight, JPAnswer;
	private static JPanel JPQuestion;
	private JPanel[] JPQuestionArr = new JPanel[5];
	private JLabel JlbTipA, JlbTipB, JlbTipRecord;
	private static JLabel JlbTipANum, JlbTipBNum;
	private static JLabel[] JlbQuestionArr;
	private JButton BtnAttack, BtnClear;
	private static JTextArea TextRecord;
	private static CardLayout Cards;
	private Font FontQuestion;
	private int CardsNum, EnemyAtkNum;
	private static int Level, PlayerHealthNum, PlayerAtkNum, EnemyHealthNum;
	private static int[] QuestionNumArr, AnswerNumArr;
	private boolean isGameOver;
	private static Stage Stage; Enemy Enemy; Player Player; static Number Number;
	
	private void Init() {
		//�ŧi����
		Stage = new Stage(); Number = new Number();
		Player = new Player(); Enemy = new Enemy();
		
		//���d���šB�ɶ������C������
		Level = 1;
		min = Stage.MapLevel.get(String.valueOf(1))[0];
		sec = Stage.MapLevel.get(String.valueOf(1))[1];
		
		isGameOver = false;
		
		//Player�PEnemy�ƭ�
		PlayerHealthNum = 100;
		PlayerAtkNum = Stage.MapLevel.get(String.valueOf(1))[4];
		EnemyHealthNum = Stage.MapLevel.get(String.valueOf(1))[3];
		EnemyAtkNum = 5;
		EnemyImgCurrentNum = 0;
		
		//�H���ͦ��D��
		QuestionNumArr = new int[Stage.MapLevel.get(String.valueOf(1))[2]];
		AnswerNumArr = new int[Stage.MapLevel.get(String.valueOf(1))[2]];
		Number.random(QuestionNumArr);
		System.out.println(QuestionNumArr[0] + " " + QuestionNumArr[1]);
		
		//�ثe���d�I����
		BGImgCurrentNum = 0;
		
		//�ɶ��I����
		IconTopTextBG = new ImageIcon("src\\main\\image\\white.png");
		IconTopTextBG.setImage(
				IconTopTextBG.getImage().getScaledInstance(145, 30, Image.SCALE_DEFAULT));
		
		//�פJ�I�����֤�Enemy�Ϥ�
		ImgPlayer = Toolkit.getDefaultToolkit().getImage("src\\main\\image\\player.gif");
		for(int i = 0; i < 5; ++i) {
			FileNameArr[i] = "src\\main\\audio\\stage"+(i+1)+".mid";
			ImgEnemyArr[i] = Toolkit.getDefaultToolkit().getImage("src\\main\\image\\monster" + (i+1) + ".gif");
		}
		
		//���񭵼֤Ϋ��wEnemy�Ϥ�
		BGMCurrentNum = 0;
		ap = new AudioPlay(new File(FileNameArr[0]));
		ImgEnemy = ImgEnemyArr[0];
	}
	
	public static void isFirstStart(boolean isFirstStart) {SecondPage.isFirstStart = isFirstStart;}
	
	public SecondPage() throws InterruptedException {
		Init();
		
		JPSecondPage = new JPanel();
		JPSecondPage.setBackground(Color.DARK_GRAY);
		//GridLayout(�C, ��, �������Z, �������Z)
		JPSecondPage.setLayout(new GridLayout(2, 1, 0, 0));
        
		GOV = new GameOverAndVictory();
		SetPanelTopAndBottom();
	}
	
	private void SetPanelTopAndBottom() throws InterruptedException {
		TopPanel(); //�W�譱�O
		PanelBottom(); //�U�譱�O
	}
	
	/*�W�譱�O*/
	private void TopPanel() throws InterruptedException {
		PanelTop = new JLayeredPane();
        PanelTop.setLayout(null);
        
        SetBG(); //�I��
        SetLeftTopTextArea(); //���W���r�϶�
        SetTimeArea(); //�W�����ɶ���r�϶�
        SetRightTopBtn(); //�k�W��ϥܫ��s
        SetCenterArea(); //�����ʵe�����϶�
        SetDebugBtn(); //Debug
        
        PanelTop.add(GameOverAndVictory.JPGOV, JLayeredPane.POPUP_LAYER);
        JPSecondPage.add(PanelTop);
	}
	
	/*�I��*/
	private void SetBG() {
		for(int i = 0; i < 5; ++i) {
        	PanelBGArr[i] = new JPanel();
        	PanelBGArr[i].setLayout(null);
        	PanelBGArr[i].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT/2);
        	IconBGArr[i] = new ImageIcon("src\\main\\image\\bg" + (i+1) + ".jpg");
        	IconBGArr[i].setImage(
        			IconBGArr[i].getImage().getScaledInstance(
        					MainFrame.WIDTH, MainFrame.HEIGHT/2, Image.SCALE_DEFAULT));
        	JlbBGArr[i] = new JLabel(IconBGArr[i]);
        	JlbBGArr[i].setBounds(
        			0, 0, IconBGArr[i].getIconWidth(), IconBGArr[i].getIconHeight());
        	PanelBGArr[i].add(JlbBGArr[i]);
        }
		PanelTop.add(PanelBGArr[BGImgCurrentNum], JLayeredPane.DEFAULT_LAYER);
        System.out.println(PanelTop.getComponentZOrder(PanelBGArr[BGImgCurrentNum]));
	}
	
	/*���W���r�϶�*/
	private void SetLeftTopTextArea() {
		RoundCurrentBG = new JLabel(IconTopTextBG);
		RoundCurrentBG.setBounds(
				20, 20, IconTopTextBG.getIconWidth(), IconTopTextBG.getIconHeight());
		PanelTop.add(RoundCurrentBG, JLayeredPane.PALETTE_LAYER);
		
		RoundCurrent = new JLabel("�ثe�^�X�G");
        RoundCurrent.setFont(new Font("�L�n������", Font.BOLD, 25));
        RoundCurrent.setBounds(20, 20, 125, 30);
        PanelTop.add(RoundCurrent, JLayeredPane.MODAL_LAYER);
        
        RoundCurrentNum = new JLabel("1");
        RoundCurrentNum.setFont(new Font("�L�n������", Font.BOLD, 25));
        RoundCurrentNum.setBounds(145, 20, 15, 30);
        PanelTop.add(RoundCurrentNum, JLayeredPane.MODAL_LAYER);
        
        RoundSurplusBG = new JLabel(IconTopTextBG);
        RoundSurplusBG.setBounds(230, 20, IconTopTextBG.getIconWidth()
			, IconTopTextBG.getIconHeight());
		PanelTop.add(RoundSurplusBG, JLayeredPane.PALETTE_LAYER);
        
        RoundSurplus = new JLabel("�Ѿl�^�X�G");
        RoundSurplus.setFont(new Font("�L�n������", Font.BOLD, 25));
        RoundSurplus.setBounds(230, 20, 125, 30);
        PanelTop.add(RoundSurplus, JLayeredPane.MODAL_LAYER);
        
        RoundSurplusNum = new JLabel("4");
        RoundSurplusNum.setFont(new Font("�L�n������", Font.BOLD, 25));
        RoundSurplusNum.setBounds(355, 20, 15, 30);
        PanelTop.add(RoundSurplusNum, JLayeredPane.MODAL_LAYER);
	}
	
	/*�W�����ɶ���r�϶�*/
	private void SetTimeArea() {
		ClockBG = new JLabel(IconTopTextBG);
		ClockBG.setBounds(525, 20, IconTopTextBG.getIconWidth(), IconTopTextBG.getIconHeight());
		PanelTop.add(ClockBG, JLayeredPane.PALETTE_LAYER);
		
		IconClock = new ImageIcon("src\\main\\image\\clock.png");
        IconClock.setImage(
        		IconClock.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        JlbClock = new JLabel(IconClock);
        JlbClock.setBounds(530, 20, IconClock.getIconWidth(), IconClock.getIconHeight());
        PanelTop.add(JlbClock, JLayeredPane.MODAL_LAYER);
        tb = new TimeBlock();
        TextTime = new JLabel(df.format(min) + " : " + df.format(sec));
        TextTime.setFont(new Font("�L�n������", Font.BOLD, 27));
        TextTime.setBounds(570, 20, 120, 30);
		tb.AddListener(new TimeBlock.Listener() {
			public void timeOut() {
				isGameOver = true;
				System.out.println("�ɶ���I");
				Clear();
			}
			public void onChange(long min, long sec) {
				TextTime.setText(df.format(min) + " : " + df.format(sec));}
		});
		tb.StartTimer(min, sec); //�˼Ʈɶ� (��, ��)
		PanelTop.add(TextTime, JLayeredPane.MODAL_LAYER);
	}
	
	/*�k�W��ϥܫ��s*/
	private void SetRightTopBtn() {
		IconReplay = new ImageIcon("src\\main\\image\\replay-arrow.png");
        IconReplay.setImage(IconReplay.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        TextReplay = new JLabel(IconReplay);
        TextReplay.setBounds(
        		MainFrame.WIDTH - 140, 20, IconReplay.getIconWidth(), IconReplay.getIconHeight());
        TextReplay.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						MainFrame.WIDTH - 140, 20, IconReplay.getIconWidth(), IconReplay.getIconHeight());}
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						MainFrame.WIDTH - 130, 10, IconReplay.getIconWidth(), IconReplay.getIconHeight());}
			public void mouseClicked(MouseEvent e) {
				isGameOver = false;
				LevelChanged(Level);
			}
		});
        PanelTop.add(TextReplay, JLayeredPane.PALETTE_LAYER);
        
        IconSettings = new ImageIcon("src\\main\\image\\settings-gears.png");
        IconSettings.setImage(
        		IconSettings.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        TextSettings = new JLabel(IconSettings);
        TextSettings.setBounds(
        		MainFrame.WIDTH - 80, 20, IconSettings.getIconWidth(), IconSettings.getIconHeight());
        TextSettings.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						MainFrame.WIDTH - 80, 20, IconSettings.getIconWidth(), IconSettings.getIconHeight());}
			public void mouseEntered(MouseEvent e) {
				((JLabel)e.getSource()).setBounds(
						MainFrame.WIDTH - 70, 10, IconSettings.getIconWidth(), IconSettings.getIconHeight());}
			public void mouseClicked(MouseEvent e) {
				final JFrame menu = new JFrame("�]�w���");
				menu.setBounds(MainFrame.WIDTH-50, 100, 200, 150);
				menu.setResizable(false);
				menu.setAlwaysOnTop(true);
				menu.setLayout(null);
				JButton backmain = new JButton("��^�}�l�e��");
				backmain.setBounds(35, 20, 115, 30);
				backmain.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainFrame.cards.show(MainFrame.content, "1");
						isGameOver = false;
						Level = 1;
						isFirstStart = true;
						BGMStop();
						ap = null;
						Clear();
						main.FirstPage.PlayBGM();
					}
				});
				menu.add(backmain);
				JButton exit = new JButton("���}�C��");
				exit.setBounds(35, 60, 115, 30);
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {System.exit(0);}});
				menu.add(exit);
				menu.setVisible(true);
			}
		});
        PanelTop.add(TextSettings, JLayeredPane.PALETTE_LAYER);
	}
	
	/*�����ʵe�����϶�*/
	private void SetCenterArea() {
        //��q��
        int bar_x = 200;
        for(int i = 0; i < JbarHealthArr.length; ++i) {
        	JbarHealthArr[i] = new JProgressBar();
            JbarHealthArr[i].setStringPainted(false); //��ܶi�ױ��T��
            JbarHealthArr[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 4)); //�]�m�O�_������
            JbarHealthArr[i].setForeground(new Color(255, 0, 0)); //�]�m�e����
            JbarHealthArr[i].setBackground(new Color(188, 190, 194)); //�]�m�I����
            JbarHealthArr[i].setBounds(bar_x, 150, 200, 23);
            JbarHealthArr[i].setValue(100);
            PanelTop.add(JbarHealthArr[i], JLayeredPane.PALETTE_LAYER);
            bar_x += 400;
        }
        
        //Player��q��r�]�w
      	PlayerHealthBG = new JLabel(IconTopTextBG);
      	PlayerHealthBG.setBounds(
      			230, 110, IconTopTextBG.getIconWidth(), IconTopTextBG.getIconHeight());
      	PanelTop.add(PlayerHealthBG, JLayeredPane.PALETTE_LAYER);
      		
      	PlayerHealthText = new JLabel("HP�G" + String.valueOf(PlayerHealthNum));
        PlayerHealthText.setFont(new Font("�L�n������", Font.BOLD, 25));
        PlayerHealthText.setBounds(250, 100, 130, 50);
        PanelTop.add(PlayerHealthText, JLayeredPane.MODAL_LAYER);
        
        //Player�Ϥ�
        IconPlayer = new ImageIcon("src\\main\\image\\player.gif");
        IconPlayer.setImage(
        		IconPlayer.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        JlbPlayer = new JLabel(IconPlayer);
        JlbPlayer.setBounds(
        		230, 180, IconPlayer.getIconWidth(), IconPlayer.getIconHeight());
        PanelTop.add(JlbPlayer, JLayeredPane.PALETTE_LAYER);
        
        //Enemy��q��r�]�w
        EnemyHealthBG = new JLabel(IconTopTextBG);
        EnemyHealthBG.setBounds(
        		625, 110, IconTopTextBG.getIconWidth(), IconTopTextBG.getIconHeight());
		PanelTop.add(EnemyHealthBG, JLayeredPane.PALETTE_LAYER);
        
        EnemyHealthText = new JLabel("HP�G" + String.valueOf(EnemyHealthNum));
        EnemyHealthText.setFont(new Font("�L�n������", Font.BOLD, 25));
        EnemyHealthText.setBounds(650, 100, 130, 50);
        PanelTop.add(EnemyHealthText, JLayeredPane.MODAL_LAYER);
        
        //�ĤH�Ϥ�
        for(int i = 0; i < 5; ++i) {
        	IconEnemyArr[i] = new ImageIcon("src\\main\\image\\monster" + (i+1) + ".gif");
            IconEnemyArr[i].setImage(
            		IconEnemyArr[i].getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
            JlbEnemyArr[i] = new JLabel(IconEnemyArr[i]);
            JlbEnemyArr[i].setBounds(
            		620, 200, IconEnemyArr[i].getIconWidth(), IconEnemyArr[i].getIconHeight());
            PanelTop.add(JlbEnemyArr[i], JLayeredPane.PALETTE_LAYER);
            JlbEnemyArr[i].setVisible(false);
		}
		JlbEnemyArr[0].setVisible(true);
	}
	
	/*Debug*/
	private void SetDebugBtn() {
        JButton BtnDebug = new JButton("Debug");
        BtnDebug.setBounds(MainFrame.WIDTH - 270, 15, 70, 50);
        PanelTop.add(BtnDebug, JLayeredPane.PALETTE_LAYER);
        
        final JFrame JFrameLevelSelect = new JFrame("���Level");
        JFrameLevelSelect.setBounds(MainFrame.WIDTH - 150, 100, 180, 250);
        JFrameLevelSelect.setResizable(false);
        JFrameLevelSelect.setAlwaysOnTop(true);
        JFrameLevelSelect.setLayout(null);
        
        JlbLevelSelect = new JLabel[5];
        int SelectY = 10;
        for(int i = 0; i < JlbLevelSelect.length; ++i) {
        	JlbLevelSelect[i] = new JLabel("Level " + String.valueOf(i+1));
        	JlbLevelSelect[i].setFont(new Font("�L�n������" , Font.BOLD, 25));
        	JlbLevelSelect[i].setBounds(35, SelectY, 90, 30);
        	JlbLevelSelect[i].addMouseListener(new MouseAdapter() {
        		public void mouseEntered(MouseEvent e) {
        			((JLabel)e.getSource()).setBorder(
        					BorderFactory.createLineBorder(Color.DARK_GRAY, 3));}
        		public void mouseExited(MouseEvent e) {((JLabel)e.getSource()).setBorder(null);}
            	public void mouseClicked(MouseEvent e) {
            		if(e.getComponent() == JlbLevelSelect[0]) Level = 1;
    				else if(e.getComponent() == JlbLevelSelect[1]) Level = 2;
    				else if(e.getComponent() == JlbLevelSelect[2]) Level = 3;
    				else if(e.getComponent() == JlbLevelSelect[3]) Level = 4;
    				else if(e.getComponent() == JlbLevelSelect[4]) Level = 5;
    				LevelChanged(Level); isGameOver = false;
    				System.out.println("Level " + Level);
            	}
            });
        	JFrameLevelSelect.add(JlbLevelSelect[i]);
        	SelectY += 40;
        }
        BtnDebug.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrameLevelSelect.setVisible(true);}});
	}
	
	/*���ִ`������*/
	private static void BGMLoopPlay(boolean loop) {ap.playmidi(loop);}
	
	/*���ְ���*/
	private static void BGMStop() {ap.stopmidi();}
	
	/*���a�ʵe*/
	private AnimatedPane AnimatePlayer(Image img, JLabel player_jlb) {
		return new AnimatedPane(img, true, player_jlb);}
	
	/*�ĤH�ʵe*/
	private AnimatedPane AnimateEnemy(Image img, JLabel monster_jlb) {
		return new AnimatedPane(img, false, monster_jlb);}
	
	/*�U�譱�O*/
	private void PanelBottom() {
		JPBottom = new JPanel();
        JPBottom.setLayout(null);
        
        /*����ާ@�϶�*/
        JPBottomLeft = new JPanel();
        JPBottomLeft.setLayout(null);
        JPBottomLeft.setBounds(0, 0, MainFrame.WIDTH - (MainFrame.WIDTH / 4), MainFrame.HEIGHT / 2);
        BottomLeftContain();
        JPBottom.add(JPBottomLeft);
        
        /*�k������϶�*/
        JPBottomRight = new JPanel();
        JPBottomRight.setLayout(null);
        JPBottomRight.setBounds(
        		MainFrame.WIDTH - (MainFrame.WIDTH/4), 0, (MainFrame.WIDTH/4) - 16, 
        		(MainFrame.HEIGHT-(MainFrame.HEIGHT/2)) - 20);
        //bottom_right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        BottomRightContain();
        JPBottom.add(JPBottomRight);
        
        JPSecondPage.add(JPBottom);
	}
	
	/*�U�襪���O*/
	private void BottomLeftContain() {
		/*�W��Ʀr�϶����O*/
		FontQuestion = new Font("�L�n������", Font.BOLD, 40);
		JPQuestion = new JPanel();
		JPQuestion.setBounds(
				0, 0, MainFrame.WIDTH - (MainFrame.WIDTH/4), ((MainFrame.HEIGHT/2)/2) - 30);
		//question_panel_contain.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		Cards = new CardLayout();
		JPQuestion.setLayout(Cards);
		
		/*�D�ؼƦr�]�w*/
		JlbQuestionArr = new JLabel[20]; //2+3+4+5+6=20
		for(int i = 0; i < JlbQuestionArr.length; ++i) {
			JlbQuestionArr[i] = new JLabel("0");
			JlbQuestionArr[i].setForeground(Color.BLACK);
			JlbQuestionArr[i].setFont(FontQuestion);
			JlbQuestionArr[i].setHorizontalAlignment(JLabel.CENTER);
			JlbQuestionArr[i].addMouseListener(this);
		}
		
		/*�ULevel���O*/
		int quesion_temp = 0; CardsNum = 1;
		for(int i = 1; i <= JPQuestionArr.length; ++i) {
			int[] temp_arr = new int[] {4, 6, 8, 9, 15};
			int[] temp_arr2 = new int[] {250, 200, 150, 120, 110};
			int temp2 = (MainFrame.WIDTH - (MainFrame.WIDTH / 4)) / temp_arr[i-1];
			JPQuestionArr[i-1] = new JPanel();
			JPQuestionArr[i-1].setLayout(null);
			JPQuestionArr[i-1].setBounds(
					0, 0, MainFrame.WIDTH - (MainFrame.WIDTH/4), ((MainFrame.HEIGHT/2)/2) - 30);
			for(int j = 0; j < (i+1); ++j) {
				JPQuestionArr[i-1].add(JlbQuestionArr[quesion_temp], j).setBounds(temp2, 30, 100, 100);
				++quesion_temp;
				temp2 += temp_arr2[i-1];
			}
			JPQuestion.add(String.valueOf(CardsNum), JPQuestionArr[(i-1)]);
			++CardsNum;
		}
		JPBottomLeft.add(JPQuestion);
		
		/*�U�责�ܰ϶�*/
		JPAnswer = new JPanel();
		JPAnswer.setLayout(null);
		JPAnswer.setBounds(
				0, (((MainFrame.HEIGHT - (MainFrame.HEIGHT/2))/2) - 30), MainFrame.WIDTH - (MainFrame.WIDTH/4), 
				(((MainFrame.HEIGHT - (MainFrame.HEIGHT/2))/2) + 11));
		//answer_panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		/*���ܰ϶�*/
		JlbTipANum = new JLabel("0");
		JlbTipANum.setFont(FontQuestion);
		JlbTipANum.setForeground(Color.BLACK);
		JlbTipANum.setBounds(250, 40, 30, 50);
		JPAnswer.add(JlbTipANum);
		
		JlbTipA = new JLabel("A");
		JlbTipA.setFont(FontQuestion);
		JlbTipA.setForeground(Color.BLACK);
		JlbTipA.setBounds(300, 40, 30, 50);
		JPAnswer.add(JlbTipA);
		
		JlbTipBNum = new JLabel("0");
		JlbTipBNum.setFont(FontQuestion);
		JlbTipBNum.setForeground(Color.BLACK);
		JlbTipBNum.setBounds(400, 40, 30, 50);
		JPAnswer.add(JlbTipBNum);
		
		JlbTipB = new JLabel("B");
		JlbTipB.setFont(FontQuestion);
		JlbTipB.setForeground(Color.BLACK);
		JlbTipB.setBounds(450, 40, 30, 50);
		JPAnswer.add(JlbTipB);
		
		/*���s�϶�*/
		BtnAttack = new JButton("����");
		BtnAttack.setFont(FontQuestion);
		BtnAttack.setBounds(150, 120, 150, 50);
		BtnAttack.setActionCommand("attack");
		BtnAttack.addActionListener(this);
		JPAnswer.add(BtnAttack);
		
		BtnClear = new JButton("���m");
		BtnClear.setFont(FontQuestion);
		BtnClear.setBounds(400, 120, 150, 50);
		BtnClear.setActionCommand("clear");
		BtnClear.addActionListener(this);
		JPAnswer.add(BtnClear);
		
		JPBottomLeft.add(JPAnswer);
	}
	
	/*���ܩҿ�Ʀr*/
	private void NumChanged(JLabel temp, int id) {
		switch(Integer.parseInt(temp.getText())) {
			case 0:
				AnswerNumArr[id] = 1; break;
			case 1:
				AnswerNumArr[id] = 2; break;
			case 2:
				AnswerNumArr[id] = 3; break;
			case 3:
				AnswerNumArr[id] = 4; break;
			case 4:
				AnswerNumArr[id] = 5; break;
			case 5:
				AnswerNumArr[id] = 6; break;
			case 6:
				AnswerNumArr[id] = 7; break;
			case 7:
				AnswerNumArr[id] = 8; break;
			case 8:
				AnswerNumArr[id] = 9; break;
			case 9:
				AnswerNumArr[id] = 0; break;
		} temp.setText(String.valueOf(AnswerNumArr[id]));
	}
	
	/*�U��k���O*/
	private void BottomRightContain() {
		JlbTipRecord = new JLabel("�Ʋլ����G");
		JlbTipRecord.setFont(new Font("�L�n������", Font.BOLD, 20));
		JlbTipRecord.setForeground(Color.BLACK);
		JlbTipRecord.setBounds(10, 10, 130, 50);
		JScrollPane record_pane = new JScrollPane();
		record_pane.setBorder(null);
		record_pane.setBounds(30, 65, 200, 260);
		record_pane.setOpaque(false);
		record_pane.getViewport().setOpaque(false);
		TextRecord = new JTextArea();
		TextRecord.setBackground(null);
		TextRecord.setFont(new Font("�L�n������", Font.BOLD, 21));
		record_pane.setViewportView(TextRecord);
		JPBottomRight.add(record_pane);
		JPBottomRight.add(JlbTipRecord);
	}
	
	/*����Level��*/
	public static void LevelChanged(int level) {
		//�]�w�^�X
		RoundCurrentNum.setText(String.valueOf(level)); //�ثe�^�X
		RoundSurplusNum.setText(String.valueOf(5 - level)); //�Ѿl�^�X
		
		//�]�w�I��
		PanelTop.remove(PanelBGArr[BGImgCurrentNum]); //�����W�@�i�I���Ϥ�
		BGImgCurrentNum = level - 1; //�ثe�I���Ϥ��s��
		PanelTop.add(PanelBGArr[BGImgCurrentNum], JLayeredPane.DEFAULT_LAYER);
		
		//�D�ذѼ�
		QuestionNumArr = new int[Stage.MapLevel.get(String.valueOf(level))[2]]; //���D�ư}�C���׭��s�]�w
		AnswerNumArr = new int[Stage.MapLevel.get(String.valueOf(level))[2]]; //���װ}�C���׭��s�]�w
		
		//Player�Ѽ�
		PlayerHealthNum = 100; //���a��q
		JbarHealthArr[0].setValue(100); //���a��q���ɺ�
		PlayerHealthText.setText("HP�G" + String.valueOf(PlayerHealthNum));
		PlayerAtkNum = Stage.MapLevel.get(String.valueOf(level))[4];
		
		//Enemy�Ѽ�
		JbarHealthArr[1].setValue(100); //�ĤH��q���ɺ�
		EnemyHealthNum = Stage.MapLevel.get(String.valueOf(level))[3]; //�ĤH��q
		EnemyHealthText.setText("HP�G" + String.valueOf(EnemyHealthNum));
		ImgEnemy = ImgEnemyArr[level - 1]; //�ثe�ĤH�Ϥ�
		JlbEnemyArr[EnemyImgCurrentNum].setVisible(false);
		EnemyImgCurrentNum = level - 1; //�ثe�ĤH�Ϥ��s��
		JlbEnemyArr[EnemyImgCurrentNum].setVisible(true);
		
		//�]�w�I������
		if(!isFirstStart) BGMStop();
		BGMCurrentNum = level - 1;
		ap = new AudioPlay(new File(FileNameArr[BGMCurrentNum]));
		BGMLoopPlay(true);
		
		//�P�_�O�_�����}�l�C��
		if(isFirstStart) isFirstStart = false;
		
		//���m�Ѽ�
		Clear();
		
		//�]�w�ɶ�
		min = Stage.MapLevel.get(String.valueOf(level))[0];
		sec = Stage.MapLevel.get(String.valueOf(level))[1];
		tb.StopTimer(); //����p��
		TextTime.setText(df.format(min) + " : " + df.format(sec));
		Cards.show(JPQuestion, String.valueOf(level)); //�����Ʀr���O
		tb.StartTimer(min, sec); //�}�l�p��
	}

	/*���m*/
	private static void Clear() {
		TextRecord.setText(""); //�Ʋլ����M��
		JlbTipANum.setText("0"); //����A�k�s
		JlbTipBNum.setText("0"); //����B�k�s
		GOV.JlbVictory.setVisible(false);
		GOV.JlbOver.setVisible(false);
		Number.random(QuestionNumArr); //���s�H�����ͤ@�ռƦr
		int BtnID = 0, iMax = 0;
		switch(Level) { //�P�_�ثelevel
			case 1:
				iMax = 2;
				break;
			case 2:
				BtnID = 2; iMax = 3;
				break;
			case 3:
				BtnID = 5; iMax = 4;
				break;
			case 4:
				BtnID = 9; iMax = 5;
				break;
			case 5:
				BtnID = 14; iMax = 6;
				break;
		}
		for(int i = 0; i < iMax; ++i) {
			AnswerNumArr[i] = 0; //�N���a�����ץ��k�s
			if(Level == 1) JlbQuestionArr[i].setText("0"); //�N�D���k�s
			else {
				JlbQuestionArr[BtnID].setText("0");
				++BtnID;
			} System.out.print(QuestionNumArr[i] + " "); //���ե�
		} System.out.println("");
	}

	private void CheckAns(int LevelTemp) {
		/*�p�G(����A+����B)<�D�ؼ�*/
		if(Integer.parseInt(JlbTipANum.getText()) 
				+ Integer.parseInt(JlbTipBNum.getText()) < LevelTemp) {
			String ans_temp = "";
			//�ˬdAB�çP�_�Ӽ�
			for(int i = 0; i < LevelTemp; ++i) { //���a����
				for(int j = 0; j < LevelTemp; ++j) { //�q���D��
					//�P�_���׬O�_�P�D�ج۵��B��m�ۦP �Y��̲ŦX��A ���M��B
					if(AnswerNumArr[i] == QuestionNumArr[j] && i == j)
						JlbTipANum.setText(String.valueOf(
								Integer.parseInt(JlbTipANum.getText()) + 1));
					else if(AnswerNumArr[i] == QuestionNumArr[j])
						JlbTipBNum.setText(String.valueOf(
								Integer.parseInt(JlbTipBNum.getText()) + 1));
				} ans_temp += AnswerNumArr[i]; //�x�s���a����
			}
			
			/*�L�X�Ʋլ���*/
			TextRecord.setText(TextRecord.getText() + "\n" + ans_temp + "�@�@" 
					+ JlbTipANum.getText() + "A " + JlbTipBNum.getText() + "B");
			
			/*��A��=�D�ؼ�*/
			if(Integer.parseInt(JlbTipANum.getText()) == LevelTemp) { 
				if(EnemyHealthNum > 0) { //�p�G�ĤH�٦���q
					PanelTop.add(AnimatePlayer(ImgPlayer, JlbPlayer), JLayeredPane.PALETTE_LAYER);
					EnemyHealthNum -= PlayerAtkNum; //�ĤH��q�������a������
					switch(Level) { //��q���P�_
						case 1:
							JbarHealthArr[1].setValue(JbarHealthArr[1].getValue() - 50);
							break;
						case 2:
							JbarHealthArr[1].setValue(JbarHealthArr[1].getValue() - 40);
							break;
						case 3:
							JbarHealthArr[1].setValue(JbarHealthArr[1].getValue() - 34);
							break;
						case 4:
							JbarHealthArr[1].setValue(JbarHealthArr[1].getValue() - 34);
							break;
						case 5:
							JbarHealthArr[1].setValue(JbarHealthArr[1].getValue() - 25);
							break;
					}
					if(EnemyHealthNum <= 0) {
						if(Level != 5) {
							EnemyHealthText.setText("HP�G" + String.valueOf(0));
							LevelChanged(++Level);
						} else { //��level��5�� �h�N��L��
							Clear();
							EnemyHealthText.setText("HP�G" + String.valueOf(0));
							isGameOver = true;
							BGMStop();
							ap = new AudioPlay(new File("src\\main\\audio\\victory.mid"));
							BGMLoopPlay(false);
							JlbEnemyArr[EnemyImgCurrentNum].setVisible(false);
							GOV.JlbVictory.setVisible(true);
							System.out.println("GAME PASS!");
						}
					} else {
						EnemyHealthText.setText("HP�G" + String.valueOf(EnemyHealthNum));
						Clear();
					}
				}
			} else { //�p�G����Player�|�Q����
				if(PlayerHealthNum > 0) { //�p�GPlayer�٦���q
					PanelTop.add(AnimateEnemy(
							ImgEnemy, JlbEnemyArr[EnemyImgCurrentNum]), JLayeredPane.PALETTE_LAYER);
					PlayerHealthNum -= EnemyAtkNum; //Player����q����Enemy������
					JbarHealthArr[0].setValue(JbarHealthArr[0].getValue() - EnemyAtkNum);
					if(PlayerHealthNum <= 0) {
						isGameOver = true;
						BGMStop();
						ap = new AudioPlay(new File("src\\main\\audio\\gameover.mid"));
						BGMLoopPlay(false);
						JlbPlayer.setVisible(false);
						GOV.JlbOver.setVisible(true);
						System.out.println("GAME OVER!");
						PlayerHealthText.setText("HP�G" + 0);
					} else PlayerHealthText.setText("HP�G"  + String.valueOf(PlayerHealthNum));
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == JlbQuestionArr[0]) NumChanged(JlbQuestionArr[0], 0);
		else if(e.getComponent() == JlbQuestionArr[1]) NumChanged(JlbQuestionArr[1], 1);
		else if(e.getComponent() == JlbQuestionArr[2]) NumChanged(JlbQuestionArr[2], 0);
		else if(e.getComponent() == JlbQuestionArr[3]) NumChanged(JlbQuestionArr[3], 1);
		else if(e.getComponent() == JlbQuestionArr[4]) NumChanged(JlbQuestionArr[4], 2);
		else if(e.getComponent() == JlbQuestionArr[5]) NumChanged(JlbQuestionArr[5], 0);
		else if(e.getComponent() == JlbQuestionArr[6]) NumChanged(JlbQuestionArr[6], 1);
		else if(e.getComponent() == JlbQuestionArr[7]) NumChanged(JlbQuestionArr[7], 2);
		else if(e.getComponent() == JlbQuestionArr[8]) NumChanged(JlbQuestionArr[8], 3);
		else if(e.getComponent() == JlbQuestionArr[9]) NumChanged(JlbQuestionArr[9], 0);
		else if(e.getComponent() == JlbQuestionArr[10]) NumChanged(JlbQuestionArr[10], 1);
		else if(e.getComponent() == JlbQuestionArr[11]) NumChanged(JlbQuestionArr[11], 2);
		else if(e.getComponent() == JlbQuestionArr[12]) NumChanged(JlbQuestionArr[12], 3);
		else if(e.getComponent() == JlbQuestionArr[13]) NumChanged(JlbQuestionArr[13], 4);
		else if(e.getComponent() == JlbQuestionArr[14]) NumChanged(JlbQuestionArr[14], 0);
		else if(e.getComponent() == JlbQuestionArr[15]) NumChanged(JlbQuestionArr[15], 1);
		else if(e.getComponent() == JlbQuestionArr[16]) NumChanged(JlbQuestionArr[16], 2);
		else if(e.getComponent() == JlbQuestionArr[17]) NumChanged(JlbQuestionArr[17], 3);
		else if(e.getComponent() == JlbQuestionArr[18]) NumChanged(JlbQuestionArr[18], 4);
		else if(e.getComponent() == JlbQuestionArr[19]) NumChanged(JlbQuestionArr[19], 5);
	}
	public void mouseEntered(MouseEvent e) {
		((JLabel)e.getSource()).setBorder(
				BorderFactory.createLineBorder(Color.DARK_GRAY, 3));}
	public void mouseExited(MouseEvent e) {((JLabel)e.getSource()).setBorder(null);}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("attack") && isGameOver != true) {
			int LevelTemp = 0;
			switch(Level) {
				case 1:
					LevelTemp = Stage.Level_1[2]; //�N level_1 �����D�Ʀs�� LevelTemp
					break;
				case 2:
					LevelTemp = Stage.Level_2[2];
					break;
				case 3:
					LevelTemp = Stage.Level_3[2];
					break;
				case 4:
					LevelTemp = Stage.Level_4[2];
					break;
				case 5:
					LevelTemp = Stage.Level_5[2];
					break;
			} JlbTipANum.setText("0"); JlbTipBNum.setText("0"); //��������AB���k�s
			CheckAns(LevelTemp);
		}
		
		/*���m���s*/
		if(e.getActionCommand().equals("clear") && isGameOver != true) {Clear();}
	}
}
