public class IterativeFib {

    static int itFib(int n) {
        if (n <= 1) {
            return 1;
        }

        int fib = 1;
        int prevFib = 1;

        for (int i = 2; i < n;i++) {
            int temp = fib;
            fib = fib + prevFib;
            prevFib = temp;
        }
        return fib;
    }//O(n), only one for loop being incremented

    public static void main(String[] args) {
        int n = 50;
        Stopwatch timer = new Stopwatch();
        int result = itFib(n);
        System.out.println(result);
        System.out.println("elapsed time = " + timer.elapsedTime());
    }
}
