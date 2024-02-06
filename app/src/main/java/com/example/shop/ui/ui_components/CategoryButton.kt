package com.example.shop.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.ui.theme.DarkBlue
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun CategoryButton(
    isSelected: Boolean,
    text: String,
    onButtonClick: () -> Unit,
    onCloseClick: () -> Unit
) {

    val buttonColor = if (isSelected) DarkBlue else LighterGrey
    val contentColor = if (isSelected) Color.White else Grey

    val buttonInteractionSource = remember { MutableInteractionSource() }
    val clearInteractionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentSize()
            .background(buttonColor, RoundedCornerShape(15.dp))
            .padding(5.dp)
            .clickable(
                interactionSource = buttonInteractionSource,
                indication = null
            ) { onButtonClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 6.dp)
        ) {
            Text(
                text = text,
                color = contentColor,
                fontSize = 14.sp,
                fontFamily = sanFrancisco
            )
            if (isSelected) {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .clickable(
                            interactionSource = clearInteractionSource,
                            indication = null
                        ) {
                            onCloseClick()
                        }
                )
            }
        }
    }
}