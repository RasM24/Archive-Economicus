package endroad.dl.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import endroad.dl.databaseReference
import endroad.dl.models.News
import endroad.dl.models.Schedule

class FirebaseDataSource {

	fun getNewsList(callback: (List<News>) -> Unit) {
		getListData(NEWS_COMMON, callback)
	}

	fun getRatingList(callback: (List<News>) -> Unit) {
		getListData(NEWS_RATING, callback)
	}

	fun getScheduleList(callback: (List<Schedule>) -> Unit) {
		getListData(SCHEDULE_COMMON, callback)
	}

	private inline fun <reified T : Any> getListData(source: String, crossinline callback: (List<T>) -> Unit) {
		databaseReference.child(source).addListenerForSingleValueEvent(
			object : ValueEventListener {
				override fun onDataChange(dataSnapshot: DataSnapshot) {
					val values = dataSnapshot
						.children
						.mapNotNull { it.getValue(T::class.java) }

					callback(values)
				}

				override fun onCancelled(databaseError: DatabaseError) {}
			}
		)
	}

	companion object {
		const val NEWS_COMMON = "news-common"
		const val NEWS_RATING = "news-rating"
		const val SCHEDULE_COMMON = "schedule-common"
	}
}