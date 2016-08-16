package week9;
import java.util.ArrayList;
import java.util.Scanner;
 
class Point{
    int x;
    int y;
    public Point(int x,int y) {
        // TODO Auto-generated constructor stub
        this.x = x;
        this.y = y;
    }
}
//스도쿠
public class Q1824{
     
     
    static ArrayList<Point> zeroP;
    static int[][] map;
    static boolean stop = false;
    public static void main(String[] args) {
        zeroP = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        map = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j]==0){
                    zeroP.add(new Point(i, j));
                }
            }
        }
        backTracking(0);
         
    }
    static public void backTracking(int num){
//      System.out.println("BackTracking function       "+num);
         
        if(num==zeroP.size()){
            stop = true;
            for(int a=0;a<9;a++){
                for(int b=0;b<9;b++){
                    System.out.print(map[a][b]+" ");
                }
                System.out.println();
            }
            return;
        }
         
        Point p = zeroP.get(num);
//      System.out.println("Point"+p.x+","+p.y);
        //num 위치에 들어갈수 있는 숫자목록을 불러옴
        ArrayList<Integer> list = getNum(p.x,p.y);
//      System.out.println("List "+list.toString());
         
        if(list.size()==0){
//          System.out.println("요기?");
            return;
        }
         
        for(int i=0;i<list.size();i++){
            map[p.x][p.y] = list.get(i);
            if(stop){
                return;
            }else{
                backTracking(num+1);
//              System.out.println(">");
            }
        }
        map[p.x][p.y] = 0;
    }
 
    static ArrayList<Integer> getNum(int x, int y) {
        boolean flag = true;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if(lineXCheck(x, i) && lineYCheck(y,i) && recCheck(x, y, i)){
                list.add(i);
            }
        }
         
        return list;
    }
    static boolean lineXCheck(int lineX, int input){
        for (int i = 0; i < 9; i++) {
            if(map[lineX][i]==input){
                return false;
            }
        }
        return true;
    }
    static boolean lineYCheck(int lineY, int input){
        for (int i = 0; i < 9; i++) {
            if(map[i][lineY]==input){
                return false;
            }
        }
        return true;
    }
    static boolean recCheck(int x, int y, int input){
        int startX = x/3*3;
        int startY = y/3*3;
        for(int i =startX;i<startX+3;i++){
            for(int j =startY;j<startY+3;j++){
                if(map[i][j]==input){
                    return false;
                }
            }
        }
        return true;
    }
 
}