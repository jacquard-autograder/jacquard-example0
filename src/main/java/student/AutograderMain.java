package student;

import com.spertus.jacquard.checkstylegrader.CheckstyleGrader;
import com.spertus.jacquard.common.*;
import com.spertus.jacquard.junittester.JUnitTester;
import com.spertus.jacquard.pmdgrader.PmdGrader;
import com.spertus.jacquard.publisher.GradescopePublisher;

import java.util.*;

import static java.lang.System.exit;

public class AutograderMain {
    public static void main(String[] args) {
        Autograder.initForTest();

        // For this assignment, students upload only a single file.
        final Target target = Target.fromClass(Adder.class);

        // Create checkstyle grader.
        CheckstyleGrader checkstyleGrader = new CheckstyleGrader(
                "config/checkstyle-rules.xml",
                1.0,
                5.0);

        // Create PMD grader.
        PmdGrader pmdGrader = PmdGrader.createFromRuleSetPaths(
                1.0,
                5.0,
                "category/java/bestpractices.xml");

        // Run all graders, collecting results.
        List<Result> results = Grader.gradeAll(
                target,
                checkstyleGrader, pmdGrader);

        // Run unit tests, adding on to existing results.
        JUnitTester runner = new JUnitTester(AdderTest.class);
        List<Result> junitResults = runner.run();
        results.addAll(junitResults);

        // Display the results.
        new GradescopePublisher().displayResults(results);

        // Explicitly exit (required by Gradle).
        exit(0);
    }
}
