import java.util.Random;

public class Labirinth {
    private char[][] L;
    private int size;
    private int len;

    public Labirinth(int size) {
        this.size = size;
        this.len = size*2+1;
        // Matriz com tamanho necessário para marcar paredes do labirinto
        L = new char[len][len];
        genLabirinth();
    }

    private void genLabirinth() {
        // Marca todas as posiçoes como não visitadas
        for (int i = 0; i < len; i++) {
            L[0][i] = '-';
        }
        for (int i = 0; i < len; i++) {
            L[len-1][i] = '-';
        }
        for (int i = 0; i < len; i++) {
           L[i][0] =  '|';
        }
        for (int i = 0; i < len; i++) {
           L[i][len - 1] =  '|';
        }
        for (int i = 1; i < len-1; i++) {
            for (int j = 1; j < len-1; j++) {
                if (i % 2 == 0 )
                    if(j%2 == 0)
                        L[i][j] = '+';
                    else
                        L[i][j] = '-';
                else if (j % 2 == 0)
                    L[i][j] = '|';
                else
                    L[i][j] = ' ';
            }
        }

        // Define a posição inicial do labirinto
        Random random = new Random();
        int side, pos;
        // x = row   y = column
        int x = 0, y = 0;
        // Posição
        pos = random.nextInt(size);
        pos = pos*2+1;
        // Lado que o labirinto começa
        side = random.nextInt(3);
        switch (side) {
            //TOP
            case 0:
                x = 0;
                y = pos;
                break;
            //RIGHT
            case 1:
                x = pos;
                y = len-1;
                break;
            //BOTTOM
            case 2:
                x = len-1;
                y = pos;
                break;
            //LEFT
            case 3:
                x = pos;
                y = 0;
                break;
        }
        L[x][y] = ' ';
    }

    private void walk(){
        // Anda no labirinto abrindo caminhos
    }

    public char[][] getLabirinth() {
        return L;
    }

    public void printLabirinth() {
        for (int i = 0; i < len ; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }
}
