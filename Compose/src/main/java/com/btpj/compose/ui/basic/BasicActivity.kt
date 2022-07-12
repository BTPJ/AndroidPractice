package com.btpj.compose.ui.basic

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.btpj.compose.ui.theme.AndroidPracticeTheme

class BasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPracticeTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    var showList by rememberSaveable { mutableStateOf(false) }

    if (showList) {
        ListScreen()
    } else {
        WelcomeScreen(onContinueClick = { showList = true })
    }
}

@Composable
fun WelcomeScreen(onContinueClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("欢迎来到Compose")
            Button(onClick = onContinueClick, modifier = Modifier.padding(10.dp)) {
                Text("进入列表")
            }
        }

    }
}

@Composable
fun ListScreen(strList: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = strList) { item -> Item(item) }
    }
}

@Composable
fun Item(item: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Hello")
                Text(text = item)
                if (expanded) {
                    Text(text = ("我是隐藏文本").repeat(10))
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = ""
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 640
)
@Composable
fun DefaultPreview() {
    AndroidPracticeTheme {
        ListScreen()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
fun WelcomeScreenPreview() {
    AndroidPracticeTheme {
        WelcomeScreen(onContinueClick = {})
    }
}