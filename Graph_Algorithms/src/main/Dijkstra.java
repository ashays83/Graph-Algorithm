package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dijkstra {
    private int adj[][];
    private boolean directed=false;
    private int size,edge;
    private int source;
    private int[] distance;
    private int[] path;

    private int getInt(char c) {
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.indexOf(c);
    }

    private char getChar(int i) {
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.charAt(i);
    }

    public void input(String filename) throws IOException {
        FileReader fd=null;

        try {
            fd=new FileReader(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(fd!=null) {
            BufferedReader br=new BufferedReader(fd);
            String line;
            if((line=br.readLine())!=null) {
                String[] arr=line.split(" ");
                //take the size
                size=Integer.parseInt(arr[0]);
                //create the adjacency array
                adj=new int[size][size];
                edge=Integer.parseInt(arr[1]);
                if(arr[2].equals("D")) {
                    directed=true;
                }

                //Retrieve all edge from file
                //Considering that the file has no error

                for(int i=0;i<edge;i++) {
                    line=br.readLine();
                    arr=line.split(" ");
                    adj[getInt(arr[0].charAt(0))][getInt(arr[1].charAt(0))]=Integer.parseInt(arr[2]);
                    //Graph is undirected add reverse path also
                    if(directed!=true) {
                        adj[getInt(arr[1].charAt(0))][getInt(arr[0].charAt(0))]=Integer.parseInt(arr[2]);
                    }

                }

                //if source vertices is given take that

                if((line=br.readLine())!=null) {
                    source=getInt(line.charAt(0));
                }

                else { /*add first index as source(not given in your question,consider for simplicity*/
                    source=0;
                }

            }

        }

    }

    /*For display the graph*/

    void displayGraph() {
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                System.out.print("\t"+adj[i][j]);
            }
            System.out.println();
        }
    }

    public void dijkstra() {
        /*Store the distance from all vertices*/
        distance=new int[size];
        boolean[] visited=new boolean[size];
        /* Initialize all distance as max value

         * Initialize all visited false*/

        for(int i=0;i<size;i++) {
            distance[i]=Integer.MAX_VALUE;
            visited[i]=false;
        }

        /* It store the whole path*/

        path=new int[size];

        /* Initialize source vertex distance 0

         * Initialize source vertex path -1*/
        distance[source]=0;
        path[source]=-1;
        /* Find shortest path for all vertices*/

        for(int i=0;i<size;i++) {
            int nextVertex=-1;
            int shortestDistance=Integer.MAX_VALUE;
            for(int v=0;v<size;v++) {
                if(visited[v]==false && distance[v]<shortestDistance) {
                    nextVertex=v;
                    shortestDistance=distance[v];
                }
            }
            visited[nextVertex]=true;

            /* Update distance value*/

            for(int v=0;v<size;v++) {
                int edgeDistance=adj[nextVertex][v];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < distance[v])) {
                    path[v] = nextVertex;
                    distance[v] = shortestDistance + edgeDistance;
                }

            }

        }

    }

    public void displayResult() {
        System.out.print("Vertex\t\t Distance\t\tPath");
        for (int i = 0; i < size; i++)
        {
            if (i!= source)
            {
                System.out.print("\n" + getChar(source) + " -> ");
                System.out.print(getChar(i) + " \t\t ");
                System.out.print(distance[i] + "\t\t\t");
                int v=i;
                String str="";
                while(path[v]!=-1) {
                    str=getChar(v)+" "+str;
                    v=path[v];
                }
                System.out.print(str);

            }

        }

    }

    public static void main(String[] args) throws IOException {
    	File fileIndex = new File("fileIndex.txt");
		//System.out.println("File: " + fileIndex.toString());
    	
    	Dijkstra ob=new Dijkstra();
        ob.input("dataFiles/file_03.txt");
        //ob.displayGraph();
        ob.dijkstra();
        ob.displayResult();

    }

}