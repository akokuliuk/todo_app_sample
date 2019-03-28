package akokuliuk.todoapp.presentation.my_list.items

import akokuliuk.todoapp.R
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder


@EpoxyModelClass(layout = R.layout.fragment__my_list__task_item)
abstract class TaskItem : EpoxyModelWithHolder<TaskItem.Holder>() {


    @EpoxyAttribute
    lateinit var name: String

    @EpoxyAttribute
    var description: String? = null

    @EpoxyAttribute
    var done: Boolean? = null

    @EpoxyAttribute
    lateinit var onClick: View.OnClickListener

    @EpoxyAttribute
    lateinit var onCheckedChanged: (Boolean) -> Unit

    override fun bind(holder: Holder) {
        done ?: throw IllegalArgumentException("done flag must be provided")
        holder.itemView.setOnClickListener(onClick)
        holder.doneCheckBox.setOnCheckedChangeListener { _, isChecked -> onCheckedChanged(isChecked) }
        holder.taskName.text = name
        holder.taskDescription.text = description
        holder.taskDescription.visibility = if(description.isNullOrEmpty()) View.GONE else View.VISIBLE
        super.bind(holder)
    }

    override fun unbind(holder: Holder) {
        holder.itemView.setOnClickListener(null)
        holder.doneCheckBox.setOnCheckedChangeListener(null)
        super.unbind(holder)
    }

    class Holder : EpoxyHolder() {

        lateinit var doneCheckBox: CheckBox
            private set
        lateinit var taskName: TextView
            private set
        lateinit var taskDescription: TextView
            private set
        lateinit var itemView: View
            private set

        override fun bindView(itemView: View) {
            taskName = itemView.findViewById(R.id.task_name)
            taskDescription = itemView.findViewById(R.id.task_description)
            doneCheckBox = itemView.findViewById(R.id.is_task_done)
            this.itemView = itemView
        }
    }
}