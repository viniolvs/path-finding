import java.util.LinkedList;
import java.util.Random;

public class Labirinth {
    private char[][] L; // Matriz que contém o labirinto
    private char[][] path; // Matriz que contém o labirinto solucionado
    private Point start; // Coordenada da entrada do labirinto
    private Point finish; // Coordenada da saída do labirinto
    private int size; // Tamanho do labirinto
    private int len; // Tamanho da matriz que representa o labirinto
    private PathFinder pathFinder;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m"; 
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public Labirinth(Labirinth L){
        this.L = L.getLabirinth();
        this.start = L.getStart();
        this.finish = L.getFinish();
        this.size = L.getSize();
        this.len = L.getLen();
        pathFinder = new DepthSearch(this);
        path = new char[len][len];
        restartPath();
    };

    public Labirinth(int size) {
        this.size = size;
        this.len = size * 2 + 1;
        start = new Point();
        finish = new Point();
        // Matriz com tamanho necessário para marcar paredes do labirinto
        L = new char[len][len];
        path = new char[len][len];
        //Algoritmo solucionador padrão
        pathFinder = new DepthSearch(this);
        genLabirinth();
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public int findPath() {
        return pathFinder.find();
    }

    public int findPath(int ms) {
        return pathFinder.find(ms);
    }

    public char[][] getLabirinth() {
        return L;
    }

    public char[][] getPath() {
        return path;
    }

    public Point getStart() {
        return start;
    }

    public Point getFinish() {
        return finish;
    }

    public int getSize() {
        return size;
    }

    public int getLen() {
        return len;
    }

    public void restartPath() {
        // Copia o labirinto para a matriz que conterá a solução
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                path[i][j] = L[i][j];
            }
        }
    }

    public void printLabirinth() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(L[i][j] == '|' || L[i][j] == '+' || L[i][j] == '-' || L[i][j] == 'X')
                    System.out.print(ANSI_RED_BACKGROUND + L[i][j] + ANSI_RESET);
                else
                    System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }

    public static void printLabirinth(char[][] L, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(L[i][j] == '|' || L[i][j] == '+' || L[i][j] == '-' || L[i][j] == 'X')
                    System.out.print(ANSI_RED_BACKGROUND + L[i][j] + ANSI_RESET);
                else if(L[i][j] == 'o')
                    System.out.print(ANSI_GREEN_BACKGROUND + L[i][j] + ANSI_RESET);
                else
                    System.out.print(L[i][j]);
            }
            System.out.println();
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {}
    }

    public void printPath() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if(path[i][j] == '|' || path[i][j] == '+' || path[i][j] == '-' || path[i][j] == 'X')
                    System.out.print(ANSI_RED_BACKGROUND + path[i][j] + ANSI_RESET);
                else if(path[i][j] == 'o')
                    System.out.print(ANSI_GREEN_BACKGROUND + path[i][j] + ANSI_RESET);
                else
                    System.out.print(path[i][j]);
            }
            System.out.println();
        }
    }

    private void genLabirinth() {
        // Inicializa o labirinto
        int k = 1;
        for (int i = 0; i < len; i++, k++) {
            L[0][i] = 'X';
            L[len - 1][i] = 'X';
            if (k < len - 1) {
                L[k][0] = '|';
                L[k][len - 1] = '|';
            }
        }
        for (int i = 1; i < len - 1; i++) {
            for (int j = 1; j < len - 1; j++) {
                if (i % 2 == 0)
                    if (j % 2 == 0)
                        L[i][j] = '+';
                    else
                        L[i][j] = '-';
                else if (j % 2 == 0)
                    L[i][j] = '|';
                else
                    L[i][j] = ' ';
            }
        }

        // Define a posição inicial do labirinto
        Random random = new Random();
        int side, pos;
        // x = row y = column
        int x = 0, y = 0; // coordenadas para a matriz de visitados
        int temp_x = 0, temp_y = 0; // Coordenadas para a matriz labirinto
        // Posição
        pos = random.nextInt(size);
        pos = pos * 2 + 1;
        // Lado que o labirinto começa
        side = random.nextInt(3);
        switch (side) {
            // TOP
            case 0:
                x = 0 + 1;
                temp_x = 0;
                y = pos;
                temp_y = pos;
                break;
            // RIGHT
            case 1:
                x = pos;
                temp_x = pos;
                y = len - 2;
                temp_y = len - 1;
                break;
            // BOTTOM
            case 2:
                x = len - 2;
                temp_x = len - 1;
                y = pos;
                temp_y = pos;
                break;
            // LEFT
            case 3:
                x = pos;
                temp_x = pos;
                y = 0 + 1;
                temp_y = 0;
                break;
        }
        this.start.setX(temp_x);
        this.start.setY(temp_y);
        // Marca a entrada no labirinto
        L[temp_x][temp_y] = 'e';

        // Define a saída do labirinto
        int x2 = 0, y2 = 0;
        // Posição
        pos = random.nextInt(size);
        pos = pos * 2 + 1;
        // Lado que o labirinto começa
        side = random.nextInt(3);
        switch (side) {
            // TOP
            case 0:
                x2 = 0;
                y2 = pos;
                break;
            // RIGHT
            case 1:
                x2 = pos;
                y2 = len - 1;
                break;
            // BOTTOM
            case 2:
                x2 = len - 1;
                y2 = pos;
                break;
            // LEFT
            case 3:
                x2 = pos;
                y2 = 0;
                break;
        }
        this.finish.setX(x2);
        this.finish.setY(y2);
        // Marca a saída no labirinto
        L[x2][y2] = 's';

        int[][] vis = new int[size][size];
        walk(new Point((x - 1) / 2, (y - 1) / 2), vis, new LinkedList<Point>());
        restartPath();
    }

    // Abre os caminhos no labirinto utilizando busca em profundidade aleatória
    private void walk(Point p, int[][] vis, LinkedList<Point> next_pos) {
        vis[p.getX()][p.getY()] = 1;
        // Adiciona todas as possibilidades de movimentaçao em uma Lista
        next_pos = Point.getNeighbors(p);

        Random random = new Random();
        while (next_pos.size() > 0) {
            // sorteia o próximo caminho a ser seguido
            int next = random.nextInt(next_pos.size());
            // Verifica o lado que a busca continuará
            String str = new String();
            str = "";
            int next_x = next_pos.get(next).getX();
            int next_y = next_pos.get(next).getY();
            if (next_x >= 0 && next_y >= 0) {
                if (next_x < p.getX())
                    str = "TOP";
                else if (next_x > p.getX())
                    str = "BOTTOM";
                else if (next_y > p.getY())
                    str = "RIGHT";
                else if (next_y < p.getY())
                    str = "LEFT";
            }
            // Remove da Lista o caminho já escolhido
            next_pos.remove(next);

            // Verifica se é possível seguir para o lado sorteado
            if (next_x >= 0 && next_x < size && next_y >= 0 && next_y < size && vis[next_x][next_y] == 0) {
                // Abre o caminho no labirinto
                int temp_x = next_x * 2 + 1, temp_y = next_y * 2 + 1;
                switch (str) {
                    case "TOP":
                        L[temp_x + 1][temp_y] = ' ';
                        break;
                    case "BOTTOM":
                        L[temp_x - 1][temp_y] = ' ';
                        break;
                    case "RIGHT":
                        L[temp_x][temp_y - 1] = ' ';
                        break;
                    case "LEFT":
                        L[temp_x][temp_y + 1] = ' ';
                        break;
                }
                // Chamada recursiva para o próximo
                walk(new Point(next_x, next_y), vis, new LinkedList<Point>());
            }
        }
        return;
    }
}
