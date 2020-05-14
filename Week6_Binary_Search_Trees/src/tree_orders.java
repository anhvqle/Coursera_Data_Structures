import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
	
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}
		ArrayList<Integer> result;
		List<Integer> inOrder() {
			result = new ArrayList<Integer>();
			InOrderTraversal(0);
			return result;
		}

		List<Integer> preOrder() {
			result = new ArrayList<Integer>();
			PreOrderTraversal(0);  
			return result;
		}

		List<Integer> postOrder() {
			result = new ArrayList<Integer>();
			PostOrderTraversal(0);
			return result;
		}

		private void InOrderTraversal(int node) {
			if(node == -1) 
				return;
			InOrderTraversal(left[node]);
			result.add(key[node]);
			InOrderTraversal(right[node]);
		}

		private void PreOrderTraversal(int node) {
			if (node == -1)
				return;
            result.add(key[node]);
            PreOrderTraversal(left[node]);
            PreOrderTraversal(right[node]);
        }

		private void PostOrderTraversal(int node) {
			if (node == -1) 
				return;
            PostOrderTraversal(left[node]);
            PostOrderTraversal(right[node]);
            result.add(key[node]);
        }
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_orders().run();
					}
				catch (IOException e) {
					
				}
				}
			}, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
/*
5
4 1 2
2 3 4
5 -1 -1
1 -1 -1
3 -1 -1 

Output: 
1 2 3 4 5
4 2 1 3 5
1 3 2 5 4

10
0 7 2
10 -1 -1
20 -1 6
30 8 9
40 3 -1
50 -1 -1
60 1 -1
70 5 4
80 -1 -1
90 -1 -1

Output:
50 70 80 30 90 40 0 20 10 60
0 70 50 40 30 80 90 20 60 10
50 80 90 30 40 70 10 60 20 0
*/
