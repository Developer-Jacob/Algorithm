package week1;

import java.util.ArrayList;
//말의 이동 방향이 다음과 같다고 할 때, 말이 최소의 이동횟수로 졸을 잡으려고 한다.
//성공
import java.util.Scanner;
class Queue {
    ArrayList<Point_1> al;
 
    public Queue() {
        al = new ArrayList<Point_1>();
    }
 
    public void push(Point_1 input) {
        al.add(input);
    }
 
    public Point_1 pop() {
        if (al.size() == 0) {
            return null;
        }
        Point_1 result = al.get(0);
        for (int i = 0; i < al.size() - 1; i++) {
            al.set(i, al.get(i + 1));
        }
        al.remove(al.size() - 1);
        return result;
    }
}
class Point_1{
    int x;
    int y;
    int l= 0;
    Point_1(){}
    Point_1(int x, int y, int l){
        this.x = x;
        this.y = y;
        this.l = l;
    }
}
 
public class Q1106 {
    int n,m;//map size
    Point_1 horse = new Point_1();
    Point_1 soldier = new Point_1();
    int[][] visit;
    public static void main(String[] args) {
    	Q1106  q = new Q1106 ();
        q.input();
        int result = q.bfs();
        System.out.println(result);
    }
    public int bfs(){
        Point_1 p ;
        Queue q = new Queue();
        q.push(horse);
          
        while(true){
            p = q.pop();
            if(p == null ){
                System.out.println("null??");
                break;
            }
            visit[p.x][p.y]=1;
            if(p.x== soldier.x && p.y==soldier.y){
                break;
            }
            if(soldier.x>=p.x && soldier.y<p.y){//1사분면
                if(checkPoint_1(p.x+2,p.y-1)){
                    q.push(new Point_1(p.x+2,p.y-1,p.l+1));
                    visit[p.x+2][p.y-1]=1;
                }
                if(checkPoint_1(p.x+1,p.y-2)){
                    q.push(new Point_1(p.x+1,p.y-2,p.l+1));
                    visit[p.x+1][p.y-2]=1;
                }
            }
            else if(soldier.x>p.x && soldier.y>=p.y){//2
                if(checkPoint_1(p.x+2,p.y+1)){
                    q.push(new Point_1(p.x+2,p.y+1,p.l+1));
                    visit[p.x+2][p.y+1]=1;
                }
                if(checkPoint_1(p.x+1,p.y+2)){
                    q.push(new Point_1(p.x+1,p.y+2,p.l+1));
                    visit[p.x+1][p.y+2]=1;
                }
            }
            else if(soldier.x<=p.x && soldier.y>p.y){//3
                if(checkPoint_1(p.x-1,p.y+2)){
                    q.push(new Point_1(p.x-1,p.y+2,p.l+1));
                    visit[p.x-1][p.y+2]=1;
                }
                if(checkPoint_1(p.x-2,p.y+1)){
                    q.push(new Point_1(p.x-2,p.y+1,p.l+1));
                    visit[p.x-2][p.y+1]=1;
                }
            }
            else if(soldier.x<p.x && soldier.y<=p.y){//4
                if(checkPoint_1(p.x-1,p.y-2)){
                    q.push(new Point_1(p.x-1,p.y-2,p.l+1));
                    visit[p.x-1][p.y-2]=1;
                }
                if(checkPoint_1(p.x-2,p.y-1)){
                    q.push(new Point_1(p.x-2,p.y-1,p.l+1));
                    visit[p.x-2][p.y-1]=1;
                }
            }
            else{
                System.out.println("else????????");
            }
        }
        return p.l;
         
    }
    public boolean checkPoint_1(int x, int y){
        if(x>0 && x<=m && y>0 && y <=n && visit[x][y]!=1){
            return true;
        }
        else return false;
    }
    public void input() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        horse.y = sc.nextInt();
        horse.x = sc.nextInt(); 
        soldier.y = sc.nextInt();
        soldier.x = sc.nextInt();
        visit = new int[m+1][n+1];
    }
}