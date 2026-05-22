package com.example.taskflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg2.ui.navigation.Splash
import com.example.taskvmg2.ui.navigation.TaskDetail
import com.example.taskvmg2.ui.screen.SplashScreen
import com.example.taskvmg2.ui.screen.TaskDetailScreen
import com.example.taskvmg2.ui.screen.TaskListScreen
import com.example.taskvmg2.ui.viewmodel.TaskViewModel
import com.example.taskvmg2.ui.navigation.TaskList

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(navController = navController,
        startDestination = Splash)
    {
        composable<Splash>
        {
            SplashScreen(navController = navController)
        }
        composable<TaskList>
        {
            TaskListScreen(navController = navController,
                viewModel = viewModel)
        }
        composable<TaskDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<TaskDetail>()
            TaskDetailScreen(navController = navController,
                taskId = route.taskId,
                viewModel = viewModel)
        }
    }
}