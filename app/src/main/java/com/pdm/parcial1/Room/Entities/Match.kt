package com.pdm.parcial1.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class Match(

        @ColumnInfo(name = "local")
        var local:String,

        @ColumnInfo(name = "visitor")
        var visitor:String,

        @ColumnInfo(name = "local_score")
        var localScore:Int,

        @ColumnInfo(name = "visitorScore")
        var visitorScore:Int,

        @ColumnInfo(name = "winner")
        var winner:String = "unknown",

        @ColumnInfo(name = "state")
        var state:Int

) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}