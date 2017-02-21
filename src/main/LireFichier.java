package main;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class LireFichier {
	
	protected String source;
	private Map<String, TreeMap<Integer, List<String>>> map;
	
	public LireFichier(String source) {
		this.source = source;
		this.setMap(lecture());
	}




	private Map<String, TreeMap<Integer, List<String>>> lecture() { 

		Map<String, TreeMap<Integer, List<String>>> map = new HashMap<String, TreeMap<Integer, List<String>>>();
		
		try {
			String ligne = null;
			String cle = null;
			Scanner fichier = new Scanner(new FileReader(source));
			while ((fichier.hasNextLine())) {
				ligne = fichier.nextLine();				
				if(Character.isLetter(ligne.charAt(0))){
					cle = ligne;
					ligne = fichier.nextLine();
					TreeMap<Integer, List<String>> treetmp = new TreeMap<Integer, List<String>>();
					while(!ligne.isEmpty()){
						List<String> listetmp = new ArrayList<String>();
						for(String str : ligne.split(" "))
							listetmp.add(str);
						listetmp.remove(0);
						treetmp.put(Character.getNumericValue(ligne.charAt(0)), listetmp);
						ligne = fichier.nextLine();
						if(ligne.equals("/"))
							break;	
					}
					map.put(cle, treetmp);	
				}	
			}
			fichier.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;    
	}




	public Map<String, TreeMap<Integer, List<String>>> getMap() {
		return map;
	}




	public void setMap(Map<String, TreeMap<Integer, List<String>>> map) {
		this.map = map;
	}    
	
}