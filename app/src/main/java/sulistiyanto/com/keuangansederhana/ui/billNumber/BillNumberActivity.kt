package sulistiyanto.com.keuangansederhana.ui.billNumber

import android.app.AlertDialog
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import sulistiyanto.com.keuangansederhana.R

import kotlinx.android.synthetic.main.activity_bill_number.*
import kotlinx.android.synthetic.main.content_bill_number.*
import sulistiyanto.com.keuangansederhana.adapter.AdapterBill
import sulistiyanto.com.keuangansederhana.application.MyApplication
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.di.subcomponent.ActivityComponent
import sulistiyanto.com.keuangansederhana.model.BillNumberModel
import sulistiyanto.com.keuangansederhana.ui.base.BaseActivity
import javax.inject.Inject
import android.app.Activity
import android.content.Intent


class BillNumberActivity : BaseActivity(), BillNumberView {

    @Inject
    lateinit var presenter: BillNumberPresenter
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
        setContentView(R.layout.activity_bill_number)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.attach(this)

        initRecyclerView()
        presenter.getAllBill(dbHandler)
        fab.setOnClickListener { view ->
            showCreateDialog()
        }
    }

    override fun displayError(message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    private fun initRecyclerView() {
        rvBill.layoutManager = LinearLayoutManager(this)
        rvBill.itemAnimator = DefaultItemAnimator()
        rvBill.setHasFixedSize(true)
    }

    override fun displayBillNumber(adapter: AdapterBill) {
        rvBill.adapter = adapter
        txtEmpty.visibility = View.GONE
    }

    override fun emptyData() {
        txtEmpty.visibility = View.VISIBLE
    }

    override fun success() {
        presenter.getAllBill(dbHandler)
    }

    override fun chooseBiil(model: BillNumberModel) {
        val returnIntent = Intent()
        returnIntent.putExtra("bill", model.noBill)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private fun showCreateDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tambah Rekening Baru")

        val view = layoutInflater.inflate(R.layout.dialog_bill, null)

        val etBank = view.findViewById(R.id.etBank) as EditText
        val etName = view.findViewById(R.id.etName) as EditText
        val etBill = view.findViewById(R.id.etBill) as EditText

        builder.setView(view)
        builder.setCancelable(false)
        builder.setPositiveButton("Simpan") { dialog, p1 ->
            val bank = etBank.text.toString()
            val name = etName.text.toString()
            val bill = etBill.text.toString()
            presenter.saveBill(dbHandler, bank, name, bill)
        }

        builder.setNegativeButton("Batal") { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }
}
