package pl.dmcs.whatsupdoc.shared;

public enum UserType {
	PATIENT("Pacjent"), DOCTOR("Lekarz"), VERIFIER("Weryfikator");
	
	private final String userTypeString;
	
	private UserType(final String userTypeString){
		this.userTypeString = userTypeString;
	}
	
	public String toString(){
		return userTypeString;
	}
	
	public static UserType getUserType(String userTypeString){
		for(UserType userType: UserType.values()){
			if(userType.toString().equals(userTypeString)){
				return userType;
			}
		}
		
		return PATIENT;
	}
}