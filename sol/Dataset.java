package sol;

import src.IDataset;
import src.Row;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    private List<String> attributeList;
    private List<Row> dataObjects;

    public Dataset(List<String> attrList, List<Row> dataObjs) {
        this.attributeList = attrList;
        this.dataObjects = dataObjs;
    }

    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    @Override
    public List<Row> getDataObjects() {
        return this.dataObjects;
    }

    @Override
    public int size() {
        return dataObjects.size();
    }

    // DELETE ME
    @Override
    public String toString() {
        List<String> listOfStrings = new ArrayList<String>();
        for (Row r : this.dataObjects) {
            listOfStrings.add(r.toString());
        }
        return listOfStrings.toString();
    }

    public boolean allSameVal(String attribute) {
        String compareValue = this.dataObjects.get(0).getAttributeValue(attribute);
        for (Row r : this.dataObjects) {
            if (!(r.getAttributeValue(attribute).equals(compareValue))) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> valList(String attribute) {
        ArrayList<String> uniqueAttributes = new ArrayList<String> ();
        for (Row r : this.dataObjects) {
            if (!(uniqueAttributes.contains(r.getAttributeValue(attribute)))) {
                uniqueAttributes.add(r.getAttributeValue(attribute));
            }
        }
        return uniqueAttributes;
    }

    /**
     * Method that calculates the number of attributes left to use on the random generator
     */
    public int countAttributes() {
        return this.attributeList.size() - 1;
    }

    /**
     * Method that returns a list of new datasets created for each new Edge
     * @return
     */
    public List<Dataset> partition(String attribute) {
        List<Dataset> datasets = new ArrayList<Dataset>();
        List<String> uniqueDataObjs = this.valList(attribute);
        for (String edgeName: uniqueDataObjs) {
             datasets.add(this.partitionHelper(edgeName, attribute));
        }
        return datasets;
    }

    /**
     * Method that creates the dataset based on if the edge name matches the attribute's dataObject
     * @param edgeName
     * @param attribute
     * @return
     */
    public Dataset partitionHelper(String edgeName, String attribute) {
        List<Row> rowOfDataObjs = new ArrayList<Row>();
        for (Row r: this.dataObjects) {
            if (r.getAttributeValue(attribute).equals(edgeName)) {
                rowOfDataObjs.add(r);
            }
        }
        Dataset edgeData = new Dataset(this.attributeList, rowOfDataObjs);
        return edgeData;
    }

    public boolean allSameSize(List<Dataset> listOfData) {
        Dataset d1 = listOfData.get(0);
        for (Dataset d: listOfData) {
            if (d.size() != d1.size()) {
                return false;
            }
        }
        return true;
    }

    public String getDefaultVal(String targetAttr) {
        List<Dataset> partitionedData = this.partition(targetAttr);
        if (allSameSize(partitionedData)) {
            Random random = new Random();
            int upperBound = partitionedData.size();
            int randomNum = random.nextInt(upperBound);
            return partitionedData.get(randomNum).getDataObjects().get(0).getAttributeValue(targetAttr);
        } else {
            Dataset compareDataset = partitionedData.get(0);
            for (Dataset d: partitionedData) { // for each dataset,
                if (d.size() > compareDataset.size()) {
                    compareDataset = d;
                }
            }
            return compareDataset.getDataObjects().get(0).getAttributeValue(targetAttr);
        }
    }
}
