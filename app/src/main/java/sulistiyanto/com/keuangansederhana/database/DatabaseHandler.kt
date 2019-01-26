package sulistiyanto.com.keuangansederhana.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import javax.inject.Inject

class DatabaseHandler @Inject constructor(context: Context) :
    SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    companion object {
        private val DB_VERSION = 2
        private val DB_NAME = "financialDB"
        private val TABLE_FINANCIAL = "financial"
        private val ID = "id"
        private val NAME = "name"
        private val DESC = "desc"
        private val COUNT = "count"
        private val DATE = "date"
        private val SERIAL_NUMBER = "serial_numebr"
    }

    val CREATE_TABLE = "CREATE TABLE $TABLE_FINANCIAL (" +
            ID + " INTEGER PRIMARY KEY," +
            NAME + " TEXT," +
            DESC + " TEXT," +
            COUNT + " TEXT);"

    val DATABASE_ALTER_DATE = ("ALTER TABLE $TABLE_FINANCIAL ADD COLUMN $DATE TEXT;")
    val DATABASE_ALTER_SERIAL_NUMBER = ("ALTER TABLE $TABLE_FINANCIAL ADD COLUMN $SERIAL_NUMBER TEXT;")

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL(DATABASE_ALTER_DATE)
            db?.execSQL(DATABASE_ALTER_SERIAL_NUMBER)
        }
    }

    fun addData(financial: FinancialModel): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, financial.name)
        values.put(DESC, financial.description)
        values.put(COUNT, financial.count)
        values.put(DATE, financial.date)
        values.put(SERIAL_NUMBER, financial.serialNumber)
        val success = db.insert(TABLE_FINANCIAL, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }


    val getAllFinancial: List<FinancialModel>
        get() {
            val taskList = ArrayList<FinancialModel>()
            val db = readableDatabase
            val selectQuery = "SELECT  * FROM $TABLE_FINANCIAL"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor != null) {
                cursor.moveToFirst()
                while (cursor.moveToNext()) {
                    val tasks = FinancialModel(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))),
                        cursor.getString(cursor.getColumnIndex(NAME)),
                        cursor.getString(cursor.getColumnIndex(COUNT)),
                        cursor.getString(cursor.getColumnIndex(DESC)),
                        cursor.getString(cursor.getColumnIndex(DATE)),
                        cursor.getString(cursor.getColumnIndex(SERIAL_NUMBER))
                    )
                    taskList.add(tasks)
                }
            }
            cursor.close()
            return taskList
        }

    fun updateData(financial: FinancialModel): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, financial.name)
        values.put(DESC, financial.description)
        values.put(COUNT, financial.count)
        val success = db.update(TABLE_FINANCIAL, values, "$ID=?", arrayOf(financial.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun deleteData(_id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_FINANCIAL, "$ID=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getLastData(): FinancialModel? {
        var model: FinancialModel? = null
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_FINANCIAL ORDER BY $ID DESC LIMIT 1"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null && cursor.moveToFirst()) {
            model = FinancialModel(
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID))),
                cursor.getString(cursor.getColumnIndex(NAME)),
                cursor.getString(cursor.getColumnIndex(COUNT)),
                cursor.getString(cursor.getColumnIndex(DESC)),
                cursor.getString(cursor.getColumnIndex(DATE)),
                cursor.getString(cursor.getColumnIndex(SERIAL_NUMBER))
            )
        }
        cursor.close()
        return model
    }
}
