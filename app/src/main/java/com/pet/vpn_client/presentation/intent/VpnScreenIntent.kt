package com.pet.vpn_client.presentation.intent

sealed interface VpnScreenIntent {
    object ToggleConnection : VpnScreenIntent
    object SwitchVpnProxy : VpnScreenIntent
    object TestConnection : VpnScreenIntent
    object RestartConnection : VpnScreenIntent
    object ImportConfigFromClipboard : VpnScreenIntent
    data class DeleteItem(val id: String) : VpnScreenIntent
    object RefreshItemList : VpnScreenIntent
}