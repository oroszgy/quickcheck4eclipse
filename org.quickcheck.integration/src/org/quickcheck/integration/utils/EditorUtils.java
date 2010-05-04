package org.quickcheck.integration.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.quickcheck.integration.ui.DynamicInputDialog;
import org.quickcheck.integration.ui.NullInputException;

/**
 * Utility class, which contain functions, for basic text manipulation in the
 * editor plane.
 * 
 * @author György Orosz
 * 
 */
public class EditorUtils {

	public static final String NEWLINE = System.getProperty("line.separator");
	public static final String COMMA = ", ";
	public static final String TAB = "\t";

	/**
	 * Returns the actual position of the cursor in a text editor
	 * 
	 * @return
	 */
	static public int getActiveSelectionOffset() {
		int offset = ((TextSelection) getEditor().getSelectionProvider()
				.getSelection()).getOffset();
		return offset;
	}

	static public int getActiveSelectionLength() {
		int length = ((TextSelection) getEditor().getSelectionProvider()
				.getSelection()).getLength();
		return length;
	}

	/**
	 * Inserts text asound the current selection
	 * 
	 * @param before
	 *            text before the selection
	 * @param after
	 *            text after the selection
	 */
	static public void insertText(String before, String after) {
		insertText(before, after, getActiveSelectionOffset(),
				getActiveSelectionLength());

	}

	/**
	 * Inserts text before the active editor's selected position
	 * 
	 * @param text
	 */
	static public void insertText(String text) {
		insertText(text, getActiveSelectionOffset());
	}

	/**
	 * Inserts text to the active editor
	 * 
	 * @param offest
	 *            where to insert
	 * @param text
	 *            what to insert
	 */
	static public void insertText(String text, int offset) {
		insertText(text, "", offset, 0);
	}

	/**
	 * Insert texts around a selection
	 * 
	 * @param before
	 *            string inserted before the selection
	 * @param after
	 *            string inserted after the selection
	 * @param offset
	 *            selection offest
	 * @param length
	 *            selection length
	 */
	static protected void insertText(String before, String after, int offset,
			int length) {
		ITextEditor editor = getEditor();
		if (editor == null)
			return;
		IDocumentProvider dp = editor.getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		try {
			String original = doc.get(offset, length);
			doc.replace(offset, length, before + original + after);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns the active text editor object
	 * 
	 * @return
	 */
	static protected ITextEditor getEditor() {
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		IEditorPart part = page.getActiveEditor();
		if (!(part instanceof AbstractTextEditor))
			return null;
		ITextEditor editor = (ITextEditor) part;
		return editor;
	}

	static public String getFileName() {

		String moduleName = getEditor().getEditorInput().getName();

		return moduleName;
	}

	static public String getModuleName() {
		return getFileName().substring(0, getFileName().lastIndexOf("."));
	}

	static public String getAuthorName() {
		return getUserName();
	}

	static public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.UK);
		Date date = new Date();
		return dateFormat.format(date);
	}

	static public String getUserName() {
		return System.getProperty("user.name");
	}

	static public String getMachineName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static StringPair toCompass(String name, String title,
			String... parameters) throws NullInputException {
		ArrayList<String> input = new ArrayList<String>();
		if (parameters != null && parameters.length > 0)
			input = DynamicInputDialog.run(title, parameters);
		String before = name + "(";
		for (String s : input)
			before += s + EditorUtils.COMMA;
		before += EditorUtils.NEWLINE + EditorUtils.TAB;
		String after = ").";
		return new StringPair(before, after);

	}

}
