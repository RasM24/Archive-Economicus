package endroad.dl.application

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import endroad.dl.R
import endroad.dl.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.activity_main_navigation.*
import kotlinx.android.synthetic.main.navigation_community.*
import ru.endroad.arena.data.startUrl
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
		//nav_view_footer.setNavigationItemSelectedListener(this)
		navigation_menu.setNavigationItemSelectedListener(this)
		navigation_menu.setCheckedItem(R.id.navigation_news)

		val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer.addDrawerListener(toggle)
		toggle.syncState()
		setupViewComponent()
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.navigation_schedule   -> fragmentManager.changeRoot(ScheduleFragment.newInstance())
			R.id.navigation_news       -> fragmentManager.changeRoot(NewsFragment.newInstance())
			R.id.navigation_rating     -> fragmentManager.changeRoot(RatingFragment.newInstance())
			R.id.navigation_enterprise -> fragmentManager.changeRoot(EnterprisesFragment.newInstance())
			R.id.navigation_map        -> fragmentManager.changeRoot(MapFragment.newInstance())
			R.id.navigation_contact    -> fragmentManager.changeRoot(ContactFragment.newInstance())
		}

		drawer.closeDrawer(GravityCompat.START)
		return true
	}

	override fun onFirstCreate() {
		fragmentManager.changeRoot(NewsFragment.newInstance())
	}

	private fun setupViewComponent() {
		link_vkontakte.setOnClickListener { startUrl(LINK_VK) }
		link_instagram.setOnClickListener { startUrl(LINK_INSTA) }
		link_facebook.setOnClickListener { startUrl(LINK_FB) }
	}

	fun toEnterpriseInfo(position: Int) {
		EnterpriseInfoDialogFragment.newInstance(position).show(fragmentManager, null)
	}

	private companion object {
		//вынести в datasource
		const val LINK_VK = "https://vk.com/umka_forever"
		const val LINK_INSTA = "https://www.instagram.com/umka_bk/"
		const val LINK_FB = "https://www.facebook.com/groups/umkaBK/permalink/411592642514212/"
	}
}