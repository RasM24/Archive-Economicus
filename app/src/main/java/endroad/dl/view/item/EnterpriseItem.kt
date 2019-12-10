package endroad.dl.view.item

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.items.ModelAbstractItem
import endroad.dl.R
import endroad.dl.models.Enterprise

class EnterpriseItem(item: Enterprise) : ModelAbstractItem<Enterprise, EnterpriseItem.ViewHolder>(item) {

	override val type = R.id.item_enterprise

	override val layoutRes = R.layout.item_enterprise

	override fun getViewHolder(v: View) = ViewHolder(v)

	override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
		super.bindView(holder, payloads)

		holder.name.text = model.name
		holder.image.setImageResource(model.image)
	}

	class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
		val image: ImageView = root.findViewById(R.id.image)
		val name: TextView = root.findViewById(R.id.title)
	}
}