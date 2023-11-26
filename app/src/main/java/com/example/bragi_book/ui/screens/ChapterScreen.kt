package com.example.bragi_book.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.bragi_book.R
import com.example.bragi_book.common.Single
import com.example.bragi_book.ui.BookDetailUiState
import com.example.bragi_book.ui.ChapterUiState
import com.example.bragi_book.ui.theme.mainBackground
import com.example.bragi_book.ui.theme.mainBorder
import com.example.bragi_book.ui.theme.mainItemBackground
import com.example.bragi_book.ui.theme.mainText
import okhttp3.ResponseBody


@Composable
fun ChapterScreen(
    chapterUiState: ChapterUiState,
    retryAction: () -> Unit,
    modifier: Modifier,
    navController: NavController,
) {
    when (chapterUiState) {
        is ChapterUiState.Loading -> LoadingScreen(modifier)
        is ChapterUiState.Success -> ChapterViewScreen(
            chapterText = chapterUiState.chapterSearch,
            modifier = modifier,
            navController = navController,
        )
        is ChapterUiState.Error -> ErrorScreen(retryAction = retryAction, modifier)
    }
}

@Composable
fun ChapterViewScreen(
    chapterText: ResponseBody,
    modifier: Modifier,
    navController: NavController,
){

    if (Single.getInstance().chapterStr == null){
        Single.getInstance().chapterStr = chapterText.string()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(mainBackground)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(mainItemBackground),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.chapter_list),
                color = mainText,
                fontSize = 18.sp,
                modifier = Modifier
            )
        }

        Text(
            text = "Глава: " + Single.getInstance().chapterNumber,
            color = mainText,
            fontSize = 18.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = Single.getInstance().bookRuName,
            color = mainText,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 20.dp),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    Single.getInstance().chapterStr = null
                    Single.getInstance().chapterNumber = (Single.getInstance().chapterNumber.toInt() - 1).toString()
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                          },
                modifier = Modifier
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_before_24),
                    contentDescription = null,
                    tint = mainText
                )
                Text(
                    text = stringResource(id = R.string.before),
                    color = mainText,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 5.dp),
                )
            }
            Button(
                onClick = {
                    Single.getInstance().chapterStr = null
                    Single.getInstance().chapterNumber = (Single.getInstance().chapterNumber.toInt() + 1).toString()
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                          },
                modifier = Modifier
                    .fillMaxHeight()
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    color = mainText,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = 5.dp),
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = null,
                    tint = mainText
                )
            }
        }

        Text(
            text = Single.getInstance().chapterStr!!,
            modifier = Modifier
                .padding(20.dp),
            fontSize = 24.sp,
            color = mainText
        )

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    Single.getInstance().chapterStr = null
                    Single.getInstance().chapterNumber = (Single.getInstance().chapterNumber.toInt() - 1).toString()
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                          },
                modifier = Modifier
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_before_24),
                    contentDescription = null,
                    tint = mainText
                )
                Text(
                    text = stringResource(id = R.string.before),
                    color = mainText,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 5.dp),
                )
            }
            Button(
                onClick = {
                    Single.getInstance().chapterStr = null
                    Single.getInstance().chapterNumber = (Single.getInstance().chapterNumber.toInt() + 1).toString()
                    navController.navigate(
                        route = "ChapterScreen"
                    )
                          },
                modifier = Modifier
                    .fillMaxHeight()
                    .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
                colors = ButtonDefaults.buttonColors(mainItemBackground),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    color = mainText,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = 5.dp),
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_navigate_next_24),
                    contentDescription = null,
                    tint = mainText
                )
            }
        }
    }
}