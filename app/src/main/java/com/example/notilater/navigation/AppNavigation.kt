package com.example.notilater.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature.notes.AddNoteScreen
import com.example.feature.notes.NoteDetailsScreen
import com.example.feature.notes.NotesIntent
import com.example.feature.notes.NotesScreen
import com.example.feature.notes.NotesViewModel
import com.example.feature.summary.SummaryScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val notesViewModel: NotesViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.NOTES
    ) {
        composable(Routes.NOTES) {
            NotesScreen(
                viewModel = notesViewModel,
                onAddNoteClick = {
                    navController.navigate(Routes.ADD_NOTE)
                },
                onNoteClick = { noteId ->
                    notesViewModel.onIntent(
                        NotesIntent.SelectNote(noteId)
                    )
                    navController.navigate(Routes.NOTE_DETAILS)
                }
            )
        }

        composable(Routes.ADD_NOTE) {
            AddNoteScreen(
                viewModel = notesViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.NOTE_DETAILS) {
            NoteDetailsScreen(
                viewModel = notesViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditClick = { noteId ->
                    navController.navigate(Routes.editNote(noteId))
                },
                onAiSummaryClick = { title, content ->
                    navController.navigate(
                        "${Routes.SUMMARY}/${android.net.Uri.encode(title)}/${android.net.Uri.encode(content)}"
                    )
                }
            )
        }

        composable(
            route = Routes.EDIT_NOTE,
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")

            AddNoteScreen(
                viewModel = notesViewModel,
                noteId = noteId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${Routes.SUMMARY}/{noteTitle}/{noteContent}",
            arguments = listOf(
                navArgument("noteTitle") {
                    type = NavType.StringType
                },
                navArgument("noteContent") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val noteTitle = backStackEntry.arguments
                ?.getString("noteTitle")
                .orEmpty()

            val noteContent = backStackEntry.arguments
                ?.getString("noteContent")
                .orEmpty()

            SummaryScreen(
                noteTitle = noteTitle,
                noteContent = noteContent,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}