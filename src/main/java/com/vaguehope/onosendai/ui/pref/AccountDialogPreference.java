package com.vaguehope.onosendai.ui.pref;

import org.json.JSONException;

import android.content.Context;
import android.preference.DialogPreference;
import android.view.View;

import com.vaguehope.onosendai.config.Account;
import com.vaguehope.onosendai.util.DialogHelper;

/*
 * https://developer.android.com/intl/fr/guide/topics/ui/settings.html
 * Useful: http://d.hatena.ne.jp/hidecheck/20100905/1283706015
 */
public class AccountDialogPreference extends DialogPreference {

	private final Account account;
	private final AccountsPrefFragment accountsPrefFragment;
	private AccountDialog dialog;

	public AccountDialogPreference (final Context context, final Account account, final AccountsPrefFragment accountsPrefFragment) {
		super(context, null);
		this.account = account;
		this.accountsPrefFragment = accountsPrefFragment;

		setKey(account.getId());
		setTitle(account.getUiTitle());

		switch(account.getProvider()) {
			case TWITTER:
			case SUCCESSWHALE:
			case INSTAPAPER:
				break;
			default:
				setEnabled(false);
		}

		setDialogTitle("Edit Account (" + getKey() + ")");
		setPositiveButtonText(android.R.string.ok);
		setNegativeButtonText(android.R.string.cancel);
	}

	@Override
	protected View onCreateDialogView () {
		this.dialog = new AccountDialog(getContext(), this.account);
		return this.dialog.getRootView();
	}

	@Override
	protected void onDialogClosed (final boolean positiveResult) {
		if (positiveResult) {
			try {
				if (this.dialog.isDeleteSelected()) {
					this.accountsPrefFragment.askDeleteAccount(this.dialog.getInitialValue());
				}
				else if (this.dialog.isSaveable()) {
					persistString(this.dialog.getValue().toJson().toString());
					this.accountsPrefFragment.refreshAccountsList();
				}
			}
			catch (JSONException e) {
				DialogHelper.alert(getContext(), e);
			}
		}
		super.onDialogClosed(positiveResult);
	}

}
