package endroad.dl.view.item

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import endroad.dl.R
import endroad.dl.models.Schedule

class ScheduleItem(item: Schedule) : ModelAbstractItem<Schedule, ScheduleItem.ViewHolder>(item) {

	override val type = R.id.item_schedule

	override val layoutRes = R.layout.item_shedule

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
		super.bindView(holder, payloads)

		holder.mTextField.text = model.text
	}

	class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
		val mTextField: TextView = itemView.findViewById(R.id.description)
	}
}