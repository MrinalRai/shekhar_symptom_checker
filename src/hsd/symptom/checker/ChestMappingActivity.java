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

public class ChestMappingActivity extends Activity {

	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_chest_mapping);
		cd = new ConnectionDetector(this);

		findViewById(R.id.man_chest).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					int colour = getColour(x, y);
					if (cd.isConnectingToInternet()) {
						if (Color.rgb(63, 72, 204) == colour) {
							Intent intent = new Intent(
									ChestMappingActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Lung");
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "Lung",
									Toast.LENGTH_SHORT).show();
						}
						if (Color.rgb(237, 28, 36) == colour) {
							Intent intent = new Intent(
									ChestMappingActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Heart");
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "Heart",
									Toast.LENGTH_SHORT).show();
						}
						if (Color.rgb(255, 174, 201) == colour) {
							Intent intent = new Intent(
									ChestMappingActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Sternum");
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "Sternum",
									Toast.LENGTH_SHORT).show();
						}
						if (Color.rgb(255, 242, 0) == colour) {
							Intent intent = new Intent(
									ChestMappingActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Kidney");
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "Kidney",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(ChestMappingActivity.this,
								"Not connected to internet!",
								Toast.LENGTH_SHORT).show();
					}
				}
				return true;
			}
		});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_chest_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
