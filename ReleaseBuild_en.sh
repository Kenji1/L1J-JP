#!/bin/bash
ant
cd db
sh MakeCreateTables_en.sh
sh MakeUpdateTables_en.sh
cd ..
mkdir -p release-build
cp -ivR config release-build/config
cp -ivR data release-build/data
mkdir -p release-build/db
cp -iv db/create_db.sql release-build/db/create_db.sql
cp -iv db/create_tables.sql release-build/db/create_tables.sql
cp -iv db/update_tables.sql release-build/db/update_tables.sql
mkdir -p release-build/emblem
cp -fvR lib release-build/lib
mkdir -p release-build/log
cp -fvR maps release-build/maps
cp -fv l1jserver.jar release-build/l1jserver.jar
cp -fv license.txt release-build/license.txt
cp -fv ServerStart.bat release-build/ServerStart.bat
cp -fv ServerStart.sh release-build/ServerStart.sh
