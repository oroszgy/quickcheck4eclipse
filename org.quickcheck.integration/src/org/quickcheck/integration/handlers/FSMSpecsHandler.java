package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.text.BadLocationException;
import org.erlide.core.erlang.ErlModelException;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.InsertionStringPair;

public class FSMSpecsHandler extends AbstractQuickCheckHandler implements
		IHandler {

	@Override
	protected InsertionStringPair getInsertionString(String id)
			throws NullInputException, ErlModelException, BadLocationException {

		String before = "";
		String after = "";
		ArrayList<String> input = null;
		InsertionStringPair ret = null;
		if (id.equals("org.quickcheck.fsmspecs.completeeqc_fsm spec")) {
		} else if (id
				.equals("org.quickcheck.fsmspecs.fulleqc_fsm module header")) {
		} else if (id.equals("org.quickcheck.fsmspecs.includeeqc_fsm")) {
		} else if (id.equals("org.quickcheck.fsmspecs.initial_state")) {
		} else if (id.equals("org.quickcheck.fsmspecs.initial_state_data")) {
		} else if (id.equals("org.quickcheck.fsmspecs.next_state_data")) {
		} else if (id.equals("org.quickcheck.fsmspecs.precondition")) {
		} else if (id.equals("org.quickcheck.fsmspecs.postcondition")) {
		} else if (id.equals("org.quickcheck.fsmspecs.fsmproperty")) {
		} else if (id.equals("org.quickcheck.fsmspecs.transitionweight")) {
		} else if (id.equals("org.quickcheck.fsmspecs.symbolicfunctioncall")) {
		} else if (id.equals("org.quickcheck.fsmspecs.duplicatefunctionclause")) {
		} else if (id
				.equals("org.quickcheck.fsmspecs.duplicatefunctionclauseheader")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionanalyze")) {
		} else if (id
				.equals("org.quickcheck.fsmspecs.functionautomate_weights1")) {
		} else if (id
				.equals("org.quickcheck.fsmspecs.functionautomate_weights2")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functioncommands1")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functioncommands2")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functiondot")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionrun_commands/2")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionrun_commands/3")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionstate_names")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionvisualize1")) {
		} else if (id.equals("org.quickcheck.fsmspecs.functionvisualize2")) {
		}
		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}

}
