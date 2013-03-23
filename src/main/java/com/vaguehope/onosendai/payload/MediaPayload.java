package com.vaguehope.onosendai.payload;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vaguehope.onosendai.R;
import com.vaguehope.onosendai.model.Meta;
import com.vaguehope.onosendai.payload.PayloadListAdapter.RowView;
import com.vaguehope.onosendai.util.EqualHelper;

public class MediaPayload extends Payload {

	private final String url;

	public MediaPayload (final Meta meta) {
		this(meta.getData());
	}

	public MediaPayload (final String url) {
		super(PayloadType.MEDIA);
		this.url = url;
	}

	public String getUrl () {
		return this.url;
	}

	@Override
	public String getTitle () {
		return this.url;
	}

	@Override
	public boolean intentable () {
		return true;
	}

	@Override
	public Intent toIntent () {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(this.url));
		return i;
	}


	@Override
	public PayloadLayout getLayout () {
		return PayloadLayout.TEXT_IMAGE;
	}

	@Override
	public RowView makeRowView (final View view) {
		return new RowView((TextView) view.findViewById(R.id.txtMain), (ImageView) view.findViewById(R.id.imgMain));
	}

	@Override
	public int hashCode () {
		return this.url.hashCode();
	}

	@Override
	public boolean equals (final Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof MediaPayload)) return false;
		MediaPayload that = (MediaPayload) o;
		return EqualHelper.equal(this.url, that.url);
	}

}