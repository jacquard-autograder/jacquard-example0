package cities.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class NodeTest {
    Graph.Node<String> mexico;
    Graph.Node<String> canada;
    Graph.Node<String> usa;
    Graph.Node<String> france;
    Graph.Node<String> netherlands;

    @BeforeEach
    public void setup() {
        mexico = new Graph.Node<>("Mexico");
        canada = new Graph.Node<>("Canada");
        usa = new Graph.Node<>("USA");
        france = new Graph.Node<>("France");
        netherlands = new Graph.Node<>("Netherlands");
    }

    @Test
    public void findTestOnNewNode() {
        assertEquals(mexico, mexico.find());
    }

    @Test
    //   @GradedTest(name = "Student code passes autograder test unionWorksOnIdenticalNodes()", points = 3.0)
    public void unionWorksOnIdenticalNodes() {
        mexico.union(mexico);
        assertEquals(mexico, mexico.find());
    }

    @Test
//    @GradedTest(name = "Student code passes autograder test unionWorksOnDistinctNodes()", points = 5.0)
    public void unionWorksOnDistinctNodes() {
        mexico.union(canada);
        assertEquals(mexico.find(), canada.find());
    }

    @Test
//    @GradedTest(name = "Student code passes autograder test unionBalancesNodes()", points = 5.0)
    public void unionBalancesNodes() {
        mexico.union(canada);
        assertEquals(mexico.find(), canada.find());
        Graph.Node<String> rep1 = mexico.find();
        usa.union(canada);
        assertEquals(usa.find(), canada.find());
        assertEquals(mexico.find(), canada.find());
        assertEquals(rep1, usa.find());
        assertEquals(rep1, mexico.find());
        assertEquals(rep1, canada.find());
    }

    @Test
//    @GradedTest(name = "Student code passes autograder test unionDoesNotWronglyMergeNodes()", points = 5.0)
    public void unionDoesNotWronglyMergeNodes() {
        mexico.union(canada);
        canada.union(usa);
        assertEquals(france, france.find());
        assertEquals(netherlands, netherlands.find());
        france.union(netherlands);
        assertNotEquals(france.find(), usa.find());
    }
}
