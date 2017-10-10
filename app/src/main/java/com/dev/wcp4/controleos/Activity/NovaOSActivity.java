package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    private ProgressBar progressBar;
    String param = "nome_cliente=w";
   // ArrayAdapter adapter;

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

        this.imagemEquip = (ImageView) findViewById(R.id.fotoPreview);

        AutoCompleteTextView mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteClientes);
        mAutoCompleteTextView.setThreshold(1);
        //new carregaDados().execute();
        new carregaDados().execute(Rotas.URL_DADOS_CLIENTE_TUDO);
       // AutoCompleteTextView estados = (AutoCompleteTextView) findViewById(R.id.antoCompleteClientes);
        // ArrayAdapter para preencher com os estados
       // ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ESTADOS);
        //mAutoCompleteTextView.setAdapter(adaptador);


        botaoNovaOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exibir(selecionado);
                //exibir(selecionado2);
            }
        });



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

                JSONArray jsonArray = jsonObj.getJSONArray("cliente");
                //list.clear();
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject obj  = jsonArray.getJSONObject(i);
                        Cliente cl = new Cliente(
                                obj.getInt("idcliente"),
                                obj.getString("nome_cliente")
                        );
                        list.add(cl);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
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