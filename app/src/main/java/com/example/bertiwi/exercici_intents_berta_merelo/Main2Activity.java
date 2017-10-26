package com.example.bertiwi.exercici_intents_berta_merelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spQuantity;
    private static int estock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //si posem la activitatmain es crea el mateix pero volem afegir un botó mes tot i que invisible per la activity main i visible per aquesta

        spQuantity = (Spinner) findViewById(R.id.cantidad_def);
        int quantity = getIntent().getIntExtra(MainActivity.SELECTED_QUANTITY, 0);
        populateSpinner();
        spQuantity.setSelection(quantity);

        Button btBuy = (Button) findViewById(R.id.main_button);
        btBuy.setOnClickListener(this);

    }
    private void populateSpinner(){
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item);
        for (int i = 0; i <= estock; i++){
            adapter.add(i);
        }
        spQuantity.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_button:
                int quantity = Integer.valueOf(spQuantity.getText().toString());
                Intent intent = getIntent();
                intent.putExtra(MainActivity.SELECTED_QUANTITY, quantity);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.main_cancel:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
