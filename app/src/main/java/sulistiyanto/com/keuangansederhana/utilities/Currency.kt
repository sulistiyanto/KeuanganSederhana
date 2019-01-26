package sulistiyanto.com.keuangansederhana.utilities

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun toRupiah(price: Double?): String {
    val idnFormat = DecimalFormat.getCurrencyInstance() as DecimalFormat
    val formatRp = DecimalFormatSymbols()
    formatRp.currencySymbol = ""
    formatRp.monetaryDecimalSeparator = '-'
    formatRp.groupingSeparator = '.'
    idnFormat.decimalFormatSymbols = formatRp
    val priceString = idnFormat.format(price).replace(",", ".")
        .replace("-00", "").replace("-",",")
    return "Rp $priceString"
}