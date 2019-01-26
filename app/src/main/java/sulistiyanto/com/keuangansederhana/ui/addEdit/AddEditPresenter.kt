package sulistiyanto.com.keuangansederhana.ui.addEdit

import android.annotation.SuppressLint
import android.content.Intent
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.ui.base.BasePresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AddEditPresenter @Inject constructor(): BasePresenter<AddEditView>() {

    fun getKey(key: String, intent: Intent) {
        if (key == "add") {
            view?.setTitleToolbar("Tambah Data")
        } else {
            view?.setTitleToolbar("Ubah Data")
            view?.displayButtonDelete()
            val model = intent.getParcelableExtra<FinancialModel>("model")
            view?.displayDataEdit(model)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun save(
        dbHandler: DatabaseHandler,
        name: String,
        count: String,
        desc: String,
        key: String,
        id: Int,
        serialNumber: String
    ) {
        when {
            name == "" -> view?.displayError("Nama dari hari diisi")
            count == "" -> view?.displayError("Jumlah Harus diisi")
            else -> {
                val date = Calendar.getInstance().time
                val df = SimpleDateFormat("dd/MM/yyyy")
                val formattedDate = df.format(date)
                val serial = generateSerialNumber(dbHandler, formattedDate)
                if (key == "add") {
                    val model = FinancialModel(id, name, count, desc, formattedDate, serial)
                    val result = dbHandler.addData(model)
                    resultDisplay(result)
                } else {
                    val model = FinancialModel(id, name, count, desc, formattedDate, serialNumber)
                    val result = dbHandler.updateData(model)
                    resultDisplay(result)
                }
            }
        }
    }

    private fun resultDisplay(result: Boolean) {
        if (result) {
            view?.successSave()
        } else {
            view?.displayError("Data gagal disimpan")
        }
    }

    fun deleteData(dbHandler: DatabaseHandler, id: Int) {
        val result = dbHandler.deleteData(id)
        resultDisplay(result)
    }

    private fun generateSerialNumber(
        dbHandler: DatabaseHandler,
        formattedDate: String
    ): String {

        val dateList = formattedDate.split("/".toRegex())

        val model =  dbHandler.getLastData()

        return if (null == model?.serialNumber) {
            "UM/${getFormatDateSerial(dateList)}/1"
        } else {
            val serialList = model.serialNumber.split("/".toRegex())
            val result = checkDateSerial(dateList, serialList[1])
            var number = 1
            if (result) {
                number = serialList[2].toInt() + 1
            }
            "UM/${getFormatDateSerial(dateList)}/$number"
        }
    }

    private fun checkDateSerial(list: List<String>, s1: String): Boolean{
        val date = getFormatDateSerial(list)
        return date == s1
    }

    private fun getFormatDateSerial(dateList: List<String>): String
            = "${dateList[0]}${dateList[1]}${dateList[2].substring(2,4)}"

}