package endroad.dl.view

import androidx.fragment.app.Fragment
import endroad.dl.R
import endroad.dl.data.FirebaseDataSource
import endroad.dl.view.item.ScheduleItem
import ru.endroad.arena.viewlayer.fragment.ListFragment

class ScheduleFragment : ListFragment() {

	private val firebaseDataSource = FirebaseDataSource()

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {}

	override fun setupViewComponents() {
		firebaseDataSource.getScheduleList {
			it.reversed()
				.map(::ScheduleItem)
				.setItems()
		}

		setDivider(R.drawable.divider)
	}

	companion object {
		fun newInstance(): Fragment = ScheduleFragment()
	}
}