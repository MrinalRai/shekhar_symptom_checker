package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;
import hsd.symptom.checker.navigation.drawer.Fragment1;
import hsd.symptom.checker.navigation.drawer.Fragment2;
import hsd.symptom.checker.navigation.drawer.FragmentMyAppointments;
import hsd.symptom.checker.navigation.drawer.FragmentSettings;
import hsd.symptom.checker.navigation.drawer.NavigationDrawerFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class Symptom_Checker_Menu_Activity extends ActionBarActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom_checker_menu);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		// Set a toolbar which will replace the action bar.
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

		// Load Fragment1 when the app starts
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, new Fragment1()).commit();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mNavigationDrawerFragment.getDrawerToggle().syncState();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {

		FragmentManager fragmentManager = getSupportFragmentManager();

		switch (position) {
		case 0:

			fragmentManager.beginTransaction()
					.replace(R.id.container, new Fragment1()).commit();
			toolbar.setTitle("Symptom Checker");
			break;
		case 1:
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.container, new Fragment2()).commit();
			toolbar.setTitle("BMI Calculator");
			break;
		case 2:
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.container, new FragmentMyAppointments()).commit();
			toolbar.setTitle("My Appointments");
			break;
		case 3:
			fragmentManager.popBackStack(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fragmentManager.beginTransaction()
					.replace(R.id.container, new FragmentSettings()).commit();
			toolbar.setTitle("Settings");
			break;
		case 4:
			SharedPreferences prefs = getSharedPreferences(
					Constant.MyPREFERENCES, MODE_PRIVATE);
			prefs.edit().clear().commit();
			finish();
			startActivity(new Intent(Symptom_Checker_Menu_Activity.this,
					Login_Activity.class));
			break;
		}

	}
}
