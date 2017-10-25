package com.dev.wcp4.controleos.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.Acompanhamento;
import com.dev.wcp4.controleos.Adapter.AcompanhamentoAdapter;
import com.dev.wcp4.controleos.Entidade.OrdemServico;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OSActivity extends AppCompatActivity {

    private TextView txtIdOs;
    private TextView txtDesc;
    private TextView txtEquip;
    private TextView txtDataA;
    private TextView txtDataF;
    private TextView txtCont;
    private TextView txtCont2;
    private TextView txtStatus;
    private TextView txtNomeFunc;
    private TextView txtNomeCliente;
    private TextView txtCpfCliente;
    private TextView txtEmailCliente;
    private TextView txtEndComp;
    private ImageView imgImagem;
    private ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
    private ArrayList<Acompanhamento> acompanhamentos = new ArrayList<>();
    //private List<Acompanhamento> acompanhamentos = new ArrayList<>();

    private String param = "";
    private ProgressBar progressBar;
    private ProgressBar progressBarList;
    private String status;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    //ArrayList<Acompanhamento> acompanhamentos;
    //ListView listView;
    //private static AcompanhamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progressBarOs);
        progressBarList = (ProgressBar) findViewById(R.id.progressBarList);

        txtIdOs = (TextView) findViewById(R.id.textViewIdOs);
        txtDesc = (TextView) findViewById(R.id.textViewDescProblem);
        txtEquip = (TextView) findViewById(R.id.textViewEquip);
        txtDataA = (TextView) findViewById(R.id.textViewDataA);
        txtDataF = (TextView) findViewById(R.id.textViewDataF);
        txtCont = (TextView) findViewById(R.id.textViewCont);
        txtCont2 = (TextView) findViewById(R.id.textViewCont2);
        txtStatus = (TextView) findViewById(R.id.textViewStatusOs);
        txtNomeFunc = (TextView) findViewById(R.id.textViewAbertaPor);
        txtNomeCliente = (TextView) findViewById(R.id.textViewNomeCl);
        txtCpfCliente = (TextView) findViewById(R.id.textViewCpfCl);
        txtEmailCliente = (TextView) findViewById(R.id.textViewEmailCl);
        txtEndComp = (TextView) findViewById(R.id.textViewEndComp);
        imgImagem = (ImageView) findViewById(R.id.imageViewOs);

        Intent intent = getIntent();
        intent.getExtras();
        String idpassada = intent.getStringExtra("id");
        param = "idos=" + idpassada;

        new carregaDados().execute(Rotas.URL_DADOS_OS_TUDO);

        new carregaAcomp().execute(Rotas.URL_DADOS_ACOMPANHAMENTO);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAcomp);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AcompanhamentoAdapter(this, acompanhamentos);
        recyclerView.setAdapter(adapter);

    }

    public void setaDadosOs(OrdemServico dadosOs) {
        String id = Integer.toString(dadosOs.getIdOS());
        txtIdOs.setText(id);
        txtDesc.setText(dadosOs.getDescricaoOS());
        txtEquip.setText(dadosOs.getEquipamentosOS());

        String data = dadosOs.getDataAberturaOS().replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2] + "/" + s[1] + "/" + s[0];
        txtDataA.setText(novaData);

        String data2 = dadosOs.getDataFechamentoOS();
        String novaData2;
        if (data2 == null || data2.equals("") || data2.length() == 0 || data2 == "null") {
            novaData2 = "O.S. nao finalizada";
        } else {
            data2 = data2.replaceAll("-", "/");
            String[] s2 = data2.split("/");
            novaData2 = s2[2] + "/" + s2[1] + "/" + s2[0];
        }
        txtDataF.setText(novaData2);

        txtCont.setText(dadosOs.getContato());

        String cont2 = dadosOs.getContato2();
        if (cont2 == null || cont2.equals("") || cont2.length() == 0 || cont2 == "null") {
            cont2 = "Não Informado";
        } else {
            cont2 = cont2;
        }
        txtCont2.setText(cont2);


/*
       String valor = "";
        Float val = dadosOs.getValorOrcamentoOS();
        if (val == null || val.equals("") || val == 0) {
            valor = "NC";
        } else {
            valor = val.toString();
        }
        txtValOrc.setText(valor);

        String valor2 = "";
        Float val2 = dadosOs.getValorFinalOS();
        if (val2 == null || val2.equals("") || val2 == 0) {
            valor2 = "NC";
        } else {
            valor2 = val2.toString();
        }
        txtValFinal.setText(valor2);
*/

        if (dadosOs.getStatusOS() == 0) {
            status = "Aberto";
        } else if (dadosOs.getStatusOS() == 1) {
            status = "Em Analise";
        } else if (dadosOs.getStatusOS() == 2) {
            status = "Aguardando \nautorização";
        } else if (dadosOs.getStatusOS() == 3) {
            status = "Orçamento \nAprovado";
        } else if (dadosOs.getStatusOS() == 4) {
            status = "Em reparo";
        } else if (dadosOs.getStatusOS() == 5) {
            status = "Pronto para \nEntrega/Retirada";
        } else if (dadosOs.getStatusOS() == 6) {
            status = "Finalizado";
        }
        txtStatus.setText(status);

        String corte = dadosOs.getUsuarioNome();
        String[] corte2 = corte.split(" ");
        corte = corte2[0];
        txtNomeFunc.setText(corte);

        txtNomeCliente.setText(dadosOs.getClienteNome());

        txtCpfCliente.setText(dadosOs.getClienteCpf());

        txtEmailCliente.setText(dadosOs.getClienteEmail());

        String comp = dadosOs.getClienteComplemento();
        if (comp == null || comp.equals("") || comp.length() == 0 || comp == "null") {
            comp = "Sem Comp.";
        } else {
            comp = "Comp.: " + comp;
        }
        String end = dadosOs.getClienteRua() + ", " + dadosOs.getClienteNumero() + ", " + comp + ", Bairro: " + dadosOs.getClienteBairro() + "\n" + dadosOs.getClienteCidade() + ", " + dadosOs.getClienteEstado() + ", CEP: " + dadosOs.getClienteCep();
        txtEndComp.setText(end);

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
                JSONArray jsonArray = jsonObj.getJSONArray("os");
                ordemServicos.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        OrdemServico os = new OrdemServico(
                                obj.getInt("idos"),
                                obj.getString("descricao"),
                                obj.getString("equipamento"),
                                obj.getString("dataabertura"),
                                obj.getString("datafechamento"),
                                obj.getString("contato_cliente"),
                                obj.getString("contato2_cliente"),
                                obj.getInt("status"),
                                obj.getString("nome"),
                                obj.getString("nome_cliente"),
                                obj.getString("cpf_cliente"),
                                obj.getString("email_cliente"),
                                obj.getString("rua_cliente"),
                                obj.getInt("numero_cliente"),
                                obj.getString("complemento_cliente"),
                                obj.getString("bairro_cliente"),
                                obj.getString("cidade_cliente"),
                                obj.getString("estado_cliente"),
                                obj.getString("cep_cliente"),
                                obj.getString("imagem")
                        );
                        ordemServicos.add(os);
                        setaDadosOs(ordemServicos.get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], param);
        }
    }

    private class carregaAcomp extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarList.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            progressBarList.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObj = new JSONObject(s);
                JSONArray jsonArray = jsonObj.getJSONArray("acompanhamentoDados");
                acompanhamentos.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Acompanhamento ac = new Acompanhamento(
                                //obj.getInt("idacompanhamento"),
                                obj.getString("descricao_acompanhamento"),
                                obj.getString("data_acompanhamento"),
                                obj.getString("nome")
                                //obj.getInt("os_idos"),
                                //obj.getInt("funcionario_idfuncionario")
                        );
                       // exibir(i+"");
                        acompanhamentos.add(ac);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
            progressBarList.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], param);
        }
    }


}