package endroad.dl.view

import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import endroad.dl.R
import endroad.dl.databaseReference
import endroad.dl.models.News
import endroad.dl.view.item.NewsItem
import ru.endroad.arena.viewlayer.fragment.ListFragment

class RatingFragment : ListFragment() {

	private val postListener = object : ValueEventListener {
		override fun onDataChange(dataSnapshot: DataSnapshot) {
			dataSnapshot
				.children
				.reversed()
				.mapNotNull { it.getValue(News::class.java) }
				.map(::NewsItem)
				.setItems()
		}

		override fun onCancelled(databaseError: DatabaseError) {}
	}

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {}

	override fun setupViewComponents() {
		databaseReference.child(News.NEWS_RATING).addListenerForSingleValueEvent(postListener)

		setDivider(R.drawable.divider)
	}

	companion object {
		fun newInstance(): Fragment = NewsFragment()
	}
}