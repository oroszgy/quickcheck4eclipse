package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.eclipse.jface.text.BadLocationException;
import org.erlide.core.erlang.ErlModelException;
import org.protest.integration.lib.textutils.EditorUtils;
import org.protest.integration.lib.textutils.InsertionStringPair;
import org.protest.integration.lib.textutils.InsertionStringPair.Position;
import org.protest.integration.lib.ui.DynamicInputDialog;
import org.protest.integration.lib.ui.NullInputException;


public abstract class AbstractQuickCheckSpecsHandler extends
		AbstractQuickCheckHandler {

	protected static InsertionStringPair getDuplicateFunctionClause()
			throws ErlModelException, BadLocationException, NullInputException {
				String s = EditorUtils.getActiveFunctionClause();
				if (s.equals(null))
					throw new NullInputException();
				s = s.replace(".", ";");
				String before = s;
				return new InsertionStringPair(before, "", Position.BEFORE);
			
			}

	protected static InsertionStringPair getDuplicateFunctionClauseHeader()
			throws NullInputException, ErlModelException, BadLocationException {
				String s = EditorUtils.getActiveFunctionClause();
				if (s.equals(null))
					throw new NullInputException();
				s = s.substring(0, s.indexOf("->") + 2);
				String before = s;
				return new InsertionStringPair(before, "", Position.BEFORE);
			}

	protected static InsertionStringPair getSymbolicFunctionCall()
			throws NullInputException {
				ArrayList<String> input = DynamicInputDialog.run(
						"Smybolic function call", "Module name [?MODULE]",
						"Function name");
				String before = "{call" + EditorUtils.COMMA + input.get(0)
						+ EditorUtils.COMMA + "[";
				String after = "]}";
				return new InsertionStringPair(before, after);
			}

	protected static InsertionStringPair getSMProperty(String name, String module, boolean hasNotSelection) {
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
