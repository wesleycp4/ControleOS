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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.Cliente;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AtualizarClienteActivity extends AppCompatActivity {

    private String parametros;
    private ArrayList<Cliente> list = new ArrayList<>();
    private ArrayList<Cliente> listCliente = new ArrayList<>();
    private ProgressBar progressBar;
    private EditText txtEmailCliente;
    private EditText txtNomeCliente;
    private EditText txtContatoCliente;
    private EditText txtContato2Cliente;
    private EditText txtCpfCliente;
    private EditText txtRuaCliente;
    private EditText txtNumeroCliente;
    private EditText txtCompCliente;
    private EditText txtBairroCliente;
    private EditText txtCidadeCliente;
    private EditText txtCepCliente;
    private EditText txtUfCliente;
    private String param = "cliente=cliente";
    // ArrayAdapter adapter;
    String TAG = "DADOS"; //debug
    private int controle = 0;
    private int idCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_cliente);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBarAlter);

        Button botaoAtualizar = (Button) findViewById(R.id.botao_atualizar_cliente);

        txtNomeCliente = (EditText) findViewById(R.id.txtAlterNome);
        txtEmailCliente = (EditText) findViewById(R.id.txtAlterEmail);
        txtContatoCliente = (EditText) findViewById(R.id.txtAlterCont);
        txtContato2Cliente = (EditText) findViewById(R.id.txtAlterCont2);
        txtRuaCliente = (EditText) findViewById(R.id.txtAlterRua);
        txtNumeroCliente = (EditText) findViewById(R.id.txtAlterNumero);
        txtCompCliente = (EditText) findViewById(R.id.txtAlterComp);
        txtBairroCliente = (EditText) findViewById(R.id.txtAlterBairro);
        txtCidadeCliente = (EditText) findViewById(R.id.txtAlterCidade);
        txtCepCliente = (EditText) findViewById(R.id.txtAlterCep);
        //txtCpfCliente =(EditText) findViewById(R.id.txtAlterCep);
        //txtUfCliente =(EditText) findViewById(R.id.txtAlterCep);

        new carregaDados().execute(Rotas.URL_DADOS_CLIENTE_TUDO);

        botaoAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome, email, cont, cont2, rua, numero, comp, bairro, cidade, cep;

                    nome = txtNomeCliente.getText().toString().trim();
                    email = txtEmailCliente.getText().toString().trim();
                    cont = txtContatoCliente.getText().toString().trim();
                    cont2 = txtContato2Cliente.getText().toString().trim();
                    rua = txtRuaCliente.getText().toString().trim();
                    numero = txtNumeroCliente.getText().toString().trim();
                    comp = txtCompCliente.getText().toString().trim();
                    bairro = txtBairroCliente.getText().toString().trim();
                    cidade = txtCidadeCliente.getText().toString().trim();
                    cep = txtCepCliente.getText().toString().trim();

                    if (true) {
                        try {
                            progressBar.setVisibility(View.VISIBLE);

                            parametros = param + "&nome_cliente=" + nome + "&email_cliente=" + email + "&contato_cliente=" + cont + "&contato2_cliente=" + cont2 + "&rua_cliente=" + rua + "&numero_cliente=" + numero + "&complemento_cliente=" + comp + "&bairro_cliente=" + bairro + "&cidade_cliente=" + cidade + "&cep_cliente=" + cep;
                            //exibir(parametros);
                            new atualizarCliente().execute(Rotas.URL_UPDATE_CLIENTE);
                            //verificar depois sql***
                        } catch (ParseException e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        exibir("Nao ouve alteração!");
                    }

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":( Favor verificar a sua conexão com a Internet!");
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); // Finaliza a Activity atual
        return true;
    }

    public void exibir(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public Context getContexto() {
        return this;
    }

    public void preencheArray(ArrayList<Cliente> clientes) {

        AutoCompleteTextView mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteClientes);
        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
        mAutoCompleteTextView.setAdapter(adaptador);
        mAutoCompleteTextView.setThreshold(3);
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cliente someObject = (Cliente) parent.getItemAtPosition(position);
                idCliente = someObject.getIdCliente();
                param = "idcliente=" + idCliente;
                controle = 1;
                new carregaDados().execute(Rotas.URL_DADOS_CLIENTE);

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

    }

    public void setaDadosCliente(Cliente cliente) {
        txtNomeCliente.setText(cliente.getNomeCliente());
        txtEmailCliente.setText(cliente.getEmailCliente());
        txtContatoCliente.setText(cliente.getContatoCliente());
        String cont2 = cliente.getContato2Cliente();
        if (cont2 == null || cont2.equals("") || cont2.length() == 0 || cont2 == "null") {
            cont2 = "";
        } else {
            cont2 = cont2;
        }
        txtContato2Cliente.setText(cont2);
        txtRuaCliente.setText(cliente.getRuaCliente());
        String n = Integer.toString(cliente.getNumeroCliente());
        txtNumeroCliente.setText(n);
        String comp = cliente.getComplementoCliente();
        if (comp == null || comp.equals("") || comp.length() == 0 || comp == "null") {
            comp = "";
        } else {
            comp = comp;
        }
        txtCompCliente.setText(comp);
        txtBairroCliente.setText(cliente.getBairroCliente());
        txtCidadeCliente.setText(cliente.getCidadeCliente());
        txtCepCliente.setText(cliente.getCepCliente());

    }

    private class carregaDados extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObj = new JSONObject(s);
                if (controle == 0) {

                    list.clear();
                    JSONArray jsonArray = jsonObj.getJSONArray("cliente");
                    //list.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Cliente c2 = new Cliente(
                                    obj.getInt("idcliente"),
                                    obj.getString("nome_cliente")
                            );
                            list.add(c2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    listCliente.clear();
                    JSONArray jsonArray = jsonObj.getJSONArray("cliente");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            Cliente cl = new Cliente(
                                    obj.getInt("idcliente"),
                                    obj.getString("nome_cliente"),
                                    obj.getString("email_cliente"),
                                    obj.getString("cpf_cliente"),
                                    obj.getString("contato_cliente"),
                                    obj.getString("contato2_cliente"),
                                    obj.getString("rua_cliente"),
                                    obj.getInt("numero_cliente"),
                                    obj.getString("complemento_cliente"),
                                    obj.getString("bairro_cliente"),
                                    obj.getString("cidade_cliente"),
                                    obj.getString("cep_cliente"),
                                    obj.getString("estado_cliente")
                            );

                            listCliente.add(cl);
                            setaDadosCliente(listCliente.get(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                preencheArray(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], param);
        }
    }

    private class atualizarCliente extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObj = new JSONObject(s);
                if (!jsonObj.getBoolean("error")) {
                    exibir("Cadastro Atualizado!");
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":/ Ocorreu um erro!");
                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.INVISIBLE);
                //exibir(e.getMessage());
                exibir("Cadastro Atualizado!");
                progressBar.setVisibility(View.INVISIBLE);
                finish();
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], parametros);
        }
    }
}
