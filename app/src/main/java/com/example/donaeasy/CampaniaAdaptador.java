package com.example.donaeasy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class  CampaniaAdaptador extends  RecyclerView.Adapter<CampaniaAdaptador.ViewHolder> {


    private List<Campania> listaCampanias;
    private Context contexto;
    private Donador donador;

    public CampaniaAdaptador(List<Campania> listaCampanias, Context contexto, Donador donador) {
        this.listaCampanias = listaCampanias;
        this.contexto = contexto;
        this.donador = donador;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_campania, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.txtNombre.append(listaCampanias.get(position).getNombrePaciente());
         holder.txtTipoSangre.append(listaCampanias.get(position).getTipoSangre());
         holder.txtDonadoresNecesarios.append(Integer.toString(listaCampanias.get(position).getDonadoresNecesarios()));
         holder.txtUbicacion.append(listaCampanias.get(position).getUbicacion());
         holder.txtDescripcion.append(listaCampanias.get(position).getDescripcion());
         holder.btnDonarCampania.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(donador.getEstatus().equals("Activo")){
                     if(donador.getCita()==null){
                         Campania campaniaDonar = listaCampanias.get(holder.getAdapterPosition());
                         Intent intentAgendarCita =new Intent(contexto, AgendarCita.class);
                         intentAgendarCita.putExtra("campaniaDonar", campaniaDonar);
                         intentAgendarCita.putExtra("donador", donador);
                         contexto.startActivity(intentAgendarCita);
                         //((Activity)contexto).finish();
                     }else {
                         Toast.makeText(contexto, "Solo puedes participar en una campaña", Toast.LENGTH_SHORT).show();
                     }
                 }else {
                     Toast.makeText(contexto, "Actualmente no cuentas con los requisitos minimos para donar", Toast.LENGTH_SHORT).show();
                 }
             }
         });

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
