package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ExtendedStable implements StableStrategie {

	
	
	
	public ExtendedStable(Map<String, TreeMap<Integer, List<String>>> preferenceDesHommes, 
			Map<String, TreeMap<Integer, List<String>>> preferenceDesFemmes){
		
		
		
	}
	
	public void print(Map<String, TreeMap<Integer, List<String>>> preferenceDesHommes,
			Map<String, TreeMap<Integer, List<String>>> preferenceDesFemmes){
		
		System.out.println("------- Preferences des Hommes --------");
		for (Entry<String, TreeMap<Integer, List<String>>> entry : preferenceDesHommes.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			TreeMap<Integer, List<String>> myTree = entry.getValue();


			for (Entry<Integer, List<String>> entry1 : myTree.entrySet()) {
				System.out.println(entry1.getKey() + " => " + entry1.getValue());	              
			}
			System.out.println();
		}

		System.out.println("------- Preferences des Femmes --------");
		for (Entry<String, TreeMap<Integer, List<String>>> entry : preferenceDesFemmes.entrySet()) {
			String key = entry.getKey();
			System.out.println(key);
			TreeMap<Integer, List<String>> myTree = entry.getValue();


			for (Entry<Integer, List<String>> entry1 : myTree.entrySet()) {
				System.out.println(entry1.getKey() + " => " + entry1.getValue());
			}
			System.out.println();
		}
	}
	


	@Override
	public Map<String, String> trouveUneSolution(Map<String, ArrayList<List<String>>> preferenceDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes) {
		List<String> hommesLibre = new ArrayList<String>();
		TreeMap<String, String> coupleEngage = new TreeMap<String, String>();
		
		//Assigne tout les hommes à libre
		for (Entry<String, TreeMap<Integer, List<String>>> entry : preferenceDesHommes.entrySet()) {
			String nomDesHommes = entry.getKey();
			hommesLibre.add(nomDesHommes);
		}	
		
		// Tant Qu'il y a des hommes libres
		while(!hommesLibre.isEmpty()){
			System.out.println("Liste des hommes libre : " +hommesLibre);
			// Selection de l'homme libre et sa suppresion dans la liste
			String cetHomme = hommesLibre.remove(0);  
			// L'indice de la premiere clé dans la preference de cet homme
			int indiceDesPremieresFemmeDeCetHomme = preferenceDesHommes.get(cetHomme).firstKey();
			System.out.println("indiceDesPremieresFemmeDeCetHomme : " + indiceDesPremieresFemmeDeCetHomme);
			// Nombre de femme dans la premiere cle des preference de cet homme
			int nbDeFemmeDansLaPremiereCle = preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme).size();
			System.out.println("nbDeFemmeDansLaPremiereCle : " + nbDeFemmeDansLaPremiereCle);
			// Nom de la femme prefere de cet homme
			String femmePrefereDeCetHomme = preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme).get(0);
			System.out.println("femmePrefereDeCetHomme Avant de Rentrer dans la boucle : " + femmePrefereDeCetHomme);
			
			// Si il y a plus d'une femme dans la premiere clé, un ordre aleatoire est généré
			if(nbDeFemmeDansLaPremiereCle > 1){
				System.out.println("Il y a plus d'une femme dans la liste");
				// Generation d'un ordre aleatoire des femmes dans la clé 
				Collections.shuffle(preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme));
				System.out.println("Ordre de la liste apres le SHUFFLE : " + preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme));
				Boolean trouve = false;
				for(String femme : preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme)){
					if(trouve)
						break;
					System.out.println("Dans la boucle for la femme est : " + femme);
					for(Entry<Integer, List<String>> entr : preferenceDesFemmes.get(femme).entrySet()){
						if(entr.getValue().contains(cetHomme)){
							femmePrefereDeCetHomme = femme;
							trouve = true;
							break;
						}
						else
							continue;
					}
				}
			}
				preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme).remove(femmePrefereDeCetHomme);
				if(preferenceDesHommes.get(cetHomme).get(indiceDesPremieresFemmeDeCetHomme).isEmpty())
					preferenceDesHommes.get(cetHomme).remove(indiceDesPremieresFemmeDeCetHomme);
				System.out.println("Suppresion : " + preferenceDesHommes.get(cetHomme));
				//preferenceDesHommes.get(cetHomme).remove(preferenceDesHommes.get(cetHomme).firstKey());
				Integer cleDeHommeDansPreferenceDeCetteFemme = null;

				for(Entry<Integer, List<String>> entry : preferenceDesFemmes.get(femmePrefereDeCetHomme).entrySet()) {
					List<String> value = entry.getValue();
					if(!value.contains(cetHomme)) 
						continue;
					else
						cleDeHommeDansPreferenceDeCetteFemme = entry.getKey();	
				} 
				System.out.println("cleDeHommeDansPreferenceDeCetteFemme : " + cleDeHommeDansPreferenceDeCetteFemme);
			//	System.out.println("femmepreferedecettehomme : " + femmePrefereDeCetHomme);
				System.out.println("listeDesPrefereDeCetteFemme : \n" +  preferenceDesFemmes.get(femmePrefereDeCetHomme));
				coupleEngage.put(cetHomme, femmePrefereDeCetHomme);
				
				if(!preferenceDesFemmes.get(femmePrefereDeCetHomme).isEmpty()){

					if(preferenceDesFemmes.get(femmePrefereDeCetHomme).get(cleDeHommeDansPreferenceDeCetteFemme).size() > 1){ // il y a des indifferences dans la case
						preferenceDesFemmes.get(femmePrefereDeCetHomme).get(cleDeHommeDansPreferenceDeCetteFemme).remove(cetHomme);
					}
					
					
					
					
					Iterator<Entry<Integer, List<String>>> iter1 = 
							preferenceDesFemmes.get(femmePrefereDeCetHomme).tailMap(cleDeHommeDansPreferenceDeCetteFemme+1).entrySet().iterator();
					@SuppressWarnings("unused")
					Entry<Integer, List<String>> entry1;
					while (iter1.hasNext()) {
						entry1 = iter1.next();
//						Integer intCle = entry1.getKey();
//						List<String> strList = entry1.getValue();
				//		if(listeDesPreferencesDeCetteFemme.get(cleDeHommeDansPreferenceDeCetteFemme).size() > 1){
//							Iterator<String> iter2 = entry1.getValue().iterator();
//							String name ; 
//							int index = -1;
//							while(iter2.hasNext()){
//								name = iter2.next();
//								index++;
//								if(!name.contentEquals(cetHomme))
//									entry1.getValue().get(index).replaceAll(name, "");
//							}
//							for( String name : entry1.getValue()){
//								++index;
//								if(!name.contains(cetHomme))
//									entry1.getValue().remove(entry1.getValue().get(index));
//								else
//									continue;
//							}
//						}
//						else
							iter1.remove();
					}
				}

				System.out.println("Apres modif :" +preferenceDesFemmes.get(femmePrefereDeCetHomme));
				System.out.println("Couple Engage : " + coupleEngage);
				for (Entry<Integer, List<String>> entry : preferenceDesFemmes.get(femmePrefereDeCetHomme).entrySet()) {
					Integer nomDesFemmes = entry.getKey();
					List <String> listeDesHommes = entry.getValue();
					preferenceDesFemmes.get(femmePrefereDeCetHomme).put(nomDesFemmes, listeDesHommes);	
					System.out.println(entry.getKey() + " => " + entry.getValue());
					
				}	
				Iterator<Entry<String, String>> iterateur2 = coupleEngage.entrySet().iterator();
				Entry<String, String> entry2;
				while (iterateur2.hasNext()) {

					entry2 = iterateur2.next();
					String autreHomme = entry2.getKey();
					String laFemmeAdultere = entry2.getValue();
					if(!autreHomme.contentEquals(cetHomme) && laFemmeAdultere.contentEquals(femmePrefereDeCetHomme)){
						iterateur2.remove();
						System.out.println("Modif de ouf : " + coupleEngage);	
						hommesLibre.add(autreHomme);
					}
				}
				System.out.println();

			}	
	}

	@Override
	public boolean verifieLaSolution(Map<String, ArrayList<List<String>>> preferencesDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes,
			Map<String, String> coupleEngage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void affiche(Map<String, ArrayList<List<String>>> preferenceDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes) {
		// TODO Auto-generated method stub
		
	}
}
