import java.util.*;
import java.io.*;

public class StackWithMax {
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
    
    private Stack<Integer> data;
    private Stack<Integer> max;

    public StackWithMax() {
        data = new Stack<Integer>();
        max = new Stack<Integer>();
    }

    public void push(int x) {
        data.push(x);
        if (max.empty() || x >= max())
            max.push(x);
    }

    public int pop() {
        if (data.peek() == max())
            max.pop();
        return data.pop();
    }

    public int peek() {
        return data.peek();
    }

    public int max() {
        return max.peek();
    }
    
    
    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        StackWithMax stack = new StackWithMax();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                stack.push(value);
            } 
            
            else if ("pop".equals(operation)) 
                stack.pop();
            
            else if ("max".equals(operation)) 
                System.out.println(stack.max());

        }
    }

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}


/*
5
push 2 
push 1 
max 
pop 
max
output: 2 2

5
push 1
push 2 
max 
pop 
max
output: 2 1

10 
push 2 
push 3 
push 9 
push 7 
push 2 
max 
max 
max 
pop 
max
output: 9 9 9 9

3
push 1 
push 7 
pop
output: null

6
push 7
push 1
push 7
max 
pop 
max
output: 7 7
*/
