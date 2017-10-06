package com.dev.wcp4.controleos.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<OrdemServico> list = new ArrayList<>();
    private String status;
    private ImageButton btn1, btn2, btn3;

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
            //btn2 = (ImageButton) view.findViewById(R.id.botaoAlterStatus);
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

            //final TextView textEnd = new TextView(mContext);
            //textEnd.setText("Endereço: " + end);

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

    public Adapter(Context mContext, List<OrdemServico> ordemservico) {
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

    //menu que abre
    /*private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.base_filtro, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        MyMenuItemClickListener() {
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int id = menuItem.getItemId();

            if (id == R.id.filtro0) {
                LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.base_add_acomp_status,null);
                AlertDialog.Builder alertbox = new AlertDialog.Builder(layout.getRootView().getContext());
                alertbox.setMessage("Atualizar O.S.");
                alertbox.setView(layout);
                alertbox.setPositiveButton("Atualizar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //to do
                        exibir("att");
                    }
                });
                alertbox.setNeutralButton("Cancelar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //to do
                        exibir("can");
                    }
                });
                alertbox.show();
            } //else if (id == R.id.filtro1) {
                //exibir("filtro1");
            //}
            return true;
        }
    }

    public void showPopup(View v) {
        //View popupView = LayoutInflater.from(mContext).inflate(R.layout.opcoes_activity, null);
        //PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        //popupView.callOnClick();
        //popupView.setBackgroundColor(1);
        //popupWindow.showAsDropDown(popupView, 0, 0);
        //popupWindow.getBackground();


    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void exibir(String msg){
        Toast.makeText(mContext,msg, Toast.LENGTH_SHORT).show();
    }

}