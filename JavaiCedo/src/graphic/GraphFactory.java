package graphic;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphFactory {

    public static Graph<String, DefaultWeightedEdge> createGraphFromRouteFile(String path) {

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = content.toString().split("\n");

        // Parsing HEADER
        String header = lines[0];
        String[] headerParts = header.split(";");
        int numNodes = Integer.parseInt(headerParts[0]);
        int sumOfWeights = Integer.parseInt(headerParts[1]);

        // Create a directed weighted graph
        DefaultDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // Map to store edge weights
        Map<DefaultWeightedEdge, Double> edgeWeights = new HashMap<>();

        // Parsing RESUMO DE CONEXOES
        int lineIndex = 1;
        while (lineIndex < lines.length && lines[lineIndex].startsWith("01")) {
            String connection = lines[lineIndex];
            String[] nodesAndWeight = connection.split("=");
            String[] nodes = nodesAndWeight;
            String source = nodes[0].substring(2);
            String target = nodes[1];
            graph.addVertex(source);
            graph.addVertex(target);
            DefaultWeightedEdge edge = graph.addEdge(source, target);
            edgeWeights.put(edge, 0.0); // Initialize weight to 0
            lineIndex++;
        }

        // Parsing RESUMO DOS PESOS
        while (lineIndex < lines.length && lines[lineIndex].startsWith("02")) {
            String weight = lines[lineIndex];
            String[] nodesAndWeight = weight.split("=");
            String[] nodes = nodesAndWeight[0].split(";");
            String source = nodes[0].substring(2);
            String target = nodes[1];
            double weightValue = Double.parseDouble(nodesAndWeight[1]);
            DefaultWeightedEdge edge = graph.getEdge(source, target);
            edgeWeights.put(edge, weightValue);
            lineIndex++;
        }

        // Associate edge weights with the edges
        for (DefaultWeightedEdge edge : edgeWeights.keySet()) {
            double weight = edgeWeights.get(edge);
            graph.setEdgeWeight(edge, weight);
        }

        return graph;
    }

    public static double shortestDistance(Graph<String, DefaultWeightedEdge> graph, String source, String target) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
        double distance = dijkstra.getPathWeight(source, target);
        return distance;
    }
}
