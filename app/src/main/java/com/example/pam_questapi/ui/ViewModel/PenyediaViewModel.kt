package com.example.pam_questapi.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam_questapi.MahasiswaApplications

object PenyediaViewModel{
    val Factory = viewModelFactory{
        initializer { HomeViewModel(MahasiswaApplications().container.mahasiswaRepository) }
        initializer { InsertViewModel(MahasiswaApplications().container.mahasiswaRepository) }
    }

    fun CreationExtras.MahasiswaApplications(): MahasiswaApplications =
        (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplications)
}