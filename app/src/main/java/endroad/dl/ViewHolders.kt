package endroad.dl

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.models.Schedule

class ViewHolders {

	class ScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val mTextField: TextView = itemView.findViewById(R.id.text_information)

		fun bindData(schedule: Schedule) {
			mTextField.text = schedule.text
		}
	}
}