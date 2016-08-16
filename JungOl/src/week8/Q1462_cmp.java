package week8;

import java.util.ArrayList;
import java.util.Scanner;

class Point_1 {
	public Point_1(int x, int y, int length) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.length = length;
	}

	int x;
	int y;
	int length;
}

class Queue3040_1 {
	ArrayList<Point_1> list = new ArrayList<Point_1>();
	int start = 0;
	int end = -1;

	public void push(Point_1 input) {
		list.add(input);
		end++;
	}

	public Point_1 pop() {
		if (end < start)
			return null;
		Point_1 result = list.get(start);
		start++;
		return result;
	}
}

public class Q1462_cmp {
	static char[][] map;
	static int inputX;
	static int inputY;
	static int[][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		inputX = sc.nextInt();
		inputY = sc.nextInt();
		visited = new int[inputX][inputY];
		map = new char[inputX][inputY];
		int result =0;
		for (int i = 0; i < inputX; i++) {
			String str = sc.next();
			for (int j = 0; j < inputY; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		while (true) {
//			System.out.println("@@@");
			Point_1 startP = findStartP(visited);
			if(startP==null)break;
//			System.out.println("startP "+startP.x+","+startP.y);
			ArrayList<Point_1> p = bfs(startP);
			for(int i = 0; i<p.size();i++){
//				System.out.println(p.get(i).x+","+p.get(i).y+","+p.get(i).length);
				Point_1 test = bfsPoint(p.get(i));
				if(result<test.length){
					result = test.length;
				}
			}
//			System.out.println("---------------------------------------------");
			
		}
		System.out.println(result);
	}

	static Point_1 bfsPoint(Point_1 startP) {
		int maxLength = 0;
		startP.length = 0;
		Point_1 result = null;
		Queue3040_1 q = new Queue3040_1();
		q.push(startP);
		int[][] visit = new int[inputX][inputY];
		visit[startP.x][startP.y] = 1;
		while (true) {
			Point_1 p = q.pop();
			if (p == null){
				break;
			}
			result = p;
			int pointX = p.x;
			int pointY = p.y;
			int pointL = p.length;
			if (pointX - 1 >= 0 && visit[pointX - 1][pointY] == 0 && map[pointX - 1][pointY] == 'L') {
				q.push(new Point_1(pointX-1, pointY, pointL+1));
				visit[pointX-1][pointY] = 1;
				if(maxLength<pointL+1){
					maxLength = pointL+1;
				}
			}
			if (pointX + 1 <inputX && visit[pointX + 1][pointY] == 0 && map[pointX + 1][pointY] == 'L') {
				q.push(new Point_1(pointX+1, pointY, pointL+1));
				visit[pointX+1][pointY] = 1;
				if(maxLength<pointL+1){
					maxLength = pointL+1;
				}
			}
			if (pointY - 1 >= 0 && visit[pointX][pointY - 1] == 0 && map[pointX][pointY - 1] == 'L') {
				q.push(new Point_1(pointX, pointY-1, pointL+1));
				visit[pointX][pointY-1] = 1;
				if(maxLength<pointL+1){
					maxLength = pointL+1;
				}
			}
			if (pointY + 1 < inputY && visit[pointX][pointY + 1] ==0 && map[pointX][pointY + 1] == 'L') {
				q.push(new Point_1(pointX, pointY+1, pointL+1));
				visit[pointX][pointY+1] = 1;
				if(maxLength<pointL+1){
					maxLength = pointL+1;
				}
			}

		}
		return result;
	}
	static ArrayList<Point_1> bfs(Point_1 startP) {
			startP.length = 0;
			ArrayList<Point_1> resultList = new ArrayList<Point_1>();
			Queue3040_1 q = new Queue3040_1();
			q.push(startP);
			visited[startP.x][startP.y] = 1;
			int count = 0;
			int countUp = 0;
			while (true) {
				count = 0;
				countUp =0;
				Point_1 p = q.pop();
				if (p == null){
					break;
				}
				int pointX = p.x;
				int pointY = p.y;
				int pointL = p.length;
				if (pointX - 1 >= 0  && map[pointX - 1][pointY] == 'L') {
					if(visited[pointX-1][pointY]>pointL){
						countUp++;
					}
					if(visited[pointX - 1][pointY] == 0 ){
						count++;
						q.push(new Point_1(pointX-1, pointY, pointL+1));
						visited[pointX - 1][pointY] = pointL+1;
					}
				}
				
				if (pointX + 1 <inputX &&  map[pointX + 1][pointY] == 'L') {
					if(visited[pointX + 1][pointY]>pointL){
						countUp++;
					}
					if(visited[pointX + 1][pointY] == 0){
						count++;
						q.push(new Point_1(pointX+1, pointY, pointL+1));
						visited[pointX+1][pointY] = pointL+1;
					}
				}
				if (pointY - 1 >= 0  && map[pointX][pointY - 1] == 'L') {
					if(visited[pointX][pointY - 1]>pointL){
						countUp++;
					}
					if(visited[pointX][pointY - 1] == 0){
						count++;
						q.push(new Point_1(pointX, pointY-1, pointL+1));
						visited[pointX][pointY-1] = pointL+1;
					}
				}
				if (pointY + 1 < inputY  && map[pointX][pointY + 1] == 'L') {
					if(visited[pointX][pointY + 1]>pointL){
						countUp++;
					}
					if(visited[pointX][pointY + 1] == 0){
						count++;
						q.push(new Point_1(pointX, pointY+1, pointL+1));
						visited[pointX][pointY+1] = pointL+1;
					}
				}
				if(count==0&&countUp==0){
					resultList.add(p);
				}
			}
			return resultList;
		}

	

	// 출발 점을 구하는 함수
	static Point_1 findStartP(int[][] visited) {
		for (int i = 0; i < inputX; i++) {
			for (int j = 0; j < inputY; j++) {
				// 출발가능 지점(땅인곳) && 방문하지 않은 지점
				if (map[i][j] == 'L' && visited[i][j] == 0 ) {
					return new Point_1(i, j, 0);
				}
			}
		}
		return null;
	}

//
}
