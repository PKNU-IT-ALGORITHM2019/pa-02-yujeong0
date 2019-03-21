import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TSP {

	public static void main(String []args) {
		int N = 0; // 지점의 개수
		int[][] arr = new int[100][2];
		
		try {
			Scanner sc = new Scanner(new File("input0.txt"));
			N = sc.nextInt();
			
			for(int i=0;i<N;i++) {
				arr[i][0] = sc.nextInt();
				arr[i][1] = sc.nextInt();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("파일 없음");
		}
		find_tour(arr, 1, N, 0);
		
		System.out.println(min);
		
		int[] idx = new int[N];
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(min_route[i][0] == arr[j][0] && min_route[i][1] == arr[j][1]) {
					idx[i] = j;
					break;
				}
			}
		}
		System.out.print("[ ");
		for(int i=0;i<N;i++)
			System.out.print(idx[i] + " ");
		System.out.println("]");
	}
	
	static void swap(int[][] arr, int k, int i) {
		for(int j = 0;j<2;j++) {
			int tmp = arr[k][j];
			arr[k][j] = arr[i][j];
			arr[i][j] = tmp;
		}
	}
	
	static double min = Double.MAX_VALUE;
	static int [][] min_route = new int[100][2];
	static void find_tour(int[][] arr, int k, int N, double dist) {
		if(min < dist) 	return;
		if(k == N) { 
			dist += get_dist(arr[0][0], arr[k-1][0], arr[0][1], arr[k-1][1]);
			if(min > dist) {
				min = dist;
				for(int i = 0; i < N; i++) {
					min_route[i][0] = arr[i][0];
					min_route[i][1] = arr[i][1];
				}
			}
		}
		else {
			for(int i = k; i < N; i++) {
				swap(arr, k ,i);
				find_tour(arr, k+1, N, dist + get_dist(arr[k-1][0], arr[k][0], arr[k-1][1], arr[k][1]));
				swap(arr, k, i);
			}
		}
	}
	
	static double get_dist(int x0, int x1, int y0, int y1) {
		int a = x1 - x0;
		int b = y1 - y0;
		
		return Math.sqrt(a*a + b*b);
	}
	
}
