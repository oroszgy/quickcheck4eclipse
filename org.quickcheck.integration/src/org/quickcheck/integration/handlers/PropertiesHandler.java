/*******************************************************************************
 * Copyright (c) 2010 György Orosz
 * 
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 * 
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.quickcheck.integration.ui.DynamicInputDialog;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.InsertionStringPair;

public class PropertiesHandler extends AbstractQuickCheckHandler {

	/**
	 * The constructor.
	 */
	public PropertiesHandler() {

	}

	@Override
	protected InsertionStringPair getInsertionString(String id)
			throws NullInputException {
		String before = "";
		String after = "";
		ArrayList<String> input;
		InsertionStringPair ret = null;
		if (id.equals("fullmoduleheader")) {
			before = getHeader();
			before += getInsertionString("includeeqc").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("exportall").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;

		} else if (id.equals("includeeqc")) {
			before = getIncludeEqc();
		} else if (id.equals("exportall")) {
			before = getCompileAll();

		} else if (id.equals("property")) {
			input = DynamicInputDialog.run("Properties",
					"Property name (prop_NAME)",
					"Property parameters (X,Y,...)", "Value", "Generator");

			before = "prop_" + input.get(0) + "(" + input.get(1) + ") ->"
					+ EditorUtils.NEWLINE + EditorUtils.TAB + "?FORALL("
					+ input.get(2) + EditorUtils.COMMA + input.get(3)
					+ EditorUtils.COMMA + EditorUtils.NEWLINE + EditorUtils.TAB
					+ EditorUtils.TAB;
			after = ").";

		} else if (id.equals("macroimplies")) {
			ret = EditorUtils.compassWith("?IMPLIES", "Macro IMPLIES",
					"Precondition");
			// input = DynamicInputDialog.run("Macro IMPLIES", "Precondition");
			// before = "?IMPLIES(" + input.get(0);
			// before += EditorUtils.COMMA + EditorUtils.NEWLINE +
			// EditorUtils.TAB;
			// after = ").";

		} else if (id.equals("macroforall")) {
			ret = EditorUtils.compassWith("?FORALL", "Macro FORALL",
					"Value", "Generator");
			// input = DynamicInputDialog
			// .run("Macro FORALL", "Value", "Generator");
			// before = "?FORALL(" + input.get(0) + EditorUtils.COMMA
			// + input.get(1);
			// before += EditorUtils.NEWLINE + EditorUtils.TAB;
			// after = ").";

		} else if (id.equals("macrowhenfail")) {
			ret = EditorUtils.compassWith("?WHENFAIL", "Macro WHENFAIL",
					"Action");

		} else if (id.equals("macrotrapexit")) {
			ret = EditorUtils.compassWith("?TRAPEXIT", "Macro TRAPEXIT",
					new String[0]);

		} else if (id.equals("macroalways")) {
			ret = EditorUtils.compassWith("?ALWAYS", "Macro ALWAYS",
					"Number of times to test");

		} else if (id.equals("macrosometimes")) {
			ret = EditorUtils.compassWith("?SOMETIMES", "Macro SOMETIMES",
					"Number of times to test");

		} else if (id.equals("functionaggregate")) {
			ret = EditorUtils.compassWith("aggregate",
					"Function aggregate", "List of terms to aggregate");

		} else if (id.equals("functionclassify")) {
			ret = EditorUtils.compassWith("classify", "Function classify",
					"Boolean expression", "Label");
		} else if (id.equals("functioncollect")) {
			ret = EditorUtils.compassWith("collect", "Function collect",
					"Collected term");
		} else if (id.equals("functionfails")) {
			ret = EditorUtils.compassWith("fails", "Function fails",
					new String[0]);
		} else if (id.equals("functionmeasure")) {
			ret = EditorUtils.compassWith("measure", "Function measure",
					"Label", "Term (or list of terms) to measure");
		}
		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}
}
