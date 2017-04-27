package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;


public class StrongStable implements StableStrategie{

	@Override
	public Map<String, String> trouveUnCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
			Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B) {
		
		/*
		 * 1ère partie : Générer les couples possibles 
		 * 2ème partie : Trouver un couplage 
		 * 3ème partie : Utiliser le BFS sur un noeud u libre puis parcourir tout les noeuds jusqu'à trouver un v libre
		 * 4ème partie : Utiliser le DFS pour augmenter le chemin de u vers v 
		 * Recommencer jusqu'à qu'il n'y est plus de chemins augmentant
		 */
		
		Multimap<String,String> coupleEngage = HashMultimap.create(); // Map (v -> u)
		Map<String, ArrayList<List<String>>> tmpPreferenceDes_A = new HashMap<String, ArrayList<List<String>>>();
		Map<String, ArrayList<List<String>>> tmpPreferenceDes_B = new HashMap<String, ArrayList<List<String>>>();
		List<String> tmpListeDes_A1 = new ArrayList<String>(listeDes_A);
		List<String> tmpListeDes_B1 = new ArrayList<String>(listeDes_B);
		tmpPreferenceDes_A = copieMap(preferenceDes_A);
		tmpPreferenceDes_B = copieMap(preferenceDes_B);


		//		do{
		while(!tmpListeDes_A1.isEmpty()){
			String ce_A = tmpListeDes_A1.remove(0);  

			for(String B_PrefereDe_A : tmpPreferenceDes_A.get(ce_A).get(0)){ // On prend la premiere femme dans la liste ci-dessus (A FAIRE : un random pour choisir une autre femme)
				coupleEngage.put(B_PrefereDe_A, ce_A);
				if(tmpListeDes_B1.contains(B_PrefereDe_A))
					tmpListeDes_B1.remove(tmpListeDes_B1.indexOf(B_PrefereDe_A));


				int indexOf_Ce_A = 0; //	Indice de ce_A dans la preference de B_PrefereDe_A
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
					
					for(String s : successeur){
						if(coupleEngage.get(B_PrefereDe_A).contains(s)){
//								tmpListeDes_B.add(B_PrefereDe_A);
							tmpListeDes_A1.add(s);
						}
						coupleEngage.remove(B_PrefereDe_A, s);
					}

				}
			}
		}
//		Queue<String> queue = new ArrayDeque<>();
//		HashSet<String> marque_A = new HashSet<>();

	
		Multimap<String,String> coupleEngageInverse = invKey_Values(coupleEngage); // Map (u -> v)	
		Map<String, String> tmpCouplage = new HashMap<String, String>(); // Couplage temporaire
		List<String> uLibre = new ArrayList<String>(listeDes_A); // Liste des u libres
		List<String> vLibre = new ArrayList<String>(listeDes_B); // Liste des v libres
		for(String u : coupleEngageInverse.keySet()){
			Collection<String> asso_A = coupleEngageInverse.get(u);
			for(String v : asso_A){
				if(vLibre.contains(v)){
					tmpCouplage.put(u, v);
					vLibre.remove(vLibre.indexOf(v));
					uLibre.remove(u);
					break;
				}		
			}

		}

		System.out.println(coupleEngage);
		System.out.println(coupleEngageInverse);
		System.out.println(tmpCouplage);
		System.out.println(uLibre);
		System.out.println(vLibre);

		BFS(tmpCouplage, coupleEngageInverse, coupleEngage, listeDes_A, uLibre, vLibre);
		//		}
		//		while(tmpListeDes_A1.isEmpty() || (tmpListeDes_A1.isEmpty() && tmpListeDes_B1.isEmpty()));
		//		


		return null;
	}

	public static void BFS(Map<String, String> couplage, Multimap<String,String> mapA, Multimap<String,String> mapB, List<String> listeDes_A, List<String> start, List<String> end) {
		Queue<String> queue = new ArrayDeque<>();
		//	    Queue<String> arbre = new ArrayDeque<>();
		HashSet<String> seen = new HashSet<>();

		for(String s : start)//		Ajouter tout les noeuds u libre dans la file
			queue.add(s); 
		//	    arbre.add(start);
		while(0 != queue.size()){
			String vertex = queue.poll();
			if(listeDes_A.contains(vertex)){ // Si vertex appartient à u (aux hommes)
				for(String ls : mapA.get(vertex)){
					if(!couplage.containsKey(vertex))
						queue.add(ls); 
				}
				seen.add(vertex);
			}
			else{ //	        	 Si vertex appartient à v (aux femmes)
				if(end.contains(vertex)){ // Si le vertex courant est dans la liste des v libres on s'arrete et on commence le dfs
					// DFS
				}
				for(String ls : mapB.get(vertex)){
					if(couplage.containsKey(ls))
						if(couplage.get(ls).contains(vertex))
							queue.add(ls); // Ajouter tout les voisins de vertex à la file
				}
				seen.add(vertex);
			}	
		}
		//	    }
		System.out.println("Seen "+ seen);
		System.out.println("Queue " + queue);

	}

	
	public Multimap<String,String> invKey_Values (Multimap<String,String> map){
		
		Multimap<String, String> tmpMap = HashMultimap.create();
		
		for(String key : map.keySet()){
			for(String value : map.get(key)){
				tmpMap.put(value, key);
			}
		}
		
		return tmpMap;
		
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