import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dijkstra {

    public static int n = 0;
    public static String[] path = new String[n];

    public static void main(String[] args) throws IOException{
        System.out.println("The easter egg of the original matrix is the path from node 9 to 0 formed the word 'Dijkstra' !!!");
        Scanner in = new Scanner(System.in);
        System.out.println("Input the file name (EX: matrix.txt): ");
        String filename = in.nextLine();
        System.out.println("Input the index of the source node (EX: 0): ");
        int source = in.nextInt();
        System.out.println("Input the index of the destination node (EX: 9): ");
        int destination = in.nextInt();

        int matrix[][] = readFile(filename);

        int distance[] = dijkstra(matrix, source);

        System.out.println();
        printResult(distance, source, destination);
    }

    /**
     * read and format the file into a matrix
     * @param filename the name of the file
     * @return two-dimensional array of all data
     * @throws IOException the error of not finding the file
     */
    public static int[][] readFile(String filename) throws IOException{
        try {
            FileReader file = new FileReader(filename);
            BufferedReader input = new BufferedReader(file);
            String currentLine = input.readLine();
            n = Integer.parseInt(currentLine);
            int matrix[][] = new int[n][n];
            path = new String[n];

            currentLine = input.readLine();
            int node = 0;
            while(currentLine != null){
                String[] data = currentLine.split(" ");
                for(int i=0; i<n; i++){
                    matrix[node][i] = Integer.parseInt(data[i]);
                }
                node++;
                currentLine = input.readLine();
            }

            return matrix;

        } catch (IOException e) {
            System.out.println("Your file was not found");
            throw e;
        }
    }

    /**
     * find the shortest path using dijkstra's algorithm
     * @param matrix the two-dimensional array of the data
     * @param s the source node
     * @return an array of all shortest path
     */
    public static int[] dijkstra(int[][] matrix, int s){
        int distance[] = new int[n];
        boolean processed[] = new boolean[n];

        for(int i=0; i<n; i++){
            distance[i] = Integer.MAX_VALUE;
            processed[i] = false;
            path[i] = String.valueOf(s);
        }

        distance[s] = 0;

        //Last node is self-processed
        for(int i=0; i<n-1; i++){
            int minNode = minNextNode(distance,processed);

            processed[minNode] = true;

            for(int j=0; j<n; j++){
                if(!processed[j]){                                                          //true if other node is not processed yet (run fine without this, but greedy)
                    if(matrix[minNode][j] != 0){                                            //true if that node is adjacent to the minNode
                        if(distance[minNode] + matrix[minNode][j] < distance[j]){           //true if minNode to other node is shorter than stored distance of other node
                            distance[j] = distance[minNode] + matrix[minNode][j];
                            String newPath = path[minNode] + " " + j;
                            path[j] = newPath;
                        }
                    }
                }
            }
        }

        return distance;
    }

    /**
     * find the next node with the shortest distance
     * @param distance the distance of all current node
     * @param processed the array of if nodes are processed (priority queue)
     * @return the index of the node with the shortest distance
     */
    public static int minNextNode(int distance[], boolean processed[]){
        int min = Integer.MAX_VALUE;
        int index = -1;

        //Find the node with the current shortest distance
        for(int i=0; i<n; i++){
            if(!processed[i] && distance[i] < min){
                min = distance[i];
                index = i;
            }
        }
        return index;
    }

    /**
     * print out the result
     * @param distance the array with all the shortest distance
     * @param s the source node
     * @param d the destination node
     */
    public static void printResult(int distance[], int s, int d){
        System.out.printf("Source node is %d \n", s);
        System.out.printf("Vertex       Distance            Path\n");
        for(int i=0; i<n; i++){
            System.out.printf(" %2d          %5d               %s\n", i, distance[i], path[i]);
        }

        System.out.println("___________________________________________");
        System.out.printf("Shortest distance from %d to %d is %d \n", s, d, distance[d]);
        System.out.printf("The path (respectively) is %s \n", path[d]);
        System.out.println("___________________________________________");

    }
}
