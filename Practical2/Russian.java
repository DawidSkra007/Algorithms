import java.math.BigInteger;

public class Russian {
        public static BigInteger russ(BigInteger a,BigInteger b) {
            BigInteger res = new BigInteger("0");
            BigInteger zero = new BigInteger("0");
            BigInteger two = new BigInteger("2");

            while (b.compareTo(zero) > 0) {//O(n)
                if (!b.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
                    res = res.add(a);
                }
                a = a.multiply(two);
                b = b.divide(two);
            }//O(logn)
            return res;
        }

    public static void main(String[] args) {
        BigInteger count = new BigInteger("0");
        BigInteger a = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        BigInteger b = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
        //Helper file to measure time count function takes to run
        Stopwatch timer = new Stopwatch();
        count = russ(a,b);
        System.out.println("elapsed time = " + timer.elapsedTime());
        System.out.println(count);
    }
}
