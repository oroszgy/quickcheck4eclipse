package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.quickcheck.integration.ui.DynamicInputDialog;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.StringPair;

public class PropertiesHandler extends AbstractHandler {

	/**
	 * The constructor.
	 */
	public PropertiesHandler() {

	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String s = event.getCommand().getId();
		String[] sparts = s.split("\\.");
		String id = sparts[sparts.length - 1];
		try {

			StringPair insertionStringPair = getInsertionString(id);
			if (!insertionStringPair.second.equals(""))
				EditorUtils.insertText(insertionStringPair.first,
						insertionStringPair.second + EditorUtils.NEWLINE);
			else
				EditorUtils.insertText(insertionStringPair.first
						+ EditorUtils.NEWLINE);
		} catch (NullInputException e) {
			// The execution has terminated
		}
		return null;

	}

	protected StringPair getInsertionString(String id)
			throws NullInputException {
		String before = "";
		String after = "";
		ArrayList<String> input;
		StringPair ret = null;
		if (id.equals("fullmoduleheader")) {
			String userAtMachine = "<" + EditorUtils.getUserName() + "@"
					+ EditorUtils.getMachineName() + ">";
			before = "%%% File\t: " + EditorUtils.getFileName()
					+ EditorUtils.NEWLINE;
			before += "%%% Author\t: " + EditorUtils.getAuthorName() + " "
					+ userAtMachine + EditorUtils.NEWLINE;
			before += "%%% Description\t: " + EditorUtils.NEWLINE;
			before += "%%% Created\t: " + EditorUtils.getDate() + " by "
					+ EditorUtils.getAuthorName() + " " + userAtMachine
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += "-module(" + EditorUtils.getModuleName() + ")."
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("includeeqc") + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;
			before += getInsertionString("exportall") + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;

		} else if (id.equals("includeeqc")) {
			before = "-include_lib(\"eqc/include/eqc.hrl\").";
		} else if (id.equals("exportall")) {
			before = "-compile(export_all).";

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
			ret = EditorUtils.toCompass("?IMPLIES", "Macro IMPLIES",
					"Precondition");
			// input = DynamicInputDialog.run("Macro IMPLIES", "Precondition");
			// before = "?IMPLIES(" + input.get(0);
			// before += EditorUtils.COMMA + EditorUtils.NEWLINE +
			// EditorUtils.TAB;
			// after = ").";

		} else if (id.equals("macroforall")) {
			ret = EditorUtils.toCompass("?FORALL", "Macro FORALL", "Value",
					"Generator");
			// input = DynamicInputDialog
			// .run("Macro FORALL", "Value", "Generator");
			// before = "?FORALL(" + input.get(0) + EditorUtils.COMMA
			// + input.get(1);
			// before += EditorUtils.NEWLINE + EditorUtils.TAB;
			// after = ").";

		} else if (id.equals("macrowhenfail")) {
			ret = EditorUtils
					.toCompass("?WHENFAIL", "Macro WHENFAIL", "Action");

		} else if (id.equals("macrotrapexit")) {
			ret = EditorUtils.toCompass("?TRAPEXIT", "Macro TRAPEXIT",
					new String[0]);

		} else if (id.equals("macroalways")) {
			ret = EditorUtils.toCompass("?ALWAYS", "Macro ALWAYS",
					"Number of times to test");

		} else if (id.equals("macrosometimes")) {
			ret = EditorUtils.toCompass("?SOMETIMES", "Macro SOMETIMES",
					"Number of times to test");

		} else if (id.equals("functionaggregate")) {
			ret = EditorUtils.toCompass("aggregate", "Function aggregate",
					"List of terms to aggregate");

		} else if (id.equals("functionclassify")) {
			ret = EditorUtils.toCompass("classify", "Function classify",
					"Boolean expression", "Label");
		} else if (id.equals("functioncollect")) {
			ret = EditorUtils.toCompass("collect", "Function collect",
					"Collected term");
		} else if (id.equals("functionfails")) {
			ret = EditorUtils.toCompass("fails", "Function fails",
					new String[0]);
		} else if (id.equals("functionmeasure")) {
			ret = EditorUtils.toCompass("measure", "Function measure", "Label",
					"Term (or list of terms) to measure");
		}
		if (ret != null)
			return ret;
		return new StringPair(before, after);
	}
}