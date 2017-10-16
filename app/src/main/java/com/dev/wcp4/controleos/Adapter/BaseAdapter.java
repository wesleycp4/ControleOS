package com.dev.wcp4.controleos.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Entidade.OrdemServico;
import com.dev.wcp4.controleos.R;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    private Context mContext;
    private List<OrdemServico> list = new ArrayList<>();
    private String status;
    private ImageButton btn1, btn2;
    String endereco1, endereco2,endereco3,endereco4,endereco5,endereco6,endereco7;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textNomeCl, textDataAb, textDesc, textStatOs, textNumOs, textContatoOs;

        MyViewHolder(View view) {
            super(view);
            textNomeCl = (TextView) view.findViewById(R.id.textNomeCliente);
            textNumOs = (TextView) view.findViewById(R.id.textNumeroOs);
            textDataAb = (TextView) view.findViewById(R.id.textDataAbertura);
            textDesc = (TextView) view.findViewById(R.id.textDescricao);
            textStatOs = (TextView) view.findViewById(R.id.textStatusOs);
            textContatoOs = (TextView) view.findViewById(R.id.textContato);

            btn1 = (ImageButton) view.findViewById(R.id.botaoNovoAcomp);
            btn2 = (ImageButton) view.findViewById(R.id.botaoAbrir);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            criaAlertDialog(view, getAdapterPosition());
        }

        @SuppressLint("SetTextI18n")
        void criaAlertDialog(View view, int position) {
            OrdemServico ordemservico = list.get(position);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Informações: ");

            final TextView textNumOs = new TextView(mContext);
            textNumOs.setText("Numero da O. S.:  " + ordemservico.getIdOS());

            final TextView textDataAb = new TextView(mContext);
            String data = ordemservico.getDataAberturaOS().replaceAll("-", "/");
            String[] s = data.split("/");
            String novaData = s[2]+"/"+s[1]+"/"+s[0];
            textDataAb.setText("Aberta em:  " +novaData);

            final TextView textNomeCl = new TextView(mContext);
            textNomeCl.setText("\nCliente:  " +ordemservico.getClienteNome());

            final TextView textEmail = new TextView(mContext);
            String emailC = ordemservico.getClienteEmail();
            if (emailC == null || emailC.equals("") || emailC.length()==0 || emailC == "null"){
                textEmail.setText("Email:  Não Informado!");
            } else{
                textEmail.setText("Email:  " + emailC);
            }

            final TextView textContato2 = new TextView(mContext);
            String cont2 = ordemservico.getContato2();
            if (cont2 == null || cont2.equals("") || cont2.length()==0 || cont2 == "null"){
                textContato2.setText("Contato 2:  Não Informado!");
            } else{
                textContato2.setText("Contato 2:  " + cont2 );
            }

            final TextView textEnd = new TextView(mContext);
            String compl = ordemservico.getClienteComplemento();
            if (compl == null || compl.equals("") || compl.length()==0 || compl == "null"){
                endereco3 = "NC";
            } else{
                endereco3 = compl;
            }
            String cp = ordemservico.getClienteCep();
            if (cp == null || cp.equals("") || cp.length()==0 || cp == "null"){
                endereco7 = "NC";
            } else{
                endereco7 = cp;
            }
            endereco1 = ordemservico.getClienteRua();
            endereco2 = Integer.toString(ordemservico.getClienteNumero());
            endereco4 = ordemservico.getClienteBairro();
            endereco5 = ordemservico.getClienteCidade();
            endereco6 = ordemservico.getClienteEstado();

            textEnd.setText("Endereço:  " +endereco1+ ", Nº " +endereco2+", "+endereco3+", Bairro: "+endereco4+", Cidade: "+endereco5+", "+endereco6+", CEP: "+endereco7);

            final TextView textDesc = new TextView(mContext);
            textDesc.setText("\nDescrição:  " + ordemservico.getDescricaoOS());

            final TextView textStatOs = new TextView(mContext);
            if (ordemservico.getStatusOS() == 0){
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
            textStatOs.setText("\nStatus:  " +status);

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

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.base_add_acomp_status,null);
                AlertDialog.Builder alertbox = new AlertDialog.Builder(layout.getRootView().getContext());
                alertbox.setMessage("Atualizar O.S.");
                alertbox.setView(layout);
                alertbox.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //to do
                        exibir("atualizar");
                    }
                });
                alertbox.setNeutralButton("Cancelar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //to do
                        exibir("Operação Cancelada!");
                    }
                });
                alertbox.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                exibir("abrir tela os");
                //abre intent jogando tudo pra nova tela todos os dados etc***********************************///********000000000000000000000000000000

            }
        });

        if (ordemservico.getStatusOS() == 0){
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
        String novaData = s[2]+"/"+s[1]+"/"+s[0];

        holder.textDataAb.setText(novaData);
        holder.textDesc.setText(ordemservico.getDescricaoOS());
        holder.textContatoOs.setText(ordemservico.getContato());
        holder.textStatOs.setText(status);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void exibir(String msg){
        Toast.makeText(mContext,msg, Toast.LENGTH_SHORT).show();
    }

}