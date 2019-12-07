package endroad.dl.data

import endroad.dl.models.Contact

class ContactDataSource {

	private val hardcoreData = listOf(
		Contact("Наталья Магурина", "президент", "https://vk.com/natasha_magurina", "file:///android_asset/link_image/natasha_magurina.jpg"),
		Contact("Анна Мазовская", "старший советник", "https://vk.com/mazovskaya", "file:///android_asset/link_image/mazovskaya.jpg"),
		Contact("Никита Мещеряков", "старший советник", "https://vk.com/nikeetqa", "file:///android_asset/link_image/nikeetqa.jpg"),
		Contact("Елена Смолина", "советник", "https://vk.com/teeeeeeeee", "file:///android_asset/link_image/teeeeeeeee.jpg"),
		Contact("Татьяна Дукельская", "советник", "https://vk.com/tanusha199", "file:///android_asset/link_image/tanusha199.jpg"))

	fun getContactList() = hardcoreData

}