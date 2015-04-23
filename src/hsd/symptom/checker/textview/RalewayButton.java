package hsd.symptom.checker.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class RalewayButton extends Button {

	public RalewayButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public RalewayButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RalewayButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"Raleway-Regular.ttf");
		setTypeface(tf);
	}

}