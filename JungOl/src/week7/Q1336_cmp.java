package week7;

import java.util.ArrayList;
import java.util.Scanner;
class Value{
	public Value(int val , int length) {
		// TODO Auto-generated constructor stub
		this.val = val;
		this.length = length;
				
	}
	int val;
	int length;
}
class Queeu {
	ArrayList<Value> al = new ArrayList<Value>();
	int idxS = 0;
	int idxE = -1;

	public void push(Value input) {
		al.add(input);
		idxE++;
	}

	public Value pop() {
		if (idxS > idxE)
			return null;
		Value result = al.get(idxS);
		idxS++;
		return result;
	}
}

public class Q1336_cmp {
	static int start;
	static int end;
	static int[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		start = sc.nextInt();
		end = sc.nextInt();
		visited = new int[10000];
		bfs();
	}

	static public void bfs() {
		Queeu q = new Queeu();
		q.push(new Value(start,0));
		while (true) {
			Value temp = q.pop();
		
			if(temp==null){
				break;
			}
		
			if(check(temp,q)==-1){
				return;
			}
			
		}
	}
	static public int check(Value input, Queeu q){
		ArrayList<Value> al = new ArrayList<Value>();
		int length = input.length;
		int val = input.val;
		String number = String.valueOf(val);
		while(number.length()<4){
			number = "0"+number;
		}
		for(int i =0; i<=3;i++){
			String str = number.substring(0, i) +"0"+ number.substring(i + 1, number.length());
			int temp = Integer.valueOf(str);
			for(int j =0; j<=9;j++){
				if(i==0&j==0)continue;
				int n = temp+(int)Math.pow(10, 3-i)*j;
				if(n==input.val)continue;
				if(check2(n) && visited[n]==0){
					visited[n] = 1;
					if(n==end){
//						System.out.println(pre);
						System.out.println(length+1);
						return -1;
					}
					q.push(new Value(n, length+1));
				}
			}
		}
		for(Value v : al){
			System.out.println(v.val);
		}
		return 0;
	}
	public static boolean check2(int input){
		for(int i = 2;i<input;i++){
			int result = input%i;
			if(result==0)return false;
		}
		return true;
	}

}
