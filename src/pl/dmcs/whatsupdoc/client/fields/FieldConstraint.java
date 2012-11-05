/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

/**
 * 05-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public interface FieldConstraint {
	/**
	 * @return boolean - true if field is valid false otherwise
	 */
	public boolean checkConstraint();
}
