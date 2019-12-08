package endroad.dl.view

import androidx.fragment.app.Fragment
import endroad.dl.R
import endroad.dl.data.FirebaseDataSource
import endroad.dl.view.item.NewsItem
import ru.endroad.arena.viewlayer.fragment.ListFragment

class NewsFragment : ListFragment() {

	private val firebaseDataSource = FirebaseDataSource()

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {}

	override fun setupViewComponents() {
		firebaseDataSource.getNewsList {
			it.reversed()
				.map(::NewsItem)
				.setItems()
		}

		setDivider(R.drawable.divider)
	}

	companion object {
		fun newInstance(): Fragment = NewsFragment()
	}
}