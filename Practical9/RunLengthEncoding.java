public class RunLengthEncoding {

    public static void encode(String str) {

         for (int i = 0; i < str.length();i++) {

            int counter = 1;
            while (i < (str.length() - 1) && str.charAt(i) == str.charAt(i + 1)) {
                counter++;
                i++;
            }
            //encountered different character
            System.out.print(str.charAt(i)); // prints the letter
            System.out.print(counter); // prints the amount of occurences of that letter
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        encode(args[0]);
    }
}
