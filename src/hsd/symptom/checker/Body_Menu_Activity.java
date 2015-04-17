package hsd.symptom.checker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Body_Menu_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_menu);

		findViewById(R.id.button_head).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								HeadActivity.class));
					}
				});

		findViewById(R.id.button_left_hand).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								HandActivity.class));
					}
				});

		findViewById(R.id.button_right_hand).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								HandActivity.class));
					}
				});

		findViewById(R.id.button_chest).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								ChestActivity.class));
					}
				});
		findViewById(R.id.button_abdomen).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								AbdomenActivity.class));
					}
				});

		findViewById(R.id.button_pelvic).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								PelvicActivity.class));
					}
				});
		findViewById(R.id.button_left_leg).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								LegActivity.class));
					}
				});
		findViewById(R.id.button_right_leg).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(new Intent(Body_Menu_Activity.this,
								LegActivity.class));
					}
				});
	}
}
