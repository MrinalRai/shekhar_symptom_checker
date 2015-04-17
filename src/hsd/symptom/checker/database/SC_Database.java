package hsd.symptom.checker.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SC_Database extends SQLiteOpenHelper {

	public static final String TABLE_SUGAR = "sc_sugar";
	public static final String TABLE_BP = "sc_bp";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_VALUE = "_value";
	public static final String COLUMN_DATE = "_date";
	public static final String COLUMN_SENT = "_sent";

	private static final String DATABASE_NAME = "symptom_checker.db";
	private static final int DATABASE_VERSION = 101;

	private static final String DATABASE_CREATE_SUGAR_TABLE = "CREATE TABLE "
			+ TABLE_SUGAR + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_DATE + " TEXT,"
			+ COLUMN_VALUE + " TEXT );";// Sugar Table

	private static final String DATABASE_CREATE_BP_TABLE = "CREATE TABLE "
			+ TABLE_BP + "(" + COLUMN_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT , " + COLUMN_DATE + " TEXT,"
			+ COLUMN_VALUE + " TEXT );";// Bp Table

	public SC_Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_SUGAR_TABLE);
		database.execSQL(DATABASE_CREATE_BP_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SC_Database.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUGAR);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BP);
		onCreate(db);
	}

	// Adding new sugar
	public void addSugar(String sugar, String date) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_VALUE, sugar); // sugar value
		values.put(COLUMN_DATE, date); // sugar value

		// Inserting Row
		db.insert(TABLE_SUGAR, null, values);
		db.close(); // Closing database connection
	}

	// Getting single sugar
	public String getSugar(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SUGAR, new String[] { COLUMN_ID,
				COLUMN_VALUE }, COLUMN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		String sugar = cursor.getString(1);
		// return sugar
		return sugar;
	}

	// Getting All Sugars
	public List<SugarBp> getAllSugars() {
		List<SugarBp> sugarsList = new ArrayList<SugarBp>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_SUGAR;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				// Adding sugar to list
				SugarBp sugar = new SugarBp(Float.parseFloat(cursor
						.getString(2)), cursor.getString(1));
				sugarsList.add(sugar);
			} while (cursor.moveToNext());
		}

		// return sugar list
		return sugarsList;
	}

	// Adding new bp
	public void addBp(String bp, String date) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COLUMN_VALUE, bp); // sugar value
		values.put(COLUMN_DATE, date); // sugar value

		// Inserting Row
		db.insert(TABLE_BP, null, values);
		db.close(); // Closing database connection
	}

	// Getting single bp
	public String getBp(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_BP, new String[] { COLUMN_ID,
				COLUMN_VALUE }, COLUMN_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		String bp = cursor.getString(1);
		// return bp
		return bp;
	}

	// Getting All Bps
	public List<SugarBp> getAllBps() {
		List<SugarBp> bpsList = new ArrayList<SugarBp>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BP;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				SugarBp bp = new SugarBp(Float.parseFloat(cursor.getString(2)),
						cursor.getString(1));
				// Adding bp to list
				bpsList.add(bp);
			} while (cursor.moveToNext());
		}

		// return bp list
		Log.e("bpsList size", "bpsList size is " + bpsList.size());
		return bpsList;
	}
}
