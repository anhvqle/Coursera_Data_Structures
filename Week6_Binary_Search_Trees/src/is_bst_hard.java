import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        class Node {
            long key;								//long instead of int to handle big cases
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
        
        boolean isBinarySearchTree() {
            if(this.tree.length == 0) 
            	return true;
            return isBST(this.tree[0], Long.MIN_VALUE, Long.MAX_VALUE); 
            
        }

        boolean isBST(Node node, long min, long max) { 
            if (node == null) 
                return true; 
            if (node.key < min || node.key > max) 
                return false; 
            Node left;
            if(node.left == -1) 
            	left = null;
            else
            	left = this.tree[node.left];

            Node right;
            if(node.right == -1) 
            	right = null;
            else
            	right = this.tree[node.right];
            
            return (isBST(left, min, node.key-1) && isBST(right, node.key, max)); 
        } 

        Node next(Node node) {
            if(node.right != -1)
                return leftDescendant(this.tree[node.right]);
            else
                return null;
        }
        
        Node leftDescendant(Node node) {
            if(node.left == -1)
                return node;
            else
                return leftDescendant(this.tree[node.left]);
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
/*
3
2 1 2
1 -1 -1
2 -1 -1
Output: CORRECT

3
2 1 2
2 -1 -1
3 -1 -1
Output: INCORRECT

3
1 1 2
2 -1 -1
3 -1 -1
Output: INCORRECT
*/
