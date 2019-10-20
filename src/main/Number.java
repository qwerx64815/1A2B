package main;

public class Number {
	public int[] random(int[] QuestionArr) { //隨機產生數組
		for(int i = 0; i < QuestionArr.length; ++i) {
			QuestionArr[i] = (int)((int)(Math.random() * 1000 + 0) / 100);
			for(int j = 0; j < i; ++j) {
				while(QuestionArr[i] == QuestionArr[j])
					QuestionArr[i] = (int)((int)(Math.random() * 1000 + 0) / 100);
				for(int k = 0; k < j; ++k)
					while(QuestionArr[i] == QuestionArr[k]) 
						QuestionArr[i] = (int)((int)(Math.random() * 1000 + 0) / 100);
			}
		} return QuestionArr;
	}
}
