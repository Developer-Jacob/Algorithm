package week5;

import java.util.Scanner;

public class Q1520 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		int[] input = new int[count];
		int[][] result = new int[count + 1][3];
		for (int i = 0; i < count; i++) {
			input[i] = sc.nextInt();
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < 3; j++) {
				if(j-i<0)continue;
				
			}
		}
	}
}
