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
