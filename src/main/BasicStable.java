/**
 * 
 */
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author beatwipps
 *
 */
public class BasicStable implements StableStrategie, Convertable{
	
	/* 
	 * Les A proposent aux B 
	 * 
	 * 
	 * Renvoie une map de couple (B, A)
	 */
	@Override
	public Map<String, String> trouveUnCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		
		Map<String, List<String>> tmpPreferenceDes_A = new HashMap<String, List<String>>();
		tmpPreferenceDes_A = convert(preferenceDes_A);
		Map<String, List<String>> tmpPreferenceDes_B = new HashMap<String, List<String>>();
		tmpPreferenceDes_B = convert(preferenceDes_B);
		System.out.println("Conversion : "+ tmpPreferenceDes_B);
		List<String> tmpListeDes_A = new ArrayList<String>(listeDes_A);
		List<String> tmpListeDes_B = new ArrayList<String>(listeDes_B);
		Map<String, String> coupleEngage = new HashMap<String, String>();
		
		while(!tmpListeDes_A.isEmpty()){
			String ce_A = tmpListeDes_A.remove(0);  
			String B_PrefereDe_A = tmpPreferenceDes_A.get(ce_A).remove(0);	
			// Si le prefere de A est libre
			if(tmpListeDes_B.contains(B_PrefereDe_A)){
				coupleEngage.put(B_PrefereDe_A, ce_A);
				tmpListeDes_B.remove(B_PrefereDe_A);
			}
			else{
				String autre_A = coupleEngage.get(B_PrefereDe_A);
				if(tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(ce_A) < 
						tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(autre_A)){
					coupleEngage.put(B_PrefereDe_A, ce_A);
					tmpListeDes_A.add(autre_A);
				}
				else{
					tmpListeDes_A.add(ce_A);
				}
			}
		}
		return coupleEngage;
	}

	/*
	 * Verifie le couplage, 
	 * Renvoie true si le couplage est stable, false sinon
	 * 
	 */
	
	@Override
	public boolean verifieLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B,
			Map<String, String> coupleEngage) {
		
		Map<String, List<String>> tmpPreferenceDes_A = new HashMap<String, List<String>>();
		tmpPreferenceDes_A = convert(preferenceDes_A);
		Map<String, List<String>> tmpPreferenceDes_B = new HashMap<String, List<String>>();
		tmpPreferenceDes_B = convert(preferenceDes_B);
		Map<String, String> coupleEngageInverse = new HashMap<String, String>();
		for(Map.Entry<String, String> couple: coupleEngage.entrySet()){
			coupleEngageInverse.put(couple.getValue(), couple.getKey());
		}
		
		
		if(!coupleEngage.keySet().containsAll(listeDes_B)){
			return false;
		}

		if(!coupleEngage.values().containsAll(listeDes_A)){
			return false;
		}
		
		for(Entry<String, String> couple : coupleEngage.entrySet()){
			String ce_A = couple.getValue();
			for(String b : tmpPreferenceDes_A.get(ce_A)){
				if(tmpPreferenceDes_A.get(ce_A).indexOf(b) < tmpPreferenceDes_A.get(ce_A).indexOf(couple.getKey())){
					System.out.printf("%s prefere %s à %s\n", ce_A, b, couple.getKey());
					String a = coupleEngage.get(b);
					if(tmpPreferenceDes_B.get(b).indexOf(ce_A) < tmpPreferenceDes_B.get(b).indexOf(a)){
						System.out.printf("%s prefere %s à %s\n", b, ce_A, a);
						System.out.println("Le couplage n'est pas stable");
						return false;
					}else{
						System.out.printf("Par contre %s prefere %s, plutôt que %s", b, coupleEngage.get(b), ce_A);
						System.out.println();
					}
				}
			}
		}
		System.out.println("\nLe couplage est stable");
		return true;
	}


	
	@Override
	public void afficheLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		 Map<String, String> matches = trouveUnCouplage(preferenceDes_A, preferenceDes_B, listeDes_A, listeDes_B);
		 
	        for(Map.Entry<String, String> couple:matches.entrySet()){
	            System.out.println(couple.getValue() + " est engagé à " + couple.getKey());
	        }
	        System.out.println();
	}

	
	/*
	* Convertie une map de type Map<String, ArrayList<List<String>>> vers une map de type
	*	Map<String, List<String>
	*
	*/
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
