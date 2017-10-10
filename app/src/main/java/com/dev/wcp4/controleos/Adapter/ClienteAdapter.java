package com.dev.wcp4.controleos.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import java.util.ArrayList;
import java.util.List;

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

import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteAdapter extends ArrayAdapter {

    private Context mContext;
    private List<Cliente> list = new ArrayList<>();

    public ClienteAdapter(@NonNull Context context, @LayoutRes int resource, Context mContext, List<Cliente> list) {
        super(context, resource);
        this.mContext = mContext;
        this.list = list;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public List<Cliente> getList() {
        return list;
    }

    public void setList(List<Cliente> list) {
        this.list = list;
    }

    public int getItemCount() {
        return list.size();
    }

    public void exibir(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}

/*
public class ClienteAdapter extends ArrayAdapter<Cliente> {

    private final Context mContext;
    private final List<Cliente> mCliente;
    private final List<Cliente> mCliente_All;
    private final List<Cliente> mCliente_Suggestion;
    private final int mLayoutResourceId;

    public ClienteAdapter(Context context, int resource, List<Cliente> client) {
        super(context, resource, client);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mCliente = new ArrayList<>(client);
        this.mCliente_All = new ArrayList<>(client);
        this.mCliente_Suggestion = new ArrayList<>();
    }

    public int getCount() {
        return mCliente.size();
    }

    public Cliente getItem(int position) {
        return mCliente.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            Cliente client = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.textView);
            name.setText(client.getNomeCliente());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((Cliente) resultValue).getNomeCliente();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mCliente_Suggestion.clear();
                    for (Cliente client : mCliente_All) {
                        if (client.getNomeCliente().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            mCliente_Suggestion.add(client);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mCliente_Suggestion;
                    filterResults.count = mCliente_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCliente.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Cliente) {
                            mCliente.add((Cliente) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mCliente.addAll(mCliente_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}
*/


