package endroad.dl.view.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import endroad.dl.R
import endroad.dl.models.Contact
import ru.endroad.arena.data.CircleTransform
import ru.endroad.arena.data.load

class ContactItem(item: Contact) : ModelAbstractItem<Contact, ContactItem.ViewHolder>(item) {

	override val type = R.id.item_contact

	override val layoutRes = R.layout.item_contact

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
		super.bindView(holder, payloads)

		holder.name.text = model.name
		holder.post.text = model.post
		holder.image.load(model.image, CircleTransform())
	}

	class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
		val image: ImageView = root.findViewById(R.id.image)
		val post: TextView = root.findViewById(R.id.name)
		val name: TextView = root.findViewById(R.id.post)
	}
}