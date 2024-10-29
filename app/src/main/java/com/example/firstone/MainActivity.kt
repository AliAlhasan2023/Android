package com.example.firstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstone.ui.theme.FirstOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    FirstOneTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android $it" }) {
    var counterState by remember { mutableStateOf(0) }  // Define counterState

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))

        Counter(
            count = counterState,
            updateCount = { newCount -> counterState = newCount }
        )
        if (counterState > 5) {
            Text("I love to count", modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) {
            Greeting(name = it)
            Divider()
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {  mutableStateOf(false) }

    val targetColor by animateColorAsState( targetValue = if (isSelected) Color.Red else Color.Transparent,
        animationSpec = tween(durationMillis = 2000))
    Surface(color = targetColor) {
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .clickable { isSelected = !isSelected }
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}
