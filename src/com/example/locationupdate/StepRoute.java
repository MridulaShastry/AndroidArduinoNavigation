package com.example.locationupdate;

import com.example.androidarduinonavigation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;


public class StepRoute{

    private final Context mContext;
	GPSTracker gps;
	Geocoder geoCoder;
	
	double latitude;
	double longitude;
	String lat2;
	String lon2;
	String strLatitude;
	String strLongitude;
	String responseStr;
	HttpEntity entity;
	String json;
    String firstStep;
    Spanned displayStep;
    String displayFirstStep;
    double destLatitude;
    double destLongitude;
    String destLatitudeStr;
    String destLongitudeStr;
    String userDestination;
    String testURL;

    public StepRoute(Context context) {
    	this.mContext = context;
    	
		gps = new GPSTracker(context);
		gps.setStepRoute(StepRoute.this);
		
		geoCoder = new Geocoder(context);
    }
    
	/*String tURL="https://maps.googleapis.com/maps/api/place/search/json?"
			+ "location=" + strLatitude + "," + strLongitude
			+ "&radius=25000&"
			+ "&mode=bicycling&sensor=false";*/

    public void setUserDestination(String destination) {
    	userDestination = destination;
    }
    
	public String postData() throws JSONException, IOException {
		
		latitude = gps.getLatitude();
		longitude = gps.getLongitude();
		
		strLatitude = String.valueOf(latitude);
		strLongitude = String.valueOf(longitude);

        try {
            List<Address> address =
             geoCoder.getFromLocationName(userDestination,1);
            if (address.size() >  0) {
               destLatitude = address.get(0).getLatitude(); 
               destLongitude = address.get(0).getLongitude();
               
               destLatitudeStr = Double.toString(destLatitude);
               destLongitudeStr = Double.toString(destLongitude);
               Log.i("destLatitude :", destLatitudeStr);
               
             }

        } catch (IOException e) { // TODO Auto-generated catch block
        e.printStackTrace(); }

		
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.googleapis.com/maps/api/directions/json?");
		urlString.append("origin=");// from
		urlString.append(strLatitude);
		urlString.append(",");
		urlString.append(strLongitude);
		urlString.append("&destination=");// to
		urlString.append(destLatitudeStr);
		urlString.append(",");
		urlString.append(destLongitudeStr);
		urlString.append("&mode=walking&sensor=true");
		Log.d("xxx", "URL=" + urlString.toString());
		
		testURL = urlString.toString();
		
		/*URL url = new URL(urlString.toString());
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.connect();
		HttpResponse response;
		
		if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	          BufferedReader input = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()),8192);
	          String strLine = null;
	          while ((strLine = input.readLine()) != null)
	          {
	              ((Appendable) response).append(strLine);
	          }
	          input.close();
	      }
	      String jsonOutput = response.toString();*/
		
		
		String testurl = "http://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&sensor=false&avoid=highways&mode=bicycling";

		// URL url = new URL(tURL);
		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();
		// Creating HTTP Post
		
		

		HttpPost httppost = new HttpPost(testurl);
		{
			try {



				// Execute HTTP Post Request
				HttpResponse response = httpClient.execute(httppost);
				Log.i("Http Response:", response.toString());

				responseStr = response.toString();
				
				entity = response.getEntity();
				json = EntityUtils.toString(entity);
				
				Log.i("Content:", json);
				
				final JSONObject jsonObj =new JSONObject(json);
				final JSONObject jsonRoute = jsonObj.getJSONArray("routes").getJSONObject(0);
				final JSONObject leg = jsonRoute.getJSONArray("legs").getJSONObject(0);
				final JSONObject step = leg.getJSONArray("steps").getJSONObject(0);
				firstStep = step.getString("html_instructions").replaceAll("<[^>]*>","");
				
				displayStep = Html.fromHtml(firstStep);
				displayFirstStep = displayStep.toString();
				Log.i("First Step:", displayFirstStep);
				

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		}

		return json;

	}
}




