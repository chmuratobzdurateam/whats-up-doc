package pl.dmcs.whatsupdoc.shared;

public enum Speciality {
	KARDIOLOG("kardiolog"), UROLOG("urolog"), NEUROLOG("neurolog"), GINEKOLOG("ginekolog");
	
	private final String specialityString;
	
	private Speciality(final String specialityString){
		this.specialityString = specialityString;
	}
	
	public String toString(){
		return specialityString;
	}
	
	public static Speciality getSpeciality(String specialityString){
		for(Speciality speciality: Speciality.values()){
			if(speciality.toString().equals(specialityString)){
				return speciality;
			}
		}
		
		return KARDIOLOG;
	}
}
