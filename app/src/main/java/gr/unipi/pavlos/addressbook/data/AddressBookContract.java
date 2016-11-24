package gr.unipi.pavlos.addressbook.data;

import android.provider.BaseColumns;

/**
 * Created by pavlos on 22/11/16.
 */

public final class AddressBookContract {
    private AddressBookContract() {}
    public static class AddressBookEntry implements BaseColumns{
        public static final String TABLE_NAME = "address_book";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_KINITO = "kinito";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_BIRTH_DATE = "birth_date";
    }
}
