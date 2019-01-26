package sulistiyanto.com.keuangansederhana.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_financial.view.*
import sulistiyanto.com.keuangansederhana.R
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.utilities.toRupiah

class AdapterFinancial(
    private val items: List<FinancialModel>,
    private val listener: (FinancialModel) -> Unit
) : RecyclerView.Adapter<AdapterFinancial.FinancialHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): FinancialHolder {
        return FinancialHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_financial, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: FinancialHolder, position: Int) {
        holder.bindData(items[position], listener)
    }

    class FinancialHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName = itemView.txt_name
        private val txtCount = itemView.txt_count
        private val txtDesc = itemView.txt_desc

        @SuppressLint("SetTextI18n")
        fun bindData(financialModel: FinancialModel, listener: (FinancialModel) -> Unit) {

            txtName.text = "Dari : ${financialModel.name}"
            val countInt = financialModel.count!!.toDouble()
            txtCount.text = "Jumlah : ${toRupiah(countInt)}"
            txtDesc.text = "Keterangan : ${financialModel.serialNumber}"

            itemView.setOnClickListener {
                listener(financialModel)
            }
        }

    }
}