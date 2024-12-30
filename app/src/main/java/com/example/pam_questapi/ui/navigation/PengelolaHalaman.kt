package com.example.pam_questapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pam_questapi.ui.View.DestinasiEntry
import com.example.pam_questapi.ui.View.DestinasiHome
import com.example.pam_questapi.ui.View.EntryMhsScreen
import com.example.pam_questapi.ui.View.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    // Mengatur navigasi antar layar menggunakan NavHost
    NavHost(
        navController = navController, // Controller navigasi
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        // Halaman Home
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) }, // Navigasi ke EntryMhsScreen
                onDetailClick = {
                }
            )
        }
        // Halaman EntryMhs
        composable(DestinasiEntry.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) { // Navigasi kembali ke HomeScreen
                    popUpTo(DestinasiHome.route) {
                        inclusive = true // Bersihkan layar sebelumnya
                    }
                }
            })
        }
    }
}