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
echo "Updating the L1J database Copyright (c) L1J-JP <http://l1j.org/>"

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
:: Update the tables
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
mysql -u %username% -p%password% %database% < .\schema\mysql\armor_sets.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\armors.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\auction_houses.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\beginner_items.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\castles.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\commands.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\cooking_ingredients.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\cooking_recipes.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\door_gfxs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\drop_items.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\drop_rates.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\dungeons.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\etc_items.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\houses.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\inns.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\item_rates.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\magic_dolls.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\map_ids.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\mob_groups.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\mob_skills.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\npc_actions.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\npc_chats.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\npcs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\pet_items.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\pet_types.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\polymorphs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\random_dungeons.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\resolvents.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\restart_locations.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\return_locations.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\shops.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\skills.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_boss_mobs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_doors.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_lights.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_mobs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_npcs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_times.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_traps.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spawn_ub_mobs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\spr_actions.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\towns.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\traps.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\ub_managers.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\ub_times.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\ubs.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\weapon_skills.sql
mysql -u %username% -p%password% %database% < .\schema\mysql\weapons.sql

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: Store the csv data
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
mysqlimport -u %username% -p%password% %database% %d%\armor_sets.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\armors.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\auction_houses.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\beginner_items.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\castles.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\commands.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\cooking_ingredients.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\cooking_recipes.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\door_gfxs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\drop_items.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\drop_rates.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\dungeons.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\etc_items.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\houses.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\inns.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\item_rates.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\magic_dolls.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\map_ids.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\mob_groups.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\mob_skills.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\npc_actions.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\npc_chats.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\npcs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\pet_items.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\pet_types.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\polymorphs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\random_dungeons.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\resolvents.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\restart_locations.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\return_locations.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\shops.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\skills.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_boss_mobs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_doors.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_lights.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_mobs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_npcs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_times.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_traps.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spawn_ub_mobs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\spr_actions.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\towns.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\traps.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\ub_managers.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\ub_times.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\ubs.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\weapon_skills.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n
mysqlimport -u %username% -p%password% %database% %d%\weapons.csv --fields-enclosed-by= --fields-terminated-by=, --lines-terminated-by=\n

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:END
::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo "Updating the database is complete."
pause
exit
