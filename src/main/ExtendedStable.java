package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExtendedStable implements StableStrategie, Convertable {


	
	

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
			
			if(tmpListeDes_B.contains(B_PrefereDe_A)){
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
						tmpPreferenceDes_B.get(B_PrefereDe_A).size())
						.iterator();
				while (iter.hasNext()) {
					String current = iter.next();
				    	System.out.println(current);
				    	if(tmpPreferenceDes_A.get(current).indexOf(B_PrefereDe_A) != -1)
							tmpPreferenceDes_A.get(current).remove(tmpPreferenceDes_A.get(current).indexOf(B_PrefereDe_A));
				        iter.remove();
				}
			}
			
		}
		afficheLeCouplag(coupleEngage);
		verifieLeCouplageWeakly(tmpPreferenceDes_A, tmpPreferenceDes_B, tmpListeDes_A, tmpListeDes_B, coupleEngage);
		
		return coupleEngage;
	}

	
	
	
	

	public boolean verifieLeCouplageWeakly(Map<String, List<String>> preferenceDes_A,
			Map<String, List<String>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B,
			Map<String, String> coupleEngage) {
		
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
			for(String b : preferenceDes_A.get(ce_A)){
				if(preferenceDes_A.get(ce_A).indexOf(b) < preferenceDes_A.get(ce_A).indexOf(couple.getKey())){
					System.out.printf("%s prefere %s à %s\n", ce_A, b, couple.getKey());
					String a = coupleEngage.get(b);
					if(preferenceDes_B.get(b).indexOf(ce_A) < preferenceDes_B.get(b).indexOf(a)){
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

	public void afficheLeCouplag(Map<String, String> coupleEngage) {
		
		for(Entry<String, String> couple : coupleEngage.entrySet()){
			System.out.println("| "+ couple.getKey() + " ♥ " + couple.getValue());
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
					Collections.shuffle(list);
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





	@Override
	public void afficheLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		// TODO Auto-generated method stub
		
	}
}
