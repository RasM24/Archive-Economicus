package endroad.dl.data

import endroad.dl.R
import endroad.dl.models.Contact

class ContactDataSource {

	//TODO по хорошему вынести бы все данные в ресурсы, но пусть останется здесь :)
	private val hardcoreData = listOf(
		Contact("Наталья Магурина", "президент", "https://vk.com/natasha_magurina", R.drawable.link_natasha_magurina),
		Contact("Анна Мазовская", "старший советник", "https://vk.com/mazovskaya", R.drawable.link_mazovskaya),
		Contact("Никита Мещеряков", "старший советник", "https://vk.com/nikeetqa", R.drawable.link_nikeetqa),
		Contact("Елена Смолина", "советник", "https://vk.com/teeeeeeeee", R.drawable.link_teeeeeeeee),
		Contact("Татьяна Дукельская", "советник", "https://vk.com/tanusha199", R.drawable.link_tanusha199))

	fun getContactList() = hardcoreData
}