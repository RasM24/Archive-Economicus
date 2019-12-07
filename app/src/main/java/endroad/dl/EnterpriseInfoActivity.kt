package endroad.dl

import android.app.Activity
import android.os.Bundle
import android.text.Html
import endroad.dl.data.EnterpriseDataSource
import kotlinx.android.synthetic.main.dialog_enterprise_info.*
import ru.endroad.arena.data.load

/**
 * Created by dvoly on 28.02.2017.
 */
class EnterpriseInfoActivity : Activity() {

	private val enterpriseDataSource = EnterpriseDataSource()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_enterprise_info)

		//TODO нет обработки ошибки NoSuchElementException
		val enterprise = enterpriseDataSource.getByPosition(intent.getIntExtra("enterpriseId", -1))

		text_name.text = enterprise.name
		text_information.text = Html.fromHtml(enterprise.information)
		image.load(enterprise.imagePath)
	}
}