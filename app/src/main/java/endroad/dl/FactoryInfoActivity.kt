package endroad.dl

import android.app.Activity
import android.os.Bundle
import android.text.Html
import endroad.dl.data.EnterpriseDataSource
import kotlinx.android.synthetic.main.dialog_factory_info.*
import ru.endroad.arena.data.load

/**
 * Created by dvoly on 28.02.2017.
 */
class FactoryInfoActivity : Activity() {

	private val enterpriseDataSource = EnterpriseDataSource()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_factory_info)

		//TODO нет обработки ошибки NoSuchElementException
		val factory = enterpriseDataSource.getByPosition(intent.getIntExtra("factoryId", -1))

		text_name.text = factory.name
		text_information.text = Html.fromHtml(factory.information)
		image.load(factory.imagePath)
	}
}