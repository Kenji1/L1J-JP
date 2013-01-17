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
mv -fvR db/templates release-build/db/templates
mv -fv db/create_tables.sql release-build/db/create_tables.sql
mv -fv db/update_tables.sql release-build/db/update_tables.sql
cp -fv db/create_db.sql release-build/db/create_db.sql
mkdir -p release-build/emblem
cp -fvR lib release-build/lib
mkdir -p release-build/log
cp -fvR maps release-build/maps
cp -fv l1jserver.jar release-build/l1jserver.jar
cp -fv license.txt release-build/license.txt
cp -fv ServerStart.bat release-build/ServerStart.bat
cp -fv ServerStart.sh release-build/ServerStart.sh
