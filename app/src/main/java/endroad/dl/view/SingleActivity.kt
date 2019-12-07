package endroad.dl.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import endroad.dl.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.activity_main_navigation.*
import ru.endroad.arena.viewlayer.activity.AppBarActivity
import ru.endroad.navigation.changeRoot

class SingleActivity : AppBarActivity(), NavigationView.OnNavigationItemSelectedListener {

	override val layout: Int = R.layout.activity_main

	override fun onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START))
			drawer.closeDrawer(GravityCompat.START)
		else
			super.onBackPressed()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setSupportActionBar(toolbar)
		nav_view_footer.setNavigationItemSelectedListener(this)
		nav_view_menu.setNavigationItemSelectedListener(this)
		nav_view_menu.setCheckedItem(R.id.nav_news)

		val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer.addDrawerListener(toggle)
		toggle.syncState()
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.nav_schedule   -> fragmentManager.changeRoot(ScheduleFragment.newInstance())
			R.id.nav_news       -> fragmentManager.changeRoot(NewsFragment.newInstance())
			R.id.nav_rating     -> fragmentManager.changeRoot(RatingFragment.newInstance())
			R.id.nav_enterprise -> fragmentManager.changeRoot(EnterprisesFragment.newInstance())
			R.id.nav_map        -> fragmentManager.changeRoot(MapFragment.newInstance())
			R.id.nav_contact    -> fragmentManager.changeRoot(ContactFragment.newInstance())
		}

		drawer.closeDrawer(GravityCompat.START)
		return true
	}

	override fun onFirstCreate() {
		fragmentManager.changeRoot(NewsFragment.newInstance())
	}

	fun onClickCommunity(view: View) {
		val address: Uri? = when (view.id) {
			R.id.nav_vk    -> Uri.parse(LINK_VK)
			R.id.nav_insta -> Uri.parse(LINK_INSTA)
			R.id.nav_fb    -> Uri.parse(LINK_FB)
			else           -> null
		}

		address
			?.run { Intent(Intent.ACTION_VIEW, this) }
			?.let(::startActivity)
	}

	private companion object {
		//вынести в datasource
		const val LINK_VK = "https://vk.com/umka_forever"
		const val LINK_INSTA = "https://www.instagram.com/umka_bk/"
		const val LINK_FB = "https://www.facebook.com/groups/umkaBK/permalink/411592642514212/"
	}
}