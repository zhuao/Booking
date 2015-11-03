#!/bin/sh

DIR=$( cd "$( dirname $0 )" && pwd )

cd $DIR
cd ..
./gradlew -PbuildNumber=$BUILD_NUMBER clean assembleDebug testDebug