package pl.dmcs.whatsupdoc.shared;

public enum Gender {
	UNKNOWN("nieznana"), MALE("mê¿czyzna"), FEMAL("kobieta");
	
	private final String genderString;
	
	private Gender(final String genderString){
		this.genderString = genderString;
	}
	
	public String toString(){
		return genderString;
	}
	
	public static Gender getAlergy(String genderString){
		for(Gender gender: Gender.values()){
			if(gender.toString().equals(genderString)){
				return gender;
			}
		}
		
		return UNKNOWN;
	}
}
