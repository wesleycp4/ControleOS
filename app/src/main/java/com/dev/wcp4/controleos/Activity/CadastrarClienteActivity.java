package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastrarClienteActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextCpf;
    private EditText editTextContato;
    private EditText editTextContato2;
    private EditText editTextRua;
    private EditText editTextNumero;
    private EditText editTextComplemento;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private Spinner editTextEstado;
    private EditText editTextCep;
    private String parametros = "";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        editTextNome = (EditText) findViewById(R.id.nomeCadCliente);
        editTextEmail = (EditText) findViewById(R.id.emailCadCliente);
        editTextCpf = (EditText) findViewById(R.id.cpfCadCliente);
        editTextContato = (EditText) findViewById(R.id.contatoCadCliente);
        editTextContato2 = (EditText) findViewById(R.id.contato2CadCliente);
        editTextRua = (EditText) findViewById(R.id.ruaCadCliente);
        editTextNumero = (EditText) findViewById(R.id.numeroCadCliente);
        editTextComplemento = (EditText) findViewById(R.id.complementoCadCliente);
        editTextBairro = (EditText) findViewById(R.id.bairroCadCliente);
        editTextCidade = (EditText) findViewById(R.id.cidadeCadCliente);
        editTextEstado = (Spinner) findViewById(R.id.spinnerCadCliente);
        //editTextCep = (EditText) findViewById(R.id.);

        Button botaoCadastrar = (Button) findViewById(R.id.button_cadCliente);
        progressBar = (ProgressBar) findViewById(R.id.progressBarCadCliente);
        //spinner.setOnItemSelectedListener(this);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = editTextNome.getText().toString().trim();
                    String usuario = editTextNumero.getText().toString().trim();
                    String senha = editTextBairro.getText().toString().trim();
                    String contato = editTextContato.getText().toString().trim();
                    String contato2 = editTextContato2.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String confirmasenha = editTextCpf.getText().toString().trim();

                    if (!nome.isEmpty() && !email.isEmpty() && !contato.isEmpty() && !usuario.isEmpty() && !senha.isEmpty()) {
                        try {
                            if (senha.equals(confirmasenha) ) {
                                parametros = "nome=" + nome + "&email=" + email + "&contato=" + contato + "&contato2=" + contato2 + "&usuario=" + usuario + "&senha=" + senha;
                                progressBar.setVisibility(View.VISIBLE);
                                new Cadastrar().execute(Rotas.CADASTRAR_USUARIO);
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                exibir("Senhas não conferem! Verifique e tente novamente.");
                            }
                        } catch (ParseException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        exibir("Preencha todos os campos com *");
                    }

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":( Favor verificar a sua conexão com a Internet!");
                }
            }
        });

    }


private class Cadastrar extends AsyncTask<String, Object, String> {

    @Override
    protected void onProgressUpdate(Object... values) {
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObj = new JSONObject(s);
            if(!jsonObj.getBoolean("error")){
                exibir(":D Usuario cadastrado com sucesso!");
                startActivity(new Intent(CadastrarClienteActivity.this,BaseActivity.class));
                finish();
            } else{
                progressBar.setVisibility(View.INVISIBLE);
                exibir(":/ Usuario ja cadastrado no sistema!");
            }
        } catch (JSONException e) {
            progressBar.setVisibility(View.INVISIBLE);
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... url) {
        return Conexao.postDados(url[0],parametros);
    }
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