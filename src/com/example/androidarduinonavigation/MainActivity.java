package com.example.androidarduinonavigation;


import com.example.locationupdate.GPSTracker;
import com.example.locationupdate.StepRoute;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

	StepRoute sr;
	EditText etDest;
	Button mBtnDest;
	double destLatitude;
	double destLongitude;
	GPSTracker gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Getting reference to the get Directions button
        mBtnDest = (Button) findViewById(R.id.button1);
        
     // Getting reference to EditText
        etDest = (EditText) findViewById(R.id.editText02);
        
        mBtnDest.setOnClickListener(new View.OnClickListener() {
        	
        	
        	
        	@Override
			public void onClick(View v) {
				// Get the user entered destination 
        		
        	    String destination = etDest.getText().toString();
        		
        		//Used for debugging Log.i("destination:", destination);
        		
        	    //Instantiate StepRoute object
				sr = new StepRoute(MainActivity.this);
	            //pass the user entered destination to setUserDestination function in StepRoute class
				sr.setUserDestination(destination);
				
			}
       
				
	   });
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
//