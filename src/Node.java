class Node implements Comparable<Node> {
    Character character;
    Integer freq;
    Node left = null;
    Node right = null;

    Node(Character ch, Integer freq) {
        this.character = ch;
        this.freq = freq;
    }

    @Override
    public int compareTo(Node other) {
        return this.freq - other.freq;
    }
}