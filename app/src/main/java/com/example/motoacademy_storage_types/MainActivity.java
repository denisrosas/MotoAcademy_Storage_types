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
            Toast.makeText(getApplicationContext(), "Shared Preferences stored", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Shared Preferences field is empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void ReadSharedPreferences(View view){
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.motoacademy_storage_types0", MODE_PRIVATE);
        String textData = sharedPreferences.getString("textData", "");
        Toast.makeText(getApplicationContext(), "Value from Shared Preferences is "+textData, Toast.LENGTH_SHORT).show();
    }

    public void StoreInDatabase(View view){
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MotoAcademy", MODE_PRIVATE, null);
        String name = ((EditText)findViewById(R.id.editTextPersonName)).getText().toString();
        String phoneNumber = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();

        if (name.equals("") | phoneNumber.equals("")){
            Toast.makeText(getApplicationContext(), "One or more fields is empty", Toast.LENGTH_SHORT).show();
        } else{
            String query = "INSERT INTO users (name, phone) VALUES ('"+
                    name+"', '" +
                    phoneNumber+"')";

            try {
                sqLiteDatabase.execSQL(query);
                Toast.makeText(getApplicationContext(), "Values stored in database", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed to store values in database", Toast.LENGTH_SHORT).show();
            }

            Log.i("StoreInDatabase", "Query: "+query);
        }
    }

    public void ClearDatabaseTable(View view){
        SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("MotoAcademy", MODE_PRIVATE, null);

        String query = "DELETE FROM users";

        try {
            sqLiteDatabase.execSQL(query);
            Toast.makeText(getApplicationContext(), "All values were deleted from table users", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Failed to store values in database", Toast.LENGTH_SHORT).show();
        }

        Log.i("StoreInDatabase", "Query: "+query);

    }

    public void ReadProperty(View view){
        String propName = "dev.motoacademy.value";
        SystemProperties properties = new SystemProperties();
        String propValue = SystemProperties.read(propName);

        Log.i("MotoAcademy", "Property "+propName+" has value of "+propValue);

        if (propValue.equals("")){
            Toast.makeText(getApplicationContext(), "Prop is empty!!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(getApplicationContext(), "Property "+propName+ " has value of "+propValue+".", Toast.LENGTH_SHORT).show();
        }

    }

}