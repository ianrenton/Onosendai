package com.vaguehope.onosendai.ui.pref;

import java.util.regex.PatternSyntaxException;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.vaguehope.onosendai.R;
import com.vaguehope.onosendai.model.Filters;
import com.vaguehope.onosendai.util.DialogHelper.Listener;

public class FilterDialog {

	private final View llParent;
	private final EditText txtFilter;
	private final CheckBox chkDelete;
	private Listener<Boolean> onValidateListener;

	public FilterDialog (final Context context) {
		this(context, null, false);
	}

	public FilterDialog (final Context context, final String initialValue, final boolean deleteable) {
		final LayoutInflater inflater = LayoutInflater.from(context);
		this.llParent = inflater.inflate(R.layout.filterdialog, null);

		this.txtFilter = (EditText) this.llParent.findViewById(R.id.txtFilter);
		this.chkDelete = (CheckBox) this.llParent.findViewById(R.id.chkDelete);

		this.txtFilter.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged (final CharSequence s, final int start, final int before, final int count) {}

			@Override
			public void beforeTextChanged (final CharSequence s, final int start, final int count, final int after) {}

			@Override
			public void afterTextChanged (final Editable s) {
				validateAndNotify(s);
			}
		});

		if (initialValue != null) this.txtFilter.setText(initialValue);
		this.chkDelete.setChecked(false);
		this.chkDelete.setVisibility(deleteable ? View.VISIBLE : View.GONE);
	}

	public View getRootView () {
		return this.llParent;
	}

	public boolean isDeleteSelected () {
		return this.chkDelete.isChecked();
	}

	public String getValue () {
		return this.txtFilter.getText().toString();
	}

	public void setOnValidateListener (final Listener<Boolean> listener) {
		this.onValidateListener = listener;
	}

	public void validateAndNotify () {
		validateAndNotify(this.txtFilter.getText());
	}

	protected void validateAndNotify (final Editable s) {
		if (this.onValidateListener == null) return;
		this.onValidateListener.onAnswer(s.length() > 0 && validateFilter(s));
	}

	private static boolean validateFilter (final Editable s) {
		try {
			Filters.validateFilter(s.toString());
			return true;
		}
		catch (final PatternSyntaxException e) {
			return false;
		}
	}

}
