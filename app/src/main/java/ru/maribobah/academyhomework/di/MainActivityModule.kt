package ru.maribobah.academyhomework.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.maribobah.academyhomework.MainActivity

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity
}