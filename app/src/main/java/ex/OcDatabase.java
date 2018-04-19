package ex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mysql.jdbc.Connection;

/**
 * Created by sp on 2018/3/12.
 */

public class OcDatabase extends SQLiteOpenHelper {
    public static final String CREATE_OC ="create table oc(" +
            "id integer primary key," +
            "subject varchar," +
            "A varchar," +
            "B varchar," +
            "C varchar," +
            "D varchar," +
            "answer varchar)";
    private Context mContext;
    public  OcDatabase(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext =context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OC);
        Toast.makeText(mContext,"生成成功!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists oc");
        db.execSQL("drop table if exists mc");
        db.execSQL("drop table if exists judga");
        db.execSQL("drop table if exists grade");
    }
}
