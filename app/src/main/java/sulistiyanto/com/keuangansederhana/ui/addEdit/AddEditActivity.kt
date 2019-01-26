package sulistiyanto.com.keuangansederhana.ui.addEdit

import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_add_edit.*
import sulistiyanto.com.keuangansederhana.R
import sulistiyanto.com.keuangansederhana.application.MyApplication
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.di.subcomponent.ActivityComponent
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.ui.base.BaseActivity
import javax.inject.Inject
import android.content.Intent
import sulistiyanto.com.keuangansederhana.ui.billNumber.BillNumberActivity
import android.app.Activity


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class AddEditActivity : BaseActivity(), AddEditView {

    private var id: Int = 0
    private var serialNumber: String? = ""

    @Inject
    lateinit var presenter: AddEditPresenter
    @Inject
    lateinit var dbHandler: DatabaseHandler

    override fun initInjection() {
        val activityComponent: ActivityComponent = (applicationContext as MyApplication)
            .appComponent
            .activityComponent()
            .build()
        activityComponent.inject(this)
    }

    override fun initLayout() {
        setContentView(R.layout.activity_add_edit)
        presenter.attach(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val key = intent.getStringExtra("key")
        presenter.getKey(key, intent)

        btnSave.setOnClickListener {
            val name = etFromName.text.toString()
            val count = etCount.text.toString()
            val desc = etDescription.text.toString()
            val bill = etBillNumber.text.toString()
            serialNumber?.let { it1 -> presenter.save(dbHandler, name, count, desc, key, id, it1, bill) }
        }

        btnDelete.setOnClickListener {
            presenter.deleteData(dbHandler, id)
        }

        imageAdd.setOnClickListener {
            val i = Intent(this, BillNumberActivity::class.java)
            startActivityForResult(i, 1)
        }
    }

    override fun setTitleToolbar(title: String) {
        supportActionBar?.title = title
    }

    override fun displayButtonDelete() {
        btnDelete.visibility = View.VISIBLE
    }

    override fun displayDataEdit(model: FinancialModel) {
        id = model.id!!
        serialNumber = model.serialNumber
        etFromName.setText(model.name)
        etCount.setText(model.count)
        etDescription.setText(model.description)
        etBillNumber.setText(model.billNumber)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                val bill = data?.getStringExtra("bill")
                etBillNumber.setText(bill)
            }
        }
    }

    override fun successSave() {
        finish()
    }

    override fun displayError(message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
