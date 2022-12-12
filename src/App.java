public class App {
    public static void main(String[] args) throws Exception {
        Labirinth L = new Labirinth(10);
        L.printLabirinth();
        L.setPathFinder(new Astar(L));
        L.findPath();
        L.printPath();
        System.out.println();
    }
}
