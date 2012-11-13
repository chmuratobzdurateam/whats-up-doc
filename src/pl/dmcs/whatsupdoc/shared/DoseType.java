package pl.dmcs.whatsupdoc.shared;

public enum DoseType {
	GRAMS("g"), MILILITERS("ml"), PIECES("sztuk");

	private final String doseTypeString;
	
	private DoseType(final String doseTypeString){
		this.doseTypeString = doseTypeString;
	}
	
	public String toString(){
		return doseTypeString;
	}
	
	public static DoseType getDoseType(String doseTypeString){
		for(DoseType doseType: DoseType.values()){
			if(doseType.toString().equals(doseTypeString)){
				return doseType;
			}
		}
		
		return GRAMS;
	}
}
