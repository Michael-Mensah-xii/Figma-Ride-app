package com.example.rideapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rideapp.ui.theme.*

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
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(start = 27.dp, end = 34.dp)
    ) {
        Column(modifier) {
            Text(
                text = stringResource(id = R.string.Extra),
                style = TextStyle(fontSize = 15.sp),
                // add some padding to the top of title and underneath title
                modifier = Modifier
                    .padding(vertical = 16.dp) //add some space between title and column

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
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(34.dp), // spaces all items in lazyRow
        contentPadding = PaddingValues(horizontal = 8.dp),// this parameter ensures that padding is maintained while scrolling without clipping
        modifier = modifier,
        content = {
            items(extrasData) { item ->
                ExtrasElement(
                    drawable = item.drawable,
                    text = item.text
                )
            }
        }
    )
}

//Extras
@Composable
fun ExtrasElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
) {
    Column(
        modifier = Modifier,
        // sets the alignment of the inversions text horizontally
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,

            contentScale = ContentScale.Crop, //crops image composable
            modifier = Modifier
                .heightIn(34.dp)
                .widthIn(34.dp)
                //sets the image composable background (faded look)
                .paint(
                    painter = painterResource(R.drawable.rectangle_4),
                    contentScale = ContentScale.FillWidth
                )
                .padding(8.dp)
                .size(34.dp),

            )

        Text(
            text = stringResource(id = text),
            style = TextStyle(fontSize = 10.sp, lineHeight = 14.sp),
            modifier = Modifier.paddingFromBaseline(
                top = 12.dp, bottom = 2.dp
            )
        )
    }

}


//data for extras row
private val extrasData = listOf(
    R.drawable.breakfast to R.string.Breakfast,
    R.drawable.sleeping to R.string.Sleeping,
    R.drawable.drinks to R.string.Drinks,
    R.drawable.blanket to R.string.Blanket
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
)


// Extended buttons
@Composable
fun ExtendedButton() {

    Box(
        modifier = Modifier
            .height(87.dp)
            .widthIn(322.dp)
            .background(Color.Transparent)
            .alpha(1f)
            .paddingFromBaseline(top = 286.dp)
            .padding(start = 27.dp, end = 26.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            ) {
            Text(
                text = stringResource(id = R.string.Extendedbt),
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .paddingFromBaseline(top = 0.dp, bottom = 20.dp)
                    .padding(horizontal = 0.dp)
            )

            val dateList = listOf("Today", "Tomorrow", "22nd", "Other")
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 6.dp),
                modifier = Modifier
                    .padding(end = 26.dp)
            ) {
                items(dateList.size) { item ->
                    Box(
                        modifier = Modifier
                            .border(
                                color = Green500,
                                width = 0.dp,
                                shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    end = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ),
                            fontSize = 12.sp,
                            text = dateList[item],
                            color = if (item == 0) Green500 else Color.Black,

                            )
                    }
                }

            }

        }
    }
}

/*private val dateList = listOf(
    R.string.Today,
    R.string.Tomorrow,
    R.string.twentysecond,
    R.string.Other)*/

//Odd - + button
@Composable
fun OddButton() {

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(start = 27.dp, end = 35.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(134.dp),
            modifier = Modifier
                .widthIn(90.dp)
                .heightIn(80.dp)

        ) {
            val myId = "inlineContent"
            val text = buildAnnotatedString {
                append("1 person")
                // Append a placeholder string "[icon]" and attach an annotation "inlineContent" on it.
                appendInlineContent(myId, "[icon]")
            }

            val inlineContent = mapOf(
                Pair(
                    myId,
                    InlineTextContent(
                        Placeholder(
                            width = 20.sp,
                            height = 20.sp,
                            placeholderVerticalAlign = PlaceholderVerticalAlign.Center,

                            )
                    ) {


                        Icon(Icons.Filled.Person, "")
                    }
                )
            )

            BasicText(
                inlineContent = inlineContent,
                text = text,
            )

            Image(
                painter = painterResource(id = R.drawable.light),
                contentDescription = "plus and minus toggle",
                modifier = Modifier
                    .widthIn(104.dp)
                    .heightIn(32.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }


}

//Button
@Composable
fun Button(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 31.dp, top = 40.dp, end = 31.dp, bottom = 47.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier
                .widthIn(313.dp)
                .heightIn(49.dp),

            colors = (ButtonDefaults.buttonColors(backgroundColor = Buttonc)),
        ) {
            Text(
                stringResource(R.string.placeholder_button),
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}


//App Header
@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .widthIn(375.dp)
            .background(color = Green200),

        ) {
        val textState = rememberSaveable { mutableStateOf("") }
        val textState2 = rememberSaveable { mutableStateOf("") }

        Column()
        {
            Row(
                modifier = Modifier
                    .widthIn(296.dp)
                    .paddingFromBaseline(top = 62.dp, bottom = 28.dp)
                    .padding(start = 16.dp)


            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 62.dp, start = 8.dp)
                        .weight(1f),
                    fontSize = 24.sp,
                    text = "Hi Kojo,",
                    style = SatoshiTypography.h1,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.padding(horizontal = 40.dp))


            }

            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_firsttextbox),
                        color = Color.White.copy(alpha = 0.21f),
                        style = SatoshiTypography.body2,

                        )
                },
                modifier = Modifier

                    .padding(start = 24.dp, top = 14.dp, bottom = 14.dp)
                    .widthIn(296.dp)
                    .heightIn(min = 50.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 4.dp,
                            topEnd = 4.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )
                    )
                    .background(
                        Color(
                            red = 0.21131945f,
                            green = 0.35534722f,
                            blue = 0.31744516f,
                            alpha = 1f
                        )
                    )


            )

            TextField(
                value = textState2.value,
                onValueChange = { textState2.value = it },
                placeholder = {
                    Text(
                        stringResource(R.string.placeholder_secondtextbox),
                        color = Color.White.copy(alpha = 0.21f),
                        style = SatoshiTypography.body2,
                    )
                },
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 14.dp)
                    .widthIn(296.dp)
                    .heightIn(min = 50.dp)
                    .padding(bottom = 32.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 4.dp,
                            topEnd = 4.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp
                        )

                    )
                    .background(
                        Color(
                            red = 0.21131945f,
                            green = 0.35534722f,
                            blue = 0.31744516f,
                            alpha = 1f
                        )
                    )

            )

        }
        //Profile picture
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = Modifier
                .padding(start = 296.dp, top = 58.dp, end = 16.dp)
                //set image size to 40dp
                .size(40.dp)
                //shape image as circle
                .clip(CircleShape)

        )


        //Floating action button
        FloatingActionButton(
            onClick = {},
            elevation = FloatingActionButtonDefaults.elevation(20.dp),
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(top = 168.dp, bottom = 70.dp, start = 296.dp, end = 16.dp)
                .size(40.dp, 40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.up_down_arrow),
                contentDescription = "bidirectional button"
            )
        }
        /* */
    }

}

//Bottom navigation
@Composable
private fun MyBottomNavigation(modifier: Modifier = Modifier) {


    BottomNavigation(
        modifier = modifier
            .widthIn(375.dp)
            .heightIn(100.dp),
        //.padding( 40.dp,12.dp,40.dp,0.dp)
        backgroundColor = MaterialTheme.colors.background,


        ) {
        BottomNavigationItem(modifier = Modifier.padding(bottom = 24.dp, top = 2.23.dp),
            selected = true,
            onClick = { /*TODO*/ },
            label = { Text(stringResource(id = R.string.Home), style = SatoshiTypography.caption) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(modifier = Modifier.padding(bottom = 24.dp, top = 2.23.dp),
            selected = true,
            onClick = { /*TODO*/ },
            label = {
                Text(
                    stringResource(id = R.string.Tickets),
                    style = SatoshiTypography.caption,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ticket),
                    contentDescription = null,

                    )
            }
        )
        BottomNavigationItem(modifier = Modifier.padding(bottom = 24.dp, top = 2.23.dp),
            selected = true,
            onClick = { /*TODO*/ },
            label = {
                Text(stringResource(id = R.string.Users),
                    style = SatoshiTypography.caption)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.users),
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(modifier = Modifier.padding(bottom = 24.dp, top = 2.23.dp),
            selected = true,
            onClick = { /*TODO*/ },
            label = {
                Text(
                    stringResource(id = R.string.Settings),
                    style = SatoshiTypography.caption,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = null
                )
            }
        )
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
        Scaffold(
            bottomBar = { MyBottomNavigation() }
        ) { padding ->
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
    RideAppTheme {
        ExtrasElement(
            drawable = R.drawable.blanket,
            text = R.string.Blanket,

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
    RideAppTheme { MyBottomNavigation() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun RideAppPreview() {
    RideApp()
}