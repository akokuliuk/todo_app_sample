package akokuliuk.todoapp.utils.adapter

import com.airbnb.epoxy.EpoxyController


class SimpleController : EpoxyController() {

    var modelsBuilder: (SimpleController.() -> Unit)? = null

    fun buildWithModels(block: SimpleController.() -> Unit) {
        modelsBuilder = block
        requestModelBuild()
    }

    override fun buildModels() {
        modelsBuilder?.invoke(this)
    }
}