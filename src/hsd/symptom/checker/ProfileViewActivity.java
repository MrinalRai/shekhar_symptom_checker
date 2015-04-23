package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ProfileViewActivity extends ActionBarActivity {

	private Toolbar toolbar;

	private ImageView circularImageView;
	private TextView textViewInitials, textView_name, textView_email,
			textViewBloodGroup, textViewAllergies,
			textViewPrimaryMedicalConditions;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);

		// Set a toolbar which will replace the action bar.
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Profile");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		SharedPreferences prefs = getSharedPreferences(Constant.MyPREFERENCES,
				Context.MODE_PRIVATE);
		final String logged_in_name = prefs.getString(
				Constant.USER_LOGGED_IN_NAME, "");
		final String logged_in_email = prefs.getString(
				Constant.USER_LOGGED_IN_EMAIL, "");
		final String logged_in_image = prefs.getString(
				Constant.USER_LOGGED_IN_IMAGE, "");
		final String logged_in_blood_group = prefs.getString(
				Constant.USER_LOGGED_IN_BLOOD_GROUP, "");
		final String logged_in_allergies = prefs.getString(
				Constant.USER_LOGGED_IN_ALLERGIES, "");
		final String logged_in_medical_conditions = prefs.getString(
				Constant.USER_LOGGED_IN_MEDICAL_CONDITIONS, "");

		findViewById(R.id.textView_edit_profile).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
						if (globalVariable.getSent() == 0) {
							Intent intent = new Intent(
									ProfileViewActivity.this,
									ProfileEditActivity.class);
							intent.putExtra("image", globalVariable.getImage());
							intent.putExtra("name", globalVariable.getName()
									+ "ss as");
							intent.putExtra("email", logged_in_email);
							intent.putExtra("blood_group",
									globalVariable.getBlood_group());
							intent.putExtra("allergies",
									globalVariable.getAllergies());
							intent.putExtra("medical_conditions",
									globalVariable.getMedical_conditions());
							finish();
							startActivity(intent);
						} else {
							Intent intent = new Intent(
									ProfileViewActivity.this,
									ProfileEditActivity.class);
							intent.putExtra("image", logged_in_image);
							intent.putExtra("name", logged_in_name);
							intent.putExtra("email", logged_in_email);
							intent.putExtra("blood_group",
									logged_in_blood_group);
							intent.putExtra("allergies", logged_in_allergies);
							intent.putExtra("medical_conditions",
									logged_in_medical_conditions);
							finish();
							startActivity(intent);
						}
					}
				});

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_image)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		circularImageView = (ImageView) findViewById(R.id.circularImageView);
		textViewInitials = (TextView) findViewById(R.id.textViewInitials);
		textView_name = (TextView) findViewById(R.id.textView_name);
		textView_email = (TextView) findViewById(R.id.textView_email);
		textViewBloodGroup = (TextView) findViewById(R.id.textViewBloodGroup);
		textViewAllergies = (TextView) findViewById(R.id.textViewAllergies);
		textViewPrimaryMedicalConditions = (TextView) findViewById(R.id.textViewPrimaryMedicalConditions);

		textView_name.setText(logged_in_name);
		textView_email.setText(logged_in_email);

		String[] splited = logged_in_name.split("\\s+");
		try {
			String fName = splited[0];
			String lName = splited[1];
			textViewInitials.setText(fName.charAt(0) + "" + lName.charAt(0));
		} catch (Exception e) {
		}

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).build();

		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
		imageLoader.displayImage(logged_in_image, circularImageView, options,
				new ImageLoadingListener() {
					final List<String> displayedImages = Collections
							.synchronizedList(new LinkedList<String>());

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						textViewInitials.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						textViewInitials.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						if (loadedImage != null) {
							ImageView imageView = (ImageView) view;
							boolean firstDisplay = !displayedImages
									.contains(imageUri);
							if (firstDisplay) {
								FadeInBitmapDisplayer.animate(imageView, 500);
								displayedImages.add(imageUri);
							}
							textViewInitials.setVisibility(View.GONE);
						}
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						// TODO Auto-generated method stub
					}
				});

		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
		if (globalVariable.getSent() == 0) {
			textView_name.setText(globalVariable.getName());
			circularImageView.setImageBitmap(StringToBitMap(globalVariable
					.getImage()));
			textViewBloodGroup.setText(globalVariable.getBlood_group());
			textViewAllergies.setText(globalVariable.getAllergies());
			textViewPrimaryMedicalConditions.setText(globalVariable
					.getMedical_conditions());
			globalVariable.setSent(0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			return true;
		default:
			return false;
		}
	}

	public Bitmap StringToBitMap(String encodedString) {
		try {
			byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
					encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
}
