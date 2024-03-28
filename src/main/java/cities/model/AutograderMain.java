package cities.model;

import com.spertus.jacquard.checkstylegrader.CheckstyleGrader;
import com.spertus.jacquard.common.*;
import com.spertus.jacquard.junittester.JUnitTester;
import com.spertus.jacquard.pmdgrader.PmdGrader;
import com.spertus.jacquard.publisher.GradescopePublisher;

import java.util.*;

import static java.lang.System.exit;

public class AutograderMain {
    public static void main(String[] args) {
        Autograder.init();

        final List<Target> targets = List.of(
                Target.fromClass(Graph.class),
                Target.fromClass(CityMap.class)
        );

        CheckstyleGrader checkstyleGrader = new CheckstyleGrader(
                "config/checkstyle-rules.xml",
                1.0,
                5.0);
        List<Result> results = checkstyleGrader.grade(targets);

        JUnitTester runner1 = new JUnitTester(HiddenKruskalIteratorTest.class); // 30
        results.addAll(runner1.run());
        JUnitTester runner2 = new JUnitTester(HiddenNodeTest.class); // 20
        results.addAll(runner2.run());

        new GradescopePublisher().displayResults(results);

        // Explicitly exit (required by Gradle).
        exit(0);
    }
}
