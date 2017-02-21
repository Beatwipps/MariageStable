package main;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface StableStrategie {

		public void correspondance(Map<String, TreeMap<Integer, List<String>>> hommeMap, 
				Map<String, TreeMap<Integer, List<String>>> femmeMap);
		
		public void verifieCorrespondance(Map<String, TreeMap<Integer, List<String>>> hommeMap, 
				Map<String, TreeMap<Integer, List<String>>> femmeMap);
		
		public void print(Map<String, TreeMap<Integer, List<String>>> hommeMap,
				Map<String, TreeMap<Integer, List<String>>> femmeMap);
}
