package com.jetpack.master

import android.app.Application
import com.jetpack.master.diceKoin.DiceMotorKoin
import com.jetpack.master.diceKoin.PassphraseRepositoryKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import java.security.SecureRandom

class KoinApp: Application() {

    private val module = module {
        single { SecureRandom() }
        single { PassphraseRepositoryKoin(androidContext(), get()) }
        single { DiceMotorKoin(get()) }
    }
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@KoinApp)
            modules(module)
        }
    }
}