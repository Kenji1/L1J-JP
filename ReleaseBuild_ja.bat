cd .\db
call .\MakeCreateTables_ja.bat
call .\MakeUpdateTables_ja.bat
cd ..
xcopy .\config .\release-build\config /D /E /I /-Y
xcopy .\data .\release-build\data /D /E /I /-Y
xcopy .\db\create_db.sql .\release-build\db\ /D /E /I /Y
xcopy .\db\create_tables.sql .\release-build\db\ /D /E /I /Y
xcopy .\db\update_tables.sql .\release-build\db\ /D /E /I /Y
xcopy .\emblem .\release-build\emblem /D /E /I /Y
xcopy .\lib .\release-build\lib /D /E /I /Y
xcopy .\log .\release-build\log /D /E /I /Y
xcopy .\maps .\release-build\maps /D /E /I /Y
copy .\l1jserver.jar .\release-build\l1jserver.jar /Y
copy .\license.txt .\release-build\license.txt /Y
copy .\ServerStart.bat .\release-build\ServerStart.bat /Y
copy .\ServerStart.sh .\release-build\ServerStart.sh /Y
