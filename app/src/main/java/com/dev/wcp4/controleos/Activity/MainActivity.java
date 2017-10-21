package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.Usuario;
import com.dev.wcp4.controleos.R;
import com.dev.wcp4.controleos.Activity.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private String parametros;

    private EditText editTextUsuario;
    private EditText editTextSenha;

    String param = "";
    private ArrayList<Usuario> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button) findViewById(R.id.botao_login);
        //Button botaoN = (Button) findViewById(R.id.botaoNovoUser);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        botaoN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //erro de tema
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

                AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
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
                        if (!usuario.isEmpty() && !senha.isEmpty()) {
                            parametros = "usuario=" + usuario + "&senha=" + senha + "&adm=" + 1;
                            new Logar().execute(Rotas.URL_LOGINADM);
                        } else {
                            exibir("Informe o Usuario e a Senha para acessar!");
                        }
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        */

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextUsuario = (EditText) findViewById(R.id.usuario_login);
                editTextSenha = (EditText) findViewById(R.id.senha_login);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String usuario = editTextUsuario.getText().toString().trim();
                    String senha = editTextSenha.getText().toString().trim();

                    if (!usuario.isEmpty() && !senha.isEmpty()) {
                        progressBar.setVisibility(View.VISIBLE);
                        param = "usuario=" + usuario;
                        parametros = "usuario=" + usuario + "&senha=" + senha;

                        new Logar().execute(Rotas.URL_LOGIN);

                    } else {
                        exibir("Informe o Usuario e a Senha para ter acesso ao sistema!");
                    }

                } else {
                    exibir(":( Favor verificar a sua conexão com a Internet!");
                }

            }

        });

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
                if (!jsonObj.getBoolean("error")) {
                    new carregaDados().execute(Rotas.URL_DADOS_USUARIO);
                    startActivity(new Intent(MainActivity.this, BaseActivity.class));
                    progressBar.setVisibility(View.INVISIBLE);
                    finish();

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    exibir("Usuario ou senha Incorretos!");

                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.INVISIBLE);
                exibir(e.getMessage());
            }
        }

        @Override
        protected String doInBackground(String... url) {

            return Conexao.postDados(url[0], parametros);
        }
    }

    public void exibir(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    public Context getContexto() {
        return this;
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
                JSONArray jsonArray = jsonObj.getJSONArray("usuario");
                list.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Usuario user = new Usuario(
                                obj.getInt("idfuncionario"),
                                obj.getString("nome"),
                                obj.getString("email"),
                                obj.getString("usuario"),
                                obj.getInt("adm")
                        );
                        list.add(user);
                        setaDadosUsuario(list.get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], param);
        }
    }

    public void setaDadosUsuario(Usuario usuario) {

        SharedPreferences prefs = getSharedPreferences("arq", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nome", usuario.getNome());
        editor.putString("user", usuario.getUsuario());
        editor.putInt("id", usuario.getIdfuncionario());
        editor.putString("email", usuario.getEmail());
        editor.putInt("adm", usuario.getAdm());
        editor.apply();
       /* exibir(usuario.getNome());
        exibir(usuario.getContato1());
        exibir(usuario.getContato2());
        exibir(usuario.getEmail());
        exibir(usuario.getSenha());
        exibir(Integer.toString(usuario.getAdm()));*/
    }

    /*private class LogarAdm extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            //exibir(s);
            try {
                JSONObject jsonObj = new JSONObject(s);
                if (!jsonObj.getBoolean("error")) {
                    startActivity(new Intent(MainActivity.this, NovoUserActivity.class));
                } else {
                    exibir("Este Usuario não é Administrador!");
                }
            } catch (JSONException e) {
                exibir(e.getMessage());
            }
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0], parametros);
        }
    }
*/

}


