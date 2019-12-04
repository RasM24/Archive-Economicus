package endroad.dl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.models.Factory
import kotlinx.android.synthetic.main.activity_list.*

/**
 * Created by dvoly on 27.02.2017.
 */
class FactoryActivity : ActivityExtendNavigation() {

	override val idChecked = R.id.nav_factory

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list)

		recycleList.setHasFixedSize(true)
		val linearLayoutManager = LinearLayoutManager(this)
		recycleList.addItemDecoration(DividerItemDecoration(baseContext, linearLayoutManager.orientation))
		recycleList.layoutManager = linearLayoutManager
		recycleList.adapter = FactoryAdapter(factoryList, this)
	}

	override fun onClick(view: View) {}
	private inner class FactoryAdapter internal constructor(val factoryList: List<Factory>, val context: Context) :
		RecyclerView.Adapter<FactoryAdapter.ViewHolder>() {

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

			val cv: View = itemView.findViewById(R.id.factory_view)
			val image: ImageView = itemView.findViewById(R.id.image)
			val name: TextView = itemView.findViewById(R.id.text_name)

			init {
				cv.setOnClickListener(this)
			}

			override fun onClick(view: View) {
				val position = adapterPosition
				if (position != RecyclerView.NO_POSITION) {
					val intent = Intent(applicationContext, FactoryInfoActivity::class.java)
					intent.putExtra("factoryId", position)
					startActivity(intent)
				}
			}

		}

		override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
			val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_factory, viewGroup, false)
			return ViewHolder(v)
		}

		override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
			ViewHolder.name.text = factoryList[i].name
			ViewHolder.image.setImageDrawable(loadImageFromAsset(factoryList[i].imagePath))
		}

		override fun getItemCount(): Int {
			return factoryList.size
		}
	}
}