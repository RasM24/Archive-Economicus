package endroad.dl.view

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import com.mikepenz.fastadapter.IModelItem
import endroad.dl.R
import endroad.dl.data.ContactDataSource
import endroad.dl.models.Contact
import endroad.dl.view.item.ContactItem
import ru.endroad.arena.viewlayer.fragment.ListFragment

class ContactFragment : ListFragment() {

	override val layout = R.layout.base_fragment_list

	private val contactDataSource = ContactDataSource()

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {}

	override fun setupViewComponents() {
		setDivider(R.drawable.divider)
		contactDataSource.getContactList()
			.map(::ContactItem)
			.setItems()
	}

	override fun onClickItem(item: IModelItem<*, *>): Boolean {
		val model = item.model as? Contact ?: return false
		val browser = Intent(Intent.ACTION_VIEW, Uri.parse(model.url))
		startActivity(browser)

		return true
	}

	companion object {
		fun newInstance(): Fragment = ContactFragment()
	}
}

