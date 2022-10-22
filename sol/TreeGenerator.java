package sol;

import java.util.List;
import java.util.ArrayList;
import src.ITreeGenerator;
import src.Row;
import java.util.Random;


/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {

    CategoryNode rootNode;

    public TreeGenerator() {
        this.rootNode = null;
    }

    public CategoryNode makeCatNode(Dataset trainingData, String targetAttr, List<String> availAttr) {

        // finding the attribute to split on using the random generator
        Random random = new Random();
        int upperBound = availAttr.size();
        int randomNum = random.nextInt(upperBound);
        String categoryName = availAttr.get(randomNum);
        availAttr.remove(categoryName); // removing the attribute from the availAttr list
        // now we partition the data into the diff edges
        List<Dataset> edgeDatasets = trainingData.partition(categoryName);
        // creating a list of edges with the partitioned data
        List<Edge> edgeList = new ArrayList<Edge>();
        for (Dataset d : edgeDatasets) {
            edgeList.add(this.makeEdge(d, targetAttr, d.getDataObjects().get(0).getAttributeValue(categoryName), availAttr));
        }
        // creating a category node and setting its default value
        CategoryNode catNode = new CategoryNode(categoryName, edgeList);
        catNode.setDefaultValue(trainingData.getDefaultVal(targetAttr));
        return catNode;
    }

    public Edge makeEdge(Dataset data, String targetAttr, String edgeName, List<String> attrList) {
        List<String> attrListCopy = new ArrayList<String>(attrList);
        // creating an edge that points to a leaf
        if (data.allSameVal(targetAttr)) {
            Leaf leaf = new Leaf(data.getDataObjects().get(0).getAttributeValue(targetAttr));
            return new Edge(edgeName, leaf);
        }

        // create an edge that points to leaf when no attributes are left to split on
        else if (attrListCopy.isEmpty()) {
            Leaf leafNoAttrLeft = new Leaf(data.getDefaultVal(targetAttr));
            return new Edge(edgeName, leafNoAttrLeft);
        }

        // creates an edge that points to a node to further split on
        else {
            CategoryNode catNode = makeCatNode(data, targetAttr, attrListCopy);
            return new Edge(edgeName, catNode);
        }
    }

    @Override
    public void generateTree(Dataset trainingData, String targetAttribute) {
        // removing the target attribute from the data
        List<String> availableAttributes = new ArrayList<String>(trainingData.getAttributeList());
        availableAttributes.remove(targetAttribute);

        this.rootNode = this.makeCatNode(trainingData, targetAttribute, availableAttributes);
    }

    @Override
    public String getDecision(Row datum) {
        return rootNode.getDecision(datum);
    }
}
