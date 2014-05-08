/*
 * Copyright (c) 2014 Amahi
 *
 * This file is part of Amahi.
 *
 * Amahi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Amahi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Amahi. If not, see <http ://www.gnu.org/licenses/>.
 */

package org.amahi.anywhere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.amahi.anywhere.R;
import org.amahi.anywhere.server.model.ServerFile;

import java.util.Collections;
import java.util.List;

public class ServerFilesAdapter extends BaseAdapter
{
	private final LayoutInflater layoutInflater;

	private List<ServerFile> files;

	public ServerFilesAdapter(Context context) {
		this.layoutInflater = LayoutInflater.from(context);

		this.files = Collections.emptyList();
	}

	public void replaceWith(List<ServerFile> files) {
		this.files = files;

		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return files.size();
	}

	@Override
	public ServerFile getItem(int position) {
		return files.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup container) {
		ServerFile file = getItem(position);

		if (view == null) {
			view = newView(container);
		}

		bindView(file, view);

		return view;
	}

	private View newView(ViewGroup container) {
		return layoutInflater.inflate(R.layout.view_list_item, container, false);
	}

	private void bindView(ServerFile file, View view) {
		TextView fileView = (TextView) view;

		fileView.setText(getFileName(file));
		fileView.setCompoundDrawablesWithIntrinsicBounds(getFileIcon(file), 0, 0, 0);
	}

	private String getFileName(ServerFile file) {
		return file.getName();
	}

	private int getFileIcon(ServerFile file) {
		String type = file.getMime().split("/")[0];

		if (type.equals("audio")) {
			return R.drawable.ic_doc_audio;
		}

		if (type.equals("video")) {
			return R.drawable.ic_doc_video;
		}

		if (type.equals("image")) {
			return R.drawable.ic_doc_image;
		}

		if (type.equals("text")) {
			return R.drawable.ic_doc_text;
		}

		return R.drawable.ic_doc_generic;
	}
}