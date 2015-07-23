// Copyright (c) 1998, 2006, Oracle. All rights reserved.  
package oracle.toplink.essentials.platform.server.jboss;

import oracle.toplink.essentials.internal.sessions.DatabaseSessionImpl;
import oracle.toplink.essentials.transaction.jboss.JBossTransactionController;
import oracle.toplink.essentials.platform.server.ServerPlatformBase;

/**
 * PUBLIC:
 *
 * This is the concrete subclass responsible for representing JBoss-specific server behaviour.
 *
 * This platform overrides:
 *
 * getExternalTransactionControllerClass(): to use an JBoss-specific controller class
 *
 */
public class JBossPlatform extends ServerPlatformBase {

    /**
     * INTERNAL:
     * Default Constructor: All behaviour for the default constructor is inherited
     */
    public JBossPlatform(DatabaseSessionImpl newDatabaseSession) {
        super(newDatabaseSession);
    }

    /**
     * INTERNAL: getExternalTransactionControllerClass(): Answer the class of external transaction controller to use
     * for JBoss. This is read-only.
     *
     * @return Class externalTransactionControllerClass
     *
     * @see oracle.toplink.transaction.JTATransactionController
     * @see ServerPlatformBase.isJTAEnabled()
     * @see ServerPlatformBase.disableJTA()
     * @see ServerPlatformBase.initializeExternalTransactionController()
     */
    public Class getExternalTransactionControllerClass() {
    	if (externalTransactionControllerClass == null){
    		externalTransactionControllerClass = JBossTransactionController.class;
    	}
        return externalTransactionControllerClass;
    }
}