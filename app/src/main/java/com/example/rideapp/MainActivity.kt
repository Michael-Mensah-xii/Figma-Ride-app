package com.example.rideapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rideapp.ui.theme.Buttonc
import com.example.rideapp.ui.theme.Green200
import com.example.rideapp.ui.theme.Green500
import com.example.rideapp.ui.theme.Grey100
import com.example.rideapp.ui.theme.RideAppTheme
import com.example.rideapp.ui.theme.SatoshiTypography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(1000)
        installSplashScreen()
        setContent {
            RideApp()
        }


    }
}

// Step: Extrassection
@Composable
fun ExtrasSection(
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .heightIn(119.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.Extra), style = TextStyle(fontSize = 15.sp),
                // add some padding to the top of title and underneath title
                modifier = Modifier.padding(vertical = 16.dp) //add some space between title and column

            )
            content()
        }
    }
}

//item select row
@Composable
fun ExtrasRow(
    modifier: Modifier = Modifier,
) {
    LazyRow(horizontalArrangement = Arrangement.SpaceBetween, // spaces all items in lazyRow
        contentPadding = PaddingValues(horizontal = 0.dp), // this parameter ensures that padding is maintained while scrolling without clipping
        modifier = modifier.fillMaxWidth(), content = {
            items(extrasData) { item ->
                ExtrasElement(
                    drawable = item.drawable, text = item.text, price = item.price

                )
            }
        })
}

//Extras
@Composable
fun ExtrasElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    price: String,
) {
    var isChecked by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .width(50.dp)
                    .padding(top = 1.dp, end = 2.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    modifier = Modifier
                        .size(16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(end = 0.dp),
                    colors = CheckboxDefaults.colors(Green500)
                )
            }

        }

        Text(
            text = stringResource(id = text),
            style = TextStyle(fontSize = 10.sp, lineHeight = 14.sp),
            modifier = Modifier.paddingFromBaseline(top = 12.dp, bottom = 2.dp)
        )

        Text(
            text = price,
            style = TextStyle(fontSize = 10.sp, lineHeight = 14.sp),
            color = Green500,
            modifier = Modifier.paddingFromBaseline(top = 2.dp, bottom = 12.dp)
        )
    }
}


data class ExtrasData(
    val drawable: Int,
    val text: Int,
    val price: String,
)

val extrasData = listOf(
    ExtrasData(R.drawable.breakfast, R.string.Breakfast, "GHS 50.00"),
    ExtrasData(R.drawable.sleeping, R.string.Sleeping, "GHS 100.00"),
    ExtrasData(R.drawable.drinks, R.string.Drinks, "GHS 40.00"),
    ExtrasData(R.drawable.blanket, R.string.Blanket, "GHS 92.00"),
)


// Extended buttons
@Composable
fun ExtendedButton() {

    Box(
        modifier = Modifier
            .height(87.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .alpha(1f)
            .paddingFromBaseline(top = 286.dp),
        contentAlignment = Alignment.CenterStart,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = stringResource(id = R.string.Extendedbt),
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .paddingFromBaseline(top = 0.dp, bottom = 20.dp)
                    .padding(horizontal = 0.dp)
            )

            val dateList = listOf("Today", "Tomorrow", "22nd", "Other")
            var selected by remember { mutableStateOf(-1) }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                contentPadding = PaddingValues(bottom = 0.dp), // this parameter ensures that padding is maintained while scrolling without clipping
                modifier = Modifier.fillMaxWidth()
                // .clickable(onClick = { selected = !selected }, enabled = true),
            ) {
                items(dateList.size) { item ->
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            color = if (item == selected) Green500 else Color.Transparent,
                            width = 0.dp,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .clickable { selected = item }

                    ) {
                        if (item == 3) Icon(
                            painter = painterResource(id = R.drawable.calender),
                            contentDescription = "calender Icon",
                            Modifier
                                .padding(start = 4.dp)
                                .size(14.dp)
                                .align(Alignment.CenterStart),

                            )
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
                                )
                                .padding(horizontal = 2.dp),
                            fontSize = 12.sp,
                            text = dateList[item],
                            color = if (item == selected) Green500 else Color.Black,

                            )
                    }
                }

            }

        }
    }
}


//Odd - + button
@Composable
fun OddButton() {

    var count by rememberSaveable { mutableStateOf(0) }
    val result = count

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()

    ) {
        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .heightIn(80.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()

        ) {
            val myId = "inlineContent"
            val text = buildAnnotatedString {
                // Append a placeholder string "[icon]" and attach an annotation "inlineContent" on it.
                appendInlineContent(myId, "[icon]")
                if (result == 1) append("$result person") else append("$result people")
            }

            val inlineContent = mapOf(
                Pair(myId, InlineTextContent(
                    Placeholder(
                        width = 26.sp,
                        height = 20.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center,

                        )
                ) {
                    Icon(painter = painterResource(id = R.drawable.users), "")
                })
            )

            BasicText(
                inlineContent = inlineContent,
                text = text,
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(300)),
            )


            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .height(32.dp)
                    .widthIn(104.dp)
                    .background(Grey100)
                    .padding(4.dp),
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Icon(
                        modifier = Modifier
                            .height(20.dp)
                            .widthIn(20.dp)
                            .padding(start = 8.dp)
                            .clickable { if (count > 0) count-- },
                        painter = painterResource(id = R.drawable.minimize),
                        contentDescription = stringResource(R.string.text_add_icon),
                        tint = Color.Black
                    )

                    Divider(
                        color = Color.Gray, modifier = Modifier
                            .height(16.dp) //fill the max height
                            .width(1.dp)
                    )


                    Icon(
                        modifier = Modifier
                            .height(20.dp)
                            .widthIn(14.dp)
                            .padding(end = 8.dp)
                            .clickable { count++ },
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.text_add_icon),
                        tint = Color.Black
                    )
                }


            }

        }
    }


}

//Button
@Composable
fun Button(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 40.dp, bottom = 47.dp)
            .padding(horizontal = 16.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(49.dp),

            colors = (ButtonDefaults.buttonColors(backgroundColor = Buttonc)),
        ) {
            Text(
                stringResource(R.string.placeholder_button),
                color = Color.White,
                fontSize = 14.sp,
                style = SatoshiTypography.body2,

                )
        }
    }
}


//App Header
@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Green200),

        ) {
        var locationText by rememberSaveable { mutableStateOf("") }
        var terminalText by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier
                .heightIn(119.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .paddingFromBaseline(top = 62.dp, bottom = 28.dp)

            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 62.dp)
                        .weight(1f),
                    fontSize = 24.sp,
                    text = "Hi Kojo,",
                    style = SatoshiTypography.h1,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.padding(horizontal = 40.dp))


            }

            TextField(
                colors = TextFieldDefaults.textFieldColors(Color.White),
                value = locationText,
                onValueChange = { locationText = it },
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_firsttextbox),
                        color = Color.White.copy(alpha = 0.21f),
                        style = SatoshiTypography.body2,

                        )
                },
                modifier = Modifier

                    .padding(top = 14.dp, bottom = 14.dp, end = 16.dp)
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .clip(
                        RoundedCornerShape(4.dp)
                    )
                    .background(
                        Color(
                            red = 0.21131945f, green = 0.35534722f, blue = 0.31744516f, alpha = 1f
                        )
                    )


            )

            TextField(
                colors = TextFieldDefaults.textFieldColors(Color.White),
                value = terminalText,
                onValueChange = { terminalText = it },
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_secondtextbox),
                        color = Color.White.copy(alpha = 0.21f),
                        style = SatoshiTypography.body2,
                    )
                },
                modifier = Modifier
                    .padding(bottom = 14.dp, end = 16.dp)
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .padding(bottom = 32.dp)
                    .clip(
                        RoundedCornerShape(4.dp)
                    )
                    .background(
                        Color(
                            red = 0.21131945f, green = 0.35534722f, blue = 0.31744516f, alpha = 1f
                        )
                    )

            )

        }
        //Profile picture
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "profile image",
                modifier = Modifier
                    .padding(top = 58.dp)
                    .padding(horizontal = 16.dp)
                    //set image size to 40dp
                    .size(40.dp)
                    //shape image as circle
                    .clip(CircleShape)

            )
        }


        //Floating action button
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                onClick = {},
                elevation = FloatingActionButtonDefaults.elevation(20.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(top = 168.dp, bottom = 70.dp)
                    .padding(horizontal = 16.dp)
                    .size(40.dp, 40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.up_down_arrow),
                    contentDescription = "bidirectional button"
                )
            }
        }/* */
    }

}

//Bottom navigation
@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Tickets,
        NavigationItem.Referral,
        NavigationItem.Settings,
    )

    var selected by remember { mutableStateOf(-1) }

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.Black,
        modifier = Modifier.heightIn(100.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                modifier = Modifier.padding(bottom = 32.dp, top = 14.5.dp),
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = if (selected == index) Green500 else Color.Transparent,
                unselectedContentColor = Color.Black.copy(0.5f),
                alwaysShowLabel = true,
                selected = (selected == index),
                onClick = {
                    selected = index
                },
            )
        }
    }
}


// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Merge all the views and their components together
    // provide some horizontal padding in layout
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AppHeader()
        Spacer(Modifier.height(16.dp))
        ExtendedButton()
        Divider(thickness = 0.5.dp, color = Color.LightGray)
        OddButton()
        Divider(thickness = 0.5.dp, color = Color.LightGray)
        ExtrasSection {
            ExtrasRow()
        }
        Button()

    }
}

@Composable
fun RideApp() {
    RideAppTheme {
        Scaffold(bottomBar = { BottomNavigationBar() }) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}


//OddButton preview
@Preview
@Composable
fun OddButtonPreview() {
    RideAppTheme {
        OddButton()
    }
}

//Text Field Preview
@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    RideAppTheme {
        Button()
    }
}

//Text Field Preview
@Preview(showBackground = true)
@Composable
fun ExtendedButtonPreview() {
    RideAppTheme {
        ExtendedButton()
    }
}

//home section preview
@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
    RideAppTheme {
        ExtrasSection {
            ExtrasRow()

        }

    }
}

//Extras Row preview
@Preview(showBackground = true)
@Composable
fun ExtrasRowPreview() {
    RideAppTheme { ExtrasRow() }
}

// Extras element preview
@Preview(showBackground = true)
@Composable
fun ExtrasElementPreview() {
    val item = ExtrasData(R.drawable.blanket, R.string.Blanket, "GHS 92.00")
    RideAppTheme {
        ExtrasElement(
            drawable = R.drawable.blanket,
            text = R.string.Blanket,
            price = item.price,

            )
    }
}


//App Header Preview
@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    RideAppTheme { AppHeader() }
}

//Bottom nav preview
@Preview
@Composable
fun BottomNavigationPreview() {
    RideAppTheme { BottomNavigationBar() }
}

@Preview(widthDp = 365, heightDp = 640)
@Composable
fun RideAppPreview() {
    RideApp()
}



