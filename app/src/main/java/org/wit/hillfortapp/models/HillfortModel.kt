package org.wit.hillfortapp.models
import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true)

        var id:Long=0,
        var fbId : String = "",
        var name:String="",
        var description:String="",
        var image:String="", @Embedded var location : Location = Location()): Parcelable


@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
/*
@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true)
                         var id:Long=0,
                         var name:String="",
                         var description:String="",
                         var image:String="",
                         var image2:String="",
                         var lat: Double=0.0,
                         var lng: Double=0.0,
                         var zoom: Float=0f):Parcelable

 */