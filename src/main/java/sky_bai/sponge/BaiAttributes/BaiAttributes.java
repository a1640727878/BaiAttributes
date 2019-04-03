package sky_bai.sponge.BaiAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "baiattributes", name = "BaiAttributes-Sponge")
public class BaiAttributes {
	final static Logger logger = LoggerFactory.getLogger("BaiAttributes");

	@Listener
	public void onGamePreInitialization(GamePreInitializationEvent event) {
		logger.info("插件开始加载...");
		Sponge.getEventManager().registerListeners(this, new EventListener());
		this.setConfigPath();
		this.setConfigFile();
		BaiAttributes.setConfig();
		Sponge.getCommandManager().register(this, new BaiCommand(), "BaiAttributes", "BaiA");
	}

	final private void setConfigFile() {
		
			try {
				if (BaiConfig.configPath.toFile().exists() == false) {
					Sponge.getAssetManager().getAsset(this, "JavaScript/Crit.js").get().copyToDirectory(BaiConfig.JavaScriptPath, false, true);
					Sponge.getAssetManager().getAsset(this, "JavaScript/Damage.js").get().copyToDirectory(BaiConfig.JavaScriptPath, false, true);
					Sponge.getAssetManager().getAsset(this, "JavaScript/None.js").get().copyToDirectory(BaiConfig.JavaScriptPath, false, true);
					Sponge.getAssetManager().getAsset(this, "JavaScript/Defense.js").get().copyToDirectory(BaiConfig.JavaScriptPath, false, true);
					Sponge.getAssetManager().getAsset(this, "JavaScript/Vampire.js").get().copyToDirectory(BaiConfig.JavaScriptPath, false, true);
					Sponge.getAssetManager().getAsset(this, "Item/item1.yml").get().copyToDirectory(BaiConfig.itemPath, false, true);
					Sponge.getAssetManager().getAsset(this, "Item/item2.yml").get().copyToDirectory(BaiConfig.itemPath, false, true);
				}
				Sponge.getAssetManager().getAsset(this, "config.yml").get().copyToDirectory(BaiConfig.BaiAttributesConfigPath, false, true);
			} catch (Exception e1) {
			}
		
	}

	final private void setConfigPath() {
		BaiConfig.BaiAttributesConfigPath = Sponge.getConfigManager().getPluginConfig(this).getDirectory().resolveSibling("BaiAttributes");
		BaiConfig.configPath = BaiConfig.BaiAttributesConfigPath.resolve("config.yml");
		BaiConfig.itemPath = BaiConfig.BaiAttributesConfigPath.resolve("Item");
		BaiConfig.JavaScriptPath = BaiConfig.BaiAttributesConfigPath.resolve("JavaScript");
	}

	final static public void setConfig() {
		BaiConfig bConfig = new BaiConfig();
		BaiConfig.configMap.clear();
		BaiConfig.configMap.put("config", bConfig.getConfig(BaiConfig.configPath));
		bConfig.getItemConfig();
		bConfig.getJavaScript();
	}

}
