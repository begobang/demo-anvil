package com.nttdata.demoanvil.ui

import android.app.Application
import com.nttdata.demoanvil.di.AppComponent
import com.nttdata.demoanvil.di.DaggerAppComponent

class DemoAnvilApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}