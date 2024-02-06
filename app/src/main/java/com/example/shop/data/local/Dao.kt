package com.example.shop.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Insert
    suspend fun saveToFavorite(id: FavoriteItemId)

    @Query("DELETE FROM ids WHERE in_app_id=:id")
    suspend fun deleteFromFavorite(id: String)

    @Query("SELECT * FROM ids")
    suspend fun getAllFavorites(): List<FavoriteItemId>

    @Query("SELECT * from user WHERE id = 1")
    suspend fun getUser(): User?

}