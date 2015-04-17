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

public class HeadMappingActivity extends Activity {

	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_head_mapping);
		cd = new ConnectionDetector(this);

		findViewById(R.id.man_front_head).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y);
							if (cd.isConnectingToInternet()) {
								if (Color.rgb(255, 174, 201) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "ForeHead");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"ForeHead", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(237, 28, 36) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Eye");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Eye", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(34, 177, 76) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Ear");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Ear", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(255, 201, 14) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Nose");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Nose", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(153, 217, 234) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Cheek");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Cheek", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(63, 72, 204) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Lips");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Lips", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(181, 230, 29) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Teeth");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Teeth", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(218, 116, 252) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Chin");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Chin", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(120, 241, 248) == colour) {
									Intent intent = new Intent(
											HeadMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Head");
									intent.putExtra("sub_part", "Neck");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Neck", Toast.LENGTH_SHORT).show();
								}
							} else {
								Toast.makeText(HeadMappingActivity.this,
										"Not connected to internet!",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_front_head_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
