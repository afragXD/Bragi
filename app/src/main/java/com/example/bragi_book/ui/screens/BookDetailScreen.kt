package com.example.bragi_book.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bragi_book.R
import com.example.bragi_book.common.Single
import com.example.bragi_book.data.BookDetail
import com.example.bragi_book.ui.theme.mainBackground
import com.example.bragi_book.ui.theme.mainBorder
import com.example.bragi_book.ui.theme.mainItemBackground
import com.example.bragi_book.ui.theme.mainText
import com.example.bragi_book.ui.theme.statusText

@Composable
fun BookDetailScreen(
    book: BookDetail,
    modifier: Modifier,
    navController: NavController,
){
    Log.d("GG1", book.name)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(mainBackground)
            .padding(start = 20.dp, end = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = book.name,
            color = mainText,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 5.dp, top = 20.dp)
        )
        Text(
            text = book.enName.replace("_", " "),
            color = mainText,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 20.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(book.image),
                contentDescription = null,
                modifier = Modifier
                    .size(500.dp)
                    .padding(bottom = 10.dp),
            )
            Button(
                onClick = {
                    Single.getInstance().bookName = book.enName
                    Single.getInstance().bookRuName = book.name
                    Single.getInstance().chapterNumber = "1"
                    Single.getInstance().chapterStr = null
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                    //{
                    //    popUpTo("ChapterScreen/${book.enName}/$num") {
                    //        inclusive = true
                    //    }
                    //}
                          },
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .width(300.dp)
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
            ) {
                Text(
                    text = stringResource(id = R.string.start_read),
                    modifier.background(mainItemBackground),
                    color = mainText,
                    fontSize = 14.sp
                )

            }
            Button(
                onClick = {
                    Single.getInstance().bookName = book.enName
                    Single.getInstance().chapterNumber = "1"
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                },
                modifier = Modifier
                    .width(300.dp)
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.resume_read),
                    modifier.background(mainItemBackground),
                    color = mainText,
                    fontSize = 14.sp
                )

            }
        }
        Text(
            text = stringResource(id = R.string.description),
            color = mainText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 20.dp),
        )
        Text(
            text = book.description,
            color = mainText,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(bottom = 20.dp),
        )
        Text(
            text = stringResource(id = R.string.info),
            color = mainText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 20.dp),
        )
        Row(
            modifier = Modifier
            .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.rating),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),

            )
            Text(
                text = book.rating.toString(),
                color = statusText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),
            )
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = null,
                tint = Color.Yellow
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.status),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),

                )
            Text(
                text = book.status,
                color = statusText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.chapters),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),

                )
            Text(
                text = book.chapters.toString(),
                color = statusText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(id = R.string.year),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),

                )
            Text(
                text = book.year.toString(),
                color = statusText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.author),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),

                )
            Text(
                text = book.author,
                color = statusText,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(end = 5.dp),
            )
        }

    }
}