package com.example.bragi_book.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.bragi_book.data.BooksMenu
import com.example.bragi_book.ui.theme.mainBorder
import com.example.bragi_book.ui.theme.mainItemBackground
import com.example.bragi_book.ui.theme.mainText
import com.example.bragi_book.R
import com.example.bragi_book.common.Contains
import com.example.bragi_book.common.Single
import com.example.bragi_book.ui.BookDetailViewModel
import com.example.bragi_book.ui.BooksViewModel
import com.example.bragi_book.ui.theme.mainBackground
import com.example.bragi_book.ui.theme.statusText
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksGridScreen(
    books: List<BooksMenu>,
    modifier: Modifier,
    navController: NavController,
) {
    var search by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(mainBackground),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = search,
                onValueChange = {newText->
                    search = newText
                },
                label = {
                    Text(
                        text = "Поиск произведения",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(0.9f),
                        color = mainText
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = mainBackground,
                    textColor = mainText,
                    focusedBorderColor= mainBorder, // цвет при получении фокуса
                    unfocusedBorderColor = mainBorder  // цвет при отсутствии фокуса
                ),
                modifier = Modifier
                    .padding(top = 10.dp, start = 25.dp, end = 25.dp, bottom = 20.dp),
                shape = RoundedCornerShape(20.dp)
            )
            IconButton(
                onClick = {

                },
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_sort_24),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }

        LazyColumn(
            modifier = Modifier
        ){
            items(items = books){item->
                BooksCard(item, Modifier, navController)
            }
        }
    }
}

@Composable
fun BooksCard(
    book: BooksMenu,
    modifier: Modifier,
    navController: NavController,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(mainItemBackground)
            .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(20.dp))
            .clickable {
                Single.getInstance().chapterStr = null

                Single.getInstance().bookId = book.id
                navController.navigate(route = "DetailScreen")
            },
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(book.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp),
                )
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                ) {
                    Text(
                        text = "Рейтинг: " + book.rating,
                        color = mainText,
                        fontSize = 14.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(top = 5.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = book.name,
                    color = mainText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = book.description,
                    color = mainText,
                    modifier = Modifier.fillMaxHeight(0.7f),
                    fontSize = 14.sp,
                    maxLines = 5,
                )
                Text(
                    text = "Перевод: " + book.status,
                    color = statusText,
                    fontSize = 14.sp
                )
            }
        }
    }
}