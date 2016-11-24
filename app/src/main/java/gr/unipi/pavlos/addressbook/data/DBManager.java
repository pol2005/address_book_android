package gr.unipi.pavlos.addressbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pavlos on 22/11/16.
 */

public class DBManager {
    private Context context;
    private AddressDbHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new AddressDbHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String phone,String kinito,String address,String email,String birth_date) {
        ContentValues values = new ContentValues();
        values.put(AddressBookContract.AddressBookEntry.COLUMN_NAME, name);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_PHONE, phone);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_KINITO, kinito);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_ADDRESS, address);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_EMAIL, email);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_BIRTH_DATE, birth_date);
        long rowInserted = database.insert(AddressBookContract.AddressBookEntry.TABLE_NAME, null, values);
       if(rowInserted == -1){
           Toast.makeText(context, "Η εγγραφή δεν ήταν επιτυχής", Toast.LENGTH_LONG).show();
       }
        else{
           Toast.makeText(context, "Επιτυχής εγγραφή", Toast.LENGTH_LONG).show();
       }
    }

    public Cursor fetch() {
        String[] columns = new String[] { AddressBookContract.AddressBookEntry._ID, AddressBookContract.AddressBookEntry.COLUMN_NAME, AddressBookContract.AddressBookEntry.COLUMN_ADDRESS };
        Cursor cursor = database.query(AddressBookContract.AddressBookEntry.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(int _id, String name, String phone,String kinito,String address,String email,String birth_date) {
        ContentValues values = new ContentValues();
        values.put(AddressBookContract.AddressBookEntry.COLUMN_NAME, name);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_PHONE, phone);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_KINITO, kinito);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_ADDRESS, address);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_EMAIL, email);
        values.put(AddressBookContract.AddressBookEntry.COLUMN_BIRTH_DATE, birth_date);
        int i = database.update(AddressBookContract.AddressBookEntry.TABLE_NAME, values, AddressBookContract.AddressBookEntry._ID + " = " + _id, null);
        return i;
    }

    public void delete(int _id) {
        database.delete(AddressBookContract.AddressBookEntry.TABLE_NAME, AddressBookContract.AddressBookEntry._ID + "=" + _id, null);
    }

    public Cursor fetchSingle(int _id) {
        //String[] columns = new String[] { AddressBookContract.AddressBookEntry._ID, AddressBookContract.AddressBookEntry.COLUMN_NAME, AddressBookContract.AddressBookEntry.COLUMN_ADDRESS,AddressBookContract.AddressBookEntry.COLUMN_PHONE,AddressBookContract.AddressBookEntry.COLUMN_KINITO,AddressBookContract.AddressBookEntry.COLUMN_EMAIL,AddressBookContract.AddressBookEntry.COLUMN_BIRTH_DATE };
        //Cursor cursor = database.query(AddressBookContract.AddressBookEntry.TABLE_NAME, columns, null, null, null, null, null);
        String selectQuery = "SELECT * FROM "+ AddressBookContract.AddressBookEntry.TABLE_NAME +" WHERE " + AddressBookContract.AddressBookEntry._ID +"="+_id;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


}
