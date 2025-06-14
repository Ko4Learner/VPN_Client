package com.pet.vpn_client.domain.interfaces

import com.pet.vpn_client.domain.models.ConfigProfileItem

interface SettingsManager {
    fun getHttpPort(): Int
    fun getSocksPort(): Int
    fun getRemoteDnsServers(): List<String>
    fun getDomesticDnsServers(): List<String>
    fun getServerViaRemarks(remarks: String?): ConfigProfileItem?
    fun getVpnDnsServers(): List<String>
    fun getDelayTestUrl(second: Boolean = false): String
}