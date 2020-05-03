import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
    
    private int LeftChild(int i) {
    	return 2*i + 1;
    }
    
    private int RightChild(int i) {
    	return 2*i + 2;
    }
    
    private void SiftDown(int i) {
    	int maxIndex = i;
    	int left = LeftChild(i);
    	int right = RightChild(i);
    	if(left < data.length && data[left] < data[maxIndex]) 
    		maxIndex = left;
    	
    	if(right < data.length && data[right] < data[maxIndex]) 
    		maxIndex = right;
    	
    	if(maxIndex != i) {
    		swaps.add(new Swap(i, maxIndex));
    		int temp = data[i];
    		data[i] = data[maxIndex];
    		data[maxIndex] = temp;
    		SiftDown(maxIndex);
    	}
    }

    private void buildHeap() {
    	swaps = new ArrayList<Swap>();
    	for(int i = Math.floorDiv(data.length, 2); i >= 0; i--) {
    		SiftDown(i);
    	}
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        buildHeap();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
/*
5
5 4 3 2 1

Output: 
3
1 4
0 1
1 3

5
1 2 3 4 5

Output: 0
*/
