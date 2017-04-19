package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class StrongStable implements StableStrategie{

	@Override
	public Map<String, String> trouveUnCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		
		Multimap<String,String> coupleEngage = HashMultimap.create();
		//Map<String, String> coupleEngage = new HashMap<String, String>();
		Map<String, ArrayList<List<String>>> tmpPreferenceDes_A = new HashMap<String, ArrayList<List<String>>>();
		Map<String, ArrayList<List<String>>> tmpPreferenceDes_B = new HashMap<String, ArrayList<List<String>>>();
		List<String> tmpListeDes_A = new ArrayList<String>(listeDes_A);
		List<String> tmpListeDes_B = new ArrayList<String>(listeDes_B);
		tmpPreferenceDes_A = copieMap(preferenceDes_A);
		tmpPreferenceDes_B = copieMap(preferenceDes_B);
	
		
		do{
			while(!tmpListeDes_A.isEmpty()){
				String ce_A = tmpListeDes_A.remove(0);  
				//				On prend la premiere liste de femmes dans la preference de cet homme
				//				List<String> B_PreferesDe_A = tmpPreferenceDes_A.get(ce_A).remove(0);	
				//				On prend la premiere femme dans la liste ci-dessus (A FAIRE : un random pour choisir une autre femme)
				for(String B_PrefereDe_A : tmpPreferenceDes_A.get(ce_A).get(0)){
					coupleEngage.put(B_PrefereDe_A, ce_A);
					if(tmpListeDes_B.contains(B_PrefereDe_A))
						tmpListeDes_B.remove(tmpListeDes_B.indexOf(B_PrefereDe_A));
					
//					Indice de ce_A dans la preference de B_PrefereDe_A
					int indexOf_Ce_A = 0;
					for(List<String> suc : tmpPreferenceDes_B.get(B_PrefereDe_A)){
						if(suc.contains(ce_A)){
							indexOf_Ce_A = tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(suc);
							//System.out.println(indexOf_Ce_A);
						}
					}
					for(List<String> successeur : tmpPreferenceDes_B.get(B_PrefereDe_A).subList(
							indexOf_Ce_A+1, 
							tmpPreferenceDes_B.get(B_PrefereDe_A).size()
							)){

//						System.out.println("subList " +tmpPreferenceDes_B.get(B_PrefereDe_A).subList(
//								indexOf_Ce_A+1, 
//								tmpPreferenceDes_B.get(B_PrefereDe_A).size()
//								));
//
//						System.out.println("ind " + (indexOf_Ce_A+1) );
//						System.out.println("size " +tmpPreferenceDes_B.get(B_PrefereDe_A).size());
						for(String s : successeur){
							if(coupleEngage.get(B_PrefereDe_A).contains(s)){
								//tmpListeDes_B.add(B_PrefereDe_A);
								tmpListeDes_A.add(s);
							}
							coupleEngage.remove(B_PrefereDe_A, s);
						}
						System.out.println(coupleEngage);
						//					System.out.println(tmpPreferenceDes_B.get(B_PrefereDe_A).indexOf(tmpPreferenceDes_B.get(B_PrefereDe_A).contains(ce_A)));
					}
				}
			}
		}
		while(tmpListeDes_A.isEmpty() || (tmpListeDes_A.isEmpty() && tmpListeDes_B.isEmpty()));




		return null;
	}

	@Override
	public boolean verifieLeCouplage(Map<String, ArrayList<List<String>>> preferencesDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B,
			Map<String, String> coupleEngage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void afficheLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		// TODO Auto-generated method stub
		
	}
	
	public Map<String, ArrayList<List<String>>> copieMap(Map<String, ArrayList<List<String>>> map){
		
		Map<String, ArrayList<List<String>>> newMap = new HashMap<String, ArrayList<List<String>>>();
		
		for(Entry<String, ArrayList<List<String>>> entrymap : map.entrySet()){
			String cle = entrymap.getKey();
			ArrayList<List<String>> tmpListOfList = new ArrayList<List<String>>();
			
			for(List<String> list : entrymap.getValue()){
				
				List<String> tmpList = new ArrayList<String>();
				if(list.size()>1){
					//Collections.shuffle(list);
					for(String s : list)
						tmpList.add(s);
				}else{
					tmpList.add(list.get(0));
				}
				tmpListOfList.add(tmpList);
			}
			newMap.put(cle, tmpListOfList);
			
		}
		return newMap; 
	}

	
	
}
