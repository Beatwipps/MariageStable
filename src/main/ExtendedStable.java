package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ExtendedStable implements StableStrategie {


	
	

	@Override
	public Map<String, String> trouveUnCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		
		Map<String, String> coupleEngage = new HashMap<String, String>();
		Map<String, List<String>> tmpPreferenceDes_A = new HashMap<String, List<String>>();
		Map<String, List<String>> tmpPreferenceDes_B = new HashMap<String, List<String>>();
		List<String> tmpListeDes_A = new ArrayList<String>(listeDes_A);
		List<String> tmpListeDes_B = new ArrayList<String>(listeDes_B);
		
		tmpPreferenceDes_A = convert(preferenceDes_A);	
		tmpPreferenceDes_B = convert(preferenceDes_B);
//		System.out.println("Conversion : "+ tmpPreferenceDes_A);
		
		while(!tmpListeDes_A.isEmpty()){
			String ce_A = tmpListeDes_A.remove(0);  
			String B_PrefereDe_A = tmpPreferenceDes_A.get(ce_A).remove(0);	
			//coupleEngage.put(B_PrefereDe_A, ce_A);
			if(tmpListeDes_B.contains(B_PrefereDe_A)){
				//String autre_A = coupleEngage.get(B_PrefereDe_A);
				coupleEngage.put(B_PrefereDe_A, ce_A);
				tmpListeDes_B.remove(B_PrefereDe_A);
			}else{
				String autre_A = coupleEngage.get(B_PrefereDe_A);
				tmpListeDes_A.add(autre_A);
				coupleEngage.put(B_PrefereDe_A, ce_A);
				
			}
				if(tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(ce_A)!= -1){
				Iterator<String> iter = tmpPreferenceDes_B.get(B_PrefereDe_A).subList(
						tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(ce_A), 
						tmpPreferenceDes_B.get(B_PrefereDe_A).size()).iterator();
	
				while (iter.hasNext()) {
				    	iter.next();
				        iter.remove();
				}
			}
		}
		System.out.println(coupleEngage);
//		System.out.println("Liste des Femmes: "+tmpPreferenceDes_B);
//		System.out.println("Liste des Hommes: "+tmpPreferenceDes_A);
		return coupleEngage;
	}

	
	
	
	
	@Override
	public boolean verifieLeCouplage(Map<String, ArrayList<List<String>>> preferencesDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B,
			Map<String, String> coupleEngage) {
		
		StableStrategie basic = new BasicStable();
		return basic.verifieLeCouplage(preferencesDes_A, preferenceDes_B, listeDes_A, listeDes_B, coupleEngage);
		
	}

	@Override
	public void afficheLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> ListeDes_B) {
		
		System.out.println("------- Preferences des Hommes --------");
		for (Entry<String, ArrayList<List<String>>> entry : preferenceDes_A.entrySet()) {
			String key = entry.getKey();
			System.out.println(key+ ": ");
			ArrayList<List<String>> myList = entry.getValue();


			for (List<String> entry1 : myList) {
				System.out.println(entry1);	              
			}
			System.out.println();
		}

		System.out.println("------- Preferences des Femmes --------");
		for (Entry<String, ArrayList<List<String>>> entry : preferenceDes_B.entrySet()) {
			String key = entry.getKey();
			System.out.print(key+ ": ");
			ArrayList<List<String>> myList = entry.getValue();


			for (List<String> entry1 : myList) {
				System.out.println(entry1);	              
			}
			System.out.println();
		}
		
	}




	@Override
	public Map<String, List<String>> convert(Map<String, ArrayList<List<String>>> map) {
	
		Map<String, List<String>> tmp = new HashMap<String, List<String>>();
		for(Entry<String, ArrayList<List<String>>> entrymap : map.entrySet()){
			String cle = entrymap.getKey();
			List<String> tmplist = new ArrayList<String>();
			for(List<String> list : entrymap.getValue()){
				if(list.size()>1){
					//Collections.shuffle(list);
					for(String s : list)
						tmplist.add(s);
				}else{
					tmplist.add(list.get(0));
				}
			}
			tmp.put(cle, tmplist);
		}
		return tmp;
		}
}
