package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class BackHandMappingActivity extends Activity {

	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_back_hand_mapping);
		cd = new ConnectionDetector(this);

		findViewById(R.id.man_back_hand).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y);
							if (cd.isConnectingToInternet()) {
								if (Color.rgb(237, 28, 36) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Shoulder");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Shoulder", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(255, 242, 0) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Upper Arm");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Upper Arm", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(34, 177, 76) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Elbow");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Elbow", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(255, 174, 201) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Fore Arm");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Fore Arm", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(63, 72, 204) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Wrist");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Wrist", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(153, 217, 234) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Hand");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Hand", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(181, 230, 29) == colour) {
									Intent intent = new Intent(
											BackHandMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Hand");
									intent.putExtra("sub_part", "Fingers");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Fingers", Toast.LENGTH_SHORT)
											.show();
								}
							} else {
								Toast.makeText(BackHandMappingActivity.this,
										"Not connected to internet!",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_back_hand_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
