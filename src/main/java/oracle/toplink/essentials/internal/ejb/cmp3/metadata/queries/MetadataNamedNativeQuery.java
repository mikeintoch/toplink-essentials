/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * // Copyright (c) 1998, 2007, Oracle. All rights reserved.
 * 
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package oracle.toplink.essentials.internal.ejb.cmp3.metadata.queries;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.QueryHint;
import javax.persistence.NamedNativeQuery;

import oracle.toplink.essentials.internal.ejb.cmp3.metadata.MetadataLogger;

/**
 * Object to hold onto named native query metadata.
 * 
 * @author Guy Pelletier
 * @since TopLink EJB 3.0 Reference Implementation
 */
public class MetadataNamedNativeQuery extends MetadataQuery {
    private Class m_resultClass;
    private String m_resultSetMapping;
    
    /**
     * INTERNAL:
     */
    protected MetadataNamedNativeQuery() {}
    
    /**
     * INTERNAL:
     */
    public MetadataNamedNativeQuery(NamedNativeQuery namedNativeQuery, Class javaClass) {
        // Set the location where we found this query.
        setLocation(javaClass.getName());
        
        // Process the name
        setName(namedNativeQuery.name());
        
        // Process the query string.
        setEJBQLString(namedNativeQuery.query());
        
        // Process the query hints.
        for (QueryHint hint : namedNativeQuery.hints()) {
            addHint(new MetadataQueryHint(hint.name(), hint.value()));
        }   
        
        // Process the result class.
        setResultClass(namedNativeQuery.resultClass());
        
        // Process the result set mapping.
        setResultSetMapping(namedNativeQuery.resultSetMapping());
    }
    
    /**
     * INTERNAL:
     */
    public String getIgnoreLogMessageContext() {
        return MetadataLogger.IGNORE_NAMED_NATIVE_QUERY_ANNOTATION;
    }
    
    /**
     * INTERNAL:
     */
    public Class getResultClass() {
        return m_resultClass;
    }
    
    /**
     * INTERNAL:
     */
    public String getResultSetMapping() {
        return m_resultSetMapping;
    }
    
    /**
     * INTERNAL:
     */
    protected void setResultClass(Class resultClass) {
        m_resultClass = resultClass;
    }
    
    /**
     * INTERNAL:
     */
    protected void setResultSetMapping(String resultSetMapping) {
        m_resultSetMapping = resultSetMapping;
    }
}
