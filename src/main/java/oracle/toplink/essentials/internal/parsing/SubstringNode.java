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
package oracle.toplink.essentials.internal.parsing;

import oracle.toplink.essentials.expressions.*;

/**
 * INTERNAL
 * <p><b>Purpose</b>: Represent a SUBSTRING
 * <p><b>Responsibilities</b>:<ul>
 * <li> Generate the correct expression for SUBSTRING
 * </ul>
 *    @author Jon Driscoll and Joel Lucuik
 *    @since TopLink 4.0
 */
public class SubstringNode extends StringFunctionNode {
    private Node startPosition = null;
    private Node stringLength = null;

    /**
     * SubstringNode constructor comment.
     */
    public SubstringNode() {
        super();
    }

    /** 
     * INTERNAL 
     * Check the child nodes for an unqualified field access and if so,
     * replace them by a qualified field access.     
     */
    public Node qualifyAttributeAccess(ParseTreeContext context) {
       if (left != null) {
           left = left.qualifyAttributeAccess(context);
       }
       if (startPosition != null) {
           startPosition = startPosition.qualifyAttributeAccess(context);
       }
       if (stringLength != null) {
           stringLength = stringLength.qualifyAttributeAccess(context);
       }
       return this;
    }

    /**
     * INTERNAL
     * Validate node and calculate its type.
     */
    public void validate(ParseTreeContext context) {
        TypeHelper typeHelper = context.getTypeHelper();
        if (left != null) {
            left.validate(context);
            left.validateParameter(context, typeHelper.getStringType());
        }
        if (startPosition != null) {
            startPosition.validate(context);
            startPosition.validateParameter(context, typeHelper.getIntType());
        }
        if (stringLength != null) {
            stringLength.validate(context);
            stringLength.validateParameter(context, typeHelper.getIntType());
        }
        setType(typeHelper.getStringType());
    }

    /**
     * INTERNAL
     * Generate the TopLink expression for this node
     */
    public Expression generateExpression(GenerationContext context) {
        Expression whereClause = getLeft().generateExpression(context);
        Expression startPosition = getStartPosition().generateExpression(context);
        Expression stringLength = getStringLength().generateExpression(context);
        whereClause = whereClause.substring(startPosition, stringLength);
        return whereClause;
    }

    /**
     * Return the start position object
     */
    private Node getStartPosition() {
        return startPosition;
    }

    /**
     * Return the string length object
     */
    private Node getStringLength() {
        return stringLength;
    }

    /**
     * Insert the method's description here.
     * Creation date: (1/19/01 3:41:55 PM)
     * @param newStartPosition java.lang.Integer
     */
    public void setStartPosition(Node newStartPosition) {
        startPosition = newStartPosition;
    }

    /**
     * Insert the method's description here.
     * Creation date: (1/19/01 3:42:26 PM)
     * @param newStringLength java.lang.Integer
     */
    public void setStringLength(Node newStringLength) {
        stringLength = newStringLength;
    }
}
