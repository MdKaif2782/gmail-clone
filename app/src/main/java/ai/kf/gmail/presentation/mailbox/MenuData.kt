package ai.kf.gmail.presentation.mailbox

import ai.kf.gmail.R

data class MenuData(
    val title: String,
    var unread: Int?,
    var newUnread: Int?,
    val icon: Int? = null,
    var isDivider: Boolean = false,
    var isLabel: Boolean = false,
)

fun getMenuData(): List<MenuData> {
    val Devider1 = MenuData(title = "", unread = null, newUnread = null, icon = null, isDivider = true)
    val Primary = MenuData(title = "Primary", unread = 4, newUnread = 0, icon = R.drawable.ic_inbox)
    val Social = MenuData(title = "Social", unread = 102, newUnread = 2, icon = R.drawable.ic_social)
    val Promotions = MenuData(title = "Promotions", unread = 9, newUnread = 4, icon = R.drawable.ic_promotions)
    val AllLabel = MenuData(title = "All Labels", unread = 0, newUnread = 0, icon = null, isLabel = true)
    val Starred = MenuData(title = "Starred", unread = 0, newUnread = 0, icon = R.drawable.ic_star)
    val Snoozed = MenuData(title = "Snoozed", unread = 0, newUnread = 0, icon = R.drawable.ic_snozed)
    val Important = MenuData(title = "Important", unread = 0, newUnread = 0, icon = R.drawable.ic_important)
    val Sent = MenuData(title = "Sent", unread = 0, newUnread = 0, icon = R.drawable.ic_sent)
    val Scheduled = MenuData(title = "Scheduled", unread = 0, newUnread = 0, icon = R.drawable.ic_schedule)
    val Draft = MenuData(title = "Draft", unread = 0, newUnread = 0, icon = R.drawable.ic_drafts)
    val AllMail = MenuData(title = "All Mail", unread = 0, newUnread = 0, icon = R.drawable.ic_all_mails)
    val GoogleApps = MenuData(title = "Google Apps", unread = 0, newUnread = 0, icon = null, isLabel = true)
    val Spam = MenuData(title = "Spam", unread = 0, newUnread = 0, icon = R.drawable.ic_spam)
    val Bin = MenuData(title = "Trash", unread = 0, newUnread = 0, icon = R.drawable.ic_bin)
    val Devider2 = MenuData(title = "", unread = null, newUnread = null, icon = null, isDivider = true)
    val CreateNew = MenuData(title = "Create New", unread = 0, newUnread = 0, icon = R.drawable.ic_create)
    val Settings = MenuData(title = "Settings", unread = 0, newUnread = 0, icon = R.drawable.ic_settings)
    val data = listOf(Devider1, Primary, Social, Promotions, AllLabel, Starred, Snoozed, Important, Sent, Scheduled, Draft, AllMail, GoogleApps, Spam, Bin, Devider2, CreateNew, Settings)
    return data
}
