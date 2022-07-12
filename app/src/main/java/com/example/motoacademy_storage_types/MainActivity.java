package com.example.motoacademy_storage_types;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;



public class MainActivity extends AppCompatActivity {

    //String propName = "dev.MotoAcademy.property";
    String propName = "dev.motorola.motoacademy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MotoAcademy", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, phone VARCHAR)");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void StoreSharedPref(View view){
        EditText myTextField = (EditText) findViewById(R.id.editText);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.motoacademy_storage_types0", MODE_PRIVATE);

        if (!myTextField.getText().toString().equals("")) {
            sharedPreferences.edit().putString("textData", myTextField.getText().toString()).apply();
            myTextField.setText("");
            Toast.makeText(getApplicationContext(), "Shared Preferences stored", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Shared Preferences field is empty", Toast.LENGTH_LONG).show();
        }
    }

    public void ReadSharedPreferences(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.motoacademy_storage_types0", MODE_PRIVATE);
        String textData = sharedPreferences.getString("textData", "");
        Toast.makeText(getApplicationContext(), "Value from Shared Preferences is "+textData, Toast.LENGTH_LONG).show();
    }

    public void StoreInDatabase(View view){
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MotoAcademy", MODE_PRIVATE, null);
        String name = ((EditText)findViewById(R.id.editTextPersonName)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();

        String query = "INSERT INTO users (name, phone) VALUES ('"+
                name+"', '" +
                phoneNumber+"')";

        try {
            sqLiteDatabase.execSQL(query);
            Toast.makeText(getApplicationContext(), "Values stored in database", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to store values in database", Toast.LENGTH_LONG).show();
        }

        Log.i("StoreInDatabase", "Query: "+query);

    }

    public void WriteProperty(View view){
        String newPropValue = ((EditText)findViewById(R.id.propNewValue)).getText().toString();
        if (newPropValue.equals("")){
            Toast.makeText(getApplicationContext(), "Prop field is empty!!", Toast.LENGTH_LONG).show();
        } else {
            try {
                System.setProperty(propName, newPropValue);
            } catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Property "+propName+ " has now new value "+newPropValue+".", Toast.LENGTH_LONG).show();
        }
    }

    public void ReadProperty(View view){
        //String propValue = "denis"+System.getProperty(propName);
        Properties properties = System.getProperties();

        Log.i("MotoTest", properties.getProperty(propName));

        if (properties.getProperty(propName).equals("")){
            Toast.makeText(getApplicationContext(), "Prop is empty!!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "Property "+propName+ " has value of "+properties.getProperty(propName)+".", Toast.LENGTH_LONG).show();
        }

    }

}