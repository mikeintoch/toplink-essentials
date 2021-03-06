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
package oracle.toplink.essentials.internal.sessions;


/**
 * <p>
 * <b>Purpose</b>: To record the changes for attributes that can be represented as DirectToField
 * <p>
 * @see RelatedClasses prototype.changeset.CollectionChangeRecord,prototype.changeset.SingleObjectChangeRecord
 */
public class DirectToFieldChangeRecord extends ChangeRecord implements oracle.toplink.essentials.changesets.DirectToFieldChangeRecord {
    protected Object newValue;

    /**
     * This default constructor is reference internally by SDK XML project to mapp this class
     */
    public DirectToFieldChangeRecord() {
        super();
    }

    /**
     * This constructor returns a changeRecord representing the DirectToField mapping
     * @param owner prototype.changeset.ObjectChangeSet that ObjectChangeSet that uses this record
     */
    public DirectToFieldChangeRecord(ObjectChangeSet owner) {
        this.owner = owner;
    }

    /**
     * ADVANCED:
     * Returns the new value assigned during the change
     * @return java.lang.Object
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * INTERNAL:
     * This method will be used to merge one record into another
     */
    public void mergeRecord(ChangeRecord mergeFromRecord, UnitOfWorkChangeSet mergeToChangeSet, UnitOfWorkChangeSet mergeFromChangeSet) {
        this.newValue = ((DirectToFieldChangeRecord)mergeFromRecord).getNewValue();
    }

    /**
     * ADVANCED:
     * Sets the new value assigned during the change
     * @param newValue java.lang.Object
     */
    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    /**
     * INTERNAL:
     * used by the record to update the new value ignores the value in the default implementation
     */
    public void updateChangeRecordWithNewValue(Object newValue) {
        setNewValue(newValue);
    }

    /**
     * INTERNAL:
     * This method will be used to update the objectsChangeSets references
     */
    public void updateReferences(UnitOfWorkChangeSet mergeToChangeSet, UnitOfWorkChangeSet mergeFromChangeSet) {
    }
}
