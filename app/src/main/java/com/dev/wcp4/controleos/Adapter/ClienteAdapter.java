package com.dev.wcp4.controleos.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.dev.wcp4.controleos.Entidade.Cliente;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;

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


