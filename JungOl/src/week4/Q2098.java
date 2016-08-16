package week4;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;
class Queue {
	ArrayList<String> al;

	public Queue() {
		al = new ArrayList<String>();
	}

	public void push(String v) {
		al.add(v);
	}

	public String pop() {
		if (al.size() == 0) {
			return null;
		}
		String result = al.get(0);
		for (int i = 0; i < al.size() - 1; i++) {
			al.set(i, al.get(i + 1));
		}
		al.remove(al.size() - 1);
		return result;
	}
}
//class Value{
//	String str;
//	public Value( String str) {
//		this.str = str;
//	}
//	
//}
public class Q2098 {
	static int[][] visited;
	static ArrayList<String> visit;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		ArrayList<Integer>[] map = new ArrayList[n+2];
		visit = new ArrayList<String>();
		map[0] = new ArrayList<>();
		map[0].add(0);
		//map setting
		for(int i =1; i<=n; i++){
			map[i] = new ArrayList<Integer>();
			if(i==k){
				for(int j =0; j<n; j++){
					map[i].add(j+1); 
				}
			}else{
				map[i].add(k);
				for(int j =1; j<=3; j++){
					int temp = i+j-2;
					if(temp>0 && temp!=k && temp<=n){
						map[i].add(temp);
					}
				}
				
			}
		}
//		for(int i:map[1]){
//			System.out.print(i);
//		}
//		System.out.println();
//		for(int i:map[2]){
//			System.out.print(i);
//		}
//		System.out.println();
//		for(int i:map[3]){
//			System.out.print(i);
//		}
//		System.out.println();
//		for(int i:map[4]){
//			System.out.print(i);
//		}
//		System.out.println("---------");
		visited = new int[n+1][n+1];
		Queue q = new Queue();
		q.push("");
		int count = 0;
//			System.out.println("for------");
			while(true){
				String val = q.pop();
				if(val==null){
					break;
				}
				ArrayList<Integer> tempList = map[val.length()+1];
//				System.out.println(val);
				
//				visited[i][val] = 1;
				for(int j=0;j<tempList.size();j++){
					if(!val.contains(tempList.get(j)+"")){
						String str = val+tempList.get(j);
						if(str.length()!=4){
							q.push(str);
						}else{
							count++;
						}
						
//						System.out.println(val+tempList.get(j));
						
					}
					
					
				}
			}
//		System.out.println();
		System.out.println(count);
	}
}
