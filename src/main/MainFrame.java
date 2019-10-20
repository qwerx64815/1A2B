package main;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	protected final static int WIDTH = 1000; //視窗寬度
	protected final static int HEIGHT = 750; //視窗高度
	static Container content;
	static CardLayout cards;
	
	public MainFrame() throws InterruptedException, IOException {
		super("猜數字");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false); //設定視窗是否能夠調整大小
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize(); //抓取目前螢幕大小
        int h = screenSize.height; //目前螢幕之高度
        int w = screenSize.width; //目前螢幕之寬度
        int x = (w - WIDTH) / 2; //(螢幕寬度 - 視窗寬度) / 2 = 視窗起始位置X
        int y = (h - HEIGHT) / 2; //同上 求得位置Y
        this.setLocation(x, y); //設置視窗起始(x,y)位置
        this.setSize(WIDTH, HEIGHT); //設置視窗大小
        
        content = this.getContentPane();
        cards = new CardLayout(); //cards排版方式
        content.setLayout(cards);
        
        /*將所有頁面加入CardLayout*/
        FirstPage FP = new FirstPage();
        content.add("1", FP.JPFirstPage); //第一頁
        SecondPage SP = new SecondPage();
        content.add("2", SP.JPSecondPage); //第二頁
        
        this.setVisible(true); //是否顯示視窗
	}
	
	public static void main(String[] args) 
			throws InterruptedException, IOException {new MainFrame();}
}
