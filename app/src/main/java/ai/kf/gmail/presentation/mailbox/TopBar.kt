package ai.kf.gmail.presentation.mailbox

import ai.kf.gmail.R
import ai.kf.gmail.ui.theme.GmailTheme
import android.content.res.Configuration
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(drawerState: DrawerState?, scope: CoroutineScope?){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .clickable { },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE6E6F2)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        shape = RoundedCornerShape(100)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(modifier = Modifier.size(28.dp),onClick = {
                scope?.launch {drawerState?.open()}
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_menu_24), contentDescription ="menu",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(Modifier.size(20.dp))
            Text(text = "Search in mail",
                modifier = Modifier.weight(1.0f),
                color = MaterialTheme.colorScheme.tertiary
                )
            IconButton(modifier = Modifier.size(34.dp),onClick = {} ) {
                Image(painter = painterResource(id = R.drawable.aqua),
                    contentDescription ="aqua",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(34.dp)
                        // .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .clickable {},
                )
            }

        }
    }
}

@Preview (showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, showSystemUi = true
)
@Composable
fun TopBarPreview(){
    GmailTheme {
        TopBar(null, null)
    }

}