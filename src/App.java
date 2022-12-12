public class App {
    public static void main(String[] args) throws Exception {
        Labirinth L = new Labirinth(5);
        int h1, h2;
        System.out.println("LABIRINTO");
        L.printLabirinth();
        System.out.println("\nCaminho encontrado pela busca em profundidade");
        h1 = L.findPath();
        L.printPath();
        System.out.println("Heurística = " + h1);
        
        L.restartPath();
        System.out.println("\nCaminho encontrado pelo A*");
        L.setPathFinder(new Astar(L));
        h2 = L.findPath();
        L.printPath();
        System.out.println("Heurística = " + h2);
    }
}
