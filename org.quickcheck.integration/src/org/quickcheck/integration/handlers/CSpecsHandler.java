package org.quickcheck.integration.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.text.BadLocationException;
import org.erlide.core.erlang.ErlModelException;
import org.protest.integration.lib.textutils.EditorUtils;
import org.protest.integration.lib.textutils.InsertionStringPair;
import org.protest.integration.lib.ui.NullInputException;

public class CSpecsHandler extends AbstractQuickCheckHandler implements
		IHandler {

	@Override
	protected InsertionStringPair getInsertionString(String id)
			throws NullInputException, ErlModelException, BadLocationException {
		String before = "";
		String after = "";
		ArrayList<String> input;
		InsertionStringPair ret = null;

		if (id.equals("fulleqc_cmoduleheader")) {
			before = getHeader();
			before += getIncludeEqc() + EditorUtils.NEWLINE;
			before += getInsertionString("includeeqc_c") + EditorUtils.NEWLINE
					+ EditorUtils.NEWLINE;
			before += getCompileAll();
		}

		else if (id.equals("includeeqc_c")) {
			before += "-include_lib(\"eqc/include/eqc_c.hrl\").";
		}

		else if (id.equals("functionadd_to_ptr")) {
			before = EditorUtils.createCall("add_to_ptr",
					"Function add_to_ptr", "Pointer", "N");
		}

		else if (id.equals("functionalloc")) {
			before = EditorUtils.createCall("alloc", "Function alloc", "Type",
					"Value");
		}

		else if (id.equals("functionarray_index2")) {
			before = EditorUtils.createCall("array_index/2",
					"Function array_index/2", "Pointer", "Index");
		}

		else if (id.equals("functionarray_index3")) {
			before = EditorUtils.createCall("array_index/3",
					"Function array_index/3", "Pointer", "Index", "New value");
		}

		else if (id.equals("functioncast_ptr")) {
			before = EditorUtils.createCall("cast_ptr", "Function cast_ptr",
					"Type", "Pointer");
		}

		else if (id.equals("functioncreate_array")) {
			before = EditorUtils.createCall("create_array",
					"Function create_array", "Type", "Values (list)");
		}

		else if (id.equals("functioncreate_string")) {
			before = EditorUtils.createCall("create_string",
					"Function create_string", "String");
		}

		else if (id.equals("functionderef")) {
			before = EditorUtils.createCall("deref", "Function deref",
					"Pointer");
		}

		else if (id.equals("functionfree")) {
			before = EditorUtils.createCall("free", "Function free", "Pointer");
		}

		else if (id.equals("functionread_array")) {
			before = EditorUtils.createCall("read_array",
					"Function read_array", "Pointer", "Length");
		}

		else if (id.equals("functionread_string")) {
			before = EditorUtils.createCall("read_string",
					"Function read_string", "Pointer");
		}

		else if (id.equals("functionrestart")) {
			before = EditorUtils.createCall("restart", "Function restart");
		}

		else if (id.equals("functionset_timeout")) {
			before = EditorUtils.createCall("set_timeout",
					"Function set_timeout", "Timeout (ms)");
		}

		else if (id.equals("functionsizeof")) {
			before = EditorUtils
					.createCall("sizeof", "Function sizeof", "Type");
		}

		else if (id.equals("functionstart1")) {
			before = EditorUtils.createCall("start/1", "Function start/1",
					"Module");
		}

		else if (id.equals("functionstart2")) {
			before = EditorUtils.createCall("start/2", "Function start/2",
					"Module", "Source-file");
		}

		else if (id.equals("functionstart3")) {
			before = EditorUtils.createCall("start/3", "Function start/3",
					"Module", "Source-file", "Options");
		}

		else if (id.equals("functionstart_functions")) {
			before = EditorUtils.createCall("start_functions",
					"Function start_functions", "Module", "Functions");
		}

		else if (id.equals("functionstop")) {
			before = EditorUtils.createCall("stop", "Function stop");
		}

		else if (id.equals("functionstore")) {
			before = EditorUtils.createCall("store", "Function store",
					"Pointer", "New value");
		}

		else if (id.equals("functionstore_array")) {
			before = EditorUtils.createCall("store_array",
					"Function store_array", "Pointer", "Values (list)");
		}

		if (ret != null)
			return ret;
		return new InsertionStringPair(before, after);
	}
}
