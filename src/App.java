public class App {
    public static void main(String[] args) throws Exception {
        Labirinth L = new Labirinth(5);
        L.printLabirinth();
        System.out.println();
        PathFinder path = new PathFinder(L);
        int heuristica = path.depthSearch();
        path.printPath();
        System.out.println(heuristica); 
        L.printLabirinth();
    }
}
