package com.cyberiyke.TravelApp.data.model


data class TripDetail(
    var departureLocation: String = "",
    var destinationLocation: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var tripInfo: String = "",
    var hotelList:List<Hotels> = arrayListOf()
)


data class Hotels(
    var hotelName :String = "",
    var hotelLocation: String = "",
    var checkInDate:String = "",
    var checkOutDate:String = "",
    var price:String = ""
)