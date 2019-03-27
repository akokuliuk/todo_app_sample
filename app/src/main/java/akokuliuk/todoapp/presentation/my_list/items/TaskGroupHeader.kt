package akokuliuk.todoapp.presentation.my_list.items

import akokuliuk.todoapp.R
import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder


@EpoxyModelClass(layout = R.layout.fragment__my_list__task_group_header)
abstract class TaskGroupHeader : EpoxyModelWithHolder<TaskGroupHeader.Holder>() {

    @EpoxyAttribute
    lateinit var taskGroupName: String

    override fun bind(holder: Holder) {
        holder.taskGroupName.text = taskGroupName
        super.bind(holder)
    }


    class Holder : EpoxyHolder() {
        lateinit var taskGroupName: TextView

        override fun bindView(itemView: View) {
            taskGroupName = itemView.findViewById(R.id.task_group_name)
        }

    }
}