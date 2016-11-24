package gr.unipi.pavlos.addressbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by pavlos on 22/11/16.
 */

public class AddressDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "address_book.db";

    private static final int DATABASE_VERSION = 1;
    public AddressDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_ADDRESS_BOOK_TABLE =
                "CREATE TABLE " + AddressBookContract.AddressBookEntry.TABLE_NAME +" (" +AddressBookContract.AddressBookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        AddressBookContract.AddressBookEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                        AddressBookContract.AddressBookEntry.COLUMN_PHONE + " TEXT NOT NULL, " +
                        AddressBookContract.AddressBookEntry.COLUMN_KINITO + " TEXT NOT NULL, " +
                        AddressBookContract.AddressBookEntry.COLUMN_ADDRESS + " TEXT NOT NULL, " +
                        AddressBookContract.AddressBookEntry.COLUMN_EMAIL + " TEXT NOT NULL, " +
                        AddressBookContract.AddressBookEntry.COLUMN_BIRTH_DATE + " DATE" + ")";
        sqLiteDatabase.execSQL(SQL_CREATE_ADDRESS_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AddressBookContract.AddressBookEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
