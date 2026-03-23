package com.example.mydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mydemo.ui.navigation.appNavHost
import com.example.mydemo.ui.theme.MyDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    appNavHost()
                }
            }
        }
    }
}
