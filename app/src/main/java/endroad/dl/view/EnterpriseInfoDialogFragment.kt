package endroad.dl.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import endroad.dl.R
import endroad.dl.data.EnterpriseDataSource
import kotlinx.android.synthetic.main.dialog_enterprise_info.*
import ru.endroad.arena.data.load
import ru.endroad.arena.viewlayer.extension.argument
import ru.endroad.arena.viewlayer.extension.withArgument

/**
 * Created by dvoly on 28.02.2017.
 */
class EnterpriseInfoDialogFragment : DialogFragment() {

	private val enterpriseDataSource = EnterpriseDataSource()

	private val enterpriseId: Int by argument(ENTERPRISE_ID)

	val layout: Int = R.layout.dialog_enterprise_info

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
		inflater.inflate(layout, container, false)!!

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		val enterprise = enterpriseDataSource.getByPosition(enterpriseId)

		title.text = enterprise.name
		description.text = Html.fromHtml(enterprise.information)
		image.load(enterprise.imagePath)
	}

	companion object {
		private const val ENTERPRISE_ID = "enterpriseId"

		fun newInstance(position: Int): DialogFragment =
			EnterpriseInfoDialogFragment().withArgument {
				putInt(ENTERPRISE_ID, position)
			}
	}
}