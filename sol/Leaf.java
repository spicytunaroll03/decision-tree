package sol;

import src.Row;

public class Leaf implements ITreeNode{
    private String decision;

    public Leaf (String dec) {
        this.decision = dec;
    }

    @Override
    public String getDecision(Row forDatum) {
        return this.decision;
    }
}