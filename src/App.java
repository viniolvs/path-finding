import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o tamanho da matriz do labirinto: ");
        int size = input.nextInt();
        input.close();

        Labirinth L = new Labirinth(size);
        int h1, h2;
        System.out.println("\nLABIRINTO");
        L.printLabirinth();
        System.out.println("Tamanho do labirinto = " + size);

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
