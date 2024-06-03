public class Main {

    public static void main(String[] args) {
        String text = "teste";
        HuffmanTree huffmanTree = new HuffmanTree(text);
        String encoded = huffmanTree.encode(text);
        System.out.println("Encoded: " + encoded);
        String decoded = huffmanTree.decode(encoded);
        System.out.println("Decoded: " + decoded);
    }
}