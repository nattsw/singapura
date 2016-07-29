#!/usr/bin/env bash

GRADLE_TASKS=""

function add_task {
  GRADLE_TASKS="$GRADLE_TASKS $1"
}

if [ -n "$JENKINS_HOME" ]
then
  VERSION=$("$WORKSPACE/scripts/version")
  echo "This is Jenkins build $BUILD_NUMBER"
  echo "Branch: $GIT_BRANCH"
  IFS=/ read -r GIT_REMOTE BUILD_TYPE BUILD_TOPIC <<<"$GIT_BRANCH"
  curl -s --data-urlencode "description=$BUILD_TYPE $BUILD_TOPIC" "${BUILD_URL}submitDescription"
else
  echo "No Jenkins here."
  BUILD_TYPE=${1-develop}
  WORKSPACE=`git rev-parse --show-toplevel`
fi

echo "Build type: $BUILD_TYPE"

## Set up build tasks

#add_task clean
#add_task :app:lint
#add_task :app:connectedAndroidTest
#add_task :app:test
add_task :app:generateDebugSources
add_task :app:mockableAndroidJar
add_task :app:prepareDebugUnitTestDependencies
add_task :app:compileDebugUnitTestSources
add_task :app:test

cd $WORKSPACE

echo "Tasks that will be executed $GRADLE_TASKS"

./gradlew --continue --no-daemon $GRADLE_TASKS -PdisablePreDex
