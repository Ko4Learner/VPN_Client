package com.pet.vpn_client.di

import android.content.Context
import com.google.gson.Gson
import com.pet.vpn_client.data.ConfigManagerImpl
import com.pet.vpn_client.data.SettingsManagerImpl
import com.pet.vpn_client.data.SubscriptionManagerImpl
import com.pet.vpn_client.data.config_formatter.HttpFormatter
import com.pet.vpn_client.data.config_formatter.ShadowsocksFormatter
import com.pet.vpn_client.data.config_formatter.SocksFormatter
import com.pet.vpn_client.data.config_formatter.TrojanFormatter
import com.pet.vpn_client.data.config_formatter.VlessFormatter
import com.pet.vpn_client.data.config_formatter.VmessFormatter
import com.pet.vpn_client.data.config_formatter.WireguardFormatter
import com.pet.vpn_client.data.mmkv.MMKVStorage
import com.pet.vpn_client.domain.interfaces.ConfigManager
import com.pet.vpn_client.domain.interfaces.KeyValueStorage
import com.pet.vpn_client.domain.interfaces.SettingsManager
import com.pet.vpn_client.domain.interfaces.SubscriptionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    //@ApplicationContext context: Context

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()


    @Provides
    @Singleton
    fun provideMMKVConfig(gson: Gson): KeyValueStorage = MMKVStorage(gson)

    @Provides
    @Singleton
    fun provideConfigManager(
        storage: KeyValueStorage,
        gson: Gson,
        settingsManager: SettingsManager,
        httpFormatter: HttpFormatter,
        shadowsocksFormatter: ShadowsocksFormatter,
        socksFormatter: SocksFormatter,
        trojanFormatter: TrojanFormatter,
        vlessFormatter: VlessFormatter,
        vmessFormatter: VmessFormatter,
        wireguardFormatter: WireguardFormatter,
        @ApplicationContext context: Context
    ): ConfigManager =
        ConfigManagerImpl(
            storage,
            gson,
            settingsManager,
            httpFormatter,
            shadowsocksFormatter,
            socksFormatter,
            trojanFormatter,
            vlessFormatter,
            vmessFormatter,
            wireguardFormatter,
            context
        )

    @Provides
    @Singleton
    fun provideSubscriptionManager(
        storage: KeyValueStorage,
        gson: Gson,
        settingsManager: SettingsManager,
        configManager: ConfigManager,
        httpFormatter: HttpFormatter,
        shadowsocksFormatter: ShadowsocksFormatter,
        socksFormatter: SocksFormatter,
        trojanFormatter: TrojanFormatter,
        vlessFormatter: VlessFormatter,
        vmessFormatter: VmessFormatter,
        wireguardFormatter: WireguardFormatter,
        @ApplicationContext context: Context
    ): SubscriptionManager = SubscriptionManagerImpl(
        storage,
        gson,
        settingsManager,
        configManager,
        httpFormatter,
        shadowsocksFormatter,
        socksFormatter,
        trojanFormatter,
        vlessFormatter,
        vmessFormatter,
        wireguardFormatter,
        context
    )

    @Provides
    @Singleton
    fun provideSettingsManager(storage: KeyValueStorage, gson: Gson): SettingsManager =
        SettingsManagerImpl(storage, gson)

//    @Provides
//    @Singleton
//    fun provideHttpFormatter(configManager: ConfigManager): HttpFormatter =
//        HttpFormatter(configManager)
//
//    @Provides
//    @Singleton
//    fun provideShadowsocksFormatter(configManager: ConfigManager): ShadowsocksFormatter =
//        ShadowsocksFormatter(configManager)
//
//    @Provides
//    @Singleton
//    fun provideSocksFormatter(configManager: ConfigManager): SocksFormatter =
//        SocksFormatter(configManager)
//
//    @Provides
//    @Singleton
//    fun provideTrojanFormatter(
//        configManager: ConfigManager,
//        storage: KeyValueStorage
//    ): TrojanFormatter =
//        TrojanFormatter(configManager, storage)
//
//    @Provides
//    @Singleton
//    fun provideVlessFormatter(
//        configManager: ConfigManager,
//        storage: KeyValueStorage
//    ): VlessFormatter =
//        VlessFormatter(configManager, storage)
//
//    @Provides
//    @Singleton
//    fun provideVmessFormatter(
//        configManager: ConfigManager,
//        storage: KeyValueStorage,
//        gson: Gson
//    ): VmessFormatter =
//        VmessFormatter(configManager, storage, gson)
//
//    @Provides
//    @Singleton
//    fun provideWireguardFormatter(
//        configManager: ConfigManager,
//        storage: KeyValueStorage,
//    ): WireguardFormatter =
//        WireguardFormatter(configManager, storage)
}