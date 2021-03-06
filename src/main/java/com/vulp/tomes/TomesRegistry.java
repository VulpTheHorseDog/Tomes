package com.vulp.tomes;

import com.vulp.tomes.blocks.GobletOfHeartsBlock;
import com.vulp.tomes.blocks.tile.GobletOfHeartsTileEntity;
import com.vulp.tomes.client.renderer.entity.layers.StarryFormLayer;
import com.vulp.tomes.client.renderer.entity.renderers.SpectralSteedRenderer;
import com.vulp.tomes.client.renderer.entity.renderers.TamedSpiderRenderer;
import com.vulp.tomes.client.renderer.entity.renderers.WildWolfRenderer;
import com.vulp.tomes.client.renderer.entity.renderers.WitheringStenchRenderer;
import com.vulp.tomes.client.renderer.tile.GobletOfHeartsRenderer;
import com.vulp.tomes.enchantments.EnchantmentTypes;
import com.vulp.tomes.init.*;
import com.vulp.tomes.items.DebugItem;
import com.vulp.tomes.items.HiddenDescriptorItem;
import com.vulp.tomes.items.TomeItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Collection;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TomesRegistry {

    public static final ItemGroup TOMES_TAB = new ItemGroup("tomes") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.archaic_tome);
        }
    }.setRelevantEnchantmentTypes(EnchantmentTypes.ARCHAIC_TOME, EnchantmentTypes.LIVING_TOME, EnchantmentTypes.CURSED_TOME);

    @SubscribeEvent
    public static void itemRegistryEvent(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                ItemInit.goblet_of_hearts = new BlockItem(BlockInit.goblet_of_hearts, new Item.Properties().group(TOMES_TAB)).setRegistryName(location("goblet_of_hearts")),

                ItemInit.archaic_tome = new TomeItem(new Item.Properties().group(TOMES_TAB).maxDamage(1000)).setRegistryName(location("archaic_tome")),
                ItemInit.living_tome = new TomeItem(new Item.Properties().group(TOMES_TAB).maxDamage(1000)).setRegistryName(location("living_tome")),
                ItemInit.cursed_tome = new TomeItem(new Item.Properties().group(TOMES_TAB).maxDamage(1000)).setRegistryName(location("cursed_tome")),

                ItemInit.ancient_heart = new HiddenDescriptorItem(new Item.Properties().group(TOMES_TAB).food(FoodInit.archaic_heart)).setRegistryName(location("ancient_heart")),
                ItemInit.beating_heart = new HiddenDescriptorItem(new Item.Properties().group(TOMES_TAB).food(FoodInit.beating_heart)).setRegistryName(location("beating_heart")),
                ItemInit.sweet_heart = new HiddenDescriptorItem(new Item.Properties().group(TOMES_TAB).food(FoodInit.sweet_heart)).setRegistryName(location("sweet_heart")),

                ItemInit.archaic_tome_open = new Item(new Item.Properties()).setRegistryName(location("archaic_tome_open")),
                ItemInit.living_tome_open = new Item(new Item.Properties()).setRegistryName(location("living_tome_open")),
                ItemInit.cursed_tome_open = new Item(new Item.Properties()).setRegistryName(location("cursed_tome_open")),

                ItemInit.debug_tome = new DebugItem(new Item.Properties()).setRegistryName(location("debug_tome"))
        );

        Tomes.LOGGER.info("Items Registered!");
    }

    @SubscribeEvent
    public static void blockRegistryEvent(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                BlockInit.goblet_of_hearts = new GobletOfHeartsBlock(AbstractBlock.Properties.create(Material.IRON).setRequiresTool().hardnessAndResistance(3.0F).sound(SoundType.METAL).notSolid()).setRegistryName(location("goblet_of_hearts"))
        );

        Tomes.LOGGER.info("Blocks Registered!");
    }

    @SubscribeEvent
    public static void tileRegistryEvent(final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                TileInit.goblet_of_hearts = TileInit.register("goblet_of_hearts", TileEntityType.Builder.create(GobletOfHeartsTileEntity::new, BlockInit.goblet_of_hearts))
        );

        Tomes.LOGGER.info("Tiles Registered!");
    }

    @SubscribeEvent
    public static void enchantRegistryEvent(final RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(
                EnchantmentInit.self_propulsion.setRegistryName(location("self_propulsion")),
                EnchantmentInit.force_of_wind.setRegistryName(location("force_of_wind")),
                EnchantmentInit.strike_from_above.setRegistryName(location("strike_from_above")),
                EnchantmentInit.astral_travel.setRegistryName(location("astral_travel")),
                EnchantmentInit.dying_knowledge.setRegistryName(location("dying_knowledge")),
                EnchantmentInit.linguist.setRegistryName(location("linguist")),
                EnchantmentInit.airy_protection.setRegistryName(location("airy_protection")),
                EnchantmentInit.cloudstep.setRegistryName(location("cloudstep")),
                EnchantmentInit.everchanging_skies.setRegistryName(location("everchanging_skies")),

                EnchantmentInit.lifebringer.setRegistryName(location("lifebringer")),
                EnchantmentInit.beast_tamer.setRegistryName(location("beast_tamer")),
                EnchantmentInit.wild_aid.setRegistryName(location("wild_aid")),
                EnchantmentInit.nurturing_roots.setRegistryName(location("nurturing_roots")),
                EnchantmentInit.advantageous_growth.setRegistryName(location("advantageous_growth")),
                EnchantmentInit.forest_affinity.setRegistryName(location("forest_affinity")),
                EnchantmentInit.molding_lands.setRegistryName(location("molding_lands")),

                EnchantmentInit.mind_bender.setRegistryName(location("mind_bender")),
                EnchantmentInit.ghostly_steed.setRegistryName(location("ghostly_steed")),
                EnchantmentInit.withering_stench.setRegistryName(location("withering_stench")),
                EnchantmentInit.covens_rule.setRegistryName(location("covens_rule")),
                EnchantmentInit.rotten_heart.setRegistryName(location("rotten_heart")),
                EnchantmentInit.nocturnal.setRegistryName(location("nocturnal")),
                EnchantmentInit.dark_age.setRegistryName(location("dark_age"))
        );

        EnchantmentInit.setupDescriptions();
        Tomes.LOGGER.info("Enchantments Registered!");
    }

    @SubscribeEvent
    public static void containerRegistryEvent(final RegistryEvent.Register<ContainerType<?>> event) {
        event.getRegistry().registerAll(
                ContainerInit.witch_merchant_container.setRegistryName(location("witch_merchant_container"))
        );
        Tomes.LOGGER.info("Containers Registered!");
    }

    @SubscribeEvent
    public static void recipeRegistryEvent(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
        event.getRegistry().registerAll(
                RecipeInit.goblet_of_hearts_serializer.setRegistryName(location("goblet_crafting"))
        );
        Tomes.LOGGER.info("Recipes Registered!");
    }

    @SubscribeEvent
    public static void entityRegistryEvent(final RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                EntityInit.wild_wolf,
                EntityInit.tamed_spider,
                EntityInit.spectral_steed,
                EntityInit.withering_stench
        );

        Tomes.LOGGER.info("Entities Registered!");
    }

    @SubscribeEvent
    public static void effectRegistryEvent(final RegistryEvent.Register<Effect> event) {
        event.getRegistry().registerAll(
                EffectInit.light_footed.setRegistryName(location("light_footed")),
                EffectInit.mind_bend.setRegistryName(location("mind_bend")),
                EffectInit.leaden_veins.setRegistryName(location("leaden_veins")),
                EffectInit.fire_fist.setRegistryName(location("fire_fist")),
                EffectInit.antidotal.setRegistryName(location("antidotal")),
                EffectInit.multi_jump.setRegistryName(location("multi_jump")),
                EffectInit.starry_form.setRegistryName(location("starry_form"))
                );

        Tomes.LOGGER.info("Enchantments Registered!");
    }

    @SubscribeEvent
    public static void onParticleRegistry(final RegistryEvent.Register<ParticleType<?>> event) {
        event.getRegistry().registerAll
            (
                ParticleInit.spirit_flame.setRegistryName(location("spirit_flame")),
                ParticleInit.withering_stench.setRegistryName(location("withering_stench")),
                ParticleInit.wind_deflect.setRegistryName(location("wind_deflect")),
                ParticleInit.web_net.setRegistryName(location("web_net")),
                ParticleInit.hex.setRegistryName(location("hex")),
                ParticleInit.wild_wolf_despawn.setRegistryName(location("wild_wolf_despawn")),
                ParticleInit.spectral_steed_despawn.setRegistryName(location("spectral_steed_despawn")),
                ParticleInit.living_wisp.setRegistryName(location("living_wisp"))
            );

        Tomes.LOGGER.info("Particles Registered!");
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onTextureStitchEvent(TextureStitchEvent.Pre event) {
        if (event.getMap().getTextureLocation() == AtlasTexture.LOCATION_BLOCKS_TEXTURE) {
            event.addSprite(GobletOfHeartsRenderer.texture);
        }
        Tomes.LOGGER.info("Textures Stitched!");
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers(FMLClientSetupEvent event) {
        // These are here for a dev build so that I can grab my account details for server testing. Commented out so they're here in case I need them again.
        // UNDER NO CIRCUMSTANCES SHOULD A BUILD PUBLICLY COME OUT WITH THESE UNCOMMENTED! IF IT DOES, LET ME KNOW ASAP!
        /*System.out.println("ACCESS_TOKEN "+ Minecraft.getInstance().getSession().getToken());
        System.out.println("(session)UUID "+ Minecraft.getInstance().getSession().getSessionID());
        System.out.println("(player)UUID "+ Minecraft.getInstance().getSession().getPlayerID());*/
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(BlockInit.goblet_of_hearts, RenderType.getCutout());
                });

        ClientRegistry.bindTileEntityRenderer(TileInit.goblet_of_hearts, GobletOfHeartsRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.wild_wolf, new WildWolfRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.tamed_spider, new TamedSpiderRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.spectral_steed, new SpectralSteedRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.withering_stench, new WitheringStenchRenderer.RenderFactory());

        for (EntityRenderer<?> renderer : Minecraft.getInstance().getRenderManager().renderers.values()) {
            if (renderer instanceof LivingRenderer) {
                // Unchecked because for some reason it hates using completely correct parameters.
                ((LivingRenderer<?, ?>) renderer).addLayer(new StarryFormLayer((LivingRenderer<?, ?>) renderer));
            }
        }

        for (PlayerRenderer renderer : Minecraft.getInstance().getRenderManager().getSkinMap().values()) {
            renderer.addLayer(new StarryFormLayer<>(renderer));
        }
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(Tomes.MODID, name);
    }

}
