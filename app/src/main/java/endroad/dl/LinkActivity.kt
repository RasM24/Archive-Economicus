package endroad.dl

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.models.Link
import endroad.dl.support.ImageCircle
import kotlinx.android.synthetic.main.activity_list.*

class LinkActivity : ActivityExtendNavigation() {

	override val idChecked = R.id.nav_link

	private val mLayoutManager: LinearLayoutManager = LinearLayoutManager(this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list)

		recycleList.setHasFixedSize(true)
		recycleList.layoutManager = mLayoutManager
		recycleList.addItemDecoration(DividerItemDecoration(baseContext, mLayoutManager.orientation))
		recycleList.adapter = LinkAdapter(linkList, this)
	}

	override fun onClick(view: View) {}

	private inner class LinkAdapter(val list: List<Link>, var context: Context) :
		RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
			val cv: View = itemView.findViewById(R.id.link_view)
			val image: ImageCircle = itemView.findViewById(R.id.image)
			val post: TextView = itemView.findViewById(R.id.name)
			val name: TextView = itemView.findViewById(R.id.post)

			init {
				cv.setOnClickListener(this)
			}

			override fun onClick(view: View) {
				val position = adapterPosition
				if (position != RecyclerView.NO_POSITION) {
					val address = Uri.parse(list[position].url)
					val browser = Intent(Intent.ACTION_VIEW, address)
					startActivity(browser)
				}
			}

		}

		override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
			val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_link, viewGroup, false)
			return ViewHolder(v)
		}

		override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
			ViewHolder.name.text = list[i].name
			ViewHolder.post.text = list[i].post
			ViewHolder.image.setImageDrawable(loadImageFromAsset(list[i].imagePath))
		}

		override fun getItemCount(): Int =
			list.size
	}
}