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
					OrdinalNumber.First, "List of generators");
		} else if (id.equals("SHRINKWHILE")) {
			before = EditorUtils.createMacro("?SHRINKWHILE",
					"Macro SHRINKWHILE", "Bound variable",
					"Generator for variable", "Condition");
		} else if (id.equals("LETSHRINK")) {
			ret = EditorUtils.compassWithMacro("?LETSHRINK", "Macro LETSHRINK",
					"Bound variable", "Generator (should be a list)");
		} else if (id.equals("LAZY")) {
			ret = EditorUtils.compassWithMacro("?LAZY", "Macro LAZY");
			// Functions
		} else if (id.equals("bool")) {
			before = EditorUtils.createMacro("bool", "Function bool");
		} else if (id.equals("char")) {
			before = EditorUtils.createMacro("char", "Function char");
		} else if (id.equals("choose")) {
			before = EditorUtils.createMacro("choose", "Function choose",
					"Low value", "High value");
		} else if (id.equals("default")) {
			ret = EditorUtils.compassWithMacro("default", "Function default",
					"Default value");
		} else if (id.equals("elements")) {
			ret = EditorUtils.compassWithMacro("elements", "Function elements",
					"List of elements");
		} else if (id.equals("eval/1")) {
			ret = EditorUtils.compassWithMacro("eval/1", "Function eval/1",
					new String[0]);
		} else if (id.equals("eval/2")) {
			ret = EditorUtils.compassWithMacro("eval/2", "Function eval/2",
					"Environment");
		} else if (id.equals("fault")) {
			ret = EditorUtils.compassWithMacro("fault", "Function fault",
					"Fault generator");
		} else if (id.equals("fault-rate")) {
			ret = EditorUtils.compassWithMacro("fault-rate",
					"Function fault-rate", "M (in M out of N)",
					"N (in M out of N)");
		} else if (id.equals("frequency")) {
			ret = EditorUtils.compassWithMacro("frequency",
					"Function frequency");
		} else if (id.equals("function0")) {
			ret = EditorUtils.compassWithMacro("function0",
					"Function function0");
		} else if (id.equals("function1")) {
			ret = EditorUtils.compassWithMacro("function1",
					"Function function1");
		} else if (id.equals("function2")) {
			ret = EditorUtils.compassWithMacro("function2",
					"Function function2");
		} else if (id.equals("function3")) {
			ret = EditorUtils.compassWithMacro("function3",
					"Function function3");
		} else if (id.equals("function4")) {
			ret = EditorUtils.compassWithMacro("function4",
					"Function function4");
		} else if (id.equals("growingelements")) {
			before = EditorUtils.createMacro("growingelements",
					"Function growingelements");
		} else if (id.equals("includeif")) {
			before = EditorUtils.createMacro("includeif", "Function includeif",
					"Boolean expression", "Element");
		} else if (id.equals("int")) {
			ret = EditorUtils.compassWithMacro("int", "Function int");
		} else if (id.equals("largeint")) {
			ret = EditorUtils.compassWithMacro("largeint", "Function largeint");
		} else if (id.equals("less_faulty")) {
			ret = EditorUtils.compassWithMacro("less_faulty",
					"Function less_faulty", "Decreasing factor");
		} else if (id.equals("list")) {
			ret = EditorUtils.compassWithMacro("list", "Function list");
		} else if (id.equals("maybe")) {
			ret = EditorUtils.compassWithMacro("maybe", "Function maybe");
		} else if (id.equals("more_faulty")) {
			ret = EditorUtils.compassWithMacro("more_faulty",
					"Function more_faulty", "Increasing factor");
		} else if (id.equals("nat")) {
			ret = EditorUtils.compassWithMacro("nat", "Function nat");
		} else if (id.equals("no_faults")) {
			ret = EditorUtils.compassWithMacro("no_faults",
					"Function no_faults");
		} else if (id.equals("noshrink")) {
			ret = EditorUtils.compassWithMacro("noshrink", "Function noshrink");
		} else if (id.equals("oneof")) {
			ret = EditorUtils.compassWithMacro("oneof", "Function oneof");
		} else if (id.equals("open")) {
			ret = EditorUtils.compassWithMacro("open", "Function open");
		} else if (id.equals("orderedlist")) {
			ret = EditorUtils.compassWithMacro("orderedlist",
					"Function orderedlist");
		} else if (id.equals("parameter/1")) {
			ret = EditorUtils.compassWithMacro("parameter/1",
					"Function parameter/1");
		} else if (id.equals("parameter/2")) {
			ret = EditorUtils.compassWithMacro("parameter/2",
					"Function parameter/2", OrdinalNumber.First,
					"Default value");
		} else if (id.equals("peek")) {
			ret = EditorUtils.compassWithMacro("peek", "Function peek");
		} else if (id.equals("prop_shrinks_without_duplicates")) {
			ret = EditorUtils.compassWithMacro(
					"prop_shrinks_without_duplicates",
					"Function prop_shrinks_without_duplicates");
		} else if (id.equals("real")) {
			ret = EditorUtils.compassWithMacro("real", "Function real");
		} else if (id.equals("resize")) {
			ret = EditorUtils.compassWithMacro("resize", "Function resize",
					"New size");
		} else if (id.equals("return")) {
			ret = EditorUtils.compassWithMacro("return", "Function return");
		} else if (id.equals("sample")) {
			ret = EditorUtils.compassWithMacro("sample", "Function sample");
		} else if (id.equals("sampleshrink")) {
			ret = EditorUtils.compassWithMacro("sampleshrink",
					"Function sampleshrink");
		} else if (id.equals("seal")) {
			ret = EditorUtils.compassWithMacro("seal", "Function seal");
		} else if (id.equals("shrink_int")) {
			before = EditorUtils.createMacro("shrink_int",
					"Function shrink_int", "N (range N to M)",
					"M (range N to M)", "Value X");
		} else if (id.equals("shrink_list")) {
			before = EditorUtils.createMacro("shrink_list",
					"Function shrink_list", "List (shrinks to any sublist)");
		} else if (id.equals("shrink_without_duplicates")) {
			ret = EditorUtils.compassWithMacro("shrink_without_duplicates",
					"Function shrink_without_duplicates");
		} else if (id.equals("timeout")) {
			ret = EditorUtils.compassWithMacro("timeout", "Function timeout",
					"Timeout (in ms)");
		} else if (id.equals("vector")) {
			ret = EditorUtils.compassWithMacro("vector", "Function vector",
					"Vector size");
		} else if (id.equals("wheighted_default")) {
			ret = EditorUtils.compassWithMacro("wheighted_default",
					"Function wheighted_default", "Default ({wheight,Value})");
		} else if (id.equals("with_parameter")) {
			ret = EditorUtils.compassWithMacro("with_parameter",
					"Function with_parameter", "Parameter", "Value");
		} else if (id.equals("with_parameters")) {
			ret = EditorUtils.compassWithMacro("with_parameters",
					"Function with_parameters", "List of {Parameter,Value}");
		}
		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}
}
