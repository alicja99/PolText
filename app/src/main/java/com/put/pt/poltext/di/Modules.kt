package com.put.pt.poltext.di

import com.put.pt.poltext.repository.users.FirebaseUsersRepositoryImpl
import com.put.pt.poltext.screens.home.private_chat.PrivateChatViewModel
import com.put.pt.poltext.screens.home.public_chat.PublicChatViewModel
import com.put.pt.poltext.screens.home.profile.ProfileViewModel
import com.put.pt.poltext.screens.home.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel


import org.koin.dsl.module

val userViewModelModule = module {
    single { FirebaseUsersRepositoryImpl() }
    viewModel {
        PublicChatViewModel( get())
    }
    viewModel{
        PrivateChatViewModel(get())
    }
    viewModel{
        ProfileViewModel( get())
    }
}

val settingsViewModelModule = module {
    viewModel {
        SettingsViewModel()
    }
}

val viewModelModules = listOf(userViewModelModule, settingsViewModelModule)
