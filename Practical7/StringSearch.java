public class StringSearch {
    public static int searchBrute(String pat, String text) {
        int n = text.length();
        int m = pat.length();
        for(int pos = 0;pos <= n - m;pos++) {//goes through the  whole text
            int j;
            for(j = 0;j < m;j++) {//looks for the pattern
                if(text.charAt(pos + j) != pat.charAt(j)) {//if it finds a mismatch char it breaks out of for loop
                    break;
                }
            }
            if(j == m) {//when pos equals length of pattern we know we found our pattern
                return pos;
            }
        }
        return n;//if not found
    }//TIME COMPLEXITY == O(m(n-m+1))

    public static void searchKnutt(String pat, String text) {
        int n = text.length();
        int m = pat.length();
        int[] lps = new int[m];
        int j = 0;

        computeLPSArray(pat, m,lps);//computes the amount of jumps to be made

        int i = 0;
        while (i < n) {//goes through text
            if (pat.charAt(j) == text.charAt(i)) {//when chars match up
                i++;
                j++;
            }
            if (j == m) {//when pattern found print its index and keep on looking
                System.out.println("index of pattern: " + (i-j));
                j = lps[j - 1]; // index of next char to match
            }
            else if (i < n && pat.charAt(j) != text.charAt(i)) {//mismatch
                if (j != 0) {
                    j = lps[j - 1];// index of next char to match
                }else {
                    i = i + 1;
                }
            }
        }
    }//TIME COMPLEXITY ==  O(n)

    private static void computeLPSArray(String pat, int m,int[] lps) {
        lps[0] = 0;
        int l = 0;

        int i = 1;
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(l)) {
                l++;
                lps[i] = l;
                i++;
            }else {
                if (l != 0) {
                    l = lps[l - 1];
                }else {
                    lps[i] = l;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String pat = "adba";
        String text = "abdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbababdbabadba";
        System.out.println(text.length());

        Stopwatch timer = new Stopwatch();
        //searchBrute(pat,text);
        searchKnutt(pat,text);
        double time  = timer.elapsedTime();
        System.out.println("elapsed time: " + time );
    }
}
