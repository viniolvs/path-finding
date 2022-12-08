import java.util.LinkedList;
import java.util.Random;


public class Labirinth {
    private char[][] L;
    private int size;
    private int len;

    public Labirinth(int size) {
        this.size = size;
        this.len = size * 2 + 1;
        // Matriz com tamanho necessário para marcar paredes do labirinto
        L = new char[len][len];
        genLabirinth();
    }

    private void genLabirinth() {
        // Inicializa o labirinto
        for (int i = 0; i < len; i++) {
            L[0][i] = 'X';
        }
        for (int i = 0; i < len; i++) {
            L[len - 1][i] = 'X';
        }
        for (int i = 0; i < len; i++) {
            L[i][0] = '|';
        }
        for (int i = 0; i < len; i++) {
            L[i][len - 1] = '|';
        }
        for (int i = 1; i < len - 1; i++) {
            for (int j = 1; j < len - 1; j++) {
                if (i % 2 == 0)
                    if (j % 2 == 0)
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
        // x = row y = column
        int x = 0, y = 0, temp_x = 0, temp_y = 0;
        // Posição
        pos = random.nextInt(size);
        pos = pos * 2 + 1;
        // Lado que o labirinto começa
        side = random.nextInt(3);
        switch (side) {
            // TOP
            case 0:
                x = 0 + 1;
                temp_x = 0;
                y = pos;
                temp_y = pos;
                break;
            // RIGHT
            case 1:
                x = pos;
                temp_x = pos;
                y = len - 2;
                temp_y = len - 1;
                break;
            // BOTTOM
            case 2:
                x = len - 2;
                temp_x = len - 1;
                y = pos;
                temp_y = pos;
                break;
            // LEFT
            case 3:
                x = pos;
                temp_x = pos;
                y = 0 + 1;
                temp_y = 0;
                break;
        }
        L[temp_x][temp_y] = ' ';

        int[][] vis = new int[size][size];
        vis = walk((x - 1) / 2, (y - 1) / 2, vis, new LinkedList<int[]>());
    }

    // Abre os caminhos no labirinto utilizando busca em profundidade
    private int[][] walk(int x, int y, int[][] vis, LinkedList<int[]> next_pos){
        vis[x][y] = 1;

        // Adiciona todas as possibilidades de movimentaçao em uma Lista
        for (int i = 0; i < 4; i++) {
            int[] xy = new int[2];
            xy[0] = x; xy[1] = y;
            switch (i) {
                //TOP
                case 0:
                    xy[0] = x - 1;
                    break;
                //RIGHT
                case 1:
                    xy[1] = y + 1;
                    break;
                //BOTTOM
                case 2:
                    xy[0] = x + 1;
                    break;
                //LEFT
                case 3:
                    xy[1] = y - 1;
                    break;
            }
            next_pos.push(xy);
        }
        
        Random random = new Random();
        while(next_pos.size() > 0){
            //sorteia o próximo caminho a ser seguido
            int next = random.nextInt(next_pos.size());
            // Verifica o lado que a busca continuará
            String str = new String();
            str = "";
            int next_x = next_pos.get(next)[0];
            int next_y = next_pos.get(next)[1];
            if(next_x >= 0 && next_y >=0){
                if(next_x < x )
                    str = "TOP";
                else if(next_x > x )
                    str = "BOTTOM";
                else if(next_y > y )
                    str = "RIGHT";
                else if(next_y < y )
                    str = "LEFT";
            }
            // Remove da Lista o caminho já escolhido
            next_pos.remove(next);

            // Verifica se é possível seguir para o lado sorteado
            if(next_x >= 0 && next_x < size && next_y >= 0 && next_y < size && vis[next_x][next_y] == 0){
                int temp_x = next_x*2+1, temp_y = next_y*2+1;
                // Atualiza as coordenadas para o próximo
                // Abre o caminho no labirinto
                switch (str) {
                    case "TOP":
                        L[temp_x + 1][temp_y] = ' ';
                        break;
                    case "BOTTOM":
                        L[temp_x - 1][temp_y] = ' ';
                        break;
                    case "RIGHT":
                        L[temp_x][temp_y-1] = ' ';
                        break;
                    case "LEFT":
                        L[temp_x][temp_y+1] = ' ';
                        break;
                }
                // Chamada recursiva para o próximo 
                walk(next_x, next_y, vis, new LinkedList<int[]>());
            }
        }
        return vis;
    }

    public char[][] getLabirinth() {
        return L;
    }

    public void printLabirinth() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }
}
