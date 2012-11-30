/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

/**
 * 29-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public enum StatusFieldType {
	ERROR_DB("Problem z bazą danych"), ERROR_LOGIN("Zły login lub hasło"), WAIT("Proszę czekać"), ADD_USER_SUCCESS("Użytkownika dodano pomyślnie"),
	ADD_USER_ERROR("Użytkownik nie został dodany pomyślnie");
	
	private final String name;
	
	/**
	 * 
	 */
	private StatusFieldType(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
	
	
}
