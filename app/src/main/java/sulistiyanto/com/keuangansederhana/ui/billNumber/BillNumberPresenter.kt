package sulistiyanto.com.keuangansederhana.ui.billNumber

import sulistiyanto.com.keuangansederhana.adapter.AdapterBill
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.model.BillNumberModel
import sulistiyanto.com.keuangansederhana.ui.base.BasePresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BillNumberPresenter @Inject constructor(): BasePresenter<BillNumberView>() {

    fun getAllBill(dbHandler: DatabaseHandler) {
        val list = dbHandler.getAllBillNumber
        if (list.count() > 0) {
            val adapter = AdapterBill(list) { model ->
                view?.chooseBiil(model)
            }
            view?.displayBillNumber(adapter)
        } else {
            view?.emptyData()
        }
    }

    fun saveBill(
        dbHandler: DatabaseHandler,
        bank: String,
        name: String,
        bill: String
    ) {
        when {
            bank == "" -> view?.displayError("Isi nama bank")
            name == "" -> view?.displayError("Isi nama pemilik rekening")
            bill == "" -> view?.displayError("Isi nomor rekening")
            else -> {
                val model = BillNumberModel(0, bank, name, bill)
                val result = dbHandler.addDataBillNumber(model)
                if (result) {
                    view?.success()
                } else {
                    view?.displayError("data gagal disimpan")
                }
            }
        }
    }
}