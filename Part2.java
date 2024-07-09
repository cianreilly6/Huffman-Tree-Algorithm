import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    char data;
    int frequency;
    Node left, right;

    Node(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }

    public int compareTo(Node node) {
        return this.frequency - node.frequency;
    }
}

public class Part2 {

    public static void main(String[] args) {
        String input = "to be or not to be";

        // Step 1: Counting frequencies
        int[] frequency = new int[256]; // Assuming ASCII characters
        for (char c : input.toCharArray()) {
            frequency[c]++;
        }

        // Step 2: Create trees and add them to PriorityQueue
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                priorityQueue.add(new Node((char) i, frequency[i]));
            }
        }

        // Step 3: Combine trees until only one is left
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();

            Node combinedNode = new Node('\0', left.frequency + right.frequency);
            combinedNode.left = left;
            combinedNode.right = right;

            priorityQueue.add(combinedNode);
        }

        // Step 4: The remaining tree is the Huffman tree
        Node huffmanTreeRoot = priorityQueue.poll();

        // Print the Huffman tree
        printHuffmanTree(huffmanTreeRoot, "");
    }

    private static void printHuffmanTree(Node root, String code) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            System.out.println("'" + root.data + "' : " + code);
        }

        printHuffmanTree(root.left, code + "0");
        printHuffmanTree(root.right, code + "1");
    }
}
