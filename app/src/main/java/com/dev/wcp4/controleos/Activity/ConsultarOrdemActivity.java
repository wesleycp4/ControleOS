package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev.wcp4.controleos.R;

import static com.dev.wcp4.controleos.R.id.progressBar;
import static com.dev.wcp4.controleos.R.id.start;

public class ConsultarOrdemActivity extends AppCompatActivity {

    private String parametros = "";
    private ProgressBar progressBar;
    private EditText numeroConsultaOS;
    String param = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_ordem);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button botaoBuscarOs = (Button) findViewById(R.id.botaoPesquisarOS);
        progressBar = (ProgressBar) findViewById(R.id.progressBarConOs);
        numeroConsultaOS = (EditText) findViewById(R.id.numeroConsultaOS);

        botaoBuscarOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String idos = numeroConsultaOS.getText().toString().trim();

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    Intent intent = new Intent();
                    intent.putExtra("id", idos);
                    intent.setClass(getApplicationContext(), OSActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.INVISIBLE);
                    //finish();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir("Favor verificar a sua conexão com a Internet!");
                }
            }
        });

    }

    public void exibir(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); // Finaliza a Activity atual
        return true;
    }

    public void onBackPressed() {
        finish();
    }

}
