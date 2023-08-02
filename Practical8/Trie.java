public class Trie {
    // Alphabet size (# of symbols) we pick 26 for English alphabet
    static final int ALPHABET_SIZE = 26;


    // class for Trie node
    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        // isEndOfWord is true if the node represents end of a word i.e. leaf node
        boolean isEndOfWord;

        TrieNode(){
            isEndOfWord = false;

            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    }

    static TrieNode root;
    // If not key present, inserts into trie
    // If the key is prefix of Trie node,
    //  marks leaf node
    static void insert(String key) {
        TrieNode curr = root;

        for (int i = 0; i < key.length();i++) {
            int index = key.charAt(i) - 97;

            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;//leaf node
    }
    /*
            TIME COMPLEXITY == O(length of key), as it iterates through the key
     */

    // Returns true if key presents in trie, else false
    static boolean search(String key) {
        TrieNode curr = root;

        for (int i = 0; i < key.length();i++) {
            int index = key.charAt(i) - 97;

            if (curr.children[index] == null) { // not found
                return false;
            }
            curr = curr.children[index];
        }
        if (curr != null && (curr.isEndOfWord = true)) { // if we go through the key and curr is not null and isEndOfWord is true, this means we found the word(key)
            return true;
        } else {
            return false;
        }
    }
     /*
            TIME COMPLEXITY == O(length of key), as it iterates through the key
     */

    // Driver
    public static void main(String args[]) {

        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};
        String[] find = {"bank" , "filter" , "harry", "films"};

        root = new TrieNode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++) {
            insert(keys[i]);
        }

        // Search for different keys
        if (search(find[0])) {
            System.out.println( find[0] + " is in Trie\n");
        } else {
            System.out.println( find[0] + " is not Trie\n");
        }

        if (search(find[1])) {
            System.out.println(find[1] + " is in Trie\n");
        } else {
            System.out.println(find[1] + " is not Trie\n");
        }
        if (search(find[2])) {
            System.out.println(find[2] + " is in Trie\n");
        } else {
            System.out.println(find[2] + " is not Trie\n");
        }

        if (search(find[3])) {
            System.out.println(find[3] + " is in Trie\n");
        } else {
            System.out.println(find[3] + " is not Trie\n");
        }
    }
//end of class
}
