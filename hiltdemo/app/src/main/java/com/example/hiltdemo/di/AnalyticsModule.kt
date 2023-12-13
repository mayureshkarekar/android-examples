package com.example.hiltdemo.di

import com.example.hiltdemo.Analytics
import com.example.hiltdemo.FirebaseAnalytics
import com.example.hiltdemo.WebEngageAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class AnalyticsModule {
    /* Whenever direct injection is not possible we can use Modules and Provides annotations.
       i.e. Classes in SDKs cannot be modified to add @Inject. Also some classes requires parameters
       or custom object creation.

       NOTE: Whenever a injection is requested, Hilt tries to find it in specific component. Means
       of the injection is requested in Fragment, it will look in Fragment component first for its
       object. It will go to Activity component if not found in Fragment component, and so on. */
    @Provides
    @FirebaseQualifier
    fun providesFirebaseAnalytics(): Analytics {
        return FirebaseAnalytics()
    }

    @Provides
    @WebEngageQualifier
    fun bindsWebEngage(webEngageAnalytics: WebEngageAnalytics): Analytics {
        return webEngageAnalytics
    }
}