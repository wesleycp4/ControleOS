package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.wcp4.controleos.R;

public class CadastrarClienteActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private String parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinnerCadCliente);
        Button botao = (Button) findViewById(R.id.button_cadCliente);
        progressBar = (ProgressBar) findViewById(R.id.progressBarCadCliente);
        //spinner.setOnItemSelectedListener(this);

    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); // Finaliza a Activity atual
        return true;
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    public Context getContexto(){
        return this;
    }

}