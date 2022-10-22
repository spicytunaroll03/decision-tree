package sol;

public class Edge {
    private String value;
    private ITreeNode next;

    public Edge (String val, ITreeNode nxt) {
        this.value = val;
        this.next = nxt;
    }

    public String getName() {
        return this.value;
    }

    public ITreeNode getNext() {
        return this.next;
    }
}
