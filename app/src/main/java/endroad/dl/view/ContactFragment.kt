package endroad.dl.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.R
import endroad.dl.data.ContactDataSource
import endroad.dl.models.Contact
import kotlinx.android.synthetic.main.activity_list.*
import ru.endroad.arena.data.CircleTransform
import ru.endroad.arena.data.load
import ru.endroad.arena.viewlayer.fragment.BaseFragment

class ContactFragment : BaseFragment() {

	override val layout = R.layout.base_fragment_list

	private val contactDataSource = ContactDataSource()

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {
		val mLayoutManager = LinearLayoutManager(requireContext())
		list.setHasFixedSize(true)
		list.layoutManager = mLayoutManager
		list.addItemDecoration(DividerItemDecoration(requireContext(), mLayoutManager.orientation))
		list.adapter = LinkAdapter(contactDataSource.getContactList())
	}

	companion object {
		fun newInstance(): Fragment = ContactFragment()
	}

	private inner class LinkAdapter(val list: List<Contact>) :
		RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
			val cv: View = itemView.findViewById(R.id.contact_item)
			val image: ImageView = itemView.findViewById(R.id.image)
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
			val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_contact, viewGroup, false)
			return ViewHolder(v)
		}

		override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
			ViewHolder.name.text = list[i].name
			ViewHolder.post.text = list[i].post
			ViewHolder.image.load(list[i].imagePath, CircleTransform())
		}

		override fun getItemCount(): Int =
			list.size
	}
}

