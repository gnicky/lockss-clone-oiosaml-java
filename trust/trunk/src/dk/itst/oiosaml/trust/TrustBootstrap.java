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
package dk.itst.oiosaml.trust;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLConfigurator;

/**
 * Static configuration for OIOSAML Trust.
 * 
 * The {@link #bootstrap()} method must be invoked before any WS-Trust types can be used. This is done automatically in {@link TrustClient}.
 * 
 * @author recht
 *
 */
public class TrustBootstrap {
	private static final Logger log = Logger.getLogger(TrustBootstrap.class);
	
	private static boolean bootstrapped = false;

	/**
	 * Configure xmltools with WS-Trust types. 
	 * 
	 * This method can be called any number of times, only the first will actually load the types.
	 */
	public static void bootstrap() {
		if (!bootstrapped) {
	        Class<Configuration> clazz = Configuration.class;
	
	        String[] config = {
	        		"/wsaddressing-config.xml",
	        		"/wspolicy-config.xml",
	        		"/wssecurity-config.xml",
	        		"/wstrust-config.xml",
	        		"/dk/itst/oiosaml/liberty/liberty-config.xml",
	        };
	        if (log.isDebugEnabled())  log.debug("Loading XMLTooling configuration " + config);
	        try {
				DefaultBootstrap.bootstrap();
				
	        	XMLConfigurator configurator = new XMLConfigurator();
	        	for (String c : config) {
	        		log.debug("Loading config " + c);
	        		configurator.load(clazz.getResourceAsStream(c));	
				}
			} catch (ConfigurationException e) {
				throw new RuntimeException(e);
			}
			bootstrapped = true;
		}
	}
}