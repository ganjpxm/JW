/**
 * $Id: IdentifierGenerator.java,v 1.0 2012/07/28 16:35:49 Gan Jianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.jpw.core.dao.uuid;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.id.IdentifierGenerationException;

/**
 * <p>The general contract between a class that generates unique identifiers and
 * the <tt>Session</tt>. It is not intended that this interface ever be
 * exposed to the application. It <b>is</b> intended that users implement this
 * interface to provide custom identifier generation strategies.<br>
 * <br>
 * Implementors should provide a public default constructor.<br>
 * <br>
 * Implementations that accept configuration parameters should also implement
 * <tt>Configurable</tt>. <br>
 * Implementors <em>must</em> be threadsafe</p> 
 *
 * @author GanJianping
 * @since 1.0
 */
public interface IdentifierGenerator {

	/**
	 * <p>generate a new mark</p>
	 * 
	 * @param Connection
	 * @param object  the entity or toplevel collection for which the id is being generated
	 * @return Serializable a new identifier
	 * @throws SQLException
	 * @throws IdentifierGenerationException
	 */
	public Serializable generate(Connection conn, Object object) throws SQLException;
}
