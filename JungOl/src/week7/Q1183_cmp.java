package week7;

import java.util.Scanner;
class Coin{
	public Coin(int val, int count) {
		// TODO Auto-generated constructor stub
		this.count = count;
		this.val = val;
	}
	int count ;
	int val;
	int accumVal;
	public int getAccumVal() {
		return count*val;
	}
}
public class Q1183_cmp {
	static Coin[] coinList;
	static int[] result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cost = sc.nextInt();
		coinList = new Coin[6];
		int[] coin = {500,100,50,10,5,1};
		result = new int[6];
		
		for(int i =0 ;i<6;i++){
			coinList[i] = new Coin(coin[i],sc.nextInt());
		}
		findNum(cost);
		int count=0;
		
		for(int i:result){
			count = count+i;
		}
		System.out.println(count);
		for(int i:result){
			System.out.print(i+" ");
		}
	}
	static public void findNum(int cost){
		int temp = 0;
		if(cost==0)return;
		for(int i =5; i>=0;i--){
			temp = temp +coinList[i].getAccumVal();
			if(cost<temp && coinList[i].count>0 && cost-coinList[i].val>=0){
				result[i]=result[i]+1;
				coinList[i].count = coinList[i].count-1; 
				findNum(cost-coinList[i].val);
				break;
			}
		}
	}
}
