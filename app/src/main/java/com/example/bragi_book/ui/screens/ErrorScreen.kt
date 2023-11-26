package com.example.bragi_book.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bragi_book.R
import com.example.bragi_book.ui.theme.errorBorder
import com.example.bragi_book.ui.theme.mainBackground
import com.example.bragi_book.ui.theme.mainBorder
import com.example.bragi_book.ui.theme.mainItemBackground
import com.example.bragi_book.ui.theme.mainText

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(mainBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.loading_failed),
            color = mainText,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
        Button(
            onClick = retryAction,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.1f)
                .border(width = 2.dp, color = mainBorder, shape = RoundedCornerShape(30.dp)),
            colors = ButtonDefaults.buttonColors(mainItemBackground),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                modifier.background(mainItemBackground),
                color = mainText,
                fontSize = 20.sp
            )
            
        }
        
    }
}