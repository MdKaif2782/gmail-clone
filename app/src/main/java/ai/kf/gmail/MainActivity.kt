package ai.kf.gmail

import ai.kf.gmail.presentation.mailbox.DrawerMenu
import ai.kf.gmail.presentation.mailbox.TopBar
import ai.kf.gmail.presentation.mailbox.subBox.PrimaryBox
import ai.kf.gmail.presentation.mailbox.subBox.PromotionalBox
import ai.kf.gmail.presentation.mailbox.subBox.SocialBox
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ai.kf.gmail.ui.theme.GmailTheme
import android.util.Log
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


val TAG = "MainActivity"
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GmailTheme {
                //States
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val pageState = remember { mutableIntStateOf(0) }
                //UI
                //Navigation Drawer
                ModalNavigationDrawer(
                    drawerContent = {
                      DrawerMenu(onItemClick ={
                          pageState.intValue = it
                      }, scope = scope, drawerState = drawerState)
                }, drawerState = drawerState) {
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = { TopBar(drawerState, scope) },
                    ) { innerPadding ->
                        Log.d(TAG, "onCreate: $innerPadding")
                        //Pages
                        when(pageState.intValue){
                            0-> PrimaryBox(innerPadding)
                            1-> PrimaryBox(innerPadding)
                            2-> SocialBox()
                            3-> PromotionalBox()
                            else -> PrimaryBox(innerPadding)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GmailTheme(preview = true) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(null, null) }) { innerPadding ->
            Log.d(TAG, "onCreate: $innerPadding")
        }
    }
}