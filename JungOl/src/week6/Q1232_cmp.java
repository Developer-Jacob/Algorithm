package week6;

import java.util.Scanner;

public class Q1232_cmp {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] list = new int[n + 1];
		int count = 0;
		for (int i = 2; i <= n; i++) {
			if (list[i] == 1)
				continue;
			for (int j = 1; i * j <= n; j++) {
				if (list[i * j] != 1) {
//					System.out.println(i * j);
					count++;
				}
				list[i * j] = 1;
				if (count == k) {
					 System.out.println(i*j);
					 return;
				}
				
			}
		}
	}
}
