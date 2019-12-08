package endroad.dl.view

import androidx.fragment.app.Fragment
import com.mikepenz.fastadapter.IModelItem
import endroad.dl.R
import endroad.dl.application.SingleActivity
import endroad.dl.data.EnterpriseDataSource
import endroad.dl.models.Enterprise
import endroad.dl.view.item.EnterpriseItem
import ru.endroad.arena.viewlayer.fragment.ListFragment

class EnterprisesFragment : ListFragment() {

	private val enterpriseDataSource = EnterpriseDataSource()

	override val layout = R.layout.base_fragment_list

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {}

	override fun setupViewComponents() {
		setDivider(R.drawable.divider)
		enterpriseDataSource.getList()
			.map(::EnterpriseItem)
			.setItems()
	}

	override fun onClickItem(item: IModelItem<*, *>): Boolean {
		val enterprise = item.model as? Enterprise ?: return false

		//TODO костыль, но пусть так и останется в этом проекте :)
		val position = enterpriseDataSource.getList().indexOf(enterprise)
		(requireActivity() as SingleActivity).toEnterpriseInfo(position)

		return true
	}

	companion object {
		fun newInstance(): Fragment = EnterprisesFragment()
	}
}

