package com.verdantartifice.primalmagic.common.network;

import com.verdantartifice.primalmagic.PrimalMagic;
import com.verdantartifice.primalmagic.common.network.packets.IMessageToClient;
import com.verdantartifice.primalmagic.common.network.packets.IMessageToServer;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncAttunementsPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncCooldownsPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncKnowledgePacket;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncProgressPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncResearchFlagsPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.SyncStatsPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.TileToClientPacket;
import com.verdantartifice.primalmagic.common.network.packets.data.TileToServerPacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.WandPoofPacket;
import com.verdantartifice.primalmagic.common.network.packets.misc.AnalysisActionPacket;
import com.verdantartifice.primalmagic.common.network.packets.misc.CycleActiveSpellPacket;
import com.verdantartifice.primalmagic.common.network.packets.misc.ScanItemPacket;
import com.verdantartifice.primalmagic.common.network.packets.misc.ScanPositionPacket;
import com.verdantartifice.primalmagic.common.network.packets.spellcrafting.SetSpellComponentPropertyPacket;
import com.verdantartifice.primalmagic.common.network.packets.spellcrafting.SetSpellComponentTypeIndexPacket;
import com.verdantartifice.primalmagic.common.network.packets.spellcrafting.SetSpellNamePacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.ManaSparklePacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.SpellBoltPacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.SpellImpactPacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.SpellTrailPacket;
import com.verdantartifice.primalmagic.common.network.packets.fx.TeleportArrivalPacket;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

/**
 * Handler class for processing packets.  Responsible for all custom communication between the client and the server.
 * 
 * @author Daedalus4096
 */
public class PacketHandler {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(PrimalMagic.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    
    public static void registerMessages() {
        int disc = 0;
        
        INSTANCE.registerMessage(disc++, SyncKnowledgePacket.class, SyncKnowledgePacket::encode, SyncKnowledgePacket::decode, SyncKnowledgePacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SyncProgressPacket.class, SyncProgressPacket::encode, SyncProgressPacket::decode, SyncProgressPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SyncResearchFlagsPacket.class, SyncResearchFlagsPacket::encode, SyncResearchFlagsPacket::decode, SyncResearchFlagsPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, WandPoofPacket.class, WandPoofPacket::encode, WandPoofPacket::decode, WandPoofPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, ManaSparklePacket.class, ManaSparklePacket::encode, ManaSparklePacket::decode, ManaSparklePacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, ScanItemPacket.class, ScanItemPacket::encode, ScanItemPacket::decode, ScanItemPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, ScanPositionPacket.class, ScanPositionPacket::encode, ScanPositionPacket::decode, ScanPositionPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, AnalysisActionPacket.class, AnalysisActionPacket::encode, AnalysisActionPacket::decode, AnalysisActionPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SyncCooldownsPacket.class, SyncCooldownsPacket::encode, SyncCooldownsPacket::decode, SyncCooldownsPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, CycleActiveSpellPacket.class, CycleActiveSpellPacket::encode, CycleActiveSpellPacket::decode, CycleActiveSpellPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SetSpellNamePacket.class, SetSpellNamePacket::encode, SetSpellNamePacket::decode, SetSpellNamePacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SetSpellComponentTypeIndexPacket.class, SetSpellComponentTypeIndexPacket::encode, SetSpellComponentTypeIndexPacket::decode, SetSpellComponentTypeIndexPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SetSpellComponentPropertyPacket.class, SetSpellComponentPropertyPacket::encode, SetSpellComponentPropertyPacket::decode, SetSpellComponentPropertyPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SpellTrailPacket.class, SpellTrailPacket::encode, SpellTrailPacket::decode, SpellTrailPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SpellImpactPacket.class, SpellImpactPacket::encode, SpellImpactPacket::decode, SpellImpactPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, TileToClientPacket.class, TileToClientPacket::encode, TileToClientPacket::decode, TileToClientPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, TileToServerPacket.class, TileToServerPacket::encode, TileToServerPacket::decode, TileToServerPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, TeleportArrivalPacket.class, TeleportArrivalPacket::encode, TeleportArrivalPacket::decode, TeleportArrivalPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SpellBoltPacket.class, SpellBoltPacket::encode, SpellBoltPacket::decode, SpellBoltPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SyncStatsPacket.class, SyncStatsPacket::encode, SyncStatsPacket::decode, SyncStatsPacket.Handler::onMessage);
        INSTANCE.registerMessage(disc++, SyncAttunementsPacket.class, SyncAttunementsPacket::encode, SyncAttunementsPacket::decode, SyncAttunementsPacket.Handler::onMessage);
    }
    
    public static void sendToServer(IMessageToServer message) {
        // Send a packet from a client to the server
        INSTANCE.sendToServer(message);
    }
    
    public static void sendToPlayer(IMessageToClient message, ServerPlayerEntity player) {
        // Send a message from the server to a specific player's client
        INSTANCE.sendTo(message, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
    }
    
    public static void sendToAllAround(IMessageToClient message, DimensionType dimension, BlockPos center, double radius) {
        // Send a message to the clients of all players within a given distance of the given world position
        INSTANCE.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(center.getX() + 0.5D, center.getY() + 0.5D, center.getZ() + 0.5D, radius, dimension)), message);
    }
}
