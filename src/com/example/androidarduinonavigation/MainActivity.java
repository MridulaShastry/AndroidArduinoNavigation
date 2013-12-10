package com.example.androidarduinonavigation;

/*import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}*/
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;



import com.example.locationupdate.GPSTracker;
import com.example.locationupdate.StepRoute;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        etDest = (EditText) findViewById(R.id.editText2);
        
        
        
        
        
        mBtnDest.setOnClickListener(new View.OnClickListener() {
        	
        	
        	
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
        		
        		
        		/*gps = new GPSTracker(GpsTracker.this);
        		 
                // check if GPS enabled    
                if(gps.canGetLocation()){
                     
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                     
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();   
                }*/
        		String destination = etDest.getText().toString();
        		
        		//String destination = "Sunnyvale";
        		
        		Log.i("destination:", destination);
        		
				sr = new StepRoute(MainActivity.this);
	
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
