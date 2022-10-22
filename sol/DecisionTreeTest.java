package sol;

import java.util.List;

import org.junit.Assert;
import src.Row;
import src.DecisionTreeCSVParser;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

public class DecisionTreeTest {
    Dataset training;
    List<Row> dataObjects;
    List<String> attributeList;

    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {
        
    }

    @Before
    public void setupData() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/rachel/Desktop/cs200/projects/decision-tree-brianji22-rachelchae/data/songs/testing.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);
    }

    @Test
    public void testExample() { 
        Assert.assertEquals(6, 1 + 2 + 3);
    }

    @Test
    public void testSize() {
        //Setting up our data
        setupData();
        //Testing our data
        Assert.assertEquals(87, training.size());
    }

    // TODO: Add your tests here!

    @Test
    public void testAllSameValues() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/rachel/Desktop/cs200/projects/decision-tree-brianji22-rachelchae/data/songs/testing.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        List<String> songsAttributeList = new ArrayList<String>();
        songsAttributeList = training.getAttributeList();

        // rows w same dataObjects for attribute year
        List<Row> allSameValuesList = new ArrayList<Row>();
        allSameValuesList.add(training.getDataObjects().get(0));
        allSameValuesList.add(training.getDataObjects().get(15));
        allSameValuesList.add(training.getDataObjects().get(18));
        allSameValuesList.add(training.getDataObjects().get(25));

        Dataset datasetSame = new Dataset(songsAttributeList, allSameValuesList);
        Assert.assertEquals(datasetSame.allSameVal("year"), true);

        // rows w different dataObjects for attribute year
        List<Row> allDiffValuesList = new ArrayList<Row>();
        allDiffValuesList.add(training.getDataObjects().get(0));
        allDiffValuesList.add(training.getDataObjects().get(1));
        allDiffValuesList.add(training.getDataObjects().get(2));
        allDiffValuesList.add(training.getDataObjects().get(3));

        Dataset datasetDiff = new Dataset(songsAttributeList, allDiffValuesList);
        Assert.assertFalse(datasetDiff.allSameVal("year"));
    }

    @Test
    public void testValList() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/rachel/Desktop/cs200/projects/decision-tree-brianji22-rachelchae/data/songs/testing.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        List<String> songsAttributeList = new ArrayList<String>();
        songsAttributeList = training.getAttributeList();

        // rows w same dataObjects for attribute year
        List<Row> allSameValuesList = new ArrayList<Row>();
        allSameValuesList.add(training.getDataObjects().get(0));
        allSameValuesList.add(training.getDataObjects().get(15));
        allSameValuesList.add(training.getDataObjects().get(18));
        allSameValuesList.add(training.getDataObjects().get(25));

        Dataset datasetSame = new Dataset(songsAttributeList, allSameValuesList);
        Assert.assertEquals(datasetSame.valList("year").toString(), "[2009]" );

        // rows w different dataObjects for attribute year
        List<Row> allDiffValuesList = new ArrayList<Row>();
        allDiffValuesList.add(training.getDataObjects().get(0));
        allDiffValuesList.add(training.getDataObjects().get(15));
        allDiffValuesList.add(training.getDataObjects().get(1));
        allDiffValuesList.add(training.getDataObjects().get(2));
        allDiffValuesList.add(training.getDataObjects().get(3));

        Dataset datasetDiff = new Dataset(songsAttributeList, allDiffValuesList);
        System.out.println(datasetDiff);
        System.out.println(datasetDiff.getDefaultVal("isHighValence"));
    }

    @Test
    public void testPartition() {
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/rachel/Desktop/cs200/projects/decision-tree-brianji22-rachelchae/data/songs/testing.csv");

        List<Row> emptyRow = new ArrayList<Row>();
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset onerowdata = new Dataset(attributeList, emptyRow);

        System.out.println(attributeList);
        Dataset training = new Dataset(attributeList, dataObjects);


        System.out.println(training.toString());
    }
}
