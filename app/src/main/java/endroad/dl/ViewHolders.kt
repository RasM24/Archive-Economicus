package endroad.dl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.models.News
import endroad.dl.models.Schedule
import ru.endroad.arena.data.load

class ViewHolders {

	class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val mImageField: ImageView = itemView.findViewById(R.id.image)
		private val mTextField: TextView = itemView.findViewById(R.id.text_body)

		fun bindData(news: News) {
			mTextField.text = news.bodyText
			news.imageURL?.let(mImageField::load)
		}
	}

	class ScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val mTextField: TextView = itemView.findViewById(R.id.text_information)

		fun bindData(schedule: Schedule) {
			mTextField.text = schedule.text
		}
	}
}