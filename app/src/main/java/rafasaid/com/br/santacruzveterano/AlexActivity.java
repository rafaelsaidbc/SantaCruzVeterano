package rafasaid.com.br.santacruzveterano;

/**
 * Created by Rafael on 14/08/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class AlexActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_info_jogadores);

        ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
        jogadores.add(new Jogador(R.drawable.alex_corpo, "Nome: Alex",
                "Apelido: ", "Data de Nascimento: XX/XX/XXX", "Posição: Atacante", "Número da camisa: XX",
                "Ano de ingresso: 2014"));

        JogadorAdapter adapter = new JogadorAdapter(this, jogadores);

        ListView listView = (ListView) findViewById(R.id.lista_info_jogadores);

        listView.setAdapter(adapter);
    }

}