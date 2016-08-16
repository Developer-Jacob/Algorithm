
package week9;

import java.util.ArrayList;
import java.util.Scanner;

class Position {
	public Position(int x, int y, int dir, int count, String load) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.count = count;
		this.load = load;
	}

	String load;
	int count;
	int x;
	int y;
	int dir;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return x + "," + y + "," + dir + "," + count;
	}
}

class Queue {
	ArrayList<Position> list = new ArrayList<Position>();
	int start = 0;
	int end = -1;

	public void push(Position input) {
		 System.out.println("push-----------");
		 System.out.println(input);
		list.add(input);
		end++;
	}

	public Position pop() {
		if (start > end)
			return null;
		Position result = list.get(start);
		start++;
		return result;
	}
}

public class Q1006 {
	static int[][] map;
	static int[][] visited;
	static Position start;
	static Position end;
	static int x;
	static int y;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		x = sc.nextInt();
		y = sc.nextInt();
		map = new int[x + 1][y + 1];
		visited = new int[x + 1][y + 1];
		for (int i = 1; i <= x; i++) {
			for (int j = 1; j <= y; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int pointX = sc.nextInt();
		int pointY = sc.nextInt();
		int pointD = sc.nextInt();
		start = new Position(pointX, pointY, pointD, 0, "");

		pointX = sc.nextInt();
		pointY = sc.nextInt();
		pointD = sc.nextInt();
		end = new Position(pointX, pointY, pointD, 0, "");
		bfs();
	}

	static public void bfs() {
		Queue q = new Queue();
		q.push(start);
		visited[start.x][start.y] = 1;
		while (true) {
			System.out.println("-------------------------------------------------------");
			Position p = q.pop();
			 System.out.println("pop :" + p);
			if (p == null) {
				break;
			}

			int pointX = p.x;
			int pointY = p.y;
			int pointD = p.dir;
			int pointC = p.count;
			String load = p.load;
			if (pointX == end.x && pointY == end.y) {
				// System.out.println(load);
				Position back = turnBack(p);
				Position right = turnRight(p);
				Position left = turnLeft(p);
				if(back.dir==end.dir){
					System.out.println(back.count);
					System.out.println(back.load);
				}else if(right.dir==end.dir){
					System.out.println(right.count);
					System.out.println(right.load);
				}else if(left.dir==end.dir){
					System.out.println(left.count);
					System.out.println(left.load);
				}else{
					System.out.println(pointC);
					System.out.println(load);
				}
				break;
			}
			if (checkWall(1, pointD, p)) {
				// 1칸이동
				q.push(move(1, p));
				if (checkWall(2, pointD, p)) {
					// 2칸이동
					q.push(move(2, p));
					if (checkWall(3, pointD, p)) {
						// 3칸이동
						q.push(move(3, p));
					}
				}
			}

			// i로 방향전화
			Position temp=null;
			temp = turnRight(p);
			if(checkDir(temp.dir, p)){
				q.push(temp);
			}
			temp = turnLeft(p);
			if(checkDir(temp.dir, p)){
				q.push(temp);
			}
			temp = turnBack(p);
			if(checkDir(temp.dir, p)){
				q.push(temp);
			}
		}
	}

	static public Position turnLeft(Position p) {
		int trunDir = p.dir;
		int desDir = 0;
		switch (trunDir) {
		case 1:
			desDir =4;
			break;
		case 2:
			desDir =3;
			break;
		case 3:
			desDir =1;
			break;
		case 4:
			desDir =2;
			break;
		}
		p.load = p.load+ " (" + p.x + "," + p.y + ","+desDir+","+(p.count+1)+")";
		return new Position(p.x, p.y, desDir, p.count+1, p.load);
	}

	static public Position turnRight(Position p) {
		int trunDir = p.dir;
		int desDir = 0;
		switch (trunDir) {
		case 1:
			desDir =3;
			break;
		case 2:
			desDir =4;
			break;
		case 3:
			desDir =2;
			break;
		case 4:
			desDir =1;
			break;
		}
		p.load = p.load+ " (" + p.x + "," + p.y + ","+desDir+","+(p.count+1)+")";
		return new Position(p.x, p.y, desDir, p.count+1, p.load);
	}

	static public Position turnBack(Position p) {
		int trunDir = p.dir;
		int desDir = 0;
		switch (trunDir) {
		case 1:
			desDir = 3;
			break;
		case 2:
			desDir = 1;
			break;
		case 3:
			desDir = 4;
			break;
		case 4:
			desDir = 3;
			break;
		}
		p.load = p.load+ " (" + p.x + "," + p.y + ","+desDir+","+(p.count+2)+")";
		return new Position(p.x, p.y, desDir, p.count+2, p.load);
	}

	static public boolean checkDir(int dir, Position p) {
		boolean flag = false;
		if (checkWall(1, dir, p)) {
			flag = true;
		}
		return flag;
	}

	static public Position move(int length, Position p) {
		int tempX = p.x;
		int tempY = p.y;
		int dir = p.dir;
		int count = p.count + 1;
		String tempL = p.load;
		switch (dir) {
		case 1:
			tempY = tempY + length;
			break;
		case 2:
			tempY = tempY - length;
			break;
		case 3:
			tempX = tempX + length;
			break;
		case 4:
			tempX = tempX - length;
			break;
		}

		visited[tempX][tempY] = 1;
		return new Position(tempX, tempY, dir, count, tempL + " (" + tempX + "," + tempY + ","+dir+","+count+")");
	}

	static public boolean checkWall(int legnth, int dir, Position p) {
		int curX = p.x;
		int curY = p.y;
		boolean result = false;
		switch (dir) {
		case 4:
			if (curX - legnth > 0 && map[curX - legnth][curY] == 0 && visited[curX - legnth][curY] == 0) {
				result = true;
			}
			break;
		case 3:
			if (curX + legnth <= x && map[curX + legnth][curY] == 0 && visited[curX + legnth][curY] == 0) {
				result = true;
			}
			break;
		case 2:
			if (curY - legnth > 0 && map[curX][curY - legnth] == 0 && visited[curX][curY - legnth] == 0) {
				result = true;
			}
			break;
		case 1:
			if (curY + legnth <= y && map[curX][curY + legnth] == 0 && visited[curX][curY + legnth] == 0) {
				result = true;
			}
			break;

		}
		return result;
	}
}
