package sulistiyanto.com.keuangansederhana.ui.main

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import sulistiyanto.com.keuangansederhana.R
import sulistiyanto.com.keuangansederhana.adapter.AdapterFinancial
import sulistiyanto.com.keuangansederhana.application.MyApplication
import sulistiyanto.com.keuangansederhana.database.DatabaseHandler
import sulistiyanto.com.keuangansederhana.di.subcomponent.ActivityComponent
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.ui.addEdit.AddEditActivity
import sulistiyanto.com.keuangansederhana.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    lateinit var presenter: MainPresenter
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
        presenter.attach(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initRecyclerView()

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra("key", "add")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllData(dbHandler)
    }

    override fun displayDataFinancial(adapter: AdapterFinancial) {
        txtEmpty.visibility = View.GONE
        rvFinancial.adapter = adapter
    }

    override fun dataFinancialEmpty() {
        txtEmpty.visibility = View.VISIBLE
        rvFinancial.visibility = View.GONE
    }

    override fun editData(model: FinancialModel) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("key", "edit")
        intent.putExtra("model", model)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        rvFinancial.layoutManager = LinearLayoutManager(this)
        rvFinancial.itemAnimator = DefaultItemAnimator()
        rvFinancial.setHasFixedSize(true)
    }

    override fun displayError(message: String) {
    }

}
