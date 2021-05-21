package com.example.stomatoloska_ordinacija.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stomatoloska_ordinacija.R;
import com.example.stomatoloska_ordinacija.activities.ListaKartonaActivity;
import com.example.stomatoloska_ordinacija.db.model.ZdravstveniKarton;

import java.util.List;


public class AdapterKartonPacijenta extends
        RecyclerView.Adapter<AdapterKartonPacijenta.MyViewHolder> {

    public List<ZdravstveniKarton> listMedical;
    public OnRecyclerItemClickListener listener;

    public interface OnRecyclerItemClickListener {
        void onRVItemClick(ZdravstveniKarton lisMedical);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ime;
        TextView prezime;
        TextView vrstUsluge;
        View view;

        public MyViewHolder(View itemView) {
            super( itemView );
            view = itemView;
            ime = itemView.findViewById( R.id.tv_recycler_ime );
            prezime = itemView.findViewById( R.id.tv_recycler_prezime );
            vrstUsluge = itemView.findViewById( R.id.tv_recycler_usluga );
        }

        public void bind(final ZdravstveniKarton medical, final OnRecyclerItemClickListener listener) {
            ime.setText( medical.getIme() );
            prezime.setText( medical.getPrezime() );
            vrstUsluge.setText( medical.getNazivUsluge() );

            view.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRVItemClick( medical );
                }
            } );
        }
    }

    public AdapterKartonPacijenta(List<ZdravstveniKarton> listMedical, ListaKartonaActivity listener) {
        this.listMedical = listMedical;
        this.listener = (OnRecyclerItemClickListener) listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.rv_single_item, viewGroup, false );
        MyViewHolder myViewHolder = new MyViewHolder( view );

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.bind( listMedical.get( i ), listener );

    }

    @Override
    public int getItemCount() {
        return listMedical.size();
    }

}
