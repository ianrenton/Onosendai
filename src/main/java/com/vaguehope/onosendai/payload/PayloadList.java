package com.vaguehope.onosendai.payload;

import java.util.ArrayList;
import java.util.List;

import com.vaguehope.onosendai.model.Meta;

public class PayloadList {

	private final List<Payload> payloads;

	public PayloadList (final List<Payload> payloads) {
		this.payloads = new ArrayList<Payload>(payloads);
	}

	Payload findForMeta(final Meta meta) {
		if (meta == null) return null;
		for (final Payload p : this.payloads) {
			if (meta.equals(p.getMeta())) return p;
		}
		return null;
	}

	void addItem (final Payload payload) {
		this.payloads.add(payload);
	}

	void addItemsTop(final List<Payload> items) {
		this.payloads.addAll(0, items);
	}

	void addItemAfter(final Payload toAdd, final Payload marker) {
		if (marker == null) return;
		final int x = this.payloads.indexOf(marker);
		if (x >= 0) {
			this.payloads.add(x + 1, toAdd);
		}
	}

	void replaceItem (final Payload find, final Payload with) {
		for (int i = 0; i < this.payloads.size(); i++) {
			if (find.equals(this.payloads.get(i))) this.payloads.set(i, with);
		}
	}

	void replaceItem (final Payload find, final List<? extends Payload> withs) {
		if (withs == null || withs.size() < 1) return;
		if (withs.size() < 2) {
			replaceItem(find, withs.get(0));
			return;
		}
		for (int i = 0; i < this.payloads.size(); i++) {
			if (find.equals(this.payloads.get(i))) {
				this.payloads.set(i, withs.get(0));
				for (int x = 1; x < withs.size(); x++) {
					this.payloads.add(i + x, withs.get(x));
				}
				break;
			}
		}
	}

	void removeItem (final Payload payload) {
		this.payloads.remove(payload);
	}

	public int size () {
		return this.payloads.size();
	}

	public Payload getPayload (final int index) {
		return this.payloads.get(index);
	}

	public List<Payload> getPayloads () {
		return this.payloads;
	}

	@Override
	public String toString () {
		return new StringBuilder("PayloadList{")
				.append(this.payloads)
				.append("}").toString();
	}

}
