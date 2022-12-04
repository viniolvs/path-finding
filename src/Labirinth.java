public class Labirinth {
    private int[][] L;
    private int size;

    public Labirinth(int size){
        this.size = size;
        L = new int[size][size];
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
