package endroad.dl.view.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import endroad.dl.R
import endroad.dl.models.News
import ru.endroad.arena.data.load

class NewsItem(item: News) : ModelAbstractItem<News, NewsItem.ViewHolder>(item) {

	override val type = R.id.item_news

	override val layoutRes = R.layout.item_news

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
		super.bindView(holder, payloads)

		holder.mTextField.text = model.bodyText
		model.imageURL?.let(holder.mImageField::load)
	}

	class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
		val mImageField: ImageView = root.findViewById(R.id.image)
		val mTextField: TextView = root.findViewById(R.id.information)
	}
}