package org.quickcheck.integration.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;
import org.erlide.core.erlang.ErlModelException;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.InsertionStringPair;

public abstract class AbstractQuickCheckHandler extends AbstractHandler {

	public static String getHeader() {
		String userAtMachine = "<" + EditorUtils.getUserName() + "@"
				+ EditorUtils.getMachineName() + ">";
		return "%%% File\t: " + EditorUtils.getFileName() + EditorUtils.NEWLINE
				+ "%%% Author\t: " + EditorUtils.getAuthorName() + " "
				+ userAtMachine + EditorUtils.NEWLINE + "%%% Description\t: "
				+ EditorUtils.NEWLINE + "%%% Created\t: "
				+ EditorUtils.getDate() + " by " + EditorUtils.getAuthorName()
				+ " " + userAtMachine + EditorUtils.NEWLINE
				+ EditorUtils.NEWLINE + "-module("
				+ EditorUtils.getModuleName() + ")." + EditorUtils.NEWLINE
				+ EditorUtils.NEWLINE;
	}

	public static String getIncludeEqc() {
		return "-include_lib(\"eqc/include/eqc.hrl\").";
	}

	public static String getCompileAll() {
		return "-compile(export_all).";
	}

	@Override
	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String s = event.getCommand().getId();
		String[] sparts = s.split("\\.");
		String id = sparts[sparts.length - 1];
		try {

			InsertionStringPair insertionStringPair = getInsertionString(id);
			EditorUtils.insertText(insertionStringPair);
		} catch (Exception e) {
			// The execution has terminated
		}
		return null;

	}

	protected abstract InsertionStringPair getInsertionString(String id)
			throws NullInputException, ErlModelException, BadLocationException;

	public String getIncludeEqcStatem() {
		return "-include_lib(\"eqc/include/eqc_statem.hrl\").";
	}

}
