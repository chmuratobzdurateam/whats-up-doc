package pl.dmcs.whatsupdoc.shared;

public enum Medicine {
	RUTINOSCORBIN("Rutinoscorbin"), PYRALGINA("Pyralgina"), GRIPEX("Gripex"), ULGIX("Ulgix"), NIFUROKSAZYD("Nifuroksazyd"), AMOL("Amol"), AKATAR("Akatar");
	
	private final String medicineString;
	
	private Medicine(final String medicineString){
		this.medicineString = medicineString;
	}
	
	public String toString(){
		return medicineString;
	}
	
	public static Medicine getMedicine(String medicineString){
		for(Medicine medicine: Medicine.values()){
			if(medicine.toString().equals(medicineString)){
				return medicine;
			}
		}
		
		return RUTINOSCORBIN;
	}
}
