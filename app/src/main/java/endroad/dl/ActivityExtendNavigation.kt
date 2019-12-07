package endroad.dl

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.navigation_drawer.*

abstract class ActivityExtendNavigation : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
										  View.OnClickListener {

	open val idChecked = 0

	override fun onPostResume() {
		super.onPostResume()
		setSupportActionBar(toolbar)
		val toggle = ActionBarDrawerToggle(
			this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer_layout.setDrawerListener(toggle)
		toggle.syncState()
		nav_view_footer.setNavigationItemSelectedListener(this)
		nav_view_menu.setNavigationItemSelectedListener(this)
		nav_view_menu.setCheckedItem(idChecked)
	}

	override fun onBackPressed() {
		if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
			drawer_layout.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
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

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		val activity = when (item.itemId) {
			R.id.nav_factory  -> FactoryActivity::class.java
			R.id.nav_contact  -> ContactActivity::class.java
			R.id.nav_map      -> MapActivity::class.java
			R.id.nav_news     -> NewsActivity::class.java
			R.id.nav_rating   -> RatingActivity::class.java
			R.id.nav_schedule -> ScheduleActivity::class.java
			else              -> null
		}

		activity
			?.run { Intent(baseContext, this) }
			?.let {
				startActivity(it)
				overridePendingTransition(R.anim.activity, R.anim.alpha)
			}

		drawer_layout.closeDrawer(GravityCompat.START)
		return true
	}

	private companion object {
		const val LINK_VK = "https://vk.com/umka_forever"
		const val LINK_INSTA = "https://www.instagram.com/umka_bk/"
		const val LINK_FB = "https://www.facebook.com/groups/umkaBK/permalink/411592642514212/"
	}
}