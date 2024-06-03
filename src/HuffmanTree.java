import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private final Map<Character, String> codes;
    private final Node root;

    public HuffmanTree(String text) {
        Map<Character, Integer> frequencies = getFrequencies(text);
        PriorityQueue<Node> queue = buildPriorityQueue(frequencies);
        root = buildHuffmanTree(queue);
        codes = new HashMap<>();
        generateCodes(root, "");
    }

    private Map<Character, Integer> getFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }

    private PriorityQueue<Node> buildPriorityQueue(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.offer(new Node(entry.getKey(), entry.getValue()));
        }
        return queue;
    }

    private Node buildHuffmanTree(PriorityQueue<Node> queue) {
        if (queue.size() == 1) {
            return queue.poll();
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            assert right != null;
            assert left != null;
            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            queue.offer(parent);
        }

        return queue.poll();
    }

    private void generateCodes(Node node, String code) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                codes.put(node.character, code);
            } else {
                generateCodes(node.left, code + "0");
                generateCodes(node.right, code + "1");
            }
        }
    }

    public String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }

    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        Node current = root;
        for (char bit : encoded.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else {
                current = current.right;
            }

            assert current != null;
            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
    }
}
