package com.example.shop.ui.ui_components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.Orange
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun OldPriceField(
    price: String,
    unit: String,
    color: Color = Grey
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
    ) {
        Text(
            text = "$price $unit",
            color = color,
            fontSize = 11.sp,
            fontFamily = sanFrancisco,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
                drawLine(
                    color = color,
                    start = Offset(x = size.width, y = size.height / 3f),
                    end = Offset(x = 0f, y = (size.height / 3f) * 2),
                    strokeWidth = 4f
                )
        }
    }
}

@Composable
fun DiscountField(
    discount: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .wrapContentSize()
            .background(Pink, RoundedCornerShape(5.dp))
    ) {
        Text(
            text = "-$discount%",
            color = Color.White,
            fontSize = 10.sp,
            fontFamily = sanFrancisco,
            modifier = Modifier
                .padding(horizontal = 6.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun RatingField(
    count: Int,
    rating: Double
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = null,
            tint = Orange,
            modifier = Modifier
                .padding(horizontal = 5.dp)
        )
        Text(
            text = rating.toString(),
            color = Orange,
            fontSize = 10.sp,
            fontFamily = sanFrancisco
        )
        Text(
            text = " ($count)",
            color = Grey,
            fontSize = 10.sp,
            fontFamily = sanFrancisco
        )
    }
}

@Composable
fun AddToCartButton() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(32.dp)
            .background(
                Pink,
                RoundedCornerShape(
                    topStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
    ) {
        Icon(
            painterResource(id = R.drawable.add),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onClick: () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    val painter = if (isFavorite) painterResource(id = R.drawable.heart_filled)
    else painterResource(id = R.drawable.heart_hollow)

    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Pink,
            modifier = Modifier
                .size(22.dp)

        )
    }
}


@Composable
@Preview
private fun ItemCardElementsPreview() {
    Column {
        OldPriceField(price = "348", unit = "$")
        DiscountField(discount = 35)
        RatingField(count = 4, rating = 4.3)
        AddToCartButton()
    }
}