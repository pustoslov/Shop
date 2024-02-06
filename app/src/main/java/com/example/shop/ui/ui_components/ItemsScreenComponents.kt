package com.example.shop.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.ui.theme.DarkGrey
import com.example.shop.ui.theme.LightGrey
import com.example.shop.ui.theme.LightPink
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.Orange
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Float,
    spaceBetween: Dp = 0.dp
) {

    var isHalfStar = (rating % 1) != 0f

    Row(
        modifier = modifier
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(
                    id = if (i <= rating) {
                        R.drawable.star_filled
                    } else {
                        if (isHalfStar) {
                            isHalfStar = false
                            R.drawable.star_half
                        } else R.drawable.star_hollow
                    }
                ),
                contentDescription = null,
                tint = Orange,
                modifier = Modifier
                    .padding(end = spaceBetween)
            )
        }
    }
}

@Composable
fun BrandButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(LighterGrey, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = text,
                fontFamily = sanFrancisco,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
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
fun UnderlineField(
    textLeft: String,
    textRight: String,
    modifier: Modifier = Modifier
) {

    Column {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = textLeft,
                fontFamily = sanFrancisco,
                fontSize = 12.sp,
                color = DarkGrey,
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = textRight,
                fontFamily = sanFrancisco,
                fontSize = 12.sp,
                color = DarkGrey,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(LightGrey)
        )
    }
}

@Composable
fun BuyButton(
    price: String,
    oldPrice: String,
    unit: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(55.dp)
            .fillMaxWidth()
            .background(Pink, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "$price$unit",
                color = Color.White,
                fontFamily = sanFrancisco,
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp)
            )
            OldPriceField(
                price = oldPrice,
                unit = unit,
                color = LightPink
            )
            Text(
                text = "Добавить в корзину",
                color = Color.White,
                textAlign = TextAlign.End,
                fontFamily = sanFrancisco,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
                    .weight(1f)
            )
        }
    }
}