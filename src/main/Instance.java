package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Instance {


	private StableStrategie strat;
	private Map<String, ArrayList<List<String>>> hommeMap; 
	private Map<String, ArrayList<List<String>>> femmeMap;
	private List<String> hommes;
	private List<String> femmes;

	
	
	public Instance(StableStrategie ss, Map<String, ArrayList<List<String>>> hommeMap, 
			Map<String, ArrayList<List<String>>> femmeMap, List<String> hommes, List<String> femmes){
	
		this.strat = ss;
		this.hommeMap = hommeMap;
		this.femmeMap = femmeMap;
		this.hommes = hommes;
		this.femmes = femmes;

	}

	
	public void executeStableStrategie(){
		//strat.trouveUneSolution(hommeMap, femmeMap, hommes, femmes);
		//strat.verifieLaSolution(hommeMap, femmeMap, hommes, femmes, strat.trouveUneSolution(hommeMap, femmeMap, hommes, femmes));
		
		strat.affiche(hommeMap, femmeMap, hommes, femmes);
		
		
	}
}
