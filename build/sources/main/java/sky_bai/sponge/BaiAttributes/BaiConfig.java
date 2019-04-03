package sky_bai.sponge.BaiAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

public class BaiConfig {
	static Path BaiAttributesConfigPath;
	static Path configPath;
	static Path itemPath;
	static Path JavaScriptPath;
	static Map<String, ConfigurationNode> configMap = new HashMap<>();
	static Map<String, ConfigurationNode> itemConfigMap = new HashMap<>();
	static Map<String, File> JavaScriptMap = new HashMap<>();

	protected ConfigurationNode getConfig(Path path) {
		try {
			return YAMLConfigurationLoader.builder().setPath(path).build().load();
		} catch (IOException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected void getItemConfig() {
		itemConfigMap.clear();
		String[] itemConfigs = itemPath.toFile().list();
		for (int i = 0; i < itemConfigs.length; i++) {
			Map<String, Object> itemConfig = ((HashMap<String, Object>) getConfig(itemPath.resolve(itemConfigs[i])).getValue());
			for (String string : itemConfig.keySet()) {
				itemConfigMap.put(string, getConfig(itemPath.resolve(itemConfigs[i])).getNode(string));
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void getJavaScript() {
		JavaScriptMap.clear();
		Map<String, String> javaScriptMap = (HashMap<String, String>)configMap.get("config").getNode("DamageAttributes").getValue();
		for (String string : javaScriptMap.keySet()) {
			JavaScriptMap.put(string, JavaScriptPath.resolve(javaScriptMap.get(string)).toFile());
		}
	}
}
