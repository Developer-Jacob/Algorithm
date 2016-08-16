package week2;

import java.util.ArrayList;
import java.util.Scanner;

class Queue {
	ArrayList<Point> q;

	public Queue() {
		q = new ArrayList<Point>();
	}

	public void push(Point point) {
//		System.out.println("push :"+"("+point.x+","+point.y+")");
		q.add(point);
	}

	public Point pop() {
		if (q.size() == 0) {
			return null;
		}
		Point returnP = q.get(0);
		for (int i = 0; i < q.size() - 1; i++) {
			q.set(i, q.get(i + 1));
		}
		q.remove(q.size() - 1);
//		System.out.println("pop :"+"("+returnP.x+","+returnP.y+")");
		return returnP;
	}
}

class Point {
	int x;
	int y;
	int length = 0;

	public Point(int x, int y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}
}

public class Q1078 {
	static int[][] map;
	static int[][] visited;
	static int x;
	static int y;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Q1078 q = new Q1078();
		y = sc.nextInt();// 가로
		x = sc.nextInt();// 세로
		map = new int[x+1][y+1];
		visited = new int[x+1][y+1];
		for (int i = 1; i <= x; i++) {
			String line = sc.next();
			for (int j = 1; j <= y; j++) {
				map[i][j] = Integer.valueOf(String.valueOf(line.charAt(j-1)));
			}
		}

		int attackY = sc.nextInt();
		int attackX = sc.nextInt();
		int result = q.bfs(attackX, attackY);
		System.out.println(result);
		
		int count = 0;
		for(int m = 1;m<=x;m++){
			for(int n = 1;n<=y;n++){
				if(map[m][n]==1){
					count++;
//					System.out.println(m+","+n);
				}
//				System.out.print(map[m][n]);
			}
//			System.out.println();
		}
		System.out.println(count);
	}

	public int bfs(int startX, int startY) {
		Queue q = new Queue();
		Point point;
		q.push(new Point(startX, startY, 0));

		visited[startX][startY] = 1;
		map[startX][startY] =0;
		int maxLength = 0;
		while (true) {
			point = q.pop();
			if (point == null) {
				break;
			}
			int pointX = point.x;
			int pointY = point.y;

			// 윗 방향
			if (checkPoint(pointX - 1, pointY) && visited[pointX - 1][pointY] == 0 && map[pointX - 1][pointY] == 1) {
				visited[pointX - 1][pointY] = 1;
				q.push(new Point(pointX - 1, pointY, point.length + 1));
				map[pointX-1][pointY]=0;
//				System.out.println("(" + pointX + "," + pointY + ")" + "-> 위" + "(" + (pointX - 1) + "," + (pointY) + ")");
			}
			// 아랫 방향
			if (checkPoint(pointX + 1, pointY) && visited[pointX + 1][pointY] == 0 && map[pointX + 1][pointY] == 1) {
				visited[pointX + 1][pointY] = 1;
				q.push(new Point(pointX + 1, pointY, point.length + 1));
				map[pointX+1][pointY]=0;
//				System.out.println("(" + pointX + "," + pointY + ")" + "-> 아래" + "(" + (pointX + 1) + "," + (pointY) + ")");
			}
			// 오른 방향
			if (checkPoint(pointX, pointY + 1) && visited[pointX][pointY + 1] == 0 && map[pointX][pointY + 1] == 1) {
				visited[pointX][pointY + 1] = 1;
				q.push(new Point(pointX, pointY + 1, point.length + 1));
				map[pointX][pointY+1]=0;
//				System.out.println("(" + pointX + "," + pointY+  ")" + "-> 오" + "(" + pointX + "," + (pointY+1) + ")");
			}
			// 왼 방향
			if (checkPoint(pointX, pointY - 1) && visited[pointX][pointY - 1] == 0 && map[pointX][pointY - 1] == 1) {
				visited[pointX][pointY - 1] = 1;
				q.push(new Point(pointX, pointY - 1, point.length + 1));
				map[pointX][pointY-1]=0;
//				System.out.println("("+pointX+","+pointY+")"+"-> 왼"+"("+(pointX)+","+(pointY-1)+")");
			}
			maxLength = point.length+1;
		}
		return maxLength+2;
	}

	public boolean checkPoint(int checkX, int checkY) {
		if (checkX > 0 && checkY > 0 && checkX <=x && checkY <=y) {
			return true;
		}
		return false;
	}
}
