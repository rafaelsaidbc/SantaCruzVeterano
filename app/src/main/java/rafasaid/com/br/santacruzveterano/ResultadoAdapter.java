package rafasaid.com.br.santacruzveterano;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import static rafasaid.com.br.santacruzveterano.R.id.shareItemResultado2017;

public class ResultadoAdapter extends ArrayAdapter<ResultadoFirebase> {


    public ResultadoAdapter(Context context, int resource, List<ResultadoFirebase> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_resultado, parent, false);

            ImageButton mBtnShareItemResultado2017 = (ImageButton) convertView.findViewById(shareItemResultado2017);
            mBtnShareItemResultado2017.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent shareFacebookResultado2017 = new Intent(Intent.ACTION_SEND);
                    shareFacebookResultado2017.setType("text/plain");
                    shareFacebookResultado2017.putExtra(Intent.EXTRA_TEXT, "");
                    getContext().startActivity(Intent.createChooser(shareFacebookResultado2017, "Compartilhar resultado com:"));
                }
            });

        }

        TextView idAddResultadoTextView = (TextView) convertView.findViewById(R.id.idJogoResultado);
        TextView dataAddResultadoTextView = (TextView) convertView.findViewById(R.id.data_resultado);
        TextView golsStaCruzAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_sta_cruz_resultado);
        TextView golsAdversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_adversario_resultado);
        TextView adversarioAddResultadoTextView = (TextView) convertView.findViewById(R.id.adversario_resultado);
        TextView golsMarcadoresAddResultadoTextView = (TextView) convertView.findViewById(R.id.gols_marcadores_resultado);


        ResultadoFirebase resultado = getItem(position);

        idAddResultadoTextView.setText(resultado.getIdAddResultado());
        dataAddResultadoTextView.setText(resultado.getDataAddResultado());
        golsStaCruzAddResultadoTextView.setText(resultado.getGolsStaCruzAddResultado());
        golsAdversarioAddResultadoTextView.setText(resultado.getGolsAdversarioAddResultado());
        adversarioAddResultadoTextView.setText(resultado.getAdversarioAddResultado());
        golsMarcadoresAddResultadoTextView.setText(resultado.getGolsMarcadoresAddResultado());

        return convertView;

    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_resultado, container, false);


        return view;
    }
}


