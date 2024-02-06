package com.example.shop.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shop.R
import com.example.shop.data.network.model.Item
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.LightGrey
import com.example.shop.ui.theme.Pink
import com.example.shop.ui.theme.sanFrancisco
import com.example.shop.ui.ui_components.BrandButton
import com.example.shop.ui.ui_components.BuyButton
import com.example.shop.ui.ui_components.DiscountField
import com.example.shop.ui.ui_components.FavoriteIcon
import com.example.shop.ui.ui_components.OldPriceField
import com.example.shop.ui.ui_components.RatingBar
import com.example.shop.ui.ui_components.UnderlineField
import com.example.shop.ui.view_models.CatalogViewModel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemScreen(
    catalogViewModel: CatalogViewModel,
    item: Item,
) {

    val pagerState = rememberPagerState(pageCount = { 2 })
    val isFavorite = mutableStateOf(catalogViewModel.favoritesIds.contains(item.id))

    val rowsPadding = 3.dp

    var isDescriptionExpanded by remember { mutableStateOf(true) }
    val descriptionModifier = if (isDescriptionExpanded) Modifier.wrapContentHeight()
    else Modifier.height(0.dp)

    var isIngredientsExpanded by remember { mutableStateOf(false) }
    var isIngredientsOverflow by remember { mutableStateOf(false) }

    val available = "Доступно для заказа ${countToString(
        item.available,
        listOf("штука", "штуки", "штук")
    )}"

    val feedback = countToString(
        item.feedback.count,
        listOf("отзыв", "отзыва", "отзывов")
    )


    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Image(
                    painter = painterResource(item.images[it]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(450.dp)
                )
            }
            key(isFavorite.value) {
                FavoriteIcon(
                    isFavorite = isFavorite.value,
                    onClick = {
                        if (isFavorite.value) {
                            catalogViewModel.deleteFromFavorites(item.id)
                            isFavorite.value = !isFavorite.value
                        }
                        else {
                            catalogViewModel.addToFavorite(item.id)
                            isFavorite.value = !isFavorite.value
                        }
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Pink else LightGrey
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)
                )
            }
        }

        Text(
            text = item.title,
            fontFamily = sanFrancisco,
            color = Grey,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(vertical = rowsPadding)
        )
        Text(
            text = item.subtitle,
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 24.sp,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = rowsPadding)
        )
        Text(
            text = available,
            fontFamily = sanFrancisco,
            fontSize = 11.sp,
            color = Grey,
            modifier = Modifier
                .padding(vertical = rowsPadding)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 10.dp, top = 17.dp)
        ) {
            RatingBar(
                rating = item.feedback.rating.toFloat(),
                spaceBetween = 2.dp,
                modifier = Modifier
                    .padding(end = 5.dp)
            )
            Text(
                text = item.feedback.rating.toString(),
                fontFamily = sanFrancisco,
                fontSize = 11.sp,
                color = Grey,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Text(
                text = feedback,
                fontFamily = sanFrancisco,
                fontSize = 11.sp,
                color = Grey,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 13.dp)
        ) {
            Text(
                text = "${item.price.priceWithDiscount} ${item.price.unit}",
                fontFamily = sanFrancisco,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 22.sp,
                modifier = Modifier
                    .padding(end = 10.dp)
            )
            OldPriceField(
                price = item.price.price,
                unit = item.price.unit
            )
            DiscountField(
                discount = item.price.discount,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        Text(
            text = "Описание",
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(top = rowsPadding, bottom = 7.dp)
        )
        Box(
            modifier = descriptionModifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 8.dp)
            ) {
                BrandButton(
                    text = item.title,
                    modifier = Modifier
                        .padding(vertical = rowsPadding)
                )
                Text(
                    text = item.description,
                    fontFamily = sanFrancisco,
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
            }
        }
        Text(
            text = if (isDescriptionExpanded) "Скрыть" else "Подробнее",
            color = Grey,
            fontFamily = sanFrancisco,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            modifier = Modifier
                .clickable {
                    isDescriptionExpanded = !isDescriptionExpanded
                }
        )
        Text(
            text = "Характеристики",
            fontFamily = sanFrancisco,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 14.sp,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 12.dp)
        )
        for (info in item.info) {
            UnderlineField(
                textLeft = info.title,
                textRight = info.value,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(25.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Состав",
                fontFamily = sanFrancisco,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 14.sp,
                modifier = Modifier
                    .weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.copy),
                contentDescription = null,
                tint = Grey
            )
        }
        Text(
            text = item.ingredients,
            fontFamily = sanFrancisco,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            maxLines = if (isIngredientsExpanded) 50 else 2,
            onTextLayout = {
                           isIngredientsOverflow = it.hasVisualOverflow
            },
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        if (
            (isIngredientsOverflow && !isIngredientsExpanded) ||
            (!isIngredientsOverflow && isIngredientsExpanded)
            ) {
            Text(
                text = if (isIngredientsExpanded) "Скрыть" else "Подробнее",
                color = Grey,
                fontFamily = sanFrancisco,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                modifier = Modifier
                    .clickable {
                        isIngredientsExpanded = !isIngredientsExpanded
                    }
            )
        }
        BuyButton(
            price = item.price.priceWithDiscount,
            oldPrice = item.price.price,
            unit = item.price.unit,
            modifier = Modifier
                .padding(top = 20.dp, end = 10.dp)
        )
    }
}

private fun countToString(
    count: Int,
    variants: List<String>
): String {
    val digits = count.toString()
    val itemsCount =
        if (count < 10) {
            when (count) {
                1 -> variants[0]
                in 2..4 -> variants[1]
                else -> variants[2]
            }
        } else if (digits.substring(digits.length-2, digits.length).toInt() in 11..19) {
            variants[2]
        } else {
            val lastDigit = digits.substring(digits.length-1, digits.length).toInt()
            when (lastDigit) {
                1 -> variants[0]
                in 2..4 -> variants[1]
                else -> variants[2]
            }
        }
    return "$count $itemsCount"
}