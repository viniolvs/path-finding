import java.util.Random;

public class Labirinth {
    private int[][] L;
    private int size;

    public Labirinth(int size){
        this.size = size;
        L = new int[size][size];
        L = genLabirinth();
    }

    private int[][] genLabirinth() {
        int[][] lab = new int[size][size];
        Random random = new Random();
        int[] start = new int[2];
        int[] end = new int[2];
        int TOP = 0, BOTTOM = 1, LEFT = 2, RIGHT = 3;
        
        start[0] = random.nextInt(size-1);
        start[1] = random.nextInt(3);
        end[0] = random.nextInt(size-1);
        end[1] = random.nextInt(3);

        System.out.println(start[0] + " " + start[1] + " " + end[0] + " " + end[1]);
        return lab;
    }

    public int[][] getLabirinth() {
        return L;
    }

    public void printLabirinth() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(L[i][j] + " ");
            }
            System.out.println();
        }
    }
}
