import java.util.LinkedList;

public class Astar extends PathFinder {
    protected int[] start;
    protected int[] finish;
    protected char[][] L;
    protected int len;

    public Astar(Labirinth L){
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

    public char[][] getPath() {
        return L;
    }
    
    // Implementação do método A*

    //calcula a distancia entre ponto atual e ponto final
    //calcula custo g, custo h e custo f dos pontos ao redor que podem ser seguidos
    //custo g = distância entre um ponto e o ponto inicial
    //custo h = distância entre um ponto e o ponto final
    //custo f = custo g + custo h 
    //segue o caminho com o menor custo f, caso custo f seja igual, segue o caminho com menor custo h
    //cria uma lista com a sequencia de pontos seguidos por cada caminho, ao encontrar o ponto final, atribui a lista ao atributo da classe
    private void Astar(int x, int y, LinkedList<int[]> path) {
        
    }

    public int find() {
        return 0;
    }

}
