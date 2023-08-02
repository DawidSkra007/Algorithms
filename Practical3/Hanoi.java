public class Hanoi {

    static void recTowerOfHanoi(int disk, char source, char aux, char dest) {

        if (disk == 1) {
            System.out.println("Move disk 1 from rod " + source + " to rod " + dest);
        } else {
            recTowerOfHanoi(disk - 1,source,dest,aux);
            System.out.println("Move disk " + disk + " from rod " +  source + " to rod " + dest);
            recTowerOfHanoi(disk - 1,aux,source,dest);
        }
    }


    public static void main(String[] args) {
        int n = 12;
        Stopwatch timer = new Stopwatch();

        recTowerOfHanoi(n,'A','B','C');
        System.out.println("elapsed time = " + timer.elapsedTime());

    }

}
