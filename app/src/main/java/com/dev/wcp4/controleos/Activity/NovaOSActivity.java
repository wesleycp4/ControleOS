package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.dev.wcp4.controleos.Adapter.ClienteAdapter;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.Cliente;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NovaOSActivity extends AppCompatActivity {

    AutoCompleteTextView txtSearch;
    //List<Cliente> mList;
    ClienteAdapter adapter;
    //private ImageView imagemEquip;
    //private Button botaoFoto;
    final Context context = this;
    private String parametros;
    private ArrayList<Cliente> list = new ArrayList<>();
    private ArrayList<Cliente> listCliente = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView txtEmailCliente;
    private TextView txtNomeCliente;
    private TextView txtContatoCliente;
    private TextView txtContato2Cliente;
    private TextView txtEnderecoCliente;
    private TextView txtCpfCliente;
    private String param = "cliente=cliente";
    // ArrayAdapter adapter;
    String TAG = "DADOS"; //debug
    private int controle = 0;
    private int idCliente;
    private EditText editTextEquip;
    private EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_os);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBarNOs);
        Button botaoNovaOs = (Button) findViewById(R.id.botao_ok_novaos);
        //botoesFlutuantes();
        txtEmailCliente = (TextView) findViewById(R.id.txtEmailCliente);
        txtNomeCliente = (TextView) findViewById(R.id.txtAlterNome);
        txtContatoCliente = (TextView) findViewById(R.id.txtAlterCont);
        txtContato2Cliente = (TextView) findViewById(R.id.txtContato2Cliente);
        txtEnderecoCliente = (TextView) findViewById(R.id.txtAlterNumero);
        txtCpfCliente = (TextView) findViewById(R.id.txtAlterEmail);

        //this.imagemEquip = (ImageView) findViewById(R.id.fotoPreview);

        editTextEquip = (EditText) findViewById(R.id.edtEquipamentos);
        editTextDesc = (EditText) findViewById(R.id.edtDescricao);

        new carregaDados().execute(Rotas.URL_DADOS_CLIENTE_TUDO);

        botaoNovaOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String equip = editTextEquip.getText().toString().trim();
                    String desc = editTextDesc.getText().toString().trim();
                    String idc = "&" + param;

                    SharedPreferences prefs = getSharedPreferences("arq", MODE_PRIVATE);
                    int funcid = prefs.getInt("id", 0);

                    String idf = "&idfuncionario=" + funcid;

                    if (!equip.isEmpty() && !desc.isEmpty()) {
                        try {
                            progressBar.setVisibility(View.VISIBLE);
                            parametros = "descricao=" + desc + "&equipamento=" + equip + idc + idf;
                            //exibir(parametros);
                            new cadastrarOs().execute(Rotas.CADASTRAR_OS);
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

    public void preencheArray(ArrayList<Cliente> clientes) {

        AutoCompleteTextView mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteClientes);
        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
        mAutoCompleteTextView.setAdapter(adaptador);
        mAutoCompleteTextView.setThreshold(2);
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
        String cont2 = cliente.getContato2Cliente();
        if (cont2 == null || cont2.equals("") || cont2.length() == 0 || cont2 == "null") {
            cont2 = "Não Informado";
        } else {
            cont2 = cont2;
        }
        txtContato2Cliente.setText(cont2);
        txtContatoCliente.setText(cliente.getContatoCliente());
        txtCpfCliente.setText(cliente.getCpfCliente());
        String comp = cliente.getComplementoCliente();
        if (comp == null || comp.equals("") || comp.length() == 0 || comp == "null") {
            comp = "Sem Comp.";
        } else {
            comp = "Comp.: " + comp;
        }
        String end = cliente.getRuaCliente() + ", " + cliente.getNumeroCliente() + ", " + comp + ", Bairro: " + cliente.getBairroCliente() + "\n" + cliente.getCidadeCliente() + ", " + cliente.getEstadoCliente() + ", CEP: " + cliente.getCepCliente();
        txtEnderecoCliente.setText(end);
    }


    public void botoesFlutuantes() {
        /*FloatingActionButton botao_add_img = (FloatingActionButton) findViewById(R.id.botaoAddImg);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        /*FloatingActionButton botao_add_img2 = (FloatingActionButton) findViewById(R.id.botaoAddImg2);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img2", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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

   /* public void carregaImagem(String localArquivoFoto) {
        if(localArquivoFoto != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
            imagemContato.setImageBitmap(imagemFoto);
            imagemContato.setTag(localArquivoFoto);
        }
    }*/

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

    private class cadastrarOs extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObj = new JSONObject(s);
                if (!jsonObj.getBoolean("error")) {
                    exibir("Nova O.S. Cadastrada com sucesso!");
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(NovaOSActivity.this, BaseActivity.class));
                    finish();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":/ Ocorreu um erro!");
                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.INVISIBLE);
                exibir(e.getMessage());
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], parametros);
        }
    }

}