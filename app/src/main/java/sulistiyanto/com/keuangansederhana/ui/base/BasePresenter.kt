package sulistiyanto.com.keuangansederhana.ui.base

open class BasePresenter<V: BaseView> {

    protected var view: V? = null

    fun attach(view: V) {
        this.view = view
    }


    fun onDestroy() {
        this.view = null
    }
}