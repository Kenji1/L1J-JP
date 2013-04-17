::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Backup the L1J database
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
echo "Backup the database..."

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Config
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set database=l1jdb
set username=root
set password=

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Date Format
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set tempdate=%date:/=%
set temptime=%time: =0%
set yyyy=%tempdate:~0,4%
set mm=%tempdate:~4,2%
set dd=%tempdate:~6,2%
set hh=%temptime:~0,2%
set ii=%temptime:~3,2%
set ss=%temptime:~6,2%
set datetime=%yyyy%-%mm%-%dd%_%hh%%ii%%ss%

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Create dump directory
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
if not exist .\backup md .\backup
if not exist .\backup\%datetime% md .\backup\%datetime%

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Dump all tables
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
mysqldump -u %username% -p%password% -t -T.\backup\%datetime% %database% --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Remove schema files
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
del .\backup\%datetime%\*.sql

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Change the extension to csv from text
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
ren .\backup\%datetime%\*.txt *.csv
