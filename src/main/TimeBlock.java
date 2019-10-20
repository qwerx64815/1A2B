package main;
import javax.swing.JLabel;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TimeBlock {
	public interface Listener {
		public void timeOut(); //�q���ɶ���
		public void onChange(long min, long sec); //����ܰ�
	}
	public static DecimalFormat df = new DecimalFormat("00"); //�Ʀr�榡
	private Listener lis;
	private Timer timer;
	private JLabel JlbTime;
	private long delay, min, sec, pass;
	
	public TimeBlock() {
		delay = 2; //����
		pass = 1; //��ƶ��j
		this.AddListener(new TimeBlock.Listener() {
			public void timeOut() {} //�p�ɵ���
			public void onChange(long min, long sec) {} //�C���p��
		});
	}
	
	public void SetJLabel(JLabel lab) {JlbTime = lab;} //�]�mLabel
	public void AddListener(Listener li) {lis = li;} //��ťtimer�ƥ�
	public void StartTimer(int m, int s) { //�}�l�p��
		if(timer == null) { //�Y�ثe�S�����ȶi�{
			timer = new Timer(); min = m; sec = s;
			TimerTask task = new TimerTask() {
				public void run() { //��ƭ˼�
					if(min > 0 && sec == 0) { //���������0��
						--min; sec = 59;
					} else if(min > 0 && (sec - pass) < 0) { //���ƴ�h�g�L��Ƭ��t�Ȯ�
						--min; sec = 60 - (pass - sec);
					} else {sec -= pass;}
					if(sec <= 0 && min <= 0) { //������M��Ƭ�0�� ����˼�
						StopTimer(); //����p��
						lis.onChange(min,sec);
						if (lis != null) {lis.timeOut();} //�p�ɵ���
					}
					if(lis != null && min + sec != 0) {lis.onChange(min,sec);} //���ܷ�e��ܼƭ�
				}
			};
			timer.schedule(task, delay * 1000, delay * 1000); //schedule(����, ����, �T�w�W�v)
		}
	}
	
	/*����p��*/
	public void StopTimer() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}