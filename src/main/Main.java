package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		StableStrategie choix = null;
		
		System.out.println("Choisissez la strategie :");
		System.out.println("(1) pour le basique");
		System.out.println("(2) pour le weakly");
		System.out.println("(3) pour le strong");
		
		Scanner scanIn = new Scanner(System.in);
	    int numStrat = scanIn.nextInt();    
	    switch(numStrat){
			 case 1:
				 StableStrategie Base = new BasicStable();
				 choix = Base;
		     break;
		     case 2:
		 		StableStrategie Ext = new WeaklyStable();
		 		choix = Ext;
		     break;
		     case 3:
		    	 StableStrategie Str = new StrongStable();
		    	 choix = Str;
		     break;
		     default:
		         System.out.println("La valeur rentré n'est pas juste.");
		     break;
		 }
	    System.out.println("Voulez-vous utiliser le jeu de donnée du repertoire JeuDeDonnées ? (1) : Oui (2) : Non");
	    int numJDD = scanIn.nextInt();
	    LireFichier JDD_A = null;
	    LireFichier JDD_B = null;
	    
	    switch(numJDD){
	    	case 1 :  
	    		if(choix instanceof BasicStable){
	    			JDD_A = new LireFichier("./src//main/affBasicA.txt", choix);
	    			JDD_B = new LireFichier("./src//main/affBasicB.txt", choix);
	    			
	    		}
	    		else{
	    			JDD_A = new LireFichier("./src//main/AffectationAvecIndifference_A.txt", choix);
	    			JDD_B = new LireFichier("./src//main/AffectationAvecIndifference_B.txt", choix);
	    		}
	    		break;
	    	case 2 : 
	    		scanIn.nextLine();

	    		System.out.print("Entrez le chemin du fichier .txt correspondant aux A : ");
    		    String cheminFichierTexteA = scanIn.nextLine();
    		    JDD_A = new LireFichier(cheminFichierTexteA, choix);
    		    while(JDD_A.isEstLisible() == false){
    		    	System.out.println("Veuillez écrire un fichier valide");
    		    	System.out.println("De nouveau, entrez le chemin du fichier .txt correspondant aux A : ");
    		    	cheminFichierTexteA = scanIn.nextLine();
    		    	JDD_A = new LireFichier(cheminFichierTexteA, choix);
    		    }
    		    	
    		    System.out.println();
    			
    		    System.out.print("Entrez le chemin du fichier .txt correspondant aux B : ");
    		    String cheminFichierTexteB = scanIn.nextLine();
    		    JDD_B = new LireFichier(cheminFichierTexteB, choix);
    		    while(JDD_B.isEstLisible() == false){
    		    	System.out.println("Veuillez écrire un fichier valide");
    		    	System.out.println("De nouveau, entrez le chemin du fichier .txt correspondant aux B : ");
    		    	cheminFichierTexteB = scanIn.nextLine();
    		    	JDD_B = new LireFichier(cheminFichierTexteA, choix);
    		    }
    			scanIn.close();
    			break;
	    	default:
		         System.out.println("La valeur rentré n'est pas juste.");
		     break;
	    }
	   
	    System.out.println("Création d'une nouvelle instance de " + choix.getClass());
		Instance I = new Instance(choix, JDD_A.getMap(), JDD_B.getMap(), JDD_A.getList(), JDD_B.getList());
		
		System.out.println("Exécution de la stratégie en cours ..");
		I.executeStableStrategie();

	}

}
