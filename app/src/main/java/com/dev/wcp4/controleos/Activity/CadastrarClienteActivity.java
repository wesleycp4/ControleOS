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
import android.view.inputmethod.InputMethodManager;
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
        editTextCep = (EditText) findViewById(R.id.cepCadCliente);

        Button botaoCadastrar = (Button) findViewById(R.id.button_cadCliente);
        progressBar = (ProgressBar) findViewById(R.id.progressBarCadCliente);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = editTextNome.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String cpf = editTextCpf.getText().toString().trim();
                    String contato = editTextContato.getText().toString().trim();
                    String contato2 = editTextContato2.getText().toString().trim();
                    String rua = editTextRua.getText().toString().trim();
                    String numero = editTextNumero.getText().toString().trim();
                    String complemento = editTextComplemento.getText().toString().trim();
                    String bairro = editTextBairro.getText().toString().trim();
                    String cidade = editTextCidade.getText().toString().trim();
                    String cep = editTextCep.getText().toString().trim();
                    String estado = editTextEstado.getSelectedItem().toString().trim();

                    if (!nome.isEmpty() && !cpf.isEmpty() && !contato.isEmpty()&& ! rua.isEmpty() && !numero.isEmpty() && !bairro.isEmpty() && !cidade.isEmpty() && !estado.isEmpty()) {
                        try {
                            progressBar.setVisibility(View.VISIBLE);
                            parametros = "nome_cliente=" + nome + "&email_cliente=" + email + "&cpf_cliente=" + cpf + "&contato_cliente=" + contato + "&contato2_cliente=" + contato2 + "&rua_cliente=" + rua + "&numero_cliente=" + numero + "&complemento_cliente=" + complemento + "&bairro_cliente=" + bairro + "&cidade_cliente=" + cidade + "&cep_cliente=" + cep + "&estado_cliente=" + estado;
                            new cadastrarCliente().execute(Rotas.CADASTRAR_CLIENTE);
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


    private class cadastrarCliente extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObj = new JSONObject(s);
                if(!jsonObj.getBoolean("error")){
                    exibir(":D Cliente cadastrado com sucesso!");
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(CadastrarClienteActivity.this,NovaOSActivity.class));
                    finish();
                } else{
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":/ Cliente ja cadastrado no sistema!");
                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.INVISIBLE);
                exibir(e.getMessage());
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
        finish();
    }

    public Context getContexto(){
        return this;
    }

}