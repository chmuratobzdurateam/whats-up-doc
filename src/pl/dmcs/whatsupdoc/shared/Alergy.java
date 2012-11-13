package pl.dmcs.whatsupdoc.shared;

public enum Alergy {
	DUST("kurz"), HAIR("sier��"), STRAWBERRIES("truskawki"), LIME_LEAVES("li�cie lipy");
	
	private final String alergyString;
	
	private Alergy(final String alergyString){
		this.alergyString = alergyString;
	}
	
	public String toString(){
		return alergyString;
	}
	
	public static Alergy getAlergy(String alergyString){
		for(Alergy alergy: Alergy.values()){
			if(alergy.toString().equals(alergyString)){
				return alergy;
			}
		}
		
		return DUST;
	}
}
