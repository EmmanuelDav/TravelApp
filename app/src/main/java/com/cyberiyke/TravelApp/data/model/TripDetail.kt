package com.cyberiyke.TravelApp.data.model


data class TripDetail(
    var location: String = "",
    var date: String = "",
    var tripName: String = "",
    var tripStyle: String = "",
    var tripInfo: String = "",
)


data class Hotels(
    var hotelName :String = "",
    var hotelLocation: String = "",
    var checkInDate:String = "",
    var checkOutDate:String = "",
    var price:String = ""
)