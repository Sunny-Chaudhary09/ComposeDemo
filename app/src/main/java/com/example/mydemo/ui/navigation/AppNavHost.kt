package com.example.mydemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mydemo.ui.login.LoginScreen
import com.example.mydemo.ui.login.LoginViewModel
import com.example.mydemo.ui.otp.OtpScreen
import com.example.mydemo.ui.splash.SplashScreen
import com.example.mydemo.ui.splash.SplashViewModel

@Composable
fun appNavHost(
    navController: NavHostController = rememberNavController()
): NavHostController {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            val viewModel: SplashViewModel = viewModel()
            SplashScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToOtpDemo = { navController.navigate(Routes.OTP) }
            )
        }
        composable(Routes.OTP) {
            OtpScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
    return navController
}
