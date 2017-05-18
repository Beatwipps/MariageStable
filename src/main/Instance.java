package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Instance {


	private StableStrategie strat;
	private Map<String, ArrayList<List<String>>> mapA; 
	private Map<String, ArrayList<List<String>>> mapB;
	private List<String> A;
	private List<String> B;

	
	
	public Instance(StableStrategie ss, Map<String, ArrayList<List<String>>> mapA, 
			Map<String, ArrayList<List<String>>> mapB, List<String> A, List<String> B){
	
		this.strat = ss;
		this.mapA = mapA;
		this.mapB = mapB;
		this.A = A;
		this.B = B;

	}

	
	public void executeStableStrategie(){
	//	strat.afficheLeCouplage(mapA, mapB, A, B);
		strat.trouveUnCouplage(mapA, mapB, A, B);
		
		
		
	}
}
