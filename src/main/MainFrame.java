package main;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	protected final static int WIDTH = 1000; //�����e��
	protected final static int HEIGHT = 750; //��������
	static Container content;
	static CardLayout cards;
	
	public MainFrame() throws InterruptedException, IOException {
		super("�q�Ʀr");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false); //�]�w�����O�_����վ�j�p
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); //����ثe�ù��j�p
        int h = screenSize.height; //�ثe�ù�������
        int w = screenSize.width; //�ثe�ù����e��
        int x = (w - WIDTH) / 2; //(�ù��e�� - �����e��) / 2 = �����_�l��mX
        int y = (h - HEIGHT) / 2; //�P�W �D�o��mY
        this.setLocation(x, y); //�]�m�����_�l(x,y)��m
        this.setSize(WIDTH, HEIGHT); //�]�m�����j�p
        
        content = this.getContentPane();
        cards = new CardLayout(); //cards�ƪ��覡
        content.setLayout(cards);
        
        /*�N�Ҧ������[�JCardLayout*/
        FirstPage FP = new FirstPage();
        content.add("1", FP.JPFirstPage); //�Ĥ@��
        SecondPage SP = new SecondPage();
        content.add("2", SP.JPSecondPage); //�ĤG��
        
        this.setVisible(true); //�O�_��ܵ���
	}
	
	public static void main(String[] args) 
			throws InterruptedException, IOException {new MainFrame();}
}
