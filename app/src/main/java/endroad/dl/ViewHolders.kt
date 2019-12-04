package endroad.dl

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import endroad.dl.models.News
import endroad.dl.models.Schedule

class ViewHolders {

	class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val mImageField: ImageView = itemView.findViewById(R.id.image)
		private val mTextField: TextView = itemView.findViewById(R.id.text_body)

		fun bindData(news: News) {
			mTextField.text = news.bodyText
			Picasso.with(mImageField.context).load(news.imageURL).into(mImageField)
		}
	}

	class ScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val mTextField: TextView = itemView.findViewById(R.id.text_information)

		fun bindData(schedule: Schedule) {
			mTextField.text = schedule.text
		}
	}
}