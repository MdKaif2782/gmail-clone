package ai.kf.gmail.presentation.mailbox.subBox

import ai.kf.gmail.R
import ai.kf.gmail.presentation.mailbox.subBox.Components.Mail
import ai.kf.gmail.presentation.mailbox.subBox.Components.MailComponent
import ai.kf.gmail.presentation.mailbox.subBox.Components.getDummyMail
import ai.kf.gmail.ui.theme.GmailTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.WeakHashMap

@Composable
fun PrimaryBox(innerPadding: PaddingValues? = null){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding ?: PaddingValues(0.dp)),
    ) {
        item {
            Text(
                text = "INBOX",
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF66656A)
                )
            )
            val mail = getDummyMail()
            val mail2 : Mail = Mail(
                image = R.drawable.aqua_2,
                sender = "Aqua sama",
                subject = "Kazuma Kazuma! Booze! Booze! Booze!",
                time = "10:00 AM",
                subtitle = "On Tue, 12 Oct 2021 at 15:13",
                )
            MailComponent(mail)
            MailComponent(mail2)
            MailComponent(mail)
            MailComponent(mail2)
            MailComponent(mail)
            MailComponent(mail2)
            MailComponent(mail)
            MailComponent(mail2)
            MailComponent(mail)
            MailComponent(mail2)
            MailComponent(mail)
            MailComponent(mail2)
        }
    }
}
@Preview (showBackground = true)
@Composable
fun PrimaryBoxPreview(){
    GmailTheme(preview = true) {
        PrimaryBox()
    }
}