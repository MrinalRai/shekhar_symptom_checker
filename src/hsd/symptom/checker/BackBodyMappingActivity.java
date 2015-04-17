package hsd.symptom.checker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class BackBodyMappingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_back_body_mapping);

		findViewById(R.id.man_back).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y);

							if (Color.rgb(237, 28, 36) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										BackHeadMappingActivity.class));
								Toast.makeText(getApplicationContext(), "head",
										Toast.LENGTH_SHORT).show();
							}
							// if (Color.rgb(255, 242, 0) == colour) {
							// startActivity(new
							// Intent(BackBodyMappingActivity.this,
							// BackHeadMappingActivity.class));
							// Toast.makeText(getApplicationContext(), "neck",
							// Toast.LENGTH_SHORT).show();
							// }
							if (Color.rgb(63, 72, 204) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										UpperBackMappingActivity.class));
								Toast.makeText(getApplicationContext(),
										"upper back", Toast.LENGTH_SHORT)
										.show();
							}
							if (Color.rgb(255, 174, 201) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										LowerBackMappingActivity.class));
								Toast.makeText(getApplicationContext(),
										"lower back", Toast.LENGTH_SHORT)
										.show();
							}
							if (Color.rgb(34, 177, 76) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										ButtockMappingActivity.class));
								Toast.makeText(getApplicationContext(),
										"buttock", Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(89, 230, 213) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										BackHandMappingActivity.class));
								Toast.makeText(getApplicationContext(), "hand",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(255, 128, 64) == colour) {
								startActivity(new Intent(
										BackBodyMappingActivity.this,
										BackLegMappingActivity.class));
								Toast.makeText(getApplicationContext(), "leg",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});

		findViewById(R.id.button_front).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();
						startActivity(new Intent(BackBodyMappingActivity.this,
								FrontBodyMappingActivity.class));
					}
				});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_back_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
