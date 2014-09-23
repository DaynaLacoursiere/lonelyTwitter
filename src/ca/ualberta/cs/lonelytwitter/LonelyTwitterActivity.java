package ca.ualberta.cs.lonelytwitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	HashSet<String> values;//***********************ADDED
	public Integer i=0;
	private LonelyTweetModel theTweet;//ADDED
	private static ArrayList<LonelyTweetModel> theTweets;
	private ArrayAdapter<LonelyTweetModel> adapter;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		values = new HashSet<String>();//*****************ADDED

		
		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				theTweets.add(new LonelyTweetModel(text));
				adapter.notifyDataSetChanged();
				saveInFile();
				//saveInFile(text, new Date(System.currentTimeMillis()));
				
				

			}
		});
		Button countButton = (Button) findViewById(R.id.counterButton);
		countButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				countClicked();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		//theTweets = loadFromFile();
		loadFromFile();
		if (theTweets == null){
			theTweets = new ArrayList<LonelyTweetModel>();
		}	
		adapter = new ArrayAdapter<LonelyTweetModel>(this,R.layout.list_item, theTweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			InputStreamReader irs = new InputStreamReader(fis);
			
			// From www.javacreed.com/simple-gson-example/ 2014-25-09
			Gson myGson = new GsonBuilder().create();
			theTweets = myGson.fromJson(irs, new TypeToken<ArrayList<LonelyTweetModel>>() {}.getType()); //LOADING FROM FILE
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			
			Gson gson = new Gson(); //same as new Gson.Builder().create();
			gson.toJson(theTweets, osw);
			osw.flush();
			osw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<LonelyTweetModel> getTweets(){
		return theTweets;
	}
	
	public void countClicked(){
		Intent intent = new Intent(this, CounterActivity.class);
		startActivity(intent);
	}
}

