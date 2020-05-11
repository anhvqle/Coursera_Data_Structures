import java.io.*;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).run();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }
        Table getParent() {
            // find super parent and compress path
        	while (parent != parent.parent) {
                parent = parent.parent;
            }
            return parent;
        }
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();	// i_id
        Table realSource = source.getParent();				// j_id
        if (realDestination == realSource) {
            return;
        }
        // merge two components here
        // use rank heuristic
        // update maximumNumberOfRows
        if (realDestination.rank > realSource.rank) {
            realSource.parent = realDestination;
            realDestination.numberOfRows += realSource.numberOfRows;
            realSource.numberOfRows = 0;
        }
        else {
            realDestination.parent = realSource;
            realSource.numberOfRows += realDestination.numberOfRows;
            realDestination.numberOfRows = 0;
            if (realSource.rank == realDestination.rank)
            	realSource.rank++;
        }
        maximumNumberOfRows = Math.max(Math.max(maximumNumberOfRows, realSource.numberOfRows), realDestination.numberOfRows);
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}

/*
5 5
1 1 1 1 1
3 5
2 4
1 4
5 4
5 3

Output:
2
2
3
5
5


6 4
10 0 5 0 3 3
6 6
6 5
5 4
4 3

Output:
10
10
10
11
*/
