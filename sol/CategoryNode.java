package sol;
import src.Row;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode implements ITreeNode{
    private String category;
    private String defaultValue;
    private List<Edge> next;

    public CategoryNode(String cat, List<Edge> nxt) {
        this.category = cat;
        this.next = nxt;
        this.defaultValue = null;
    }

    public void setDefaultValue(String defaultVal) {
        this.defaultValue = defaultVal;
    }

    @Override
    public String getDecision(Row forDatum) {
        String nextEdge = forDatum.getAttributeValue(category);
        for (Edge e : this.next) {
            if (e.getName().equals(nextEdge)) {
                return e.getNext().getDecision(forDatum);
            }
        }
        return this.defaultValue;
    }
}
