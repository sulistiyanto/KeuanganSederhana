package sulistiyanto.com.keuangansederhana.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_financial.view.*
import sulistiyanto.com.keuangansederhana.R
import sulistiyanto.com.keuangansederhana.model.BillNumberModel
import sulistiyanto.com.keuangansederhana.model.FinancialModel
import sulistiyanto.com.keuangansederhana.utilities.toRupiah

class AdapterBill(
    private val items: List<BillNumberModel>,
    private val listener: (BillNumberModel) -> Unit
) : RecyclerView.Adapter<AdapterBill.BillHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): BillHolder {
        return BillHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_financial, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: BillHolder, position: Int) {
        holder.bindData(items[position], listener)
    }

    class BillHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtName = itemView.txt_name
        private val txtCount = itemView.txt_count
        private val txtDesc = itemView.txt_desc

        @SuppressLint("SetTextI18n")
        fun bindData(billModel: BillNumberModel, listener: (BillNumberModel) -> Unit) {

            txtName.text = "Bank : ${billModel.bank}"
            txtCount.text = "Nama Pemilik : ${billModel.name}"
            txtDesc.text = "No. Rekening : ${billModel.noBill}"

            itemView.setOnClickListener {
                listener(billModel)
            }
        }

    }
}