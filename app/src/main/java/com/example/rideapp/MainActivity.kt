package com.example.rideapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.rideapp.ui.theme.Buttonc
import com.example.rideapp.ui.theme.Green200
import com.example.rideapp.ui.theme.RideAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(2000)
        installSplashScreen()
        setContent {
            RideApp()
            MyBottomNavigation()
        }


    }
}

// Step: Extrassection - Slot APIs
@Composable
fun ExtrasSection(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            text = stringResource(id = R.string.Extra),// shows title in uppercase
            style = TextStyle(fontSize = 15.sp),
            // add some padding to the top of title of 40 dp and underneath title of 8dp
            modifier = Modifier
                .paddingFromBaseline(top = 0.dp, bottom = 27.dp)
                .padding(horizontal = 10.dp) //add some space between title and column

        )
        content()
    }
}

//item select row
@Composable
fun ExtrasRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(30.dp), // spaces all items in lazyRow by 8dp
        contentPadding = PaddingValues(horizontal = 6.dp),// this parameter ensures that padding is maintained while scrolling without clipping
        modifier = modifier.background(MaterialTheme.colors.background),
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
    @StringRes text: Int
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
                .heightIn(10.dp)
                .widthIn(55.dp)
                //sets the image composable to a shape
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Transparent)


        )
        Text(
            text = stringResource(id = text),
            style = TextStyle(fontSize = 13.sp),
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp, bottom = 8.dp
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
    @StringRes val text: Int
)


// Extended buttons
@Composable
fun ExtendedButton(
    @StringRes title: Int
) {

    Box(
        modifier = Modifier
            .height(97.dp)
            .background(Color.Transparent)
            .alpha(1f)


    ) {
        Column(
            modifier = Modifier
                .padding(top = 0.dp, start = 10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.Extendedbt),
                style = TextStyle(fontSize = 15.sp),
                modifier = Modifier
                    .paddingFromBaseline(top = 0.dp, bottom = 20.dp)
                    .padding(horizontal = 0.dp)
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.Today_string)) },
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 12.dp),
                    contentColor = Color.Black,
                    backgroundColor = Color.White,
                )

                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.Tommorow_string)) },
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 12.dp),
                    contentColor = Color.Black,
                    backgroundColor = Color.White,
                )

                ExtendedFloatingActionButton(
                    text = { Text(text = stringResource(id = R.string.calender_string)) },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calender),
                            contentDescription = "calender"
                        )
                    },
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(bottom = 12.dp),
                    contentColor = Color.Black,
                    backgroundColor = Color.White,
                )
            }

        }
    }
}

@Composable
fun OddButton(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .widthIn(90.dp)
            .heightIn()

    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f),
            text = "Hi Kojo,",
            style = MaterialTheme.typography.h4,
            color = Color.White,
        )

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "profile image",
            modifier = Modifier
                //set image size to 40dp
                .size(40.dp)
                //shape image as circle
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.padding(20.dp))
    }
}

//Button
@Composable
fun Button() {
    Button(
        onClick = { /*TODO*/ },
        colors = (ButtonDefaults.buttonColors(backgroundColor = Buttonc)),
        modifier = Modifier
            .widthIn(1000.dp)
            .height(150.dp)
            .padding(start = 87.dp, top = 70.dp, end = 87.dp, bottom = 15.dp)
            .alpha(1f)
            .clip(
                RoundedCornerShape(4.dp)
            )
    ) {
        Text(stringResource(R.string.placeholder_button), color = Color.White)

    }

}

//App Header
@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 270.dp)
            .background(color = Green200),
        contentAlignment = Alignment.Center
    ) {
        val textState = rememberSaveable { mutableStateOf("") }
        val textState2 = rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier.padding(start = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .widthIn(90.dp)
                    .heightIn()

            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .weight(1f),
                    text = "Hi Kojo,",
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                )

                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "profile image",
                    modifier = Modifier
                        //set image size to 40dp
                        .size(40.dp)
                        //shape image as circle
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.padding(20.dp))
            }

            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                placeholder = {
                    Text(stringResource(R.string.placeholder_firsttextbox), color = Color.White)
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
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

            //Floating action button
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = Color.White,
                modifier = Modifier
                    .padding(start = 300.dp)
                    .size(40.dp, 40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.up_down_arrow),
                    contentDescription = "bidirectional button"
                )
            }

            TextField(
                value = textState2.value,
                onValueChange = { textState2.value = it },
                placeholder = {
                    Text(stringResource(R.string.placeholder_secondtextbox), color = Color.White)
                },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
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
        }
    }

}

//Bottom navigation
@Composable
private fun MyBottomNavigation() {


    BottomNavigation(
        modifier = Modifier, backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = { Text(stringResource(id = R.string.Home)) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = { Text(stringResource(id = R.string.Tickets)) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ticket),
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = { Text(stringResource(id = R.string.Users)) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.users),
                    contentDescription = null
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            label = { Text(stringResource(id = R.string.Settings)) },
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
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        AppHeader()
        ExtendedButton(title = R.string.Extendedbt)
        Spacer(Modifier.height(50.dp))
        ExtrasSection(title = R.string.Extra) {
            ExtrasRow()
        }
        Spacer(Modifier.height(20.dp))
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
        ExtendedButton(title = R.string.Extendedbt)
    }
}

//home section preview
@Preview(showBackground = true)
@Composable
fun HomeSectionPreview() {
    RideAppTheme {
        ExtrasSection(title = R.string.Extra) {
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