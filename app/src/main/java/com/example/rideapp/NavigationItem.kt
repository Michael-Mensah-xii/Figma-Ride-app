package com.example.rideapp

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    object Home : NavigationItem("home", R.drawable.home, "Home")
    object Tickets : NavigationItem("tickets", R.drawable.ticket, "Tickets")
    object Referral : NavigationItem("referral", R.drawable.users, "Referral")
    object Settings : NavigationItem("settings", R.drawable.settings, "Settings")
}
