package rafasaid.com.br.santacruzveterano.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import rafasaid.com.br.santacruzveterano.R;

public class CalendarioActivity extends AppCompatActivity {

    private static final String TAG = "CalendarioActivity";

    private ListView mCalendarioListView;
    private CalendarioAdapter mCalendarioAdapter;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mCalendarioDatabaseReference;//classe que faz referência a uma parte específica da Database;
    //para cada referência que for utilizar a database, deve ter
    //uma classe

    //leitura e exibição dos dados da database na ListView
    private ChildEventListener mChildEventListener;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private ValueEventListener eventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);


        // Find the View that shows the AdicionarCalendario category
        FloatingActionButton btnAddListCalendario = (FloatingActionButton) findViewById(R.id.btn_add_calendario);

        // Set a click listener on that View
        btnAddListCalendario.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the jogadores category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link JogadoresActivity}
                Intent btnAddListCalendarioIntent = new Intent(CalendarioActivity.this, AdicionarCalendario.class);

                // Start the new activity (show jogadores activity)
                startActivity(btnAddListCalendarioIntent);
            }

        });


        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte de interesse, no caso calendario,
        //pode ser calendário, resultados, fotos (no lugar de calendario)
        mCalendarioDatabaseReference = mFirebaseDatabase.getReference().child("calendario");

        // Inicializa as referências das Views
        mCalendarioListView = (ListView) findViewById(R.id.calendarioListView);
        registerForContextMenu(mCalendarioListView); //mostra opções quando segura click
        mCalendarioListView.setOnCreateContextMenuListener(this);

        //Permite que os itens exibidos em calendário sejam clicáveis
        mCalendarioListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object listItem = mCalendarioListView.getItemAtPosition(position);
            }
        });


        // Initialize message ListView and its adapter, o ArrayList é a fonte de dados do CalendarioAdapter
        //pelo objeto mCalendarioAdapter
        List<CalendarioFirebase> calendarioFirebases = new ArrayList<>();
        mCalendarioAdapter = new CalendarioAdapter(this, R.layout.add_item_calendario, calendarioFirebases);
        mCalendarioListView.setAdapter(mCalendarioAdapter);

        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de calendario
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados do novo calendário
                CalendarioFirebase calendarioFirebase = dataSnapshot.getValue(CalendarioFirebase.class);//desserializa o calendario do banco de dados para o objeto CalendarioFirebase
                //o objeto CalendarioFirebase deve ter os mesmos campos dos objetos de calendario do banco de dados

                mCalendarioAdapter.add(calendarioFirebase);//adiciona o objeto CalendarioFirebase ao Adapter, converte
                //o calendario em um objeto CalendarioFirebase e adiciona ao Adapter, que será exibido na ListView
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        };
        mCalendarioDatabaseReference.orderByChild("idAddCalendario").addChildEventListener(mChildEventListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.calendario_itens_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcoes:
                Intent intentOpcoesCalendario = new Intent(this, MudarCalendario.class);
                this.startActivity(intentOpcoesCalendario);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}


