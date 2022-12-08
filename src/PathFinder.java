import java.util.LinkedList;

// Implementação dos métodos para achar a saída do labirinto
public class PathFinder {
    private int[] start;
    private int[] finish;
    private char[][] L;
    private int len;

    public PathFinder(Labirinth L){
        this.start = L.getStart();
        this.finish = L.getFinish();
        this.len = L.getLen();
        // Copia o labirinto para o objeto PathFinder
        this.L = new char[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                this.L[i][j] = L.getLabirinth()[i][j];
            }
        }
    }

    public int depthSearch() {
        depthSearch(start[0], start[1], new LinkedList<int[]>());
        int heuristica = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(L[i][j] == 'o')
                    heuristica++;
            }
        }
        return heuristica;
    }
    
    public void printPath() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                    System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }

    // Busca em profundidade
    private void depthSearch(int x, int y, LinkedList<int[]> next_pos) {
        // Marca o caminho percorrido
        L[x][y] = 'o';

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
        
        while(next_pos.size() > 0 && L[finish[0]][finish[1]] != 'o'){
            int next_x = next_pos.get(0)[0];
            int next_y = next_pos.get(0)[1];
            // Remove da Lista o caminho já escolhido
            next_pos.remove(0);

            // Verifica se é possível seguir para o próximo lado
            if(next_x >= 0 && next_x < len && next_y >= 0 && next_y < len && (L[next_x][next_y] == ' ' || L[next_x][next_y] == 's')){
                // Chamada recursiva para o próximo 
                depthSearch(next_x, next_y, new LinkedList<int[]>());
            }
        }
        return ;
    }
}
