package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				finish();

				SharedPreferences prefs = getSharedPreferences(
						Constant.MyPREFERENCES, MODE_PRIVATE);
				boolean logged_in = prefs.getBoolean(Constant.USER_LOGGED_IN,
						false);
				if (logged_in) {
					startActivity(new Intent(Splash_Screen_Activity.this,
							Symptom_Checker_Menu_Activity.class));
				} else {
					startActivity(new Intent(Splash_Screen_Activity.this,
							Login_Activity.class));
				}
			}
		}, 2500);
	}
}
