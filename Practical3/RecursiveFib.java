public class RecursiveFib {

    static int reFib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return reFib(n - 1) + reFib(n - 2);//O(2^n), two functions being added
        }
    }
    public static void main(String[] args) {
        int n = 50;
        Stopwatch timer = new Stopwatch();
        int result = reFib(n);
        System.out.println(result);
        System.out.println("elapsed time = " + timer.elapsedTime());
    }
}
