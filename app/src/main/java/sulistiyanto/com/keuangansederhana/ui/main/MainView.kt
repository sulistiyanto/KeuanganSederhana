package sulistiyanto.com.keuangansederhana.ui.main

import sulistiyanto.com.keuangansederhana.adapter.AdapterFinancial
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.ui.base.BaseView

interface MainView: BaseView {

    fun displayDataFinancial(adapter: AdapterFinancial)
    fun dataFinancialEmpty()
    fun editData(model: FinancialModel)
}