package cities.model;

import com.spertus.jacquard.junittester.GradedTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
public class HiddenKruskalIteratorTest {
    Graph<String> graph;

    @BeforeEach
    public void setup() {
        graph = new Graph<>();
    }

    private Graph.Node<String> addToGraph(String s) {
        // This assumes that all data values are unique.
        graph.addNode(s);
        List<Graph.Node<String>> nodes = graph.getNodes();
        for (Graph.Node<String> node : nodes) {
            if (node.getData().equals(s)) {
                return node;
            }
        }
        fail("Error in helper method addToGraph()");
        return null;
    }

    @Test
    @GradedTest(name = "KruskalIterator: testWithNoEdges()", points = 2.0)
    public void testWithNoEdges() {
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @GradedTest(name = "KruskalIterator: testWithOneEdge()", points = 3.0)
    public void testWithOneEdge() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        graph.addEdge(boston, sf, 1);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge = iterator.next();
        assertEquals(boston, edge.getNode1());
        assertEquals(sf, edge.getNode2());
        assertEquals(1, edge.getWeight());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @GradedTest(name = "KruskalIterator: testWithTwoEdges()", points = 5.0)
    public void testWithTwoEdges() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        Graph.Node<String> chicago = addToGraph("Chicago");
        graph.addEdge(boston, chicago, 2);
        graph.addEdge(sf, boston, 4);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge1 = iterator.next();
        assertEquals(2, edge1.getWeight());
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge2 = iterator.next();
        assertEquals(4, edge2.getWeight());
        assertFalse(iterator.hasNext());
    }

    @Test
    @GradedTest(name = "KruskalIterator: testWithThreeEdge()", points = 5.0)
    public void testWithThreeEdges() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        Graph.Node<String> chicago = addToGraph("Chicago");
        graph.addEdge(boston, chicago, 2);
        graph.addEdge(sf, chicago, 3);
        graph.addEdge(sf, boston, 4);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge1 = iterator.next();
        assertEquals(2, edge1.getWeight());
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge2 = iterator.next();
        assertEquals(3, edge2.getWeight());
        assertFalse(iterator.hasNext());
    }

    @Test
    @GradedTest(name = "KruskalIterator: testWithFourEdges()", points = 5.0)
    public void testWithFourEdges() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        Graph.Node<String> chicago = addToGraph("Chicago");
        Graph.Node<String> ny = addToGraph("New York");
        graph.addEdge(boston, chicago, 1);
        graph.addEdge(sf, chicago, 1);
        graph.addEdge(sf, boston, 3);
        graph.addEdge(sf, ny, 5);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge1 = iterator.next();
        assertEquals(1, edge1.getWeight());
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge2 = iterator.next();
        assertEquals(1, edge2.getWeight());
        assertTrue(iterator.hasNext());
        Graph.Edge<String> edge3 = iterator.next();
        assertEquals(5, edge3.getWeight());
        assertFalse(iterator.hasNext());
    }

    @Test
    @GradedTest(name = "KruskalIterator: ConcurrentModificationException()", points = 5.0)
    public void testConcurrentModificationExceptionWhenEdgeAdded() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        Graph.Node<String> chicago = addToGraph("Chicago");
        graph.addEdge(sf, boston, 3);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        graph.addEdge(chicago, boston, 4);
        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    @GradedTest(name = "KruskalIterator: ConcurrentModificationException()", points = 5.0)
    public void testConcurrentModificationExceptionWhenNodeAdded() {
        Graph.Node<String> boston = addToGraph("Boston");
        Graph.Node<String> sf = addToGraph("San Francisco");
        graph.addEdge(sf, boston, 3);
        Iterator<Graph.Edge<String>> iterator = graph.getKruskalIterator();
        assertTrue(iterator.hasNext());
        Graph.Node<String> chicago = addToGraph("Chicago");
        graph.addEdge(chicago, boston, 4);
        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}
