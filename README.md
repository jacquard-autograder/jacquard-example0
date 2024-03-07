# Jacquard Example 0

This is an example of a simple [Jacquard](https://github.com/jacquard-autograder/jacquard)
autograder that applies these tests to a single submitted file.

* [Checkstyle](https://checkstyle.sourceforge.io/) tests
* [PMD](https://pmd.github.io/) tests
* JUnit tests

## Software Requirements

* bash (included on OS X and Linux)
* Python 3 if you want to test locally by executing `test_autograder.py`
  (optional)

For bash on Windows, we use and
recommend [Git for Windows](https://gitforwindows.org/) 2.41 or higher.

## Files

These directories and files have code specific to the assignment:

* `config/checkstyle-rules.xml` holds the checkstyle rules file
* `src/main/java/student` contains
    * `AutograderMain.java`, which has the `main` method that controls the autograder
    * `Adder.java`, placeholder for student code
    * `AdderTest.java`, which contains annotated JUnit 5 tests of student code
* `submission` holds a sample submission (required if you want to run
  `test_autograder.py` locally)
* `submissions` (which is not required) holds sample submissions to manually
  test the grader locally or on Gradescope
    * `perfect`, a subdirectory with a perfect submission
    * `imperfect`, a subdirectory with an imperfect submission
    * `starter`, a subdirectory with the starter code

Any of the above files could have different names or packages, although package names
must currently have only a single part (e.g., `student`, not `edu.myschool.student`.)
The `submissions/` subdirectories could also have different names.

### config.ini

The submission package and files are specified in `config.ini`:
```
[submission]
package = student
files = [Adder.java]
```
The list of files is comma-separated, with optional whitespace.

### build.gradle

The main class of the autograder is specified in `build.gradle`:

```groovy
ext {
    javaMainClass = "student.AutograderMain"
}
```

You will need to change it if you use a different package/class name for
your main autograder class.

You are free to make other additions to `build.gradle`, such as adding
dependencies.

## Dockerfile

This can be used to create a Docker image for the autograder.

## Shell scripts

### test_autograder.sh

This script, which requires Python 3, lets you test the autograder locally. If called without any
arguments, it will use the submission in the `submission/` directory.
```shell
./test_autograder.sh
```

If called with an argument, it will use the submission in that subdirectory,
prepending `submissions/` if necessary. For example, to test the autograder
against the files in `submissions/perfect`, you could execute either:
```shell
./test_autograder.sh submissions/perfect
./test_autograder.sh perfect

### make_autograder.sh

This creates the zip file for you to upload to Gradescope.

## Uploading to Gradescope

### Zip file

To create a zip file, run `./make_autograder.sh` from the command line.

To configure the autograder on Gradescope:

1. Click on "Configure Autograder" in the left sidebar.
2. Select "Zip file upload".
3. Click on "Replace Autograder (.zip)".
4. Select:
    * Base Image OS: Ubuntu
    * Base Image Version: 22.04
    * Base Image Variant: JDK 17
5. Click on "Update Autograder". (You may have to wait up to a minute for
   anything to happen. The button will go gray when the build begins.)
6. Wait for the "Built as of" time to be updated.

![screenshot showing Zip file upload of autograder.zip with Ubuntu 22.04 and
JDK 17 selected](images/configure-autograder.png)

## Docker

If you have installed Docker, you can create an image based on [`Dockerfile`](Dockerfile)
with a command line of the form:
```
docker build -t username/gradername .
```

For example, because my Docker username is `espertus`, I would type:
``` 
docker build -t espertus/example0 .
```
For more information, see [Manual Docker
Configuration](https://gradescope-autograders.readthedocs.io/en/latest/manual_docker/)_

## Next Steps

See [https://github.com/jacquard-autograder/jacquard](https://github.com/jacquard-autograder/jacquard)
for more information, including:

* Documentation
* Additional examples
* Mailing lists