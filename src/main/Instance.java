package main;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Instance {


	private StableStrategie strat;
	private Map<String, TreeMap<Integer, List<String>>> hommeMap; 
	private Map<String, TreeMap<Integer, List<String>>> femmeMap;
	
	
	
	
	public Instance(StableStrategie ss, Map<String, TreeMap<Integer, List<String>>> hommeMap, 
			Map<String, TreeMap<Integer, List<String>>> femmeMap){
	
		this.strat = ss;
		this.hommeMap = hommeMap;
		this.femmeMap = femmeMap;
	}


	
	public void executeStableStrategie(){
		strat.correspondance(hommeMap, femmeMap);
		strat.verifieCorrespondance(hommeMap, femmeMap);
		strat.print(hommeMap, femmeMap);
		
		
	}
}
