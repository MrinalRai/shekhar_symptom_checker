package hsd.symptom.checker;

import hsd.symptom.checker.circularimageview.CropOption;
import hsd.symptom.checker.circularimageview.CropOptionAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ProfileEditActivity extends ActionBarActivity {

	private Toolbar toolbar;

	private ImageView circularImageView;
	private Spinner spinner_blood_group;
	private EditText editText_name, editTextAllergies,
			editTextPrimaryMedicalConditions;
	private TextView textViewInitials, textView_email;

	private String image, name, email, blood_group, allergies = "-",
			medical_conditions = "-";

	private Uri mImageCaptureUri;
	private static final int PICK_FROM_CAMERA = 1;
	private static final int CROP_FROM_CAMERA = 2;
	private static final int PICK_FROM_FILE = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_edit_layout);

		// Set a toolbar which will replace the action bar.
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Edit Profile");
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

		image = getIntent().getStringExtra("image");
		name = getIntent().getStringExtra("name");
		email = getIntent().getStringExtra("email");
		blood_group = getIntent().getStringExtra("blood_group");
		allergies = getIntent().getStringExtra("allergies");
		medical_conditions = getIntent().getStringExtra("medical_conditions");

		circularImageView = (ImageView) findViewById(R.id.circularImageView);
		textViewInitials = (TextView) findViewById(R.id.textViewInitials);
		editText_name = (EditText) findViewById(R.id.editTextName);
		textView_email = (TextView) findViewById(R.id.textView_email);
		editTextAllergies = (EditText) findViewById(R.id.editTextAllergies);
		editTextPrimaryMedicalConditions = (EditText) findViewById(R.id.editTextPrimaryMedicalConditions);

		spinner_blood_group = (Spinner) findViewById(R.id.spinner_blood_group);
		final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.blood_group));

		// Drop down layout style - list view
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner_blood_group
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						blood_group = dataAdapter.getItem(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		findViewById(R.id.textView_edit_profile).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
						if (editTextAllergies.getText().length() > 0) {
							allergies = editTextAllergies.getText().toString();
						}
						if (editTextPrimaryMedicalConditions.getText().length() > 0) {
							medical_conditions = editTextPrimaryMedicalConditions
									.getText().toString();
						}
						if (editText_name.getText().length() > 0) {
							String[] nameSplit = editText_name.getText()
									.toString().split(" ");
							if (nameSplit.length == 1) {
								editText_name
										.setError("enter firstname lastname");
								editText_name.requestFocus();
							} else {
								editText_name.setError(null);
								final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
								globalVariable.setName(editText_name.getText()
										.toString());
								globalVariable
										.setImage(bitMapToString(((BitmapDrawable) circularImageView
												.getDrawable()).getBitmap()));
								globalVariable.setBlood_group(blood_group);
								globalVariable.setAllergies(allergies);
								globalVariable
										.setMedical_conditions(medical_conditions);
								globalVariable.setSent(0);

								finish();
								Intent intent = new Intent(
										ProfileEditActivity.this,
										ProfileViewActivity.class);
								startActivity(intent);
							}
						} else {
							editText_name
									.setError("Please dont leave name blank");
						}
					}
				});

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_image)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		editText_name.setText(name);
		int position = editText_name.length();
		editText_name.setSelection(position);

		textView_email.setText(email);
		editTextAllergies.setText(allergies);
		editTextPrimaryMedicalConditions.setText(medical_conditions);

		String[] splited = name.split("\\s+");
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
		imageLoader.displayImage(image, circularImageView, options,
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

		final String[] items = new String[] { "Take from camera",
				"Select from gallery" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, items);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Image");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) { // pick from
																	// camera
				if (item == 0) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

					mImageCaptureUri = Uri.fromFile(new File(Environment
							.getExternalStorageDirectory(), "tmp_avatar_"
							+ String.valueOf(System.currentTimeMillis())
							+ ".jpg"));

					intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
							mImageCaptureUri);

					try {
						intent.putExtra("return-data", true);

						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (ActivityNotFoundException e) {
						e.printStackTrace();
					}
				} else { // pick from file
					Intent intent = new Intent();

					intent.setType("image/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);

					startActivityForResult(Intent.createChooser(intent,
							"Complete action using"), PICK_FROM_FILE);
				}
			}
		});

		final AlertDialog dialog = builder.create();

		circularImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.show();
			}
		});
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;

		switch (requestCode) {
		case PICK_FROM_CAMERA:
			doCrop();

			break;

		case PICK_FROM_FILE:
			mImageCaptureUri = data.getData();

			doCrop();

			break;

		case CROP_FROM_CAMERA:
			Bundle extras = data.getExtras();

			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");

				circularImageView.setImageBitmap(photo);
				textViewInitials.setVisibility(View.GONE);
			}

			File f = new File(mImageCaptureUri.getPath());

			if (f.exists())
				f.delete();

			break;

		}
	}

	private void doCrop() {
		final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setType("image/*");

		List<ResolveInfo> list = getPackageManager().queryIntentActivities(
				intent, 0);

		int size = list.size();

		if (size == 0) {
			Toast.makeText(this, "Can not find image crop app",
					Toast.LENGTH_SHORT).show();

			return;
		} else {
			intent.setData(mImageCaptureUri);

			intent.putExtra("outputX", 200);
			intent.putExtra("outputY", 200);
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			intent.putExtra("scale", true);
			intent.putExtra("return-data", true);

			if (size == 1) {
				Intent i = new Intent(intent);
				ResolveInfo res = list.get(0);

				i.setComponent(new ComponentName(res.activityInfo.packageName,
						res.activityInfo.name));

				startActivityForResult(i, CROP_FROM_CAMERA);
			} else {
				for (ResolveInfo res : list) {
					final CropOption co = new CropOption();

					co.title = getPackageManager().getApplicationLabel(
							res.activityInfo.applicationInfo);
					co.icon = getPackageManager().getApplicationIcon(
							res.activityInfo.applicationInfo);
					co.appIntent = new Intent(intent);

					co.appIntent
							.setComponent(new ComponentName(
									res.activityInfo.packageName,
									res.activityInfo.name));

					cropOptions.add(co);
				}

				CropOptionAdapter adapter = new CropOptionAdapter(
						getApplicationContext(), cropOptions);

				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Choose Crop App");
				builder.setAdapter(adapter,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								startActivityForResult(
										cropOptions.get(item).appIntent,
										CROP_FROM_CAMERA);
							}
						});

				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {

						if (mImageCaptureUri != null) {
							getContentResolver().delete(mImageCaptureUri, null,
									null);
							mImageCaptureUri = null;
						}
					}
				});

				AlertDialog alert = builder.create();

				alert.show();
			}
		}
	}

	public String bitMapToString(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] b = baos.toByteArray();
		String temp = Base64.encodeToString(b, Base64.DEFAULT);
		return temp;
	}
}
