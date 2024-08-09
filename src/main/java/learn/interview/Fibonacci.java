package learn.interview;

/**
 * Created by Spring on 2016/7/22.
 */
public class Fibonacci {
	private static int getFibo(int i) {
		if (i == 1 || i == 2) {
			return 1;
		} else {
			return getFibo(i - 1) + getFibo(i - 2);
		}
	}

	public static void main(String[] args) {
		System.out.println("生成斐波那契数列的前20项");
		for (int i=1;i<=20;i++){
			System.out.println(getFibo(i)+"\t");
			if(i%5==0){
				System.out.println("**********");
			}
		}
		short i=1;
		i+=1;//right
		i=(short)(i+(short)1);//wrong->i=i+1
		System.out.println(i);
	}
}
