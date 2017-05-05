package main;



public class Client {

	public static void main(String[] args) {


//		StableStrategie Base = new BasicStable();
		StableStrategie Ext = new ExtendedStable();
//		StableStrategie Str = new StrongStable();
		
		LireFichier A_Ext = new LireFichier("./src/main/AffectationAvecIndifference_A.txt", Ext);
		LireFichier B_Ext = new LireFichier("./src/main/AffectationAvecIndifference_B.txt", Ext);
//		LireFichier A_Base = new LireFichier("./src/main/AffectationBasic_A.txt", Base);
//		LireFichier B_Base = new LireFichier("./src/main/AffectationBasic_B.txt", Base);
//		LireFichier A_Str = new LireFichier("./src/main/AffectationAvecIndifference_A.txt", Str);
//		LireFichier B_Str = new LireFichier("./src/main/AffectationAvecIndifference_B.txt", Str);
		
		Instance I1 = new Instance(Ext, A_Ext.getMap(), B_Ext.getMap(), A_Ext.getList(), B_Ext.getList());
//		Instance I2 = new Instance(Base, A_Base.getMap(), B_Base.getMap(), A_Base.getList(), B_Base.getList());
//		Instance I3 = new Instance(Str, A_Str.getMap(), B_Str.getMap(), A_Str.getList(), B_Str.getList());
		
		I1.executeStableStrategie();
//		System.out.println("#########################################");
//		I2.executeStableStrategie();
		
//		I3.executeStableStrategie();
	}

}
