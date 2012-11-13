package pl.dmcs.whatsupdoc.shared;

public enum Symptom {
	HEADACHE("b�l g�owy"), SORE_THROAT("b�l gard�a"), FEVER("gor�czka"), NAUSEA("nudno�ci"), COUGH("kaszel"), RASH("wysypka"), CATARRH("katar");
	
	private final String symptomString;
	
	private Symptom(final String symptomString){
		this.symptomString = symptomString;
	}
	
	public String toString(){
		return symptomString;
	}
	
	public static Symptom getSymptom(String symptomString){
		for(Symptom symptom: Symptom.values()){
			if(symptom.toString().equals(symptomString)){
				return symptom;
			}
		}
		
		return HEADACHE;
	}
}
