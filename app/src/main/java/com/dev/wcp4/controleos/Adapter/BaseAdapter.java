package com.dev.wcp4.controleos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Activity.BaseActivity;
import com.dev.wcp4.controleos.Activity.OSActivity;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.OrdemServico;
import com.dev.wcp4.controleos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    private Context mContext;
    private String status;
    private String parametros = "";
    private int novoStatus;
    private List<OrdemServico> list = new ArrayList<>();
    //String TAG = "TESTE";

    private EditText editTextAcompanhamento;
    private Spinner spinnerStatus;
    private ProgressBar progressBar;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textNomeCl, textDataAb, textDesc, textStatOs, textNumOs, textContatoOs;

        public MyViewHolder(View view) {
            super(view);
            textNomeCl = (TextView) view.findViewById(R.id.textNomeCliente);
            textNumOs = (TextView) view.findViewById(R.id.textNumeroOs);
            textDataAb = (TextView) view.findViewById(R.id.textDataAbertura);
            textDesc = (TextView) view.findViewById(R.id.textDescricao);
            textStatOs = (TextView) view.findViewById(R.id.textStatusOs);
            textContatoOs = (TextView) view.findViewById(R.id.textContato);

            ImageButton btn1 = (ImageButton) view.findViewById(R.id.botaoNovoAcomp);
            ImageButton btn2 = (ImageButton) view.findViewById(R.id.botaoAbrir);

            view.setOnClickListener(this);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionaAcompanhamento();
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criaAlertDialog();
                }
            });

        }

        @Override
        public void onClick(View view) {
            OrdemServico ordemservico = list.get(getAdapterPosition());

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Informações: ");

            final TextView textNumOs = new TextView(mContext);
            textNumOs.setText("Numero da O. S.:  " + ordemservico.getIdOS());

            final TextView textDataAb = new TextView(mContext);
            String data = ordemservico.getDataAberturaOS().replaceAll("-", "/");
            String[] s = data.split("/");
            String novaData = s[2] + "/" + s[1] + "/" + s[0];
            textDataAb.setText("Aberta em:  " + novaData);

            final TextView textNomeCl = new TextView(mContext);
            textNomeCl.setText("\nCliente:  " + ordemservico.getClienteNome());

            final TextView textEmail = new TextView(mContext);
            String emailC = ordemservico.getClienteEmail();
            if (emailC == null || emailC.equals("") || emailC.length() == 0 || emailC == "null") {
                textEmail.setText("Email:  Não Informado!");
            } else {
                textEmail.setText("Email:  " + emailC);
            }

            final TextView textContato2 = new TextView(mContext);
            String cont2 = ordemservico.getContato2();
            if (cont2 == null || cont2.equals("") || cont2.length() == 0 || cont2 == "null") {
                textContato2.setText("Contato 2:  Não Informado!");
            } else {
                textContato2.setText("Contato 2:  " + cont2);
            }

            final TextView textEnd = new TextView(mContext);
            String compl = ordemservico.getClienteComplemento();
            String endereco3;
            if (compl == null || compl.equals("") || compl.length() == 0 || compl == "null") {
                endereco3 = "NC";
            } else {
                endereco3 = compl;
            }
            String cp = ordemservico.getClienteCep();
            String endereco7;
            if (cp == null || cp.equals("") || cp.length() == 0 || cp == "null") {
                endereco7 = "NC";
            } else {
                endereco7 = cp;
            }
            String endereco1 = ordemservico.getClienteRua();
            String endereco2 = Integer.toString(ordemservico.getClienteNumero());
            String endereco4 = ordemservico.getClienteBairro();
            String endereco5 = ordemservico.getClienteCidade();
            String endereco6 = ordemservico.getClienteEstado();

            textEnd.setText("Endereço:  " + endereco1 + ", Nº " + endereco2 + ", " + endereco3 + ", Bairro: " + endereco4 + ", Cidade: " + endereco5 + ", " + endereco6 + ", CEP: " + endereco7);

            final TextView textDesc = new TextView(mContext);
            textDesc.setText("\nDescrição:  " + ordemservico.getDescricaoOS());

            final TextView textStatOs = new TextView(mContext);
            if (ordemservico.getStatusOS() == 0) {
                status = "Aberto";
            } else if (ordemservico.getStatusOS() == 1) {
                status = "Em Analise";
            } else if (ordemservico.getStatusOS() == 2) {
                status = "Aguardando autorização";
            } else if (ordemservico.getStatusOS() == 3) {
                status = "Orçamento Aprovado";
            } else if (ordemservico.getStatusOS() == 4) {
                status = "Em reparo";
            } else if (ordemservico.getStatusOS() == 5) {
                status = "Pronto para Entrega/Retirada";
            } else if (ordemservico.getStatusOS() == 6) {
                status = "Finalizado";
            }
            textStatOs.setText("\nStatus:  " + status);

            final TextView textEquip = new TextView(mContext);
            textEquip.setText("Equipamento(s):  " + ordemservico.getEquipamentosOS());

            final TextView textUsuario = new TextView(mContext);
            textUsuario.setText("\nO. S. Aberta por:  " + ordemservico.getUsuarioNome());

            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.addView(textNumOs);
            linearLayout.addView(textDataAb);
            linearLayout.addView(textNomeCl);
            linearLayout.addView(textEmail);
            linearLayout.addView(textContato2);
            linearLayout.addView(textEnd);
            linearLayout.addView(textDesc);
            linearLayout.addView(textStatOs);
            linearLayout.addView(textEquip);
            linearLayout.addView(textUsuario);

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            //linearLayout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
            linearLayout.setPadding(16, 16, 16, 16);

            alertDialog.setView(linearLayout);
            alertDialog.create();
            alertDialog.show();
        }

        public void criaAlertDialog() {
            OrdemServico ordemservico = list.get(getAdapterPosition());

            String idos = Integer.toString(ordemservico.getIdOS());

            //exibir("Abrindo O.S. N: " + idos);

            Intent intent = new Intent(mContext, OSActivity.class);
            intent.putExtra("id", idos);
            mContext.startActivity(intent);
        }

        public void adicionaAcompanhamento() {
            OrdemServico ordemservico = list.get(getAdapterPosition());
            final String idos = Integer.toString(ordemservico.getIdOS());

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.base_add_acomp_status, null);
            AlertDialog.Builder alertbox = new AlertDialog.Builder(layout.getRootView().getContext());
            alertbox.setMessage("Atualizar O.S.");
            alertbox.setView(layout);
            alertbox.setPositiveButton("Atualizar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //to do
                    editTextAcompanhamento = (EditText) layout.findViewById(R.id.editTextAcompAdd);
                    String acompanhamento = editTextAcompanhamento.getText().toString().trim();
                    progressBar = (ProgressBar) layout.findViewById(R.id.progressBarAddAcomp);
                    spinnerStatus = (Spinner) layout.findViewById(R.id.spinnerStatusAlter);

                    if (spinnerStatus.getSelectedItem().equals("Aberto")) {
                        novoStatus = 0;
                    } else if (spinnerStatus.getSelectedItem().equals("Em Analise")) {
                        novoStatus = 1;
                    } else if (spinnerStatus.getSelectedItem().equals("Aguardando autorização")) {
                        novoStatus = 2;
                    } else if (spinnerStatus.getSelectedItem().equals("Orçamento Aprovado")) {
                        novoStatus = 3;
                    } else if (spinnerStatus.getSelectedItem().equals("Em reparo")) {
                        novoStatus = 4;
                    } else if (spinnerStatus.getSelectedItem().equals("Pronto para Entrega/Retirada")) {
                        novoStatus = 5;
                    } else if (spinnerStatus.getSelectedItem().equals("Finalizado")) {
                        novoStatus = 6;
                    }

                    SharedPreferences prefs = mContext.getSharedPreferences("arq", 0);
                    int funcid = prefs.getInt("id", 0);

                    String idf = Integer.toString(funcid);

                    if (!acompanhamento.isEmpty()) {
                        if (novoStatus >= 0 && novoStatus <= 5) {
                            try {
                                progressBar.setVisibility(View.VISIBLE);
                                parametros = "descricao=" + acompanhamento + "&status=" + novoStatus + "&idos=" + idos + "&idfuncionario=" + idf;
                                new cadastrarAcompanhamento().execute(Rotas.CADASTRAR_ACOMPANHAMENTO_STATUS);
                                //exibir(parametros);
                            } catch (ParseException e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                e.printStackTrace();
                            }
                        } else if (novoStatus == 6) {
                            try {
                                progressBar.setVisibility(View.VISIBLE);
                                parametros = "descricao=" + acompanhamento + "&status=" + novoStatus + "&idos=" + idos + "&idfuncionario=" + idf;
                                new cadastrarAcompanhamento().execute(Rotas.CADASTRAR_ACOMPANHAMENTO_STATUS);
                                //exibir(parametros);
                            } catch (ParseException e) {
                                progressBar.setVisibility(View.INVISIBLE);
                                e.printStackTrace();
                            }
                        }
                    } else {
                        exibir("Descreva uma atualização no campo Acompanhamento");
                    }

                }
            });
            alertbox.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    //to do
                    exibir("Operação Cancelada!");
                }
            });
            alertbox.show();

        }
    }

    public BaseAdapter(Context mContext, Context context, List<OrdemServico> ordemservico) {
        this.mContext = mContext;
        Context context1 = context;
        this.list = ordemservico;
    }

    public BaseAdapter(Context mContext, List<OrdemServico> ordemservico) {
        this.mContext = mContext;
        this.list = ordemservico;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_os, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OrdemServico ordemservico = list.get(position);

        if (ordemservico.getStatusOS() == 0) {
            status = "Aberto";
        } else if (ordemservico.getStatusOS() == 1) {
            status = "Em Analise";
        } else if (ordemservico.getStatusOS() == 2) {
            status = "Aguardando autorização";
        } else if (ordemservico.getStatusOS() == 3) {
            status = "Orçamento Aprovado";
        } else if (ordemservico.getStatusOS() == 4) {
            status = "Em reparo";
        } else if (ordemservico.getStatusOS() == 5) {
            status = "Pronto para Entrega/Retirada";
        } else if (ordemservico.getStatusOS() == 6) {
            status = "Finalizado";
        }

        //dados do card da recyvlerview
        holder.textNomeCl.setText(ordemservico.getClienteNome());
        String id = Integer.toString(ordemservico.getIdOS());
        holder.textNumOs.setText(id);

        //converte data xxxx-xx-xx para xx/xx/xxxx
        String data = ordemservico.getDataAberturaOS().replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2] + "/" + s[1] + "/" + s[0];

        holder.textDataAb.setText(novaData);
        holder.textDesc.setText(ordemservico.getDescricaoOS());
        holder.textContatoOs.setText(ordemservico.getContato());
        holder.textStatOs.setText(status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void exibir(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    private class cadastrarAcompanhamento extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObj = new JSONObject(s);
                if (!jsonObj.getBoolean("error")) {

                    exibir("Acompanhamento Adicionado!\n\nFavor atualizar.");
                    progressBar.setVisibility(View.INVISIBLE);

                    //mContext.startActivity(new Intent(mContext,BaseActivity.class));

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir(":/ Erro!");
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