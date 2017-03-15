/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author beatwipps
 *
 */
public class BasicStable implements StableStrategie{
	
	/* 
	 * Renvoie une Map de couple (Homme, Femme)
	 */
	@Override
	public Map<String, String> trouveUneSolution(Map<String, ArrayList<List<String>>> preferenceDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes) {
		
		Map<String, List<String>> tmpPreferenceDesHommes = new HashMap<String, List<String>>();
		tmpPreferenceDesHommes = convert(preferenceDesHommes);
		
		Map<String, ArrayList<List<String>>> tmpPreferenceDesFemmes = new TreeMap<>(preferenceDesFemmes);
		List<String> tmpHommes = new ArrayList<>(hommes);
		List<String> tmpFemmes = new ArrayList<>(femmes);
		Map<String, String> coupleEngage = new HashMap<String, String>();
		Map<String,String> aAjouterDansCoupleEngage = new HashMap<String,String>();
		
		
		while(!tmpHommes.isEmpty()){
			String cetHomme = tmpHommes.remove(0);  
			String femmePrefereDeCetHomme = tmpPreferenceDesHommes.get(cetHomme).get(0);
			// Suppresion de la femme prefere de cet homme dans sa liste de preference
			tmpPreferenceDesHommes.get(cetHomme).remove(0);
			if(tmpFemmes.contains(femmePrefereDeCetHomme)){
				coupleEngage.put(cetHomme, femmePrefereDeCetHomme);
				tmpFemmes.remove(femmePrefereDeCetHomme);
			}
			else{
				choisirPrefereDeLaFemmeEntreDeuxHommes(tmpPreferenceDesFemmes, tmpHommes, coupleEngage, cetHomme,
						femmePrefereDeCetHomme, aAjouterDansCoupleEngage);
			}
			ajouterCouple(coupleEngage, aAjouterDansCoupleEngage);
		}
		
		return coupleEngage;
	}

	@Override
	public boolean verifieLaSolution(Map<String, ArrayList<List<String>>> preferenceDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes,
			Map<String, String> coupleEngage) {
		
		Map<String, List<String>> tmpPreferenceDesHommes = new HashMap<String, List<String>>();
		tmpPreferenceDesHommes = convert(preferenceDesHommes);
		Map<String, List<String>> tmpPreferenceDesFemmes = new HashMap<String, List<String>>();
		tmpPreferenceDesFemmes = convert(preferenceDesFemmes);
		
		if(!coupleEngage.keySet().containsAll(hommes)){
			return false;
		}

		if(!coupleEngage.values().containsAll(femmes)){
			return false;
		}
		Map<String, String> coupleEngageInverse = new TreeMap<String, String>();
		for(Map.Entry<String, String> couple: coupleEngage.entrySet()){
			coupleEngageInverse.put(couple.getValue(), couple.getKey());
		}

		for(Map.Entry<String, String> couple: coupleEngage.entrySet()){
			
			List<String> preferenceDeLaFemmeDuCouple = tmpPreferenceDesFemmes.get(couple.getValue());
			List<String> topPreferenceDeCetteFemme = new ArrayList<String>();
			topPreferenceDeCetteFemme.addAll(preferenceDeLaFemmeDuCouple.subList(0, preferenceDeLaFemmeDuCouple.indexOf(couple.getKey())));
			List<String> preferenceDeLHommeDuCouple = tmpPreferenceDesHommes.get(couple.getKey());
			List<String> topPreferenceDeCetHomme = new ArrayList<String>();
			topPreferenceDeCetHomme.addAll(preferenceDeLHommeDuCouple.subList(0, preferenceDeLHommeDuCouple.indexOf(couple.getValue())));

			for(String homme : topPreferenceDeCetteFemme){
				String laMariee = coupleEngage.get(homme);
				List<String> preferenceDeCetHomme = tmpPreferenceDesHommes.get(homme);
				if(preferenceDeCetHomme.indexOf(laMariee) >
				preferenceDeCetHomme.indexOf(couple.getKey())){
					System.out.printf("%s Aime %s plus que %s et %s"
							+ " aime %s plus que son partenaire actuel\n",
							couple.getValue(), homme, couple.getKey(),
							homme, couple.getValue());
					return false;
				}
			}

			for(String femme : topPreferenceDeCetHomme){
				String leMarie = coupleEngageInverse.get(femme);
				List<String> preferenceDeCetteFemme = tmpPreferenceDesFemmes.get(femme);
				if(preferenceDeCetteFemme.indexOf(leMarie) >
				preferenceDeCetteFemme.indexOf(couple.getKey())){
					System.out.printf("%s aime %s plus que %s et %s"
							+ " aime %s plus que son partenaire actuel\n",
							couple.getKey(), femme, couple.getValue(),
							femme, couple.getKey());
					return false;
				}
			}
		}
		return true;

	}
	
	/**
	 * @param preferenceDesFemmes
	 * @param hommesLibre
	 * @param coupleEngage
	 * @param cetHomme
	 * @param femmePrefereDeCetHomme
	 * @param aAjouterDansCoupleEngage
	 */
	private void choisirPrefereDeLaFemmeEntreDeuxHommes(Map<String, ArrayList<List<String>>> preferenceDesFemmes,
			List<String> hommesLibre, Map<String, String> coupleEngage, String cetHomme, String femmePrefereDeCetHomme,
			Map<String, String> aAjouterDansCoupleEngage) {
		
		Iterator<Entry<String, String>> iter = coupleEngage.entrySet().iterator();
		Entry<String, String> couple;
		while (iter.hasNext()){
			
			couple = iter.next();
			String autreHomme = couple.getKey();
			String laFemmeAdultere = couple.getValue();
			int placeDeHommePourCetteFemme = -1;
			int cleDeAutreHommeDansPreferenceDeLaFemme = -1;
			boolean cle1Trouve = false;
			boolean cle2Trouve = false;
			if(!autreHomme.contentEquals(cetHomme) && laFemmeAdultere.contentEquals(femmePrefereDeCetHomme)){
				for(List<String> pref : preferenceDesFemmes.get(laFemmeAdultere)) {
					
					if (pref.contains(cetHomme)){
						placeDeHommePourCetteFemme = preferenceDesFemmes.get(laFemmeAdultere).indexOf(pref);
						cle1Trouve = true;
					}
					if (pref.contains(autreHomme)){
						cleDeAutreHommeDansPreferenceDeLaFemme = preferenceDesFemmes.get(laFemmeAdultere).indexOf(pref);
						cle2Trouve = true;
					}
					// permet de ne pas continuer a bouclé sur l'on a trouvé les 2 clés
					if(cle1Trouve && cle2Trouve)
						break;
					
				} 
				if(cleDeAutreHommeDansPreferenceDeLaFemme > placeDeHommePourCetteFemme){
//				La femme prefere son nouveau copain
					aAjouterDansCoupleEngage.put(cetHomme, laFemmeAdultere);
					iter.remove();
					hommesLibre.add(autreHomme);
				}
				else{
//					La femme prefere son ancien copain
					coupleEngage.remove(cetHomme, laFemmeAdultere);
					hommesLibre.add(cetHomme);
				}
			}
		}
	}

	/**
	 * @param coupleEngage
	 * @param aAjouterDansCoupleEngage
	 */
	private void ajouterCouple(Map<String, String> coupleEngage, Map<String, String> aAjouterDansCoupleEngage) {
		for(Entry<String,String> coupleaAjoute : aAjouterDansCoupleEngage.entrySet()){
			String cle = coupleaAjoute.getKey();
			String val = coupleaAjoute.getValue();
			coupleEngage.put(cle, val);
		}
	}

	@Override
	public void affiche(Map<String, ArrayList<List<String>>> preferenceDesHommes,
			Map<String, ArrayList<List<String>>> preferenceDesFemmes, List<String> hommes, List<String> femmes) {
		 Map<String, String> matches = trouveUneSolution(preferenceDesHommes, preferenceDesFemmes, hommes, femmes);
		 
	        for(Map.Entry<String, String> couple:matches.entrySet()){
	            System.out.println(
	                    couple.getKey() + " est engagé à " + couple.getValue());
	        }
	        if(verifieLaSolution(preferenceDesHommes, preferenceDesFemmes, hommes, femmes, matches)){
	        	
	        	System.out.println("Les mariages sont stables");
	        }else{
	            System.out.println("Les mariages sont unstables");
	        }
	        
	        String tmp = matches.get(hommes.get(0));
	        matches.put(hommes.get(0), matches.get(hommes.get(1)));
	        matches.put(hommes.get(1), tmp);
	        System.out.println(
	                hommes.get(0) +" et " + hommes.get(1) + " ont echangés de partenaires");
	        if(verifieLaSolution(preferenceDesHommes, preferenceDesFemmes, hommes, femmes, matches)){
	            System.out.println("Les mariages sont stables");
	        }else{
	            System.out.println("Les mariages sont unstables");
	        }
//		System.out.println("------- Preferences des Hommes --------");
//		for (Entry<String, ArrayList<List<String>>> entry : preferenceDesHommes.entrySet()) {
//			String key = entry.getKey();
//			System.out.println("preference de : " +key);
//			ArrayList<List<String>> myArray = entry.getValue();
//
//
//			for (int i = 0; i < myArray.size(); i++){
//				System.out.println( myArray.indexOf(myArray.get(i)) + " => " + myArray.get(i).get(0));	              
//			}
//			System.out.println();
//		}
//
//		System.out.println("------- Preferences des Femmes --------");
//		for (Entry<String, ArrayList<List<String>>> entry : preferenceDesFemmes.entrySet()) {
//			String key = entry.getKey();
//			System.out.println(key);
//			ArrayList<List<String>> myArray = entry.getValue();
//
//
//			for (int i = 0; i < myArray.size(); i++){
//				System.out.println( myArray.indexOf(myArray.get(i)) + " => " + myArray.get(i).get(0));	              
//			}
//			System.out.println();
//			
//		}
	}

	public Map<String, List<String>> convert(Map<String, ArrayList<List<String>>> map){
		
		Map<String, List<String>> tmp = new HashMap<String, List<String>>();
		
		for(Entry<String, ArrayList<List<String>>> entrymap : map.entrySet()){
			String cle = entrymap.getKey();
			List<String> tmplist = new ArrayList<String>();
			for(List<String> list : entrymap.getValue()){
				String str = list.get(0);
				tmplist.add(str);
			}
			tmp.put(cle, tmplist);
		}
		return tmp;
		
	}
	
	

	
}
