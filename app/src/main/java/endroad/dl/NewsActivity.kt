package endroad.dl

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.Query
import endroad.dl.ViewHolders.*
import endroad.dl.models.News
import kotlinx.android.synthetic.main.activity_list.*

class NewsActivity : ActivityExtendNavigation() {

	override val idChecked = R.id.nav_news

	val query: Query = databaseReference.child(News.NEWS_COMMON)

	private val firebaseAdapter = object : FirebaseRecyclerAdapter<News, NewsHolder>(News::class.java, R.layout.item_news, NewsHolder::class.java, query) {
		public override fun populateViewHolder(vh: NewsHolder, news: News, position: Int) {
			vh.bindData(news)
		}
	}

	private val linearLayoutManager = LinearLayoutManager(this).apply {
		reverseLayout = true
		stackFromEnd = true
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_list)

		recycleList.setHasFixedSize(true)
		recycleList.layoutManager = linearLayoutManager
		val mDividerItemDecoration = DividerItemDecoration(baseContext,
														   linearLayoutManager.orientation)
		recycleList.addItemDecoration(mDividerItemDecoration)

		recycleList.adapter = firebaseAdapter
	}

	override fun onClick(view: View) {}
}