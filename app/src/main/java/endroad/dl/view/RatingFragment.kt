package endroad.dl.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.Query
import endroad.dl.R
import endroad.dl.ViewHolders.*
import endroad.dl.databaseReference
import endroad.dl.models.News
import kotlinx.android.synthetic.main.activity_list.*
import ru.endroad.arena.viewlayer.fragment.BaseFragment

//TODO перевести на ListFragment
class RatingFragment : BaseFragment() {

	val query: Query = databaseReference.child(News.NEWS_RATING)

	private val firebaseAdapter = object : FirebaseRecyclerAdapter<News, NewsHolder>(News::class.java, R.layout.item_news, NewsHolder::class.java, query) {
		public override fun populateViewHolder(vh: NewsHolder, news: News, position: Int) {
			vh.bindData(news)
		}
	}

	override val layout = R.layout.base_fragment_list

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {

		val linearLayoutManager = LinearLayoutManager(requireContext()).apply {
			reverseLayout = true
			stackFromEnd = true
		}

		list.setHasFixedSize(true)
		list.layoutManager = linearLayoutManager
		list.addItemDecoration(DividerItemDecoration(requireContext(), linearLayoutManager.orientation))

		list.adapter = firebaseAdapter
	}

	companion object {
		fun newInstance(): Fragment = RatingFragment()
	}
}