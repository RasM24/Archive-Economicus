package endroad.dl.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import endroad.dl.EnterpriseInfoActivity
import endroad.dl.R
import endroad.dl.data.EnterpriseDataSource
import endroad.dl.models.Enterprise
import kotlinx.android.synthetic.main.activity_list.*
import ru.endroad.arena.data.load
import ru.endroad.arena.viewlayer.fragment.BaseFragment

class EnterprisesFragment : BaseFragment() {

	private val enterpriseDataSource = EnterpriseDataSource()

	override val layout = R.layout.base_fragment_list

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {
		val mLayoutManager = LinearLayoutManager(requireContext())
		list.setHasFixedSize(true)
		list.layoutManager = mLayoutManager
		list.addItemDecoration(DividerItemDecoration(requireContext(), mLayoutManager.orientation))
		list.adapter = EnterpriseAdapter(enterpriseDataSource.getList())
	}

	companion object {
		fun newInstance(): Fragment = EnterprisesFragment()
	}

	private inner class EnterpriseAdapter internal constructor(val enterpriseList: List<Enterprise>) :
		RecyclerView.Adapter<EnterpriseAdapter.ViewHolder>() {

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

			val cv: View = itemView.findViewById(R.id.enterprise_item)
			val image: ImageView = itemView.findViewById(R.id.image)
			val name: TextView = itemView.findViewById(R.id.text_name)

			init {
				cv.setOnClickListener(this)
			}

			override fun onClick(view: View) {
				val position = adapterPosition
				if (position != RecyclerView.NO_POSITION) {
					val intent = Intent(requireContext(), EnterpriseInfoActivity::class.java)
					intent.putExtra("enterpriseId", position)
					startActivity(intent)
				}
			}

		}

		override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
			val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_enterprise, viewGroup, false)
			return ViewHolder(v)
		}

		override fun onBindViewHolder(ViewHolder: ViewHolder, i: Int) {
			ViewHolder.name.text = enterpriseList[i].name
			ViewHolder.image.load(enterpriseList[i].imagePath)
		}

		override fun getItemCount(): Int {
			return enterpriseList.size
		}
	}
}

