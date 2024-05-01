package mods.Hileb.loadingtips.core;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import mods.Hileb.loadingtips.render.EarlyFontRender;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @Project LoadingTipsFixer
 * @Author Hileb
 * @Date 2024/5/1 11:31
 **/
@IFMLLoadingPlugin.Name("LoadingTipsFixer")
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
public class LoadingTipsFixer implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                "mods.Hileb.loadingtips.core.LoadingTipTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "mods.Hileb.loadingtips.core.LoadingTipsFixer$Container";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static class Container extends DummyModContainer {
        public Container(){
            super(new ModMetadata());
            ModMetadata metadata = this.getMetadata();
            metadata.modId = "loadingtips";
            metadata.name = "LoadingTipsFixer";
            metadata.version = "1.0.0";
            metadata.authorList.add("Hileb");
        }

        @Override
        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            return true;
        }

        @Subscribe
        public void clearLoadingFixer(FMLLoadCompleteEvent event){
            EarlyFontRender.clear();
        }
    }
}
