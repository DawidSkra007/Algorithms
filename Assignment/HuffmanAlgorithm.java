import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HuffmanAlgorithm {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Do not instantiate.
    private HuffmanAlgorithm() { }

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {
        // read the input
        String inputStr = BinaryStdIn.readString();//reads the whole file to string
        char[] input = inputStr.toCharArray();//creates a char array made up of chars from the input string

        // tabulate frequency counts
        int[] freq = new int[R];//int array of frequencies
        for (char c : input) {
            freq[c]++;//updates frequency for each character
        }

        // build Huffman trie
        Node root = buildTrie(freq);

        // build code table
        String[] string = new String[R];
        buildCode(string,root,"");

        // print trie for decoder
        writeTrie(root);

        // print number of bytes in original uncompressed message
        BinaryStdOut.write(input.length);

        // use Huffman code to encode input
        for (int i = 0; i < input.length; i++) {
            String encoded = string[input[i]];
            for (int j = 0; j < encoded.length(); j++) {
                if (encoded.charAt(j) == '0') {
                    BinaryStdOut.write(false);
                }
                else if (encoded.charAt(j) == '1') {
                    BinaryStdOut.write(true);
                }
                else throw new IllegalStateException("Error");
            }
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void decompress() {

        // read in Huffman trie from input stream
        Node root = readTrie();

        // number of bytes to write
        int bytes = BinaryStdIn.readInt();

        // decode using the Huffman trie
        for (int i = 0; i < bytes; i++) {
            Node curr = root;
            while (!curr.isLeaf()) {
                boolean b = BinaryStdIn.readBoolean();
                if (b) {//1
                    curr = curr.right;
                } else {//0
                    curr = curr.left;
                }
            }
            BinaryStdOut.write(curr.ch, 8);
        }
        BinaryStdOut.close();
        BinaryStdIn.close();
    }

    // build the Huffman trie given frequencies
    private static Node buildTrie(int[] freq) {

        // initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));

        // special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) {
            if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
            else                 pq.insert(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch, 8);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    // make a lookup table from symbols and their encodings
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }

    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            return new Node(BinaryStdIn.readChar(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream(new File(args[1])));
        System.setOut(new PrintStream(new FileOutputStream(new File(args[2]))));
        long timing;
        if (args[0].equals("compress")) {
            timing = System.nanoTime();
            compress();
            timing = System.nanoTime() - timing;
            System.setOut(new PrintStream(new FileOutputStream(new File("times.txt"))));
            System.out.printf("Time(ns) taken to compress: \n" + timing+"\n");
        } else if (args[0].equals("decompress")) {
            timing = System.nanoTime();
            decompress();
            timing = System.nanoTime() - timing;
            System.setOut(new PrintStream(new FileOutputStream(new File("times.txt"))));
            System.out.printf("Time(ns) taken to decompress: " + timing +"\n");
        } else {
            throw new IllegalArgumentException("Usage! -> java HuffmanAlgorithm compress/decompress file1 file2");
        }
    }
}
