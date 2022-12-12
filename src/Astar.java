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
        this.L = L.getPath();
    }

    public char[][] getPath() {
        return L;
    }
    
    
    public int find() {
        aStar(start);
        int heuristica = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (L[i][j] == 'o')
                heuristica++;
            }
        }
        return heuristica;
    }
    
    // Implementação do método A*
    private void aStar(Point p) {
        LinkedList<Point> path = new LinkedList<Point>();
        while(p.getX() != finish.getX() || p.getY() != finish.getY()){
            L[p.getX()][p.getY()] = 'o';

            LinkedList<Point> temp = new LinkedList<Point>();
            temp = Point.getNeighbors(p);
            // Elimina os pontos que não podem ser acessados e calcula o custo f para os pontos válidos
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getX() >= 0 && temp.get(i).getX() < len && temp.get(i).getY() >= 0 && temp.get(i).getY() < len
                && (L[temp.get(i).getX()][temp.get(i).getY()] == ' ' || L[temp.get(i).getX()][temp.get(i).getY()] == 's')){
                    temp.get(i).setfCost(fCost(temp.get(i)));
                    path.push(temp.get(i));
                }
            }
            if(path.size() > 0){
                // Remove o ponto atual da lista
                path.remove(p);
                // Procura o próximo ponto com menor custo f
                Point next = path.get(getNextIndex(path));
                // Continua para o próximo ponto
                p = next;
            }
        }
        L[p.getX()][p.getY()] = 'o';
    }

    // Procura pelo ponto com menor custo f na lista 
    private int getNextIndex(LinkedList<Point> path){
        int minor = 0;
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i).getfCost() < path.get(minor).getfCost())
            minor = i;
        }
        return minor;
    }

    // Custo f = custo g + custo h
    private double fCost(Point p) {
        return (gCost(p) + hCost(p));
    }
    
    // Custo g = distância entre um ponto e o ponto inicial
    private double gCost(Point p) {
        return distance(p, start);
    }
    
    // Custo h = distância entre um ponto e o ponto final
    private double hCost(Point p) {
        return distance(p, finish);
    }
    
    // Calcula a distância entre dois pontos
    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }

}
