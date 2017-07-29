package osh.sw.foodprints;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import osh.sw.foodprints.adapter.MainRecyclerViewAdapter;
import osh.sw.foodprints.model.Food;
import osh.sw.foodprints.view.ViewDialog;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<Food> saveItems;
    private ArrayList<Food> arrayList;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    static String URL="http://13.124.15.202:8080/foods";

    private RecyclerView.LayoutManager layoutManager;

    String[] spinnerItems = new String[]{
            "한식",
            "중식",
            "양식",
            "일식",
            "분식",
            "패스트푸드"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.contract);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewDialog dialog=new ViewDialog();
                dialog.showDialog(MainActivity.this,"글쓰기");
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        spinner=(Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(this,R.layout.category_textview,spinnerItems);
        stringArrayAdapter.setDropDownViewResource(R.layout.category_textview);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),getTitle(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        AQuery aQuery=new AQuery(this);
        aQuery.ajax(URL,String.class,new AjaxCallback<String>(){
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                Toast.makeText(getApplicationContext(),object,Toast.LENGTH_SHORT).show();
                if(status.getCode()==200){
                    try {
                        JSONArray jsonArray=new JSONArray(object);
                        parseFoodJson(jsonArray);
                        setAdapter(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }

    public void setAdapter(JSONArray jsonArray){
        layoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewAdapter = new MainRecyclerViewAdapter(getApplicationContext(), parseFoodJson(jsonArray));
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Food> parseFoodJson(JSONArray jsonArray){
        arrayList=new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String foodName = jsonObject.getString("name");
                String foodCategory = jsonObject.getString("category");
                String foodStore = jsonObject.getString("place");
                String foodLocation=jsonObject.getString("location");
                String foodDate = jsonObject.getString("date");
                boolean foodRecommend = jsonObject.getBoolean("recommend");
                int starRecommend = jsonObject.getInt("stars");
                arrayList.add(new Food(foodName, foodCategory, foodStore,foodLocation, foodDate, foodRecommend, starRecommend));
            }catch (JSONException e){

            }
        }
        return  arrayList;
    }


}
