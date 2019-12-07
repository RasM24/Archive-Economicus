package endroad.dl.view

import android.view.View
import androidx.fragment.app.Fragment
import endroad.dl.R
import kotlinx.android.synthetic.main.map_fragment.*
import ru.endroad.arena.viewlayer.fragment.BaseFragment

class MapFragment : BaseFragment() {

	override val layout = R.layout.map_fragment

	//Избыточность BaseFragment.. Уберется в будущем из модуля Arena
	override fun setupViewModel() {
		view_map.settings.setSupportZoom(true)
		view_map.settings.builtInZoomControls = true
		view_map.setPadding(0, 0, 0, 0)
		view_map.isScrollbarFadingEnabled = true
		view_map.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
		view_map.loadUrl(MAP_URL)
	}

	companion object {
		const val MAP_URL = "file:///android_asset/map.jpg"
		fun newInstance(): Fragment = MapFragment()
	}
}