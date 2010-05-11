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
import org.quickcheck.integration.utils.OrdinalNumber;
import org.quickcheck.integration.utils.InsertionStringPair;

public class GeneratorsHandler extends AbstractQuickCheckHandler {

	@Override
	protected InsertionStringPair getInsertionString(String id)
			throws NullInputException {
		String before = "";
		String after = "";
		ArrayList<String> input;
		InsertionStringPair ret = null;
		if (id.equals("sizedgenerator")) {
			input = DynamicInputDialog.run("Sized generator", "Generator name");
			before = input.get(0) + "() ->" + EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "?SIZED(Size," + input.get(0) + "Gen("
					+ input.get(0) + "))." + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;
			before += input.get(0) + "(0) ->" + EditorUtils.NEWLINE
					+ EditorUtils.TAB + ";";
			before += input.get(0) + "(Size) ->" + EditorUtils.NEWLINE
					+ EditorUtils.TAB + ".";
			// Macros
		} else if (id.equals("LET")) {
			ret = EditorUtils.compassWith("?LET", "Macro LET",
					"Bound variable", "Generator for variable");
		} else if (id.equals("SIZED")) {
			ret = EditorUtils.compassWith("?SIZED", "Macro SIZED",
					"Size variable", "Generator for variable");
		} else if (id.equals("SUCHTHAT")) {
			before = EditorUtils.createCall("?SUCHTHAT", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("SUCHTHATMAYBE")) {
			before = EditorUtils.createCall("?SUCHTHATMAYBE",
					"Macro SUCHTHATMAYBE", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("SHRINK")) {
			ret = EditorUtils.compassWith("?SHRINK", "Macro SHRINK",
					OrdinalNumber.First, "List of generators");
		} else if (id.equals("SHRINKWHILE")) {
			before = EditorUtils.createCall("?SHRINKWHILE",
					"Macro SHRINKWHILE", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("LETSHRINK")) {
			ret = EditorUtils.compassWith("?LETSHRINK", "Macro LETSHRINK",
					"Bound variable", "Generator (should be a list)");
		} else if (id.equals("LAZY")) {
			ret = EditorUtils.compassWith("?LAZY", "Macro LAZY");
			// Functions
		} else if (id.equals("bool")) {
			before = EditorUtils.createCall("bool", "Function bool");
		} else if (id.equals("char")) {
			before = EditorUtils.createCall("char", "Function char");
		} else if (id.equals("choose")) {
			before = EditorUtils.createCall("choose", "Function choose",
					"Low value", "High value");
		} else if (id.equals("default")) {
			ret = EditorUtils.compassWith("default", "Function default",
					"Default value");
		} else if (id.equals("elements")) {
			ret = EditorUtils.compassWith("elements", "Function elements",
					"List of elements");
		} else if (id.equals("eval/1")) {
			ret = EditorUtils.compassWith("eval/1", "Function eval/1",
					new String[0]);
		} else if (id.equals("eval/2")) {
			ret = EditorUtils.compassWith("eval/2", "Function eval/2",
					"Environment");
		} else if (id.equals("fault")) {
			ret = EditorUtils.compassWith("fault", "Function fault",
					"Fault generator");
		} else if (id.equals("fault-rate")) {
			ret = EditorUtils.compassWith("fault-rate",
					"Function fault-rate", "M (in M out of N)",
					"N (in M out of N)");
		} else if (id.equals("frequency")) {
			ret = EditorUtils.compassWith("frequency",
					"Function frequency");
		} else if (id.equals("function0")) {
			ret = EditorUtils.compassWith("function0",
					"Function function0");
		} else if (id.equals("function1")) {
			ret = EditorUtils.compassWith("function1",
					"Function function1");
		} else if (id.equals("function2")) {
			ret = EditorUtils.compassWith("function2",
					"Function function2");
		} else if (id.equals("function3")) {
			ret = EditorUtils.compassWith("function3",
					"Function function3");
		} else if (id.equals("function4")) {
			ret = EditorUtils.compassWith("function4",
					"Function function4");
		} else if (id.equals("growingelements")) {
			before = EditorUtils.createCall("growingelements",
					"Function growingelements");
		} else if (id.equals("includeif")) {
			before = EditorUtils.createCall("includeif", "Function includeif",
					"Boolean expression", "Element");
		} else if (id.equals("int")) {
			ret = EditorUtils.compassWith("int", "Function int");
		} else if (id.equals("largeint")) {
			ret = EditorUtils.compassWith("largeint", "Function largeint");
		} else if (id.equals("less_faulty")) {
			ret = EditorUtils.compassWith("less_faulty",
					"Function less_faulty", "Decreasing factor");
		} else if (id.equals("list")) {
			ret = EditorUtils.compassWith("list", "Function list");
		} else if (id.equals("maybe")) {
			ret = EditorUtils.compassWith("maybe", "Function maybe");
		} else if (id.equals("more_faulty")) {
			ret = EditorUtils.compassWith("more_faulty",
					"Function more_faulty", "Increasing factor");
		} else if (id.equals("nat")) {
			ret = EditorUtils.compassWith("nat", "Function nat");
		} else if (id.equals("no_faults")) {
			ret = EditorUtils.compassWith("no_faults",
					"Function no_faults");
		} else if (id.equals("noshrink")) {
			ret = EditorUtils.compassWith("noshrink", "Function noshrink");
		} else if (id.equals("oneof")) {
			ret = EditorUtils.compassWith("oneof", "Function oneof");
		} else if (id.equals("open")) {
			ret = EditorUtils.compassWith("open", "Function open");
		} else if (id.equals("orderedlist")) {
			ret = EditorUtils.compassWith("orderedlist",
					"Function orderedlist");
		} else if (id.equals("parameter/1")) {
			ret = EditorUtils.compassWith("parameter/1",
					"Function parameter/1");
		} else if (id.equals("parameter/2")) {
			ret = EditorUtils.compassWith("parameter/2",
					"Function parameter/2", OrdinalNumber.First,
					"Default value");
		} else if (id.equals("peek")) {
			ret = EditorUtils.compassWith("peek", "Function peek");
		} else if (id.equals("prop_shrinks_without_duplicates")) {
			ret = EditorUtils.compassWith(
					"prop_shrinks_without_duplicates",
					"Function prop_shrinks_without_duplicates");
		} else if (id.equals("real")) {
			ret = EditorUtils.compassWith("real", "Function real");
		} else if (id.equals("resize")) {
			ret = EditorUtils.compassWith("resize", "Function resize",
					"New size");
		} else if (id.equals("return")) {
			ret = EditorUtils.compassWith("return", "Function return");
		} else if (id.equals("sample")) {
			ret = EditorUtils.compassWith("sample", "Function sample");
		} else if (id.equals("sampleshrink")) {
			ret = EditorUtils.compassWith("sampleshrink",
					"Function sampleshrink");
		} else if (id.equals("seal")) {
			ret = EditorUtils.compassWith("seal", "Function seal");
		} else if (id.equals("shrink_int")) {
			before = EditorUtils.createCall("shrink_int",
					"Function shrink_int", "N (range N to M)",
					"M (range N to M)", "Value X");
		} else if (id.equals("shrink_list")) {
			before = EditorUtils.createCall("shrink_list",
					"Function shrink_list", "List (shrinks to any sublist)");
		} else if (id.equals("shrink_without_duplicates")) {
			ret = EditorUtils.compassWith("shrink_without_duplicates",
					"Function shrink_without_duplicates");
		} else if (id.equals("timeout")) {
			ret = EditorUtils.compassWith("timeout", "Function timeout",
					"Timeout (in ms)");
		} else if (id.equals("vector")) {
			ret = EditorUtils.compassWith("vector", "Function vector",
					"Vector size");
		} else if (id.equals("wheighted_default")) {
			ret = EditorUtils.compassWith("wheighted_default",
					"Function wheighted_default", "Default ({wheight,Value})");
		} else if (id.equals("with_parameter")) {
			ret = EditorUtils.compassWith("with_parameter",
					"Function with_parameter", "Parameter", "Value");
		} else if (id.equals("with_parameters")) {
			ret = EditorUtils.compassWith("with_parameters",
					"Function with_parameters", "List of {Parameter,Value}");
		}
		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}
}
