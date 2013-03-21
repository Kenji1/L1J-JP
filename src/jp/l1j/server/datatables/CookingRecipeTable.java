/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.server.datatables;

import java.util.Map;
import jp.l1j.server.templates.L1CookingRecipe;
import jp.l1j.server.utils.collections.Maps;

public class CookingRecipeTable {
	private static Map<Integer, L1CookingRecipe> _recipes = null;

	private CookingRecipeTable() {
	}

	public static void initialize() {
		if (_recipes != null) {
			throw new DataTableAlreadyInitializedException(CookingRecipeTable.class);
		}
		_recipes = load();
	}

	private static Map<Integer, L1CookingRecipe> load() {
		Map<Integer, L1CookingRecipe> result = Maps.newHashMap();
		for (L1CookingRecipe recipe : L1CookingRecipe.all()) {
			result.put(recipe.getId(), recipe);
		}
		return result;
	}

	public static L1CookingRecipe findById(int id) {
		return _recipes.get(id);
	}
}
