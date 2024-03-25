package ai.kf.gmail.presentation.mailbox.subBox.Components

import androidx.compose.ui.graphics.Color

data class Mail(
    val image: Int?= null,
    val sender: String,
    val subject: String,
    val subtitle: String,
    val time: String,
    val tag: String?= null,
    val star: Boolean=false,
    val unread: Boolean=true,
    val draft: Boolean=false,
    val contents: List<String>?=null,
)

fun getDummyMail():Mail{
    return Mail(
        sender = "Google",
        subject = "Mat finish setting up your account",
        subtitle = "On Tue, 12 Oct 2021 at 15:13",
        time = "15:13",
    )
}

fun getRandomProfileBg():Color{
    val colors = listOf(
        Color(0xFFE57373),
        Color(0xFF81C784),
        Color(0xFF64B5F6),
        Color(0xFFFFD54F),
        Color(0xFF9575CD),
        Color(0xFF4DB6AC),
        Color(0xFFAED581),
        Color(0xFF4FC3F7),
        Color(0xFF7986CB),
        Color(0xFF4DD0E1),
        Color(0xFFFF8A65),
        Color(0xFFA1887F),
        Color(0xFF90A4AE),
        Color(0xFFAED581),
        Color(0xFF4FC3F7),
        Color(0xFF7986CB),
        Color(0xFF4DD0E1),
        Color(0xFFFF8A65),
        Color(0xFFA1887F),
        Color(0xFF90A4AE),
    )
    return colors.random()
}