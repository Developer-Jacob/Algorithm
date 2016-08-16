package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Queue {
	ArrayList<Position> al;
	int start = 0;
	int end = -1;

	public Queue() {
		al = new ArrayList<Position>();
	}

	public void push(Position p) {
		al.add(p);
		end++;
	}

	public Position pop() {
		if (start > end) {
			return null;
		}
		Position result = al.get(start);
		start++;
		return result;
	}
}

class Position {
	public Position(int x, int y, int dir, int cornerNum) {
		// TODO Auto-generated constructor stub
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.cornerNum = cornerNum;
	}

	int cornerNum;
	int dir;
	int x;
	int y;
}

public class Q1113_cmp {
	static int[][] map;
	static int[][] visited;
	static int desX;
	static int desY;
	static int m;
	static int n;
	static int U = 0;
	static int D = 1;
	static int L = 2;
	static int R = 3;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		map = new int[m][n];
		visited = new int[m][n];
		desX = sc.nextInt();
		desY = sc.nextInt();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		bfs();
//		System.out.println(Arrays.toString(visited[0]));
//		System.out.println(Arrays.toString(visited[1]));
//		System.out.println(Arrays.toString(visited[2]));
//		System.out.println(Arrays.toString(visited[3]));
//		System.out.println(Arrays.toString(visited[4]));
//		System.out.println(Arrays.toString(visited[5]));
//		System.out.println(Arrays.toString(visited[6]));
//		System.out.println(Arrays.toString(visited[7]));
//		System.out.println(Arrays.toString(visited[8]));
//		System.out.println(Arrays.toString(visited[9]));
	}

	static public void bfs() {
		int x = 0;
		int y = 0;
		Queue q = new Queue();
		visited[0][0] = 0;
		q.push(new Position(x, y, -1, 0));

		while (true) {
			Position val = q.pop();
			if (val == null) {
				System.out.println(visited[desX][desY]);
//				System.out.println("종료");
				break;
			}
			int curX = val.x;
			int curY = val.y;
			int curD = val.dir;
			int curC = val.cornerNum;
			if (curX == desX && curY == desY) {
//				System.out.println("도착"+curC);
			}
//			System.out.println("curX:"+curX+" curY:"+curY+" curD:"+curD+" curC:"+curC);
			// 상
			if ((curX - 1) >= 0 && map[curX - 1][curY] == 1 
					&& visited[curX - 1][curY] > curC) {
//				System.out.println("UUUUU");
				if(curD!=U && curD!=-1){//방향이 바뀔때
					visited[curX-1][curY] = curC+1; 
					q.push(new Position(curX-1, curY, U, curC+1));
				}else{
					visited[curX-1][curY] = curC;
					q.push(new Position(curX-1, curY, U, curC));
				}
				
			}
			// 하
			
			if ((curX + 1) < m && map[curX + 1][curY] == 1
					&& visited[curX + 1][curY] > curC) {
//				System.out.println("DDDDD");
				if(curD!=D && curD!=-1){//방향이 바뀔때
					visited[curX+1][curY] = curC+1;
					q.push(new Position(curX+1, curY, D, curC+1));
				}else{
					visited[curX+1][curY] = curC;
					q.push(new Position(curX+1, curY, D, curC));
				}
			}
			// 좌
			if ((curY - 1) >= 0 && map[curX][curY - 1] == 1 
					&& visited[curX][curY - 1] > curC) {
//				System.out.println("LLLLL");
				if(curD!=L && curD!=-1){//방향이 바뀔때
					visited[curX][curY-1] = curC+1;
					q.push(new Position(curX, curY-1, L, curC+1));
				}else{
					visited[curX][curY-1] = curC;
					q.push(new Position(curX, curY-1, L, curC));
				}
			}
			// 우
			if ((curY + 1) < m && map[curX][curY + 1] == 1 
					&& visited[curX][curY + 1] > curC) {
//				System.out.println("RRRRR");
				if(curD!=R && curD!=-1){//방향이 바뀔때
					visited[curX][curY+1] = curC+1;
					q.push(new Position(curX, curY+1, R, curC+1));
				}else{
					visited[curX][curY+1] = curC;
					q.push(new Position(curX, curY+1, R, curC));
				}
			}
		}

	}
}
