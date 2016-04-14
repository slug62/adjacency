import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by psout on 4/13/2016.
 */
public class adjacency {
    public static void main(String[] args){
        String[] verticies = {"A", "B", "C", "D"};
        Edge[] edges = {new Edge("A","B",2), new Edge("A","D",1),new Edge("B","A",2), new Edge("B","D",2),
                new Edge("C","D",3),new Edge("D","A",1),new Edge("D","B",2), new Edge("D","C",3)};


        System.out.println("---- Adjacency Matrix ----\n");
        Integer[][] adjMatrix = makeAdjMatrix(verticies,edges);
        for(int i = 0; i < adjMatrix.length; i++){

            for(int j = 0; j < adjMatrix.length; j++){
                System.out.print(adjMatrix[i][j] + " | ");
            }
            System.out.println("\n---------------");
        }

        System.out.println("\n---- Adjacency List ----\n");
        HashMap<String, ArrayList<Edge>> adjList = new HashMap<>();
        adjList = makeAdjList(verticies, edges);
        for(String s : verticies){
            System.out.println(s + " --> " + adjList.get(s));
        }

        for(String key : adjList.keySet()){
            adjList.get(key).sort(new EdgeComparator());
        }
        String descending = printByWeightDescending(adjList, findMin(adjList), findMax(adjList));
        System.out.println("\n---- Descending by edge weight ----\n");
        System.out.println(descending);
    }



    public static String printByWeightDescending(HashMap<String, ArrayList<Edge>> adjList, int min, int max){
        String result = "";
        if(min > max)
            return "";
        else{
            result += printByWeightDescending(adjList, min+1, max);
            for(String key : adjList.keySet()){
                for(int i = 0; i < adjList.get(key).size(); i++){
                    if(adjList.get(key).get(i).weight == min){
                        result += "[" + adjList.get(key).get(i).vert + adjList.get(key).get(i) + "]";
                    }
                }

            }
            return result;
        }
    }
    public static int findMin(HashMap<String, ArrayList<Edge>> adjListCopy){
        int min = findMax(adjListCopy);
        for(String key : adjListCopy.keySet())
            for(Edge e : adjListCopy.get(key))
                if(e.weight < min)
                    min = e.weight;

        return min;
    }
    public static int findMax(HashMap<String, ArrayList<Edge>> adjListCopy){
        int max = 0;
        for(String key : adjListCopy.keySet())
            for(Edge e : adjListCopy.get(key))
                if(e.weight > max)
                    max = e.weight;

        return max;
    }
    public static HashMap<String, ArrayList<Edge>> makeAdjList(String[] vert, Edge[] edges){
        HashMap<String, ArrayList<Edge>> list = new HashMap<>();
        for(String s : vert) {
            list.put(s, listHelper(s, edges));
        }
        return list;
    }
    public static ArrayList<Edge> listHelper(String s, Edge[] edges){
        ArrayList<Edge> temp = new ArrayList<>();
        for(Edge e : edges){
            if(s.compareTo(e.getVert())== 0){
                temp.add(e);
            }
        }
        return temp;
    }
    public static Integer[][] makeAdjMatrix(String[] vert, Edge[] edges){
        Integer[][] matrix = new Integer[vert.length][vert.length];
        for(int i = 0; i < vert.length; i++){
            for(int j = 0; j < vert.length; j++){
                if(i == j){
                    matrix[i][j] = 0;
                }else{
                    for(Edge e : edges){
                        if((e.getVert().compareTo(vert[i]) == 0) && (e.getEdgePartner().compareTo(vert[j]) == 0)){
                            matrix[i][j] = e.getWeight();
                        }
                    }
                }if(matrix[i][j] == null)
                    matrix[i][j] = 0;
            }
        }
        return matrix;
    }


    public static class Edge{
        private String vert;
        private String edgePartner;
        private int weight;

        public Edge(String vert, String edgePartner, int weight){
            this.vert = vert;
            this.edgePartner = edgePartner;
            this.weight = weight;
        }
        public String getVert(){ return this.vert; }
        public String getEdgePartner(){ return this.edgePartner; }
        public int getWeight(){ return this.weight; }
        public String toString(){ return edgePartner + " " + weight; }
    }
    static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o2.weight - o1.weight;
        }
    }

}
/*

HashMap<String, ArrayList<Edge>> adjListCopy = adjList;
        for(String key : adjListCopy.keySet()){
            adjListCopy.get(key).sort(new EdgeComparator());
        }
        for(String key : adjListCopy.keySet()){
            for(int i = 0; i < adjListCopy.get(key).size(); i++){

                if(adjListCopy.get(key).get(i).weight == findMax(adjListCopy)){
                    System.out.println(adjListCopy.get(key).get(i).vert + adjListCopy.get(key).get(i));
                    adjListCopy.get(key)
                }
            }
        }

 */