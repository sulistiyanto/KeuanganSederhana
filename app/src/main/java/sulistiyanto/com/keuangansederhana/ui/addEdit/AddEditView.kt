package sulistiyanto.com.keuangansederhana.ui.addEdit

import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.ui.base.BaseView

interface AddEditView: BaseView {
    fun setTitleToolbar(title: String)
    fun successSave()
    fun displayButtonDelete()
    fun displayDataEdit(model: FinancialModel)
}