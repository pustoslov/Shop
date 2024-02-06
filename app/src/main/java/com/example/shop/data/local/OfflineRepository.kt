package com.example.shop.data.local

import javax.inject.Inject

class OfflineRepository @Inject constructor(private val dao: Dao) {
    suspend fun saveUser(user: User) = dao.saveUser(user)
    suspend fun getUser() = dao.getUser()
    suspend fun getFavorites() = favoritesToListOfIds(dao.getAllFavorites())
    suspend fun addFavorite(fav: FavoriteItemId) = dao.saveToFavorite(fav)
    suspend fun deleteFavorite(id: String) = dao.deleteFromFavorite(id)
    suspend fun deleteUser(user: User) = dao.deleteUser(user)
    suspend fun deleteAllFavorites(list: List<String>) {
        for (item in list) {
            deleteFavorite(item)
        }
    }
}

private fun favoritesToListOfIds(list: List<FavoriteItemId>): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in list) {
        result.add(i.inAppId)
    }
    return result
}