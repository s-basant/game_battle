/**
 * 
 */
package org.game_battle.gameplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.game_battle.controler.MapController;
import org.game_battle.model.Implementation.ContinentZone;
import org.game_battle.model.Implementation.TerritoryZone;
import org.game_battle.model.Implementation.WorldMap;
import org.game_battle.view.WorldMapView;

/**
 * This class sets up the map interface for the game
 * 
 * @author Vini
 * @version Alpha
 * @date 5/02/19
 **/
public class MapInterface {

	private static List<Continent> continents;
	private static List<Country> countries;
	private static List<List<Country>> countriesByContinent;
	private static WorldMap model = null;

	public MapInterface() {
		super();
		/*
		 * countries = new LinkedList<Country>(Arrays.asList(new Country("ca"), new
		 * Country("cb"), new Country("cc"), new Country("cd"), new Country("ce"), new
		 * Country("cf"), new Country("cg"), new Country("ch"), new Country("ci"), new
		 * Country("cj")));
		 */
		/*
		 * continents = new LinkedList<Continent>(Arrays.asList(new Continent(10,
		 * "cta"), new Continent(20, "ctb"), new Continent(30, "ctc"), new Continent(40,
		 * "ctd")));
		 */
		/*
		 * countriesByContinent = Arrays.asList(Arrays.asList(countries.get(0),
		 * countries.get(1)), Arrays.asList(countries.get(2), countries.get(3)),
		 * Arrays.asList(countries.get(4), countries.get(5)),
		 * Arrays.asList(countries.get(6), countries.get(7), countries.get(8),
		 * countries.get(9)));
		 */

		model = new WorldMap();
		WorldMapView view = new WorldMapView();
		MapController controller = new MapController(view, model);
		// Load Map Method
		controller.loadMap(view);
		// Print Map Method
		controller.printMap(view, model);
		// Edit Map condition
		if (!view.isAddMap()) {
			if (view.intiateMapEdit()) {
				// Edit Map Method
				controller.editMap(view, model);
				// Print Map Mathod
				controller.printMap(view, model);
			}
		}
		countries = getCountriesFromTerritories(model.getTerritories());
		continents = getContinentFromContinentZone(model.getContinents());

	}

	private LinkedList<Continent> getContinentFromContinentZone(List<ContinentZone> continents2) {
		// TODO Auto-generated method stub
		LinkedList<Continent> cts = new LinkedList<>();
		for (ContinentZone continentZone : continents2) {
			String continentName = continentZone.getContinentName();
			Continent ct = new Continent(model.getContinentValues().get(continentName), continentName);
			cts.add(ct);
		}
		return cts;
	}

	private List<Country> getCountriesFromTerritories(List<TerritoryZone> territories) {
		// TODO Auto-generated method stub
		LinkedList<Country> countries = new LinkedList<Country>();
		for (TerritoryZone t : territories) {
			Country c = new Country(t.getTerritoryName());

			countries.add(c);
			// c.add(new Country(t));
		}
		return countries;
	}

	/**
	 * 
	 * @return list of countries
	 */
	public static List<Country> getCountries() {
		// TODO return all territories
		return countries;
	}

	/**
	 * 
	 * @return list of contitnents
	 */
	public static List<Continent> getContinents() {
		// TODO return all continents
		return continents;
	}

	/**
	 * @param continent name of the continent
	 * @return list of countries belongs to a continent
	 */

	public static List<Country> getCountriesByContinent(Continent continent) {
		// TODO return all countries in a given continent
		return getCountriesByContinentFromTerritoryZone(continent.getName());// countriesByContinent.get(continents.indexOf(continent));
	}

	/**
	 * @param name
	 * @return return country list form territory zone
	 */
	private static List<Country> getCountriesByContinentFromTerritoryZone(String name) {
		List<Country> countrylist = new LinkedList<Country>();
		List<TerritoryZone> l = model.getCountriesByContinent(name);
		for (TerritoryZone territoryZone : l) {
			countrylist.add(getCountryByName(territoryZone.getTerritoryName()));
		}
		return countrylist;
	}

	/**
	 * @param name
	 * @return name of the country
	 */
	private static Country getCountryByName(String name) {
		for (Country c : countries) {
			if (name.equalsIgnoreCase(c.getName())) {
				return c;
			}
		}
		return null;
	}

	/**
	 * @param country country name
	 * @return neighbouring country associated with the country passed
	 */
	public static List<Country> getNeighbours(Country country) {
		// TODO return all adjacent countries
		List<Country> countrylist = new LinkedList<Country>();
		ArrayList<String> l = model.getTerritoryNeighbour(country.getName());
		for (String countryname : l) {
			countrylist.add(getCountryByName(countryname));
		}

		return countrylist;
	}

}

/*
 * 
 * public static List<Country> getCountries() => public List<TerritoryZone>
 * getTerritories() public static List<Continent> getContinents() => public
 * List<ContinentZone> getContinents() public static List<Country>
 * getCountriesByContinent(Continent continent)=> public List<TerritoryZone>
 * getCountriesByContinent(String continent) public static List<Country>
 * getNeighbours(Country country) => public ArrayList<String>
 * getTerritoryNeighbour(String territory )
 * 
 */
