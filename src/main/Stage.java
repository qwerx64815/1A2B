package main;
import java.util.HashMap;
import java.util.Map;

public class Stage {
	Map<String, int[]> MapLevel = new HashMap<>();
	int[] Level_1, Level_2, Level_3, Level_4, Level_5;
	/*
	{分, 秒, 問題數, 敵人血量, 玩家攻擊, 提示次數}
	{min, sec, question_num, enemy_health}
	*/
	public Stage() {
		Level_1 = new int[] {0, 60, 2, 30, 15, 1};
		Level_2 = new int[] {2, 10, 3, 50, 20, 1};
		Level_3 = new int[] {3, 0, 4, 70, 25, 1};
		Level_4 = new int[] {4, 10, 5, 90, 30, 2};
		Level_5 = new int[] {5, 30, 6, 120, 35, 2};
		
		MapLevel.put("1", Level_1);
		MapLevel.put("2", Level_2);
		MapLevel.put("3", Level_3);
		MapLevel.put("4", Level_4);
		MapLevel.put("5", Level_5);
	}
}
