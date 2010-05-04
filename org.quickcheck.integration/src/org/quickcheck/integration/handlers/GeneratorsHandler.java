package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.quickcheck.integration.ui.DynamicInputDialog;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.StringPair;

public class GeneratorsHandler extends AbstractQuickCheckHandler {

	@Override
	protected StringPair getInsertionString(String id)
			throws NullInputException {
		String before = "";
		String after = "";
		ArrayList<String> input;
		StringPair ret = null;
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
		} else if (id.equals("LET")) {
			ret = EditorUtils.compassWithMacro("?LET", "Macro LET",
					"Bound variable", "Generator for variable");
		} else if (id.equals("SIZED")) {
			ret = EditorUtils.compassWithMacro("?SIZED", "Macro SIZED",
					"Size variable", "Generator for variable");
		} else if (id.equals("SUCHTHAT")) {
			before = EditorUtils.createMacro("?SUCHTHAT", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("SUCHTHATMAYBE")) {
			before = EditorUtils.createMacro("?SUCHTHATMAYBE",
					"Macro SUCHTHATMAYBE", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("SHRINK")) {
			ret = EditorUtils.compassWithMacro("?SHRINK", "Macro SHRINK",
					"List of generators");
		} else if (id.equals("SHRINKWHILE")) {
			before = EditorUtils.createMacro("?SHRINKWHILE",
					"Macro SHRINKWHILE", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("LETSHRINK")) {
			ret = EditorUtils.compassWithMacro("?LETSHRINK", "Macro LETSHRINK",
					"Bound variable", "Generator (should be a list)");
		} else if (id.equals("LAZY")) {
			ret = EditorUtils.compassWithMacro("?LAZY", "Macro LAZY",
					new String[0]);
		} else if (id.equals("bool")) {
			before = EditorUtils.createMacro("bool", "Function bool",
					new String[0]);
		} else if (id.equals("char")) {
			before = EditorUtils.createMacro("char", "Function char",
					new String[0]);
		} else if (id.equals("choose")) {
			before = EditorUtils.createMacro("choose", "Function choose",
					"Low value", "High value");
		} else if (id.equals("default")) {
			ret = EditorUtils.compassWithMacro("default", "Function default",
					"Default value");
		} else if (id.equals("elements")) {
		} else if (id.equals("eval/1")) {
		} else if (id.equals("eval/2")) {
		} else if (id.equals("fault")) {
		} else if (id.equals("fault-rate")) {
		} else if (id.equals("frequency")) {
		} else if (id.equals("function0")) {
		} else if (id.equals("function1")) {
		} else if (id.equals("function2")) {
		} else if (id.equals("function3")) {
		} else if (id.equals("function4")) {
		} else if (id.equals("growingelements")) {
		} else if (id.equals("includeif")) {
		} else if (id.equals("int")) {
		} else if (id.equals("largeint")) {
		} else if (id.equals("less_faulty")) {
		} else if (id.equals("list")) {
		} else if (id.equals("maybe")) {
		} else if (id.equals("more_faulty")) {
		} else if (id.equals("nat")) {
		} else if (id.equals("no_faults")) {
		} else if (id.equals("noshrink")) {
		} else if (id.equals("oneof")) {
		} else if (id.equals("open")) {
		} else if (id.equals("orderedlist")) {
		} else if (id.equals("parameter/1")) {
		} else if (id.equals("parameter/2")) {
		} else if (id.equals("peek")) {
		} else if (id.equals("prop_shrinks_without_duplicates")) {
		} else if (id.equals("real")) {
		} else if (id.equals("resize")) {
		} else if (id.equals("return")) {
		} else if (id.equals("sample")) {
		} else if (id.equals("sampleshrink")) {
		} else if (id.equals("seal")) {
		} else if (id.equals("shrink_int")) {
		} else if (id.equals("shrink_list")) {
		} else if (id.equals("shrink_without_duplicates")) {
		} else if (id.equals("timeout")) {
		} else if (id.equals("vector")) {
		} else if (id.equals("wheighted_default")) {
		} else if (id.equals("with_parameter")) {
		} else if (id.equals("with_parameters")) {
		}
		if (ret != null)
			return ret;
		return new StringPair(before, after);
	}
}
