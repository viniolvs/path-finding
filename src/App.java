import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {  
        int method = 0;      
        try {
            method = Integer.parseInt(args[0]);
            if(method > 2 || method < 0)
                throw new Exception();
        } catch (Exception e) {
            System.err.println("java -jar path-finding.jar < 0 | 1 | 2 >\n0 = Both algorithms\n1 = Depth Search algorithm\n2 =  A* algorithm");
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Digite o tamanho da matriz do labirinto: ");
        int size = input.nextInt();
        input.close();

        Labirinth.clearScreen();

        String message = "";
        int ms = 0;
        Labirinth L = new Labirinth(size);        

        if (method == 0) {
            Labirinth L2 = new Labirinth(L);
            L2.setPathFinder(new Astar(L2));
            int h1, h2;

            h1 = L.findPath(ms);

            h2 = L2.findPath(ms);

            Labirinth.clearScreen();
            System.out.println("\nLABIRINTO");
            L.printLabirinth();

            System.out.println("\nCaminho encontrado pela busca em profundidade");
            L.printPath();
            System.out.println("Heurística busca em profundidade = " + h1);

            System.out.println("\nCaminho encontrado pelo A*");
            L2.printPath();
            System.out.println("Heurística A* = " + h2);
        }

        else {
            if(method == 2) {
            L.setPathFinder(new Astar(L));
            message = "\nCaminho encontrado pelo A*";
            }
            else
                message = "\nCaminho encontrado pela busca em profundidade";
            int heuristica;
            
            heuristica = L.findPath(ms);

            System.out.println("\nLABIRINTO");
            L.printLabirinth();
            System.out.println("Tamanho do labirinto = " + size);
            
            System.out.println(message);
            L.printPath();
            System.out.println("Heurística = " + heuristica);
        }
    }
}
