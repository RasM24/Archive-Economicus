package endroad.dl

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.Query
import endroad.dl.ViewHolders.*
import endroad.dl.models.Schedule
import kotlinx.android.synthetic.main.activity_list.*

class ScheduleActivity : ActivityExtendNavigation() {

	override val idChecked = R.id.nav_schedule

	private val query: Query = databaseReference.child(Schedule.SCHEDULE_COMMON)

	private val firebaseAdapter = object :
		FirebaseRecyclerAdapter<Schedule, ScheduleHolder>(Schedule::class.java, R.layout.item_shedule, ScheduleHolder::class.java, query) {
		public override fun populateViewHolder(vh: ScheduleHolder, schedule: Schedule, position: Int) {
			vh.bindData(schedule)
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
		recycleList.addItemDecoration(DividerItemDecoration(baseContext, linearLayoutManager.orientation))

		recycleList.adapter = firebaseAdapter
	}

	override fun onClick(view: View) {}
}