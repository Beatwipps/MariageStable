package main;


import java.io.*;
import java.util.*;


public class LireFichier {
	
	protected StableStrategie strat;
	protected String source;
	private Map<String, ArrayList<List<String>>> mapDePreferences;
	private List<String> listeDePersonnes;
	
	
	public LireFichier(String source, StableStrategie strat) {
		this.source = source;
		this.strat = strat;
		setMap(lectureEtAffacetationDesPreferences(mapDePreferences, strat));
		setList(lectureEtAffectationDesPersonnes(listeDePersonnes));
	}


	private Map<String, ArrayList<List<String>>> lectureEtAffacetationDesPreferences(Map<String, ArrayList<List<String>>> map, StableStrategie strat) { 

		map = new HashMap<String, ArrayList<List<String>>>();
		boolean indifference = false;

		try {
			String ligne = null;
			String cle = null;
			Scanner fichier = new Scanner(new FileReader(source));
			while ((fichier.hasNextLine())) {
				ligne = fichier.nextLine();				
				if(Character.isLetter(ligne.charAt(0))){
					cle = ligne;
					ligne = fichier.nextLine();
					ArrayList<List<String>> arrayTmp = new ArrayList<List<String>>();
					while(!ligne.isEmpty()){
						List<String> listetmp = new ArrayList<String>();
						for(String str : ligne.split(" "))
							listetmp.add(str);
						if(listetmp.size()>2)
							indifference = true;
						listetmp.remove(0);
						arrayTmp.add(listetmp);
						ligne = fichier.nextLine();
						if(ligne.equals("/"))
							break;	
					}
					map.put(cle, arrayTmp);	
				}	
			}
			fichier.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(indifference == true && strat instanceof BasicStable){
			System.out.printf("Le fichier %s contient des indifferences et la strategie utilisé est Basique. ERREUR", source);
			return null;
		}
			
		return map;    
	}
	
	private List<String> lectureEtAffectationDesPersonnes (List<String> list){
		list = new ArrayList<String>();
		try {
			String ligne = null;
			Scanner fichier = new Scanner(new FileReader(source));
			while ((fichier.hasNextLine())) {
				ligne = fichier.nextLine();
				if(ligne.isEmpty())
					ligne = fichier.nextLine();
				if(Character.isLetter(ligne.charAt(0))){
					list.add(ligne);
					if(ligne.equals("/"))
						break;	
				}
			}	
			fichier.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public Map<String, ArrayList<List<String>>> getMap() {
		return mapDePreferences;
	}


	public void setMap(Map<String, ArrayList<List<String>>> map) {
		this.mapDePreferences = map;
	}    
	
	public List<String> getList() {
		return listeDePersonnes;
	}


	public void setList(List<String> list) {
		this.listeDePersonnes = list;
	}




	
}