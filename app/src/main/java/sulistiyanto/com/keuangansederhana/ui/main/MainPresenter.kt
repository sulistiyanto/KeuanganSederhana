package sulistiyanto.com.keuangansederhana.ui.main

import sulistiyanto.com.keuangansederhana.adapter.AdapterFinancial
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.ui.base.BasePresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor(): BasePresenter<MainView>() {

    fun getAllData(dbHandler: DatabaseHandler) {
        val list = dbHandler.getAllFinancial
        if (list.count() > 0) {
            val adapter = AdapterFinancial(list) { model->
                view?.editData(model)
            }
            view?.displayDataFinancial(adapter)
        } else {
            view?.dataFinancialEmpty()
        }
    }
}