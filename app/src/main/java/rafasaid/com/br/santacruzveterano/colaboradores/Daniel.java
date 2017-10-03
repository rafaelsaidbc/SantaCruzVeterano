package rafasaid.com.br.santacruzveterano.colaboradores;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rafasaid.com.br.santacruzveterano.R;
import rafasaid.com.br.santacruzveterano.jogadores.firebase.JogadoresFirebase;
import rafasaid.com.br.santacruzveterano.jogadores.firebase.JogadoresFirebaseAdapter;

public class Daniel extends AppCompatActivity {

    private static final String TAG = "Daniel";

    // cria a variável mDanielListView como uma ListView
    private ListView mDanielListView;

    //criar a variável mDanielFirebaseAdapter como um Adapter de JogadoresFirebaseAdapter
    private JogadoresFirebaseAdapter mDanielFirebaseAdapter;

    // Variáveis de instâncias do Firebase
    private FirebaseDatabase mFirebaseDatabase;//ponto de acesso do app ao Database
    private DatabaseReference mDanielDatabaseReference;//classe que faz referência a uma parte
    // específica da Database; para cada referência que for utilizar a database, deve ter uma classe

    //leitura e exibição dos dados da database na ListView indicada
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadores_firebase);

        // Inicializa os componentes do Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance(); //é o ponto de acesso principal do Database


        //mFirebaseDatabase.getReference() faz referência ao nó raiz; child() faz referência à parte
        // de interesse, no caso jogadores > torcedores > Daniel, no Firebase ainda tem o campo (child)
        //Dados, que é de onde as informações serão retiradas
        mDanielDatabaseReference = mFirebaseDatabase.getReference().child("jogadores").child("torcedores").child("Daniel");

        // Inicializa as referências das Views
        mDanielListView = (ListView) findViewById(R.id.danielDadosListView);


        // Inicializa danielListView e o adapter, o ArrayList é a fonte de dados do DanielFirebaseAdapter
        //pelo objeto mDanielFirebaseAdapter
        List<JogadoresFirebase> jogadoresFirebase = new ArrayList<>();
        mDanielFirebaseAdapter = new JogadoresFirebaseAdapter(this, R.layout.jogadores_firebase, jogadoresFirebase);
        mDanielListView.setAdapter(mDanielFirebaseAdapter);


        //leitura e exibição dos dados da database no app
        mChildEventListener = new ChildEventListener() {

            //chamado quando uma partida for inserida na lista de resultado
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //obtêm os dados de Daniel
                JogadoresFirebase danielFirebase = dataSnapshot.getValue(JogadoresFirebase.class);//desserializa o resultado do banco de dados para o objeto ResultadoFirebase
                //o objeto ResultadoFirebase deve ter os mesmos campos dos objetos de resultado do banco de dados


                mDanielFirebaseAdapter.add(danielFirebase);//adiciona o objeto ResultadoFirebase ao Adapter, converte
                //o resultado em um objeto ResultadoFirebase e adiciona ao Adapter, que será exibido na ListView
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
        mDanielDatabaseReference.addChildEventListener(mChildEventListener);
    }

}