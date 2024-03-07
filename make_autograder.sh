#!/usr/bin/env bash

DIRS_TO_COPY=("lib/" "config/" "gradle/" "src/")
GRADLE_FILES=("gradlew" "build.gradle" "gradlew.bat")
CONFIG_FILE=("config.ini")
SCRIPTS_DIR="scripts"
SCRIPTS=("run_autograder" "run_autograder.py" "setup.sh")
SCRIPT_PATHS=("${SCRIPTS[@]/#/$SCRIPTS_DIR/}")

echo "Removing carriage returns from scripts..."
for item in "${SCRIPT_PATHS[@]}"; do
    echo dos2unix "$item"
done

echo "Building Autograder..."
rm autograder.zip
zip -r autograder.zip ${DIRS_TO_COPY[@]} ${GRADLE_FILES[@]} $CONFIG_FILE
zip -urj autograder.zip ${SCRIPT_PATHS[@]}
echo "Build complete."
