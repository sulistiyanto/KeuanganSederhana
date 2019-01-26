package sulistiyanto.com.keuangansederhana.ui.billNumber

import sulistiyanto.com.keuangansederhana.adapter.AdapterBill
import sulistiyanto.com.keuangansederhana.model.BillNumberModel
import sulistiyanto.com.keuangansederhana.ui.base.BaseView

interface BillNumberView: BaseView {

    fun displayBillNumber(adapter: AdapterBill)
    fun emptyData()
    fun success()
    fun chooseBiil(model: BillNumberModel)
}