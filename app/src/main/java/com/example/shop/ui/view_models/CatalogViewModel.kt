package com.example.shop.ui.view_models

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.R
import com.example.shop.data.local.FavoriteItemId
import com.example.shop.data.local.OfflineRepository
import com.example.shop.data.network.NetworkRepository
import com.example.shop.data.network.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class CatalogViewModel @Inject constructor(
    networkRepository: NetworkRepository,
    offlineRepository: OfflineRepository
): ViewModel() {

    lateinit var allItems: List<Item>
        private set

    var currentItems by mutableStateOf(listOf<Item>())
        private set

    lateinit var currentItem: Item
        private set

    var favoritesIds by mutableStateOf(listOf<String>())
        private set


    val categories = mapOf(
        "all" to "Смотреть все",
        "face" to "Лицо",
        "body" to "Тело",
        "suntan" to "Загар",
        "mask" to "Маски"
    )
    var selectedCategory by mutableStateOf(Pair("all", "Смотреть все"))
        private set

    var selectedFilter by mutableStateOf("По популярности")
        private set

    init {
        viewModelScope.launch {
            favoritesIds = offlineRepository.getFavorites()
            allItems = networkRepository.getProducts().items
            currentItems = allItems
            addImages()
        }
    }

    // Что за идиотизм? Нельзя возвращать ссылку на изображение в json или типа джуны не
    // должны уметь в AsyncImage?
    // Кто вообще хардкодит изображения???
    private fun addImages() {
        for (item in allItems) {
            when (item.id) {
                "cbf0c984-7c6c-4ada-82da-e29dc698bb50" ->
                    item.images = listOf(
                        R.drawable.f,
                        R.drawable.e
                    )
                "54a876a5-2205-48ba-9498-cfecff4baa6e" ->
                    item.images = listOf(
                        R.drawable.a,
                        R.drawable.b
                    )
                "75c84407-52e1-4cce-a73a-ff2d3ac031b3" ->
                    item.images = listOf(
                        R.drawable.e,
                        R.drawable.f
                    )
                "16f88865-ae74-4b7c-9d85-b68334bb97db" ->
                    item.images = listOf(
                        R.drawable.c,
                        R.drawable.d
                    )
                "26f88856-ae74-4b7c-9d85-b68334bb97db" ->
                    item.images = listOf(
                        R.drawable.b,
                        R.drawable.c
                    )
                "15f88865-ae74-4b7c-9d81-b78334bb97db" ->
                    item.images = listOf(
                        R.drawable.f,
                        R.drawable.a
                    )
                "88f88865-ae74-4b7c-9d81-b78334bb97db" ->
                    item.images = listOf(
                        R.drawable.d,
                        R.drawable.c
                    )
                "55f58865-ae74-4b7c-9d81-b78334bb97db" ->
                    item.images = listOf(
                        R.drawable.a,
                        R.drawable.e
                    )
            }
        }
    }

    fun updateCategory(categoryName: String) {
        for (category in categories) {
            if (category.value == categoryName) selectedCategory = category.toPair()
        }
        currentItems = if (selectedCategory.first == "all") {
            allItems
        } else allItems.filter { it.tags.contains(selectedCategory.first) }
    }

    fun updateFilter(filter: String) {

        selectedFilter = filter

        when (filter) {
            "Цена возрастание" ->
                currentItems = currentItems.sortedBy {
                    it.price.priceWithDiscount.toInt()
                }
            "Цена убывание" ->
                currentItems = currentItems.sortedByDescending {
                    it.price.priceWithDiscount.toInt()
            }
            "По популярности" ->
                currentItems = currentItems.sortedByDescending {
                    it.feedback.rating
                }
        }
    }

    private val offlineRepo = offlineRepository

    fun addToFavorite(id: String) {
        val newList = favoritesIds.toMutableList()
        newList.add(id)
        favoritesIds = newList.toList()
        viewModelScope.launch {
            offlineRepo.addFavorite(FavoriteItemId(inAppId = id))
        }
    }

    fun deleteFromFavorites(id: String) {
        val newList = favoritesIds.toMutableList()
        newList.remove(id)
        favoritesIds = newList.toList()
        viewModelScope.launch{
            offlineRepo.deleteFavorite(id)
        }
    }

    fun updateItem(item: Item) {
        currentItem = item
    }

    fun deleteAllFavorites() {
        viewModelScope.launch {
            offlineRepo.deleteAllFavorites(favoritesIds)
        }
        favoritesIds = listOf()
    }


}