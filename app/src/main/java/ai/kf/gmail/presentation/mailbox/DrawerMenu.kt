package ai.kf.gmail.presentation.mailbox

import ai.kf.gmail.R
import ai.kf.gmail.ui.theme.GmailTheme
import ai.kf.gmail.ui.theme.LightBlue
import ai.kf.gmail.ui.theme.LightGreen
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

val TAG:String = "DrawerMenu"
val items = getMenuData()
@Composable
fun DrawerMenu(
    onItemClick:(Int)->Unit, scope: CoroutineScope?,
    drawerState: DrawerState?
){
    // icons to mimic drawer destinations
    val selectedItem = remember{ mutableIntStateOf(1) }


    Log.d(TAG, "DrawerMenu: selectedItem: ${selectedItem.intValue}..")

    LazyColumn(Modifier.wrapContentWidth()) {
        item {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(0),
                drawerContainerColor = Color.White,
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Spacer(Modifier.height(12.dp))
                // Gmail Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp, start = 24.dp, top = 5.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_gmail),
                        contentDescription = "gmail")
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = "Gmail",
                        style = MaterialTheme.typography.titleMedium, fontSize = 20.sp,
                        color = Color(0xFF66656A)
                    )
                }
                items.forEachIndexed { index,item ->
                  //  Log.d(TAG, "DrawerMenu: iterating for item ${item.title}.. index: $index")
                    if (!item.isLabel && !item.isDivider){
                      //  Log.d(TAG, "condition met for ${item.title}.. index: $index")
                        DrawerItem(
                            index = index,
                            item = item,
                            drawerState = drawerState ,
                            onItemClick = { onItemClick(index)},
                            scope = scope,
                            selectedItem = selectedItem
                        )
                    }else if (item.isLabel){
                        Label(item = item)
                    }else{
                        Divider(item = item)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, showSystemUi = true
)
@Composable
fun PreviewDrawerMenu(){
  GmailTheme {
      DrawerMenu(onItemClick = {},null,null)
  }
}

@Composable
fun DrawerItem(index:Int, item:MenuData, drawerState: DrawerState?, onItemClick:(Int)->Unit, scope: CoroutineScope?,selectedItem: MutableState<Int>){
   // Log.d(TAG, "DrawerItem: SelectedIndex: $index.. selectedItem: ${selectedItem.value.title}..")

    NavigationDrawerItem(
        shape = RoundedCornerShape(topEnd = 100f, bottomEnd = 100f),
        icon = {
            Icon(painter = painterResource(id = item.icon!!), contentDescription = null, modifier = Modifier.padding(start = 8.dp))
        },
        label = { Text(item.title) },
        selected = item==items[selectedItem.value],
        onClick = {
            selectedItem.value = index
            onItemClick(index)
            // close drawer
            scope!!.launch {
                //delay 300ms
                delay(30)
                drawerState!!.close()
            }
        },
        badge = {
               Badge(item = item,index=index)
        },
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor =MaterialTheme.colorScheme.surfaceVariant,
            unselectedContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .padding(top = NavigationDrawerItemDefaults.ItemPadding.calculateTopPadding(), bottom = NavigationDrawerItemDefaults.ItemPadding.calculateBottomPadding(), end = 10.dp)
    )
}

@Composable
fun Label(item: MenuData){
    Text(text = item.title,
        modifier = Modifier.padding(start = 28.dp, top = 10.dp, bottom = 5.dp)
        , fontSize = 14.sp, color = Color.Gray, fontWeight = MaterialTheme.typography.labelSmall.fontWeight
    )
}

@Composable
fun Divider(item: MenuData){
    Column(modifier = Modifier.padding(bottom = 10.dp)){
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray)
        )
    }
}

@Composable
fun Badge(item: MenuData,index: Int){
    if (item.unread!=0 && item.newUnread==0){
        if (item.unread!! >99){
            Text(text = "99+", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }else{
            Text(text = "${item.unread}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        }
    }else if (item.newUnread!=0){
        val bgColor = if (index%2==0) LightGreen else LightBlue

        Text(text = "${item.newUnread} new",
            modifier = Modifier
                .background(
                    color = bgColor, shape = RoundedCornerShape(50)
                )
                .padding(horizontal = 10.dp, vertical = 3.dp),
            fontSize = 13.sp,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}