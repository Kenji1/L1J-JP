::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Import the L1J database
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
::
:: This program is free software: you can redistribute it and/or modify
:: it under the terms of the GNU General Public License as published by
:: the Free Software Foundation, either version 3 of the License, or
:: (at your option) any later version.
::
:: This program is distributed in the hope that it will be useful,
:: but WITHOUT ANY WARRANTY; without even the implied warranty of
:: MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
:: GNU General Public License for more details.
::
:: You should have received a copy of the GNU General Public License
:: along with this program.  If not, see <http://www.gnu.org/licenses/>.
::
:: Copyright (c) L1J-JP Project All Rights Reserved.
@echo off
echo "Import the L1J database Copyright (c) L1J-JP <http://l1j.org/>"

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Config
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set database=l1jdb
set username=root
set password=

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Check existence of CSV directory
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set /p d="Enter the CSV directory > "
if not exist %d% exit

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Backup the database
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
call .\ExportDatabase.bat

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Create the database
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo "Drop the database and Create the database"
mysql -u %username% -p%password% < .\create_db.sql

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Create the tables
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
for %%f in (.\schema\mysql\*.sql) do echo "Create table %%~nf" && mysql -u %username% -p%password% %database% < %%f

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Store the csv data
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
for %%f in (%d%\*.csv) do mysqlimport -u %username% -p%password% %database% %%f --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:END
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo "Import the database is complete."
pause
exit
