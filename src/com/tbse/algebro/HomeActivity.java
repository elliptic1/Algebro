package com.tbse.algebro;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity {
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.homescreen);

		Button gobutton = (Button) findViewById(R.id.startButton);

		gobutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {

					Intent i = new Intent(getApplicationContext(),
							MainGameActivity.class);
					startActivity(i);
				} catch (ClassCastException issue6894) {
				} // ignore  

			}
		});

	}

}
