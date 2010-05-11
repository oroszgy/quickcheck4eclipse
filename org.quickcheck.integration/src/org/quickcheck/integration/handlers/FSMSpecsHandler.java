package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.internal.Workbench;
import org.erlide.core.erlang.ErlModelException;
import org.quickcheck.integration.ui.DynamicInputDialog;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.InsertionStringPair;

public class FSMSpecsHandler extends AbstractQuickCheckSpecsHandler implements
		IHandler {

	@Override
	protected InsertionStringPair getInsertionString(String id)
			throws NullInputException, ErlModelException, BadLocationException {

		String before = "";
		String after = "";
		ArrayList<String> input = null;
		InsertionStringPair ret = null;
		if (id.equals("completeeqc_fsmspec")) {

			before += getInsertionString("fulleqc_fsmmoduleheader").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			String record = "";
			boolean useRecord = MessageDialog.openQuestion(Workbench
					.getInstance().getActiveWorkbenchWindow().getShell(),
					"Record usage", "Use a record for state data?");
			String initState, propName;
			if (useRecord) {
				before += "-record(" + EditorUtils.COMMA + "{})."
						+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
				input = DynamicInputDialog.run("FSM template",
						"Name of initial state [init_state]",
						"Property name (prop_NAME)");
				initState = input.get(0);
				propName = input.get(1);
			} else {
				input = DynamicInputDialog.run("FSM template",
						"Property name (prop_NAME)");
				initState = "init_state";
				propName = input.get(0);
			}
			before += "%% Definition of the states. Each state is represented by a function,"
					+ EditorUtils.NEWLINE;
			before += "%% listing the transitions from that state, together with generators"
					+ EditorUtils.NEWLINE;
			before += "%% for the calls to make each transition."
					+ EditorUtils.NEWLINE;

			before += initState + "(S) ->" + EditorUtils.NEWLINE;
			before += EditorUtils.TAB
					+ "[ %% {target_state,{call,?MODULE,target_function,[]}}"
					+ EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "]." + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;

			before += getInititalState(initState) + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;
			before += getInitialStateData(false) + EditorUtils.NEWLINE;
			before += getInsertionString("command").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("next_state_data").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("precondition").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("postcondition").toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getSMProperty(propName, "?MODULE", true).toString()
					+ EditorUtils.NEWLINE + EditorUtils.NEWLINE;
			before += getInsertionString("transitionweight").toString();

		} else if (id.equals("fulleqc_fsmmoduleheader")) {
			before = getHeader();
			before += getIncludeEqc() + EditorUtils.NEWLINE;
			before += getIncludeEqcFSM() + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;
			before += getCompileAll();
		} else if (id.equals("includeeqc_fsm")) {
			before += getIncludeEqcFSM();
		} else if (id.equals("initial_state")) {
			input = DynamicInputDialog.run("initial_state",
					"Name of initial state [init_state]");
			before = getInititalState(input.get(0));
		} else if (id.equals("initial_state_data")) {
			before = getInitialStateData(false);
		} else if (id.equals("next_state_data")) {
			before += "%% Next state transformation for state data."
					+ EditorUtils.NEWLINE;
			before += "%% S is the current state, From and To are state names"
					+ EditorUtils.NEWLINE;
			before += "next_state_data(_From,_To,S,_V,{call,_,_,_}) ->"
					+ EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "S.";
		} else if (id.equals("precondition")) {
			before += "%% Precondition (for state data)." + EditorUtils.NEWLINE;
			before += "%% Precondition is checked before command is added to the command sequence"
					+ EditorUtils.NEWLINE;
			before += "precondition(_From,_To,_S,{call,_,_,_}) ->"
					+ EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "true.";

		} else if (id.equals("postcondition")) {
			before += "%% Postcondition, checked after command has been evaluated"
					+ EditorUtils.NEWLINE;
			before += "%% OBS: S is the state before next_state_data(From,To,S,_,<command>)"
					+ EditorUtils.NEWLINE;
			before += "postcondition(_From,_To,_S,{call,_,_,_},_Res) ->"
					+ EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "true.";
		} else if (id.equals("fsmproperty")) {
			input = DynamicInputDialog.run("FSM Property",
					"Property name (prop_NAME)",
					"Module for commands [?MODULE]");
			ret = getSMProperty(input.get(0), input.get(1), EditorUtils
					.getActiveSelectionLength() == 0);
		} else if (id.equals("transitionweight")) {
			before += "%% Weight for transition (this callback is optional)."
					+ EditorUtils.NEWLINE;
			before += "%% Specify how often each transition should be chosen"
					+ EditorUtils.NEWLINE;
			before += "weight(_From,_To,{call,_,_,_}) ->" + EditorUtils.NEWLINE;
			before += EditorUtils.TAB + "1." + EditorUtils.NEWLINE;
		} else if (id.equals("symbolicfunctioncall")) {
			ret = getSymbolicFunctionCall();
		} else if (id.equals("duplicatefunctionclause")) {
			ret = getDuplicateFunctionClause();
		} else if (id.equals("duplicatefunctionclauseheader")) {
			ret = getDuplicateFunctionClauseHeader();
		} else if (id.equals("functionanalyze")) {
			before = EditorUtils.createCall("analyze", "Function analyze",
					"Module");
		} else if (id.equals("functionautomate_weights1")) {
			before = EditorUtils.createCall("automate_weights/1",
					"Function automate_weights/1", "Module");
		} else if (id.equals("functionautomate_weights2")) {
			before = EditorUtils.createCall("automate_weights/2",
					"Function automate_weights/2", "Module", "ImageType");
		} else if (id.equals("functioncommands1")) {
			before = EditorUtils.createCall("commands/1",
					"Function commands/1", "Module");
		} else if (id.equals("functioncommands2")) {
			before = EditorUtils.createCall("commands/2",
					"Function commands/2", "Module", "Initial state");
		} else if (id.equals("functiondot")) {
			before = EditorUtils.createCall("dot", "Function dot", "Module");
		} else if (id.equals("functionrun_commands/2")) {
			before = EditorUtils.createCall("run_commands/2",
					"Function run_commands/2", "Module", "List of commands");
		} else if (id.equals("functionrun_commands/3")) {
			before = EditorUtils.createCall("run_commands/3",
					"Function run_commands/2", "Module", "List of commands",
					"Environment");
		} else if (id.equals("functionstate_names")) {
			before = EditorUtils.createCall("state_names", "state_names",
					"Module");
		} else if (id.equals("functionvisualize1")) {
			before = EditorUtils.createCall("visualize/1",
					"Function visualize/1", "Module");
		} else if (id.equals("functionvisualize2")) {
			before = EditorUtils.createCall("visualize/2",
					"Function visualize/2", "Module", "ImageType");
		}
		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}

	protected static String getInitialStateData(boolean useRecord) {
		String ret = "";
		ret += "%% Initialize the state data" + EditorUtils.NEWLINE;
		ret += "initial_state_data() ->" + EditorUtils.NEWLINE;
		ret += EditorUtils.TAB;
		if (!useRecord)
			ret += "#{}.";
		else
			ret += "[].";
		return ret;
	}

	protected static String getInititalState(String initState) {
		String ret = "";
		ret += "%% Identify the initial state" + EditorUtils.NEWLINE;
		ret += "initial_state() ->" + EditorUtils.NEWLINE;
		ret += EditorUtils.TAB + initState + ".";
		return ret;
	}

	protected static InsertionStringPair getSMProperty(String name,
			String module, boolean hasNotSelection) {
		String before;
		before = "prop_" + name + "() ->" + EditorUtils.NEWLINE;
		before += EditorUtils.TAB + "?FORALL(Cmds,commands(" + module + "),"
				+ EditorUtils.NEWLINE;
		before += EditorUtils.TAB + EditorUtils.TAB + "begin"
				+ EditorUtils.NEWLINE;
		before += EditorUtils.TAB + EditorUtils.TAB + EditorUtils.TAB
				+ "{H,S,Res} = run_commands(" + module + ",Cmds),"
				+ EditorUtils.NEWLINE;
		before += EditorUtils.TAB + EditorUtils.TAB + EditorUtils.TAB
				+ "?WHENFAIL(" + EditorUtils.NEWLINE;
		before += EditorUtils.TAB
				+ EditorUtils.TAB
				+ EditorUtils.TAB
				+ EditorUtils.TAB
				+ "io:format(\"History: ~p\\nState: ~p\\nRes: ~p\\n\",[H,S,Res]),";
		if (hasNotSelection)
			before += EditorUtils.NEWLINE + EditorUtils.TAB + EditorUtils.TAB
					+ EditorUtils.TAB + EditorUtils.TAB + "Res == ok";
		else
			before += EditorUtils.NEWLINE;
		String after = ")" + EditorUtils.NEWLINE;
		after += "end).";
		return new InsertionStringPair(before, after);
	}
}
