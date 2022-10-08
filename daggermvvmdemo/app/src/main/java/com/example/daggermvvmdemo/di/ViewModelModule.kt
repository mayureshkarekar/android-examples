package com.example.daggermvvmdemo.di

import androidx.lifecycle.ViewModel
import com.example.daggermvvmdemo.viewmodel.MainViewModel
import com.example.daggermvvmdemo.viewmodel.MainViewModel2
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    // @StringKey("mainViewModel")
    @ClassKey(MainViewModel::class)
    @IntoMap
    abstract fun getMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    // @StringKey("mainViewModel2")
    @ClassKey(MainViewModel2::class)
    @IntoMap
    abstract fun getMainViewModel2(mainViewModel: MainViewModel2): ViewModel
}