package com.btpj.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.btpj.compose.ui.theme.AndroidPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidPracticeTheme {
                MessageCard(msg = Message(R.drawable.test, "开始学习Compose", "2022-04-05"))
            }
        }
    }

    data class Message(val picRes: Int, val content: String, val date: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = msg.picRes),
                contentDescription = "图片内容描述",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = MaterialTheme.colors.primary)
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.content,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
                Text(text = msg.date)
            }
        }
    }

    @Composable
    fun MessageList(msgList: List<Message>) {
        LazyColumn {
            items(msgList) { msg -> MessageCard(msg = msg) }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun PreviewMessageCard() {
        val msgList = ArrayList<Message>()
        for (i in 0..100) {
            msgList.add(
                Message(
                    R.drawable.test,
                    "开始学习Compose第${i}天开始学习Compose第${i}天开始学习Compose第${i}天开始学习Compose第${i}天开始学习Compose第${i}天",
                    "2022-04-05"
                )
            )
        }
        AndroidPracticeTheme {
            MessageList(msgList = msgList)
        }
    }
}