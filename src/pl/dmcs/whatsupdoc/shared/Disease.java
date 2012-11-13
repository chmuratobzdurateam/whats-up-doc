package pl.dmcs.whatsupdoc.shared;

public enum Disease {
	FLU("grypa"), PHARYNGITIS("zapalenie gard�a"), JAUNDICE("��taczka"), FOOD_POISONING("zatrucie pokaromwe"), RUBELLA("r�yczka");
	
	private final String diseaseString;
	
	private Disease(final String diseaseString){
		this.diseaseString = diseaseString;
	}
	
	public String toString(){
		return diseaseString;
	}
	
	public static Disease getDisease(String diseaseString){
		for(Disease disease: Disease.values()){
			if(disease.toString().equals(diseaseString)){
				return disease;
			}
		}
		
		return FLU;
	}
}
