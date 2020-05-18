import java.io.*;
import java.util.*;

class RopeProblem {
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

	class Rope {
		String s;
		void process( int i, int j, int k ) {
			String t = s.substring(0, i) + s.substring(j + 1);
			s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
		}
		String result() {
			return s;
		}
		Rope( String s ) {
			this.s = s;
		}
	}

	public static void main( String[] args ) throws IOException {
		new RopeProblem().run();
	}
	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		Rope rope = new Rope(in.next());
		for (int q = in.nextInt(); q > 0; q--) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			rope.process(i, j, k);
		}
		out.println(rope.result());
		out.close();
	}
}
/*
hlelowrold
2
1 1 2
6 6 7
Output: helloworld

Explanation:
hlelowrold -> hellowrold -> helloworld
When 𝑖 = 𝑗 = 1, 𝑆[𝑖..𝑗] = 𝑙, and it is inserted after the 2-nd symbol of the remaining string h𝑒𝑙𝑜𝑤𝑟𝑜𝑙𝑑, which gives h𝑒𝑙𝑙𝑜𝑤𝑟𝑜𝑙𝑑. 
Then 𝑖 = 𝑗 = 6, so 𝑆[𝑖..𝑗] = 𝑟, and it is inserted after the 7-th symbol of the remaining string h𝑒𝑙𝑙𝑜𝑤𝑜𝑙𝑑, which gives h𝑒𝑙𝑙𝑜𝑤𝑜𝑟𝑙𝑑.

abcdef 
2
0 1 1
4 5 0
Output: efcabd
Explaination: abcdef -> cabdef -> efcabd
*/
