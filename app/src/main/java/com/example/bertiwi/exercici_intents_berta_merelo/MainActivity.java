package com.example.bertiwi.exercici_intents_berta_merelo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_main;
    private Spinner spQuantity;
    private TextView tvName;
    public static int estock = 20;

    public static final String SELECTED_QUANTITY = "selected_quantity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spQuantity = (Spinner) findViewById(R.id.cantidad_def);
        populateSpinner();
        tvName = (TextView) findViewById(R.id.nombre);

        button_main = (Button) findViewById(R.id.main_button);
        button_main.setOnClickListener(this);

        View btCancel = findViewById(R.id.main_cancel); //no hace falta crear un botoon pq el metodo set visibility es de la clase view
        btCancel.setVisibility(View.GONE);

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
        if (R.id.main_button == view.getId()){
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            int quantity = (int) spQuantity.getSelectedItem();
            //int quantity = Integer.valueOf(spQuantity.getText().toString());
            intent.putExtra(SELECTED_QUANTITY, quantity);
            startActivityForResult(intent, 0);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (0 == requestCode){
            if (RESULT_OK == resultCode){
                int quantity = data.getIntExtra(MainActivity.SELECTED_QUANTITY, 0);
                String nombre_producto = tvName.getText().toString();
                String message = getResources().getString(R.string.main_OK_message, quantity, nombre_producto);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }else if(RESULT_CANCELED == resultCode){
                Toast.makeText(getApplicationContext(), "No se ha comprado nada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
