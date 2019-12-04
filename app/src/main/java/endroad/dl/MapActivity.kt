package endroad.dl

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : ActivityExtendNavigation() {

	override val idChecked = R.id.nav_map

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_map)

		view_map.settings.setSupportZoom(true)
		view_map.settings.builtInZoomControls = true
		view_map.setPadding(0, 0, 0, 0)
		view_map.isScrollbarFadingEnabled = true
		view_map.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
		view_map.loadUrl(MAP_URL)
	}

	override fun onClick(view: View) {}

	companion object {
		const val MAP_URL = "file:///android_asset/map.jpg"
	}
}