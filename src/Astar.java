import java.util.LinkedList;

public class Astar extends PathFinder {
    protected Point start;
    protected Point finish;
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
    private void aStar(Point p, LinkedList<Point> path) {
        if (p.getX() != finish.getX() && p.getY() != finish.getY()) {
            LinkedList<Point> temp = Point.getNeighbors(p);
            // Elimina os pontos que não podem ser acessados e calcula o custo f para os pontos válidos
            for (Point point : temp) {
                if (point.getX() >= 0 && point.getX() < len && point.getY() >= 0 && point.getY() < len
                        && (L[point.getX()][point.getY()] == ' ' || L[point.getX()][point.getY()] == 's'))
                    point.setfCost(fCost(point));
                else
                    temp.remove(point);
            }
            path.addAll(temp);
            // Procura o próximo ponto com menor custo f
            Point next = path.get(getNextIndex(path));
            // Marca o próximo ponto no labirinto
            L[next.getX()][next.getY()] = 'o';
            // Continua para o próximo ponto
            aStar(next, path);
        }
    }

    public int find() {
        aStar(start, new LinkedList<Point>());
        int heuristica = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (L[i][j] == 'o')
                    heuristica++;
            }
        }
        return heuristica;
    }

    private int getNextIndex(LinkedList<Point> path){
        int minor = 0;
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i).getfCost() < path.get(minor).getfCost())
                minor = i;
        }
        return minor;
    }

    private double fCost(Point p) {
        return (gCost(p) + hCost(p));
    }

    private double gCost(Point p) {
        return distance(p, start);
    }

    private double hCost(Point p) {
        return distance(p, finish);
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }

}
