package sulistiyanto.com.keuangansederhana.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize class FinancialModel(
    val id: Int?,
    val name: String?,
    val count: String?,
    val description: String?,
    val date: String?,
    val serialNumber: String?,
    val billNumber: String?) : Parcelable