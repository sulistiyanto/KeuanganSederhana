package sulistiyanto.com.keuangansederhana.di.subcomponent

import dagger.Subcomponent
import sulistiyanto.com.keuangansederhana.ui.addEdit.AddEditActivity
import sulistiyanto.com.keuangansederhana.ui.billNumber.BillNumberActivity
import sulistiyanto.com.keuangansederhana.ui.main.MainActivity

@Subcomponent
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): ActivityComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(addEditActivity: AddEditActivity)
    fun inject(billNumberActivity: BillNumberActivity)
}