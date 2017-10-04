package com.dev.wcp4.controleos.Activity;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Adapter.Adapter;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final Context context = this;
    private String parametros;
    private TextView textUsuario;
    private ProgressBar progressBar;
    private ArrayList<OrdemServico> list = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    String param = "id_funcionario=1" ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        new carregaDados().execute(Rotas.URL_DADOS_OS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textUsuario = (TextView) findViewById(R.id.textViewOla);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);

        botoesFlutuantes();

        //recupera o nome do usuario do sharedpreferences
        SharedPreferences prefs = getSharedPreferences("arq", MODE_PRIVATE);
        String usuario = prefs.getString("nomedoUser", null);
        assert usuario != null;
        usuario = usuario.substring(0,1).toUpperCase().concat(usuario.substring(1));
        exibir("Bem vindo, "+usuario);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        textUsuario = (TextView)header.findViewById(R.id.textViewOla);
        textUsuario.setText("Ola " +usuario);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            //super.onBackPressed();
        }
        BaseActivity.this.finish();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acao_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_novo_os) {
            //Toast.makeText(this,"novo os",Toast.LENGTH_SHORT).show();
            Intent novaos = new Intent(getContexto(),NovaOSActivity.class);
            startActivity(novaos);

        } else if (id == R.id.nav_consultar_os) {
            //Toast.makeText(this,"Consultar O.S.",Toast.LENGTH_SHORT).show();
            Intent consult = new Intent(getContexto(),ConsultarOrdemActivity.class);
            startActivity(consult);

        } else if (id == R.id.nav_cadastro_cliente) {
            //Toast.makeText(this,"Cadastrar Novo Cliente",Toast.LENGTH_SHORT).show();
            Intent cadast = new Intent(getContexto(),CadastrarClienteActivity.class);
            startActivity(cadast);

        } else if (id == R.id.nav_consultar_cliente) {
            //Toast.makeText(this,"Consultar cliente",Toast.LENGTH_SHORT).show();
            Intent consc = new Intent(getContexto(),ConsultarClienteActivity.class);
            startActivity(consc);

        } else if (id == R.id.nav_logout) {
            exibir("Logout feito com sucesso.");
            Intent intent = new Intent(this, MainActivity.class);
            BaseActivity.this.finish();
            startActivity(intent);

        } else if (id == R.id.nav_avançado) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.opcoes_activity, null);
            final EditText usuarioDialog = (EditText) alertLayout.findViewById(R.id.usuario_dialog);
            final EditText senhaDailog = (EditText) alertLayout.findViewById(R.id.senha_dialog);
            final CheckBox cbShowPassword = (CheckBox) alertLayout.findViewById(R.id.checkBox);

            cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // to encode password in dots
                        senhaDailog.setTransformationMethod(null);
                    } else {
                        // to display the password in normal text
                        senhaDailog.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Autorização necessaria!\nInforme usuario e senha:");
            alert.setView(alertLayout);
            alert.setCancelable(true);
            alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    exibir("Operação Cancelada");
                }
            });

            alert.setPositiveButton("Entrar!", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String usuario = usuarioDialog.getText().toString();
                    String senha = senhaDailog.getText().toString();
                    if(!usuario.isEmpty() && !senha.isEmpty()){
                        parametros = "usuario=" + usuario + "&senha=" + senha + "&adm=" + 1;
                        new Logar().execute(Rotas.URL_LOGINADM);
                    } else{
                        exibir("Informe o Usuario e a Senha para acessar!");
                    }
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();

        } else if (id == R.id.nav_sair) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Deseja encerrar a aplicação?");
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            BaseActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class Logar extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }
        @Override
        protected void onPostExecute(String s) {
            //exibir(s);
            try {
                JSONObject jsonObj = new JSONObject(s);
                if(!jsonObj.getBoolean("error")){
                    startActivity(new Intent(BaseActivity.this,NovoUserActivity.class));
                }else{
                    exibir("Este Usuario não é Administrador!");
                }
            } catch (JSONException e) {
                exibir(e.getMessage());
            }
        }
        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0],parametros);
        }
    }

    private class carregaDados extends AsyncTask<String, Object, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Object... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObj = new JSONObject(s);

                JSONArray jsonArray = jsonObj.getJSONArray("osDados");
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject obj  = jsonArray.getJSONObject(i);
                        OrdemServico os = new OrdemServico(
                                obj.getInt("idos"),
                                obj.getString("descricao"),
                                obj.getString("equipamentos"),
                                obj.getInt("status"),
                                obj.getString("nome"),
                                obj.getString("nome_cliente")

                        );
                        list.add(os);
                    }catch (JSONException e){

                        e.printStackTrace();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            adapter.notifyDataSetChanged();

        }

        @Override
        protected String doInBackground(String... url) {

            return Conexao.postDados(url[0],param);
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void botoesFlutuantes(){
        FloatingActionButton botao_atualizar = (FloatingActionButton) findViewById(R.id.botao_atualizar);
        botao_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Atualizando...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton botao_cons = (FloatingActionButton) findViewById(R.id.botao_consultar);
        botao_cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consult = new Intent(getContexto(),ConsultarOrdemActivity.class);
                startActivity(consult);
            }
        });

        FloatingActionButton botao_add = (FloatingActionButton) findViewById(R.id.botao_addos);
        botao_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Cliente ja cadastrado no sistema?");
                alertDialogBuilder
                        //.setMessage("Cliente ja cadastrado no sistema?")
                        .setCancelable(true)
                        .setNeutralButton("Sim",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                Intent novaos = new Intent(getContexto(),NovaOSActivity.class);
                                startActivity(novaos);
                            }
                        })

                        .setNegativeButton("Não",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                Intent cadast = new Intent(getContexto(),CadastrarClienteActivity.class);
                                startActivity(cadast);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public Context getContexto(){
        return this;
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
