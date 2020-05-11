import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PhoneBook {
    private FastScanner in = new FastScanner();
    
    private HashMap<Integer, String> contacts = new HashMap<Integer, String>();

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }


    private void processQuery(Query query) {
    	if (query.type.equals("add"))
			contacts.put(query.number, query.name);
		
    	else if (query.type.equals("del")) {
			if(contacts.containsKey(query.number)) 
				contacts.remove(query.number);
		} 
    	else {
			String response = "not found";
			if(contacts.containsKey(query.number)) 
				response = contacts.get(query.number);
			writeResponse(response);
		}
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQuery(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

/*
12
add 911 police
add 76213 Mom
add 17239 Bob
find 76213
find 910
find 911
del 910
del 911
find 911
find 76213
add 76213 daddy 
find 76213

Output:
Mom
not found
police
not found
Mom
daddy

8
find 3839442
add 123456 me
add 0 granny
find 0
find 123456 
del 0
del 0
find 0

Output:
not found
granny
me
not found
*/
