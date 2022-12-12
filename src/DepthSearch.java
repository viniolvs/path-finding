import java.util.LinkedList;

public class DepthSearch extends PathFinder {
    protected Point start;
    protected Point finish;
    protected char[][] L;
    protected int len;

    public DepthSearch(Labirinth L) {
        this.start = L.getStart();
        this.finish = L.getFinish();
        this.len = L.getLen();
        this.L = L.getPath();
    }

    public int find() {
        depthSearch(start, new LinkedList<Point>());
        int heuristica = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (L[i][j] == 'o')
                    heuristica++;
            }
        }
        return heuristica;
    }

    // Busca em profundidade
    private void depthSearch(Point p, LinkedList<Point> next_pos) {
        // Marca o caminho percorrido
        L[p.getX()][p.getY()] = 'o';

        // Adiciona todas as possibilidades de movimentaçao em uma Lista
        next_pos = Point.getNeighbors(p);

        while (next_pos.size() > 0 && L[finish.getX()][finish.getY()] != 'o') {
            int next_x = next_pos.get(0).getX();
            int next_y = next_pos.get(0).getY();
            // Remove da Lista o caminho já escolhido
            next_pos.remove(0);

            // Verifica se é possível seguir para o próximo lado
            if (next_x >= 0 && next_x < len && next_y >= 0 && next_y < len
                    && (L[next_x][next_y] == ' ' || L[next_x][next_y] == 's')) {
                // Chamada recursiva para o próximo
                depthSearch(new Point(next_x, next_y), new LinkedList<Point>());
            }
        }
        return;
    }

}
