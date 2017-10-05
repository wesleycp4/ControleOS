package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.R;
import com.dev.wcp4.controleos.Activity.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private String parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button) findViewById(R.id.botao_login);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsuario = (EditText) findViewById(R.id.usuario_login);
                EditText editTextSenha = (EditText) findViewById(R.id.senha_login);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if(networkInfo != null && networkInfo.isConnected()){

                    String usuario = editTextUsuario.getText().toString().trim();
                    String senha = editTextSenha.getText().toString().trim();
                    String us = editTextUsuario.getText().toString();

                    if(!usuario.isEmpty() && !senha.isEmpty()){
                        progressBar.setVisibility(View.VISIBLE);
                        parametros = "usuario=" + usuario + "&senha=" + senha;

                        //salva nome do usuario nos sharedpreferences
                        SharedPreferences prefs = getSharedPreferences("arq", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("nomedoUser", usuario);
                        //editor.putString("nomedoUser", usuario);
                        editor.apply();

                        new Logar().execute(Rotas.URL_LOGIN);

                    } else{
                        exibir("Informe o Usuario e a Senha para ter acesso ao sistema!");
                    }

                } else{
                    exibir(":( Favor verificar a sua conexão com a Internet!");
                }

            }

        });

    }

    private class Logar extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            //exibir(s);
            try {

                JSONObject jsonObj = new JSONObject(s);
                if(!jsonObj.getBoolean("error")){

                    startActivity(new Intent(MainActivity.this,BaseActivity.class));
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();

                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir("Usuario ou senha Incorretos!");

                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.INVISIBLE);
                exibir(e.getMessage());            }
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0],parametros);
        }
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


