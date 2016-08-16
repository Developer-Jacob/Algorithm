package week6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Point {
	double x;
	double y;
	double length_r1;
	double length_r2;
}
class CompPoint implements Comparator<Point>{
	boolean falg = true;
	@Override
	public int compare(Point o1, Point o2) {
		if(falg){
			if (o1.length_r1 > o2.length_r1) {
				return 1;
			} else {
				return -1;
			}
		}else{
			if (o1.length_r2 > o2.length_r2) {
				return 1;
			} else {
				return -1;
			}
		}
	}
	
}
public class Q1225 {
	public static void main(String[] args) {
		int result = 0;
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();
		Point[] radar = new Point[2];
		Point[] input = new Point[count];
		for (int i = 0; i < 2; i++) {
			Point p = new Point();
			p.x = sc.nextDouble();
			p.y = sc.nextDouble();
			radar[i] = p;
		}
		double size = sc.nextDouble();
		for (int i = 0; i < count; i++) {
			Point p = new Point();
			p.x = sc.nextDouble();
			p.y = sc.nextDouble();
			input[i] = p;
		}

		double min_r1 = Integer.MAX_VALUE;
		double min_r2 = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			input[i].length_r1 = getLength(radar[0], input[i]);
			if (min_r1 > input[i].length_r1) {
				min_r1 = input[i].length_r1;
			}
			input[i].length_r2 = getLength(radar[1], input[i]);
			if (min_r2 > input[i].length_r2) {
				min_r2 = input[i].length_r2;
			}
		}


		double[] temp1 = new double[input.length];
		double[] temp2 = new double[input.length];
		CompPoint cmp = new CompPoint();
		if (min_r1 < min_r2) {
			cmp.falg= true;
			Arrays.sort(input,cmp);
			for (int i = 0; i < input.length; i++) {
				temp1[i] = input[i].length_r1;
				temp2[i] = input[i].length_r2;
			}

		} else {
			cmp.falg= false;
			Arrays.sort(input,cmp);
			for (int i = 0; i < input.length; i++) {
				temp1[i] = input[i].length_r2;
				temp2[i] = input[i].length_r1;
			}
		}
//		System.out.println(input[0].x + "," + input[0].y);
//		System.out.println(input[1].x + "," + input[1].y);
//		System.out.println(input[2].x + "," + input[2].y);
//		System.out.println(input[3].x + "," + input[3].y);
//		System.out.println(input[4].x + "," + input[4].y);
//		System.out.println(input[5].x + "," + input[5].y);
//		System.out.println(input[6].x + "," + input[6].y);
//		System.out.println(input[7].x + "," + input[7].y);
//		System.out.println(input[8].x + "," + input[8].y);
//		System.out.println();
//		System.out.println(input[0].length_r1 + "," + input[0].length_r2);
//		System.out.println(input[1].length_r1 + "," + input[1].length_r2);
//		System.out.println(input[2].length_r1 + "," + input[2].length_r2);
//		System.out.println(input[3].length_r1 + "," + input[3].length_r2);
//		System.out.println(input[4].length_r1 + "," + input[4].length_r2);
//		System.out.println(input[5].length_r1 + "," + input[5].length_r2);
//		System.out.println(input[6].length_r1 + "," + input[6].length_r2);
//		System.out.println(input[7].length_r1 + "," + input[7].length_r2);
//		System.out.println(input[8].length_r1 + "," + input[8].length_r2);
		int max = 0;
		for (int i = 0; i < count; i++) {
//			System.out.println("---------------" + i);
			double area = temp1[i] * Math.PI;
			double reArea = size - area;
//			System.out.println("reArea : " + reArea);
			if (reArea < 0)break;
			int temp = i + 1;
			for (int j = i + 1; j < count; j++) {
				double area2 = temp2[j] * Math.PI;
				
				if (reArea >= area2) {
					temp++;
//					System.out.println("area2:"+area2);
//					System.out.println("j:" + j);
				}
			}
			if (max < temp) {
				max = temp;
			}
//			System.out.println("i:" + i + " max :" + max);
		}
		System.out.println(count - max);
	}

	static public double getLength(Point p1, Point p2) {
		return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
	}
}
