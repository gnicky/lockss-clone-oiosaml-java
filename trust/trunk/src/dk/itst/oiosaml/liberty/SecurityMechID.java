/*
 * The contents of this file are subject to the Mozilla Public 
 * License Version 1.1 (the "License"); you may not use this 
 * file except in compliance with the License. You may obtain 
 * a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, either express 
 * or implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 *
 * The Original Code is OIOSAML Trust Client.
 * 
 * The Initial Developer of the Original Code is Trifork A/S. Portions 
 * created by Trifork A/S are Copyright (C) 2008 Danish National IT 
 * and Telecom Agency (http://www.itst.dk). All Rights Reserved.
 * 
 * Contributor(s):
 *   Joakim Recht <jre@trifork.com>
 *
 */
package dk.itst.oiosaml.liberty;

import java.util.List;

import org.opensaml.xml.AbstractXMLObject;
import org.opensaml.xml.XMLObject;

public class SecurityMechID extends AbstractXMLObject
{

	public static String LOCAL_NAME = "SecurityMechID";
	
	private String value;
	

    public SecurityMechID() 
    {
        super(LibertyConstants.DISCO_NS, SecurityMechID.LOCAL_NAME, LibertyConstants.DISCO_PREFIX);
    }
	
	
	protected SecurityMechID(String namespaceURI, String elementLocalName, String namespacePrefix) 
	{
		super(namespaceURI, elementLocalName, namespacePrefix);
	}
	
    public void setValue(String value) 
    {
    	this.value = prepareForAssignment(this.value, value);
    }
    
    public String getValue() 
    { 
    	return value; 
    }
    
	public List<XMLObject> getOrderedChildren() 
	{
		return null;
	}

}
