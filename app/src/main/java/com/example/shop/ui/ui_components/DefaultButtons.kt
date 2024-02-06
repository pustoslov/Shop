package com.example.shop.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.ui.theme.LightPink
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun TextButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(51.dp)
            .width(342.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(
                if (isActive) Modifier
                    .background(Pink)
                    .clickable {
                        onClick()
                    }
                else Modifier.background(LightPink)
            )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontFamily = sanFrancisco
        )
    }
}

@Composable
@Preview
private fun ButtonsPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextButton(
            isActive = false,
            text = "Войти",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        TextButton(
            isActive = true,
            text = "Войти",
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
    }
}