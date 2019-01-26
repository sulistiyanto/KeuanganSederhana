package sulistiyanto.com.keuangansederhana.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BillNumberModel(val id: Int?, val bank: String?, val name: String?, val noBill: String?) : Parcelable