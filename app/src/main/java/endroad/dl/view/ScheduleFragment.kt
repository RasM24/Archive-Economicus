package endroad.dl.view

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.Query
import endroad.dl.R
import endroad.dl.ViewHolders.*
import endroad.dl.databaseReference
import endroad.dl.models.Schedule
import kotlinx.android.synthetic.main.activity_list.*
import ru.endroad.arena.viewlayer.fragment.BaseFragment

//TODO перевести на ListFragment
class ScheduleFragment : BaseFragment() {

	private val query: Query = databaseReference.child(Schedule.SCHEDULE_COMMON)

	private val firebaseAdapter = object :
		FirebaseRecyclerAdapter<Schedule, ScheduleHolder>(Schedule::class.java, R.layout.item_shedule, ScheduleHolder::class.java, query) {
		public override fun populateViewHolder(vh: ScheduleHolder, schedule: Schedule, position: Int) {
			vh.bindData(schedule)
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
		fun newInstance(): Fragment = ScheduleFragment()
	}
}