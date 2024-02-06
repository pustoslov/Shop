package com.example.shop.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shop.R
import com.example.shop.ui.theme.sanFrancisco
import com.example.shop.ui.ui_components.CategoryButton
import com.example.shop.ui.ui_components.DropDownFilterMenu
import com.example.shop.ui.ui_components.ItemCard
import com.example.shop.ui.view_models.CatalogViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun CatalogScreen(
    catalogViewModel: CatalogViewModel,
    onItemClick: () -> Unit
) {

    val categories = catalogViewModel.categories.values.toList()
    val items = mutableStateOf(catalogViewModel.currentItems)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .padding(horizontal = 20.dp)
        ) {
            DropDownFilterMenu(viewModel = catalogViewModel)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = null
                )
                Text(
                    text = "Фильтры",
                    fontFamily = sanFrancisco,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
        ) {
            items(categories) {category ->
                CategoryButton(
                    isSelected = category == catalogViewModel.selectedCategory.second,
                    text = category,
                    onButtonClick = {
                        catalogViewModel.updateCategory(category) },
                    onCloseClick = { catalogViewModel.updateCategory("Смотреть все") }
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }

        key(items.value) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(horizontal = 5.dp)
            ) {
                items(items.value) {
                    ItemCard(
                        item = it,
                        viewModel = catalogViewModel,
                        onFavoriteClick = { },
                        onClick = { onItemClick() }
                    )
                }
            }
        }

    }
}