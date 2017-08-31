package rafasaid.com.br.santacruzveterano.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import rafasaid.com.br.santacruzveterano.data.CalendarioContract.CalendarioEntry;
import rafasaid.com.br.santacruzveterano.data.ResultadoContract.ResultadoEntry;

/**
 * Created by Rafael Said on 23/08/2017.
 */

//classe que liga a um banco de dados retornando o objeto [SQLiteDatabase] do banco de dados; cria o banco de dados
//se ele ainda não existir
    /*
    C = create
    R = read
    U = update
    D = delete
     */

public class DbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "santacruz.db";

    private static final int DATABASE_VERSION = 2017;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_RESULTADOS_TABLE = "CREATE TABLE " + ResultadoContract.ResultadoEntry.TABLE_NAME + "("
                + ResultadoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ResultadoEntry.COLUMN_RESULTADO_DATA + " TEXT NOT NULL, "
                + ResultadoEntry.COLUMN_RESULTADO_TIMES + " TEXT NOT NULL, "
                + ResultadoEntry.COLUMN_RESULTADO_GOLS + " TEXT NOT NULL);";

        String SQL_CREATE_CALENDARIO_TABLE = "CREATE TABLE " + CalendarioContract.CalendarioEntry.TABLE_NAME_CALENDARIO + "("
                + CalendarioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CalendarioEntry.COLUMN_CALENDARIO_DATA + " TEXT NOT NULL, "
                + CalendarioEntry.COLUMN_CALENDARIO_ADVERSARIO + " TEXT NOT NULL, "
                + CalendarioEntry.COLUMN_CALENDARIO_LOCAL + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_RESULTADOS_TABLE);
        db.execSQL(SQL_CREATE_CALENDARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}