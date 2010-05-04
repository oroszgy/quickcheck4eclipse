package org.quickcheck.integration.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.quickcheck.integration.ui.NullInputException;
import org.quickcheck.integration.utils.EditorUtils;
import org.quickcheck.integration.utils.StringPair;

public abstract class AbstractQuickCheckHandler extends AbstractHandler {

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

	protected abstract StringPair getInsertionString(String id)
			throws NullInputException;

}
