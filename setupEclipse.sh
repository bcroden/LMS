#!/bin/bash
cd LMSCommon/
./gradlew cleanEclipse eclipse
cd ../Database\ Manager/
./gradlew cleanEclipse eclipse
cd ../Workstation/
./gradlew cleanEclipse eclipse
cd ..