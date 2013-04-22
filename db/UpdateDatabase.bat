::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Update the L1J database
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
:: Copyright (c) 2012 L1J-JP Project All Rights Reserved.
@echo off
echo Update the database...

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: MySQL Config
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set database=l1jdb
set username=root
set password=

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: CSV Config
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set delimiter=,
set enclosed=\'
set newline=\n
set skipline=1

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Tables to be updated
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set t[1]=armor_sets
set t[2]=armors
set t[3]=auction_houses
set t[4]=beginner_items
set t[5]=castles
set t[6]=commands
set t[7]=cooking_ingredients
set t[8]=cooking_recipes
set t[9]=door_gfxs
set t[10]=drop_items
set t[11]=drop_rates
set t[12]=dungeons
set t[13]=etc_items
set t[14]=houses
set t[15]=inns
set t[16]=item_rates
set t[17]=magic_dolls
set t[18]=map_ids
set t[19]=mob_groups
set t[20]=mob_skills
set t[21]=npc_actions
set t[22]=npc_chats
set t[23]=npcs
set t[24]=pet_items
set t[25]=pet_types
set t[26]=polymorphs
set t[27]=random_dungeons
set t[28]=resolvents
set t[29]=restart_locations
set t[30]=return_locations
set t[31]=shops
set t[32]=skills
set t[33]=spawn_boss_mobs
set t[34]=spawn_doors
set t[35]=spawn_lights
set t[36]=spawn_mobs
set t[37]=spawn_npcs
set t[38]=spawn_times
set t[39]=spawn_traps
set t[40]=spawn_ub_mobs
set t[41]=spr_actions
set t[42]=towns
set t[43]=traps
set t[44]=ub_managers
set t[45]=ub_times
set t[46]=ubs
set t[47]=weapon_skills
set t[48]=weapons

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Enter the CSV directory
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:RETRY
echo Enter the CSV directory (Cancel: Press the Enter key without entering)
set D=
set /p D=^>
if not defined D goto :CANCEL
if not exist %D% goto RETRY

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Update the tables
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo Update the tables...
setlocal ENABLEDELAYEDEXPANSION
for /L %%i in (1,1,48) do (
  call set F=.\schema\mysql\%%t[%%i]%%.sql
  echo !F!
  mysql -u %username% -p%password% %database% < !F!
  if errorlevel 1 goto ERR
)
endlocal

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Store the csv data
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo Store the CSV data...
setlocal ENABLEDELAYEDEXPANSION
for /L %%i in (1,1,48) do (
  call set F=%D%\%%t[%%i]%%.csv
  echo !F!
  mysqlimport -u %username% -p%password% -L %database% !F! ^
  --fields-enclosed_by=%enclosed% ^
  --fields-terminated_by=%delimiter% ^
  --lines-terminated_by=%newline% ^
  --ignore-lines=%skipline%
  if errorlevel 1 goto ERR
)
endlocal

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:END
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo Update is complete.
pause
exit \b 0

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:CANCEL
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo Canceled the install.

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:ERR
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
pause
exit \b 1
