package main;
import javax.swing.JLabel;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimeBlock {
	public interface Listener {
		public void timeOut(); //通知時間到
		public void onChange(long min, long sec); //秒數變動
	}
	public static DecimalFormat df = new DecimalFormat("00"); //數字格式
	private Listener lis;
	private Timer timer;
	private JLabel JlbTime;
	private long delay, min, sec, pass;
	
	public TimeBlock() {
		delay = 2; //延遲
		pass = 1; //秒數間隔
		this.AddListener(new TimeBlock.Listener() {
			public void timeOut() {} //計時結束
			public void onChange(long min, long sec) {} //每次計時
		});
	}
	
	public void SetJLabel(JLabel lab) {JlbTime = lab;} //設置Label
	public void AddListener(Listener li) {lis = li;} //監聽timer事件
	public void StartTimer(int m, int s) { //開始計時
		if(timer == null) { //若目前沒有任務進程
			timer = new Timer(); min = m; sec = s;
			TimerTask task = new TimerTask() {
				public void run() { //秒數倒數
					if(min > 0 && sec == 0) { //當分鐘不為0時
						--min; sec = 59;
					} else if(min > 0 && (sec - pass) < 0) { //當秒數減去經過秒數為負值時
						--min; sec = 60 - (pass - sec);
					} else {sec -= pass;}
					if(sec <= 0 && min <= 0) { //當分鐘和秒數為0時 停止倒數
						StopTimer(); //停止計時
						lis.onChange(min,sec);
						if (lis != null) {lis.timeOut();} //計時結束
					}
					if(lis != null && min + sec != 0) {lis.onChange(min,sec);} //改變當前顯示數值
				}
			};
			timer.schedule(task, delay * 1000, delay * 1000); //schedule(任務, 延遲, 固定頻率)
		}
	}
	
	/*停止計時*/
	public void StopTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}