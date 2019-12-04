package endroad.dl

import android.app.Activity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.dialog_factory_info.*

/**
 * Created by dvoly on 28.02.2017.
 */
class FactoryInfoActivity : Activity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.dialog_factory_info)

		val factory = factoryList[intent.getIntExtra("factoryId", -1)]

		text_name.text = factory.name
		text_information.text = Html.fromHtml(factory.information)
		image.setImageDrawable(loadImageFromAsset(factory.imagePath))
	}
}