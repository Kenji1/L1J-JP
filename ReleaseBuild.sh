#!/bin/bash
ant
cd db
sh MakeCreateTables.sh
sh MakeUpdateTables.sh
cd ..
mkdir -p release-build
cp -ivR config release-build/config
cp -ivR data release-build/data
mkdir -p release-build/db
cp -fvR db release-build/db
mkdir -p release-build/emblem
cp -fvR lib release-build/lib
mkdir -p release-build/log
cp -fvR maps release-build/maps
cp -fv l1jserver.jar release-build/l1jserver.jar
cp -fv license.txt release-build/license.txt
cp -fv ServerStart.bat release-build/ServerStart.bat
cp -fv ServerStart.sh release-build/ServerStart.sh
