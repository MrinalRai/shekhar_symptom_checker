package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.ProfileViewActivity;
import hsd.symptom.checker.R;
import hsd.symptom.checker.constant.Constant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

	/**
	 * Remember the position of the selected item.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * A pointer to the current callbacks instance (the Activity).
	 */
	private NavigationDrawerCallbacks mCallbacks;

	public ActionBarDrawerToggle getDrawerToggle() {
		return mDrawerToggle;
	}

	/**
	 * Helper component that ties the action bar to the navigation drawer.
	 */
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private View mDrawerView;
	private ListView mDrawerListView;
	private ImageView circularImageView;
	private TextView textViewInitials, textView_name, textView_email;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;

	public NavigationDrawerFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mDrawerView = inflater.inflate(R.layout.fragment_navigation_drawer,
				container, false);
		mDrawerListView = (ListView) mDrawerView
				.findViewById(R.id.listView_menu);

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_image)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		circularImageView = (ImageView) mDrawerView
				.findViewById(R.id.circularImageView);
		textViewInitials = (TextView) mDrawerView
				.findViewById(R.id.textViewInitials);
		textView_name = (TextView) mDrawerView.findViewById(R.id.textView_name);
		textView_email = (TextView) mDrawerView
				.findViewById(R.id.textView_email);

		SharedPreferences prefs = getActivity().getSharedPreferences(
				Constant.MyPREFERENCES, Context.MODE_PRIVATE);
		String logged_in_name = prefs.getString(Constant.USER_LOGGED_IN_NAME,
				"");
		String logged_in_email = prefs.getString(Constant.USER_LOGGED_IN_EMAIL,
				"");
		String logged_in_image = prefs.getString(Constant.USER_LOGGED_IN_IMAGE,
				"");

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
				getActivity()).build();

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

		mDrawerListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						selectItem(position);
					}
				});

		ArrayList<NavDrawerItem> drawerItems = new ArrayList<>();
		drawerItems.add(new NavDrawerItem(R.drawable.ic_symptom_checker,
				"Symptom Checker"));
		drawerItems.add(new NavDrawerItem(R.drawable.ic_bmi_calculator,
				"BMI Calculator"));
		drawerItems.add(new NavDrawerItem(R.drawable.ic_book_appointment,
				"My Appointments"));
		drawerItems.add(new NavDrawerItem(R.drawable.ic_setting, "Settings"));
		drawerItems.add(new NavDrawerItem(R.drawable.ic_logout, "Logout"));

		mDrawerListView.setAdapter(new DrawerAdapter(this.getActivity(),
				drawerItems));
		mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

		mDrawerView.findViewById(R.id.linearLayout_profile_view)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(getActivity(),
								ProfileViewActivity.class));
					}
				});

		mDrawerView.findViewById(R.id.textView_about_healthserve)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(getActivity(), "About Health Serve",
								Toast.LENGTH_LONG).show();
					}
				});

		return mDrawerView;
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 *
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		toolbar, /* nav drawer image to replace 'Up' caret */
		R.string.open_drawer, /* "open drawer" description for accessibility */
		R.string.close_drawer /* "close drawer" description for accessibility */
		) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}
				getActivity().supportInvalidateOptionsMenu(); // calls
																// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}
				getActivity().supportInvalidateOptionsMenu(); // calls
																// onPrepareOptionsMenu()
			}
		};
		mDrawerToggle.setDrawerIndicatorEnabled(true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mDrawerListView != null) {
			mDrawerListView.setItemChecked(position, true);
		}
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(position);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Callbacks interface that all activities using this fragment must
	 * implement.
	 */
	public static interface NavigationDrawerCallbacks {
		/**
		 * Called when an item in the navigation drawer is selected.
		 */
		void onNavigationDrawerItemSelected(int position);
	}
}