package com.example.pam_questapi

import android.app.Application
import com.example.pam_questapi.repository.AppContainer
import com.example.pam_questapi.repository.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }

}