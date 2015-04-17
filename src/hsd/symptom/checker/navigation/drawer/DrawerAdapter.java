package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrawerAdapter extends BaseAdapter {

	/**
	 * LayoutInflater instance for inflating the requested layout in the list
	 * view
	 */
	private LayoutInflater mInflater;

	private ArrayList<NavDrawerItem> mDataSet;

	/**
	 * Default constructor
	 */
	public DrawerAdapter(Context context, ArrayList<NavDrawerItem> drawerItems) {

		mInflater = LayoutInflater.from(context);
		mDataSet = drawerItems;

	}

	public int getCount() {
		return mDataSet.size();
	}

	public Object getItem(int index) {
		return mDataSet.get(index);
	}

	public long getItemId(int index) {
		return index;
	}

	public View getView(int position, View recycledView, ViewGroup parent) {
		ViewHolder holder;

		if (recycledView == null) {

			holder = new ViewHolder();
			recycledView = mInflater.inflate(R.layout.item_drawer, parent,
					false);
			holder.image = (ImageView) recycledView.findViewById(R.id.image);
			holder.title = (TextView) recycledView.findViewById(R.id.title);

			recycledView.setTag(holder);

		} else {
			holder = (ViewHolder) recycledView.getTag();
		}

		holder.image.setImageResource(mDataSet.get(position).getImage());
		holder.title.setText(mDataSet.get(position).getTitle());

		return recycledView;
	}

	private static class ViewHolder {
		ImageView image;
		TextView title;
	}
}