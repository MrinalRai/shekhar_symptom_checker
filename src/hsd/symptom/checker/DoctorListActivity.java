package hsd.symptom.checker;

import hsd.symptom.checker.database.Doctor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class DoctorListActivity extends Activity {

	private ListView listViewDoctors;
	private DisplayImageOptions options;
	private RequestQueue mRequestQueue;
	private ProgressDialog pd;
	private String TAG = "DoctorListActivity";

	private ArrayList<Doctor> symptomList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_list_activity);

		mRequestQueue = Volley.newRequestQueue(this);
		symptomList = new ArrayList<Doctor>();
		pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Getting list of symptoms...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		listViewDoctors = (ListView) findViewById(R.id.listViewDoctors);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_image)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		findDoctors();
	}

	private void findDoctors() {

		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.get_doctor_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						pd.cancel();

						processData(response);

						ImageAdapter adapter = new ImageAdapter();

						listViewDoctors.setAdapter(adapter);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("TAG", "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(), "Error: ",
								Toast.LENGTH_SHORT).show();
						pd.cancel();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				JSONObject myo = new JSONObject();
				try {
					myo.put("part", "part");
					myo.put("sub_part", "sub_part");

				} catch (JSONException e) {
					e.printStackTrace();
				}
				Log.e(TAG, myo.toString());
				Map<String, String> map = new HashMap<String, String>();
				map.put("check_symptoms", myo.toString());
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	private void processData(String response) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jsonArray = null;
		try {
			jsonArray = jsonObject.getJSONArray("details");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject object = null;
			try {
				object = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				symptomList
						.add(new Doctor(object.getInt("doc_id"), object
								.getString("DisplayName"), object
								.getString("speciality"), object
								.getString("fee"), object.getString("area"),
								object.getString("DocImage")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static class ViewHolder {
		TextView textViewInitials, textViewName, textViewSpeciality,
				textViewLocation;
		ImageView image;
	}

	class ImageAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		ImageAdapter() {
			inflater = LayoutInflater.from(DoctorListActivity.this);
		}

		@Override
		public int getCount() {
			return symptomList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = inflater.inflate(R.layout.doctor_card, parent, false);
				holder = new ViewHolder();
				holder.textViewInitials = (TextView) view
						.findViewById(R.id.textViewInitials);
				holder.textViewName = (TextView) view
						.findViewById(R.id.textViewName);
				holder.textViewSpeciality = (TextView) view
						.findViewById(R.id.textViewSpeciality);
				holder.textViewLocation = (TextView) view
						.findViewById(R.id.textViewLocation);
				holder.image = (ImageView) view
						.findViewById(R.id.circularImageView);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			String name = symptomList.get(position).getDoc_name();
			String[] splited = name.split("\\s+");

			try {
				String fName = splited[0];
				String lName = splited[1];
				holder.textViewInitials.setText(fName.charAt(0) + ""
						+ lName.charAt(0));
			} catch (Exception e) {
			}

			holder.textViewName.setText(name);
			holder.textViewSpeciality.setText(symptomList.get(position)
					.getDoc_speciality());
			holder.textViewLocation.setText(symptomList.get(position)
					.getDoc_area());

			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					DoctorListActivity.this).build();

			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);
			imageLoader.displayImage(symptomList.get(position).getDoc_image(),
					holder.image, options, new ImageLoadingListener() {
						final List<String> displayedImages = Collections
								.synchronizedList(new LinkedList<String>());

						@Override
						public void onLoadingStarted(String imageUri, View view) {
							// TODO Auto-generated method stub
							holder.textViewInitials.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							holder.textViewInitials.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// TODO Auto-generated method stub
							if (loadedImage != null) {
								ImageView imageView = (ImageView) view;
								boolean firstDisplay = !displayedImages
										.contains(imageUri);
								if (firstDisplay) {
									FadeInBitmapDisplayer.animate(imageView,
											500);
									displayedImages.add(imageUri);
								}
								holder.textViewInitials
										.setVisibility(View.GONE);
							}
						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							// TODO Auto-generated method stub
						}
					});

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Intent intent = new Intent(DoctorListActivity.this,
					// DoctorDetailsActivity.class);
					// intent.putExtra("doc_id", symptomList.get(position)
					// .getDoc_id());
					// startActivity(intent);
				}
			});

			return view;
		}
	}
}
