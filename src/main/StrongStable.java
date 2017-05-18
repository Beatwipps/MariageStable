package main;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import sun.net.ftp.FtpClient.TransferType;


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
		Map<String, ArrayList<List<String>>> toutesLesPreferenceDes_A = new HashMap<String, ArrayList<List<String>>>();
		Map<String, ArrayList<List<String>>> toutesLesPreferenceDes_B = new HashMap<String, ArrayList<List<String>>>();
		List<String> tmpListeDes_A1 = new ArrayList<String>(listeDes_A);
		List<String> tmpListeDes_B1 = new ArrayList<String>(listeDes_B);
		toutesLesPreferenceDes_A = copieMap(preferenceDes_A);
		toutesLesPreferenceDes_B = copieMap(preferenceDes_B);


		//		do{
		while(!tmpListeDes_A1.isEmpty()){
			String ce_A = tmpListeDes_A1.remove(0);  

			for(String B_PrefereDe_A : toutesLesPreferenceDes_A.get(ce_A).get(0)){ // On prend la premiere femme dans la liste ci-dessus (A FAIRE : un random pour choisir une autre femme)
				coupleEngage.put(B_PrefereDe_A, ce_A);
				if(tmpListeDes_B1.contains(B_PrefereDe_A))
					tmpListeDes_B1.remove(tmpListeDes_B1.indexOf(B_PrefereDe_A));


				int indexOf_Ce_A = 0; //	Indice de ce_A dans la preference de B_PrefereDe_A
				for(List<String> suc : toutesLesPreferenceDes_B.get(B_PrefereDe_A)){
					if(suc.contains(ce_A)){
						indexOf_Ce_A = toutesLesPreferenceDes_B.get(B_PrefereDe_A).indexOf(suc);
						//System.out.println(indexOf_Ce_A);
					}
				}
				for(List<String> successeur : toutesLesPreferenceDes_B.get(B_PrefereDe_A).subList(
						indexOf_Ce_A+1, 
						toutesLesPreferenceDes_B.get(B_PrefereDe_A).size()
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

//		System.out.println(coupleEngage);
//		System.out.println(coupleEngageInverse);
//		System.out.println(tmpCouplage);
//		System.out.println(uLibre);
//		System.out.println(vLibre);
		
		Multimap<String,String> testa = HashMultimap.create(); // Map (u -> v)	
		Multimap<String,String> testabis = HashMultimap.create();
		Map<String, String> testb = new HashMap<String, String>();
		List<String> listeDes_Abs = new ArrayList<String>();
		List<String> libreu = new ArrayList<String>();
		List<String> librev = new ArrayList<String>();
		
		libreu.add("L");
		libreu.add("R");
		
		librev.add("4");
		librev.add("5");
		
		listeDes_Abs.add("B");
		listeDes_Abs.add("E");
		listeDes_Abs.add("J");
		listeDes_Abs.add("L");
		listeDes_Abs.add("T");
		listeDes_Abs.add("A");
		listeDes_Abs.add("R");
		
		testa.put("B", "1");
		testa.put("B", "4");
		testa.put("E", "3");
		testa.put("E", "6");
		testa.put("E", "7");
		testa.put("J", "2");
		testa.put("J", "5");
		testa.put("J", "4");
		testa.put("L", "2");
		testa.put("L", "7");
		testa.put("T", "5");
		testa.put("T", "6");
		testa.put("T", "7");
		testa.put("A", "3");
		testa.put("A", "6");
		testa.put("R", "6");
		testa.put("R", "7");
		
		testb.put("B", "1");
		testb.put("E", "7");
		testb.put("J", "2");
		testb.put("T", "6");
		testb.put("A", "3");
		
		testabis.put("1", "B");
		testabis.put("2", "J");
		testabis.put("2", "L");
		testabis.put("3", "E");
		testabis.put("3", "A");
		testabis.put("4", "B");
		testabis.put("4", "J");
		testabis.put("5", "J");
		testabis.put("5", "T");
		testabis.put("6", "E");
		testabis.put("6", "T");
		testabis.put("6", "A");
		testabis.put("6", "R");
		testabis.put("7", "E");
		testabis.put("7", "L");
		testabis.put("7", "T");
		testabis.put("7", "R");
		
		
		
		BFS(testb, testa, testabis, listeDes_Abs, libreu, librev);
		//		}
		//		while(tmpListeDes_A1.isEmpty() || (tmpListeDes_A1.isEmpty() && tmpListeDes_B1.isEmpty()));
		//		


		return null;
	}

	public static void BFS(Map<String, String> couplage, Multimap<String,String> mapA, Multimap<String,String> mapB, List<String> listeDes_A, List<String> start, List<String> end) {
		Queue<String> queue = new ArrayDeque<>();
		//	    Queue<String> arbre = new ArrayDeque<>();
		HashSet<String> seen = new HashSet<>();
		List<String> cheminAugmentant = new ArrayList<String>();
		List<List<String>> toutsLesCheminsAugmentants = new ArrayList<List<String>>();
		Multimap<String,String> coupleEngageInverseTMP = HashMultimap.create(); // la map u-> v pour le DFS
		Map<String, String> tmpCouplageTMP = new HashMap<String, String>(); // Couplage temporaire, map v -> u pour le DFS
		for(String s : start)//	Ajouter tout les noeuds u libre dans la file
			queue.add(s); 
		//	    arbre.add(start);
		while(0 != queue.size()){
			String vertex = queue.poll();
			if(listeDes_A.contains(vertex)){ // Si vertex appartient à u (aux hommes)
				
				for(String ls : mapA.get(vertex)){
					if(!couplage.containsKey(vertex)){
						queue.add(ls);
						coupleEngageInverseTMP.put(vertex, ls);
					}
					else{
						if(!couplage.get(vertex).equals(ls) && !seen.contains(ls)){
							queue.add(ls);
							coupleEngageInverseTMP.put(vertex, ls);
						}
					}
				}
				seen.add(vertex);
			}
			else{ //	        	 Si vertex appartient à v (aux femmes)
				if(end.contains(vertex)){
					System.out.println("map v-> u " +tmpCouplageTMP);
					System.out.println("map u -> v " +coupleEngageInverseTMP);
				
//					for(String s : start){// Si le vertex courant est dans la liste des v libres on s'arrete et on commence le dfs
						DFS(tmpCouplageTMP, coupleEngageInverseTMP, cheminAugmentant, toutsLesCheminsAugmentants, start.get(0), vertex);
						System.out.println("val "+ toutsLesCheminsAugmentants);
						if(!toutsLesCheminsAugmentants.isEmpty()){
							for(List<String> ls : toutsLesCheminsAugmentants)
								for(String st : ls)
									
							break;
						}
						
					System.out.println("DFS " +cheminAugmentant);
//					}
				}
				for(String ls : mapB.get(vertex)){
					if(couplage.containsKey(ls))
						if(couplage.get(ls).contains(vertex) && !seen.contains(vertex)){
							queue.add(ls); // Ajouter tout les voisins de vertex à la file
							tmpCouplageTMP.put(vertex, ls);
						}
				}
				seen.add(vertex);
				
			}	
		}
		//	    }
		System.out.println("Seen "+ seen);
		System.out.println("Queue " + queue);
		System.out.println(coupleEngageInverseTMP);
		System.out.println(tmpCouplageTMP);
	}

	public static void list2list(List<String> listA, List<String> listB){
		for(String s : listA)
			listB.add(s);
	}
	
	
	public static void DFS(Map<String, String> mapB, Multimap<String,String> mapA, List<String> chemin, List<List<String>> toutsLesChemins,  String start, String end) {
	
		List<String> list = new ArrayList<String>();
		chemin.add(start);
		if(start.contentEquals(end)){
			System.out.println("Trouvé");
			if(list.isEmpty()){
				System.out.println(chemin);
				toutsLesChemins.add(chemin);
				list2list(chemin, list);
			}
		}
		else{
			if(mapA.containsKey(start)){
				for(String s : mapA.get(start)){
					DFS(mapB, mapA, chemin, toutsLesChemins, s, end);
				}
			}
			if(mapB.containsKey(start)){
					DFS(mapB, mapA, chemin, toutsLesChemins, mapB.get(start), end);
			}
		}
	}
		

	
	public Multimap<String,String> invKey_Values(Multimap<String,String> map){
		
		Multimap<String, String> tmpMap = HashMultimap.create();
		
		for(String key : map.keySet()){
			for(String value : map.get(key)){
				tmpMap.put(value, key);
			}
		}
		
		return tmpMap;
		
	}
	

	
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
