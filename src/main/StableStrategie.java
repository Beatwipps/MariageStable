package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StableStrategie {

		public Map<String, String> trouveUnCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A, 
				Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B);
		
		public boolean verifieLeCouplage(Map<String, ArrayList<List<String>>> preferencesDes_A, 
				Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> listeDes_B,
				Map<String, String> coupleEngage);
		
		public void afficheLeCouplage(Map<String, ArrayList<List<String>>> preferenceDes_A,
				Map<String, ArrayList<List<String>>> preferenceDes_B, List<String> listeDes_A, List<String> ListeDes_B);
}
