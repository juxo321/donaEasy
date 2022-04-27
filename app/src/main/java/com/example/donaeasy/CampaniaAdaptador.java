package com.example.donaeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CampaniaAdaptador extends  RecyclerView.Adapter<CampaniaAdaptador.ViewHolder> {

    private List<Campania> listaCampanias;
    private Context contexto;

    public CampaniaAdaptador(List<Campania> listaCampanias, Context contexto) {
        this.listaCampanias = listaCampanias;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_campania, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txtNombre.setText(listaCampanias.get(position).getNombrePaciente());
         holder.txtTipoSangre.setText(listaCampanias.get(position).getTipoSangre());
         holder.txtDonadoresNecesarios.setText(Integer.toString(listaCampanias.get(position).getDonadoresNecesarios()));
         holder.txtUbicacion.setText(listaCampanias.get(position).getUbicacion());
         holder.txtDescripcion.setText(listaCampanias.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaCampanias.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFoto;
        private TextView txtNombre;
        private TextView txtTipoSangre;
        private TextView txtDonadoresNecesarios;
        private TextView txtUbicacion;
        private TextView txtDescripcion;

        private Button btnCompartirCampania;
        private Button btnDonarCampania;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.imgFotoCard);
            txtNombre = itemView.findViewById(R.id.txtNombreCard);
            txtTipoSangre = itemView.findViewById(R.id.txtTipoSangreCard);
            txtDonadoresNecesarios = itemView.findViewById(R.id.txtDonadoresNecesariosCard);
            txtUbicacion = itemView.findViewById(R.id.txtUbicacionCard);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcionCard);

            btnCompartirCampania = itemView.findViewById(R.id.btnCompartirCampaniaCard);
            btnDonarCampania = itemView.findViewById(R.id.btnDonarCampaniaCard);
        }

    }
}
