package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Adapter.Adapter;
import com.dev.wcp4.controleos.Adapter.ClienteAdapter;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NovaOSActivity extends AppCompatActivity {

    AutoCompleteTextView txtSearch;
    //List<Cliente> mList;
    ClienteAdapter adapter;
    private ImageView imagemEquip;
    //private Button botaoFoto;
    final Context context = this;
    private String parametros;
    private ArrayList<Cliente> list = new ArrayList<>();
    private ArrayList<Cliente> listCliente = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView txtEmailCliente;
    private TextView txtNomeCliente;
    private String param = "cliente=cliente";
   // ArrayAdapter adapter;
    String TAG = "DADOS";
   private int controle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_os);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBarNOs) ;
        Button botaoNovaOs = (Button) findViewById(R.id.botao_ok_novaos);
        botoesFlutuantes();
        txtEmailCliente =(TextView) findViewById(R.id.txtEmailCliente);
        txtNomeCliente =(TextView) findViewById(R.id.txtNomeCliente);

        this.imagemEquip = (ImageView) findViewById(R.id.fotoPreview);


        //new carregaDados().execute();
        new carregaDados().execute(Rotas.URL_DADOS_CLIENTE_TUDO);
       // AutoCompleteTextView estados = (AutoCompleteTextView) findViewById(R.id.antoCompleteClientes);
        // ArrayAdapter para preencher com os estados



        botaoNovaOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exibir(selecionado);
                //exibir(selecionado2);
            }
        });



    }

    public void preencheArray(ArrayList<Cliente> clientes){

            AutoCompleteTextView mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteClientes);
            ArrayAdapter<Cliente> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
            mAutoCompleteTextView.setAdapter(adaptador);
            mAutoCompleteTextView.setThreshold(3);
            mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Cliente someObject = (Cliente) parent.getItemAtPosition (position);
                   //pegando id cliente tranformar em global
                    int idCliente = someObject.getIdCliente ();
                    param = "idcliente="+idCliente;
                    controle=1;
                    new carregaDados().execute(Rotas.URL_DADOS_CLIENTE);
                }
            });

    }


    public void setaDadosCliente(Cliente cliente){
        txtNomeCliente.setText(cliente.getNomeCliente());
        txtEmailCliente.setText(cliente.getEmailCliente());
    }


    public void botoesFlutuantes() {
        FloatingActionButton botao_add_img = (FloatingActionButton) findViewById(R.id.botaoAddImg);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*FloatingActionButton botao_add_img2 = (FloatingActionButton) findViewById(R.id.botaoAddImg2);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img2", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); // Finaliza a Activity atual
        return true;
    }

    public void onBackPressed(){
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
                if (controle == 0 ){

                    list.clear();
                    JSONArray jsonArray = jsonObj.getJSONArray("cliente");
                    //list.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            JSONObject obj  = jsonArray.getJSONObject(i);
                            Cliente c2 = new Cliente(
                                    obj.getInt("idcliente"),
                                    obj.getString("nome_cliente")
                            );
                            list.add(c2);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }else {
                    listCliente.clear();
                    JSONArray jsonArray = jsonObj.getJSONArray("cliente");
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            JSONObject obj  = jsonArray.getJSONObject(i);

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
                        }catch (JSONException e){
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
            return Conexao.postDados(url[0],param);
        }
    }


    //******************************************************************************************************************************************************

    /*private class DepartmentRequest extends AsyncTask<Void, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(Void... voids) {
            OkHttpJsonArrayRequest request = new OkHttpJsonArrayRequest();
            try {
                return request.get("http://...");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            if (jsonArray != null && jsonArray.length() > 0) {
                Gson gson = new Gson();
                Department[] departments = gson.fromJson(jsonArray.toString(), Department[].class);
                mDepartmentList = Arrays.asList(departments);
                mDepartmentArrayAdapter = new DepartmentArrayAdapter(mContext, R.layout.simple_text_view, mDepartmentList);
                mAutoCompleteTextView.setAdapter(mDepartmentArrayAdapter);
            }
        }
    }

    private class OkHttpJsonArrayRequest {
        OkHttpClient client = new OkHttpClient();
        // HTTP GET REQUEST
        JSONArray get(String url) throws IOException, JSONException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
        }
    }*/


}