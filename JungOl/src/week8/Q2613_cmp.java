package week8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Point {
	public Point(int x, int y, int val) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		this.val = val;
	}

	int val;
	int x;
	int y;
}

class Queue {
	ArrayList<Point> p = new ArrayList<Point>();
	int startP = 0;
	int endP = -1;

	public void push(Point input) {
		p.add(input);
		endP++;
	}

	public Point pop() {
		if (startP > endP)
			return null;
		Point result = p.get(startP);
		startP++;
		return result;
	}
}

public class Q2613_cmp {
	static int[][] map;
	static int[][] visited;
	static int inputY;
	static int inputX;

	public static void main(String[] args) {
		ArrayList<Point> startList = new ArrayList<Point>();
		Scanner sc = new Scanner(System.in);
		inputY = sc.nextInt();
		inputX = sc.nextInt();
		map = new int[inputX][inputY];
		visited = new int[inputX][inputY];
		for (int i = 0; i < inputX; i++) {
			for (int j = 0; j < inputY; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					startList.add(new Point(i, j, 1));
				}
			}
		}
		// System.out.println(startList.get(0).x+","+startList.get(0).y);
		bfs(startList);
		// System.out.println(result);
		int max = 0;
//		for (int i = 0; i < inputX; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
//		System.out.println();
//		for (int i = 0; i < inputX; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		boolean flag =false;
		for (int i = 0; i < inputX; i++) {
			for (int j = 0; j < inputY; j++) {
				int temp = map[i][j];
				if (temp == 0) {
					flag = true;
				}
				if (max < temp) {
					max = temp;
				}
			}
		}
		if(flag){
			System.out.println(-1);
		}else{
			System.out.println(max - 1);
		}
	}

	static public void bfs(ArrayList<Point> startList) {
		Queue q = new Queue();
		for (int i = 0; i < startList.size(); i++) {
			visited[startList.get(i).x][startList.get(i).y] = 1;
			q.push(startList.get(i));
		}
		int count = 0;
		while (true) {
			count++;
			
			Point p = q.pop();
			if (p == null) {
				break;
			}
			int pointX = p.x;
			int pointY = p.y;
			int pointV = p.val;

			if (pointX - 1 >= 0 && visited[pointX - 1][pointY] == 0 && map[pointX - 1][pointY] != -1) {
				q.push(new Point(pointX - 1, pointY, pointV + 1));
				map[pointX - 1][pointY] = pointV + 1;
				visited[pointX - 1][pointY] = 1;
			}
			if (pointX + 1 < inputX && visited[pointX + 1][pointY] == 0 && map[pointX + 1][pointY] != -1) {
				q.push(new Point(pointX + 1, pointY, pointV + 1));
				map[pointX + 1][pointY] = pointV + 1;
				visited[pointX + 1][pointY] = 1;
			}
			if (pointY - 1 >= 0 && visited[pointX][pointY - 1] == 0 && map[pointX][pointY - 1] != -1) {
				q.push(new Point(pointX, pointY - 1, pointV + 1));
				map[pointX][pointY - 1] = pointV + 1;
				visited[pointX][pointY - 1] = 1;
			}
			if (pointY + 1 < inputY && visited[pointX][pointY + 1] == 0 && map[pointX][pointY + 1] != -1) {
				q.push(new Point(pointX, pointY + 1, pointV + 1));
				map[pointX][pointY + 1] = pointV + 1;
				visited[pointX][pointY + 1] = 1;
			}

		}
	}
}
