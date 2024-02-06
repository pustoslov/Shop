package com.example.shop.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.ui.theme.DarkGrey
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun HollowProfileButton(
    iconResource: Int,
    text: String,
    tint: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(LighterGrey, RoundedCornerShape(8.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = null,
                tint = tint,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
            )
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontFamily = sanFrancisco,
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_next),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun ProfileButton(
    name: String,
    surname: String,
    phoneNumber: String,
    modifier: Modifier = Modifier
) {

    val number = if (phoneNumber.length > 0) "+7 ${phoneNumber.substring(0, 3)} " +
            "${phoneNumber.substring(3, 6)} " +
            "${phoneNumber.substring(6, 8)} " +
            phoneNumber.substring(8, 10)
    else ""

    Box(
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(LighterGrey, RoundedCornerShape(8.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                tint = DarkGrey,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "$name $surname",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = sanFrancisco,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
                Text(
                    text = number,
                    fontSize = 12.sp,
                    fontFamily = sanFrancisco,
                    color = Grey,
                    modifier = Modifier
                        .padding(bottom = 3.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.exit),
                contentDescription = null,
                tint = DarkGrey,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

@Composable
fun ExitButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(LighterGrey, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Text(
            text = "Выйти",
            fontWeight = FontWeight.SemiBold,
            fontFamily = sanFrancisco
        )
    }
}

@Composable
fun FavoritesButton(
    count: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val variants = listOf("товар", "товара", "товаров")
    val digits = count.toString()
    val itemsCount =
        if (count < 10) {
            if (count == 1) variants[0]
            else if (count in 2..4) variants[1]
            else variants[2]
        } else if (digits.substring(digits.length-2, digits.length).toInt() in 11..19) {
            variants[2]
        } else {
            val lastDigit = digits.substring(digits.length-1, digits.length).toInt()
            if (lastDigit == 1) variants[0]
            else if (lastDigit in 2..4) variants[1]
            else variants[2]
        }

    Box(
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(LighterGrey, RoundedCornerShape(8.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart_hollow),
                contentDescription = null,
                tint = Pink,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp)
                    .size(18.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Избранное",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
                if (count != 0) {
                    Text(
                        text = "$count $itemsCount",
                        fontSize = 12.sp,
                        color = Grey,
                        modifier = Modifier
                            .padding(bottom = 3.dp)
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow_next),
                contentDescription = null,
                tint = DarkGrey,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }

}

@Preview
@Composable
private fun Preview() {
    Column {
        HollowProfileButton(iconResource = R.drawable.heart_hollow, text = "Избранное", tint = Pink)
        ProfileButton(name = "Кирик", surname = "Андриянов", phoneNumber = "9134565462")
    }
}