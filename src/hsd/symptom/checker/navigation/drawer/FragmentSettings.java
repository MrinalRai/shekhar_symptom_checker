package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.R;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentSettings extends Fragment {

	private View content;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.fragment3_layout, container, false);
		
		Typeface font = Typeface.createFromAsset(getResources().getAssets(),
				"Raleway-Regular.ttf");
		return content;
	}
}
