package tw.waterballsa.utopia.commons.config

import ch.qos.logback.core.util.OptionHelper.getEnv
import me.jakejmattson.discordkt.annotations.Service
import tw.waterballsa.utopia.commons.utils.loadProperties

private const val s = "DEPLOYMENT_ENV"

@Service
class WsaDiscordProperties() {
    var guildId: ULong
    var unlockEntryMessageId: ULong
    var selfIntroChannelId: ULong
    var wsaGuestRoleId: ULong
    var wsaCitizenRoleId: ULong

    init {
        val env = getEnv("DEPLOYMENT_ENV") ?: throw IllegalStateException("DEPLOYMENT_ENV environment variable is not set")
        val properties = when (env) {
            "beta" -> loadProperties("wsa.beta.properties")
            "prod" -> loadProperties("wsa.prod.properties")
            else -> throw IllegalArgumentException("doesn't support the env name ${env}.")
        }

        guildId = properties.getProperty("guild-id").toULong()
        unlockEntryMessageId = properties.getProperty("unlock-entry-message-id").toULong()
        selfIntroChannelId = properties.getProperty("self-intro-channel-id").toULong()
        wsaGuestRoleId = properties.getProperty("wsa-guest-role-id").toULong()
        wsaCitizenRoleId = properties.getProperty("wsa-citizen-role-id").toULong()
    }
}
