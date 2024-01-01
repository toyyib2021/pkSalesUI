package com.pureKnowledge.salesApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.pureKnowledge.salesApp.navigation.Screen
import com.pureKnowledge.salesApp.navigation.SetupNavGraph
import com.pureKnowledge.salesApp.ui.theme.MongoDemoTheme
import com.pureKnowledge.salesApp.util.Constants.APP_ID
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MongoDemoTheme() {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}


