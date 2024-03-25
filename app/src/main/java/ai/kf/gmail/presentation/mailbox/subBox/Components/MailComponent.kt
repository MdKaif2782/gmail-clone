
package ai.kf.gmail.presentation.mailbox.subBox.Components

import ai.kf.gmail.R
import android.content.res.Configuration
import androidx.compose.foundation.Image

import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun MailComponent(mail: Mail){
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        val (image,contents,time,star,attachment)= createRefs()

        val imageModifier = Modifier.constrainAs(image){
            start.linkTo(parent.start,margin =4.dp)
            top.linkTo(parent.top)
        }
        val contentModifier = Modifier.constrainAs(contents){
            start.linkTo(image.end)
            top.linkTo(parent.top,margin = 8.dp)
        }
        val timeModifier = Modifier.constrainAs(time){
            end.linkTo(parent.end,margin = 14.dp)
            top.linkTo(parent.top,margin = 8.dp)
        }
        val iconModifier = Modifier.constrainAs(star){
            end.linkTo(parent.end,margin = 8.dp)
            bottom.linkTo(contents.bottom)
        }
        val attachmentModifier = Modifier.constrainAs(attachment){
            start.linkTo(image.end)
            top.linkTo(contents.bottom,margin = 8.dp)
        }

        if (mail.image!=null) ProfileImage(imageModifier) else ProfileText(mail,imageModifier)
        MailContents(mail = mail, modifier = contentModifier)
        Time(mail = mail,modifier = timeModifier)
        Star(mail = mail,modifier = iconModifier)
        Row(modifier = Modifier
            .then(attachmentModifier)
            .border(1.dp, Color(0xff9f9f9f), shape = RoundedCornerShape(100)),
            verticalAlignment = Alignment.CenterVertically
            ) {
           Spacer(modifier =Modifier.size(4.dp))
           Icon(
              painter = painterResource(id = R.drawable.ic_image),
              contentDescription = "attachment",
              tint = Color.Red,
               modifier = Modifier
                   .padding(4.dp)
                   .size(20.dp)
           )
           Text(
               text = "Penis.png",
               style = TextStyle(
                   color = Color(0xFF66656A),
                   fontSize = 14.sp
               ),
               modifier = Modifier.padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
           )
         }

    }
}


@Composable
fun ProfileImage(modifier: Modifier){
    Image(painter = painterResource(id = R.drawable.aqua_2),
        contentDescription = "Aqua",
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp)
            .size(51.dp)
            .clip(shape = CircleShape)
            .then(modifier) // Combine the modifiers here
    )
}

@Composable
fun ProfileText(mail: Mail,modifier: Modifier){
    val firstLetter:String = mail.sender.substring(0,1)
    Text(text = firstLetter,
        style = TextStyle(
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium
        ),
        modifier = Modifier
            .padding(top = 16.dp, start = 26.dp, end = 18.dp)
            .drawBehind {
                drawCircle(
                    color = getRandomProfileBg(),
                    radius = 25.dp.toPx(),
                    center = center
                )
            }
            .then(modifier) // Combine the modifiers here
    )
}

@Composable
fun MailContents(mail: Mail,modifier: Modifier){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
            .then(modifier) // Combine the modifiers here
    ){
        Text(text = mail.sender,
            style = TextStyle(
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                //sans-serif default font

            )
        )
        Text(text = mail.subject,
            style = TextStyle(
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,

                ),
            modifier = Modifier.padding(top = 0.dp)
        )
        Text(text = mail.subtitle,
            style = TextStyle(
                color = Color(0xFF66656A),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal)
        )
    }
}

@Composable
fun Time(mail: Mail,modifier:Modifier){
    Text(
        text = mail.time,
        style = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        modifier = modifier
    )
}

@Composable
fun Star(mail: Mail,modifier: Modifier){
    Icon(
        painter = painterResource(id = if (!mail.star) R.drawable.ic_star else R.drawable.baseline_star_filled_24),
        contentDescription = "star",
        tint = if (!mail.star)Color(0xFF66656A) else  Color(0xFF2261C3),
        modifier = modifier
    )}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, showSystemUi = true
)
@Composable
fun PreviewMailComponent(){
    MailComponent(getDummyMail())
}