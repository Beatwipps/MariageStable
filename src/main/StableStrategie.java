package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface StableStrategie {

		public Map<String, String> trouveUneSolution(Map<String, ArrayList<List<String>>> preferenceDesHommes, 
				Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes);
		
		public boolean verifieLaSolution(Map<String, ArrayList<List<String>>> preferencesDesHommes, 
				Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes,
				Map<String, String> coupleEngage);
		
		public void affiche(Map<String, ArrayList<List<String>>> preferenceDesHommes,
				Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes );
}
