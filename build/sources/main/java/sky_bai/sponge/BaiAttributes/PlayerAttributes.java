package sky_bai.sponge.BaiAttributes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.entity.Equipable;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

public class PlayerAttributes {
	private Set<String> AttributesType = BaiConfig.JavaScriptMap.keySet();
	public Map<String, Integer> playerAttributes = new LinkedHashMap<>();
	public Map<String, ItemStack> playerEquipage = new HashMap<>();
	
	public PlayerAttributes(Equipable equipable) {
		for (String type : AttributesType) {
			playerAttributes.put(type, 0);
		}
		this.setPlayerEquipage(equipable);
	}
	
	private void setPlayerEquipage(Equipable equipable) {
		// 主手物品
		playerEquipage.put("Main_Hand", equipable.getEquipped(EquipmentTypes.MAIN_HAND).get());
		// 副手物品
		playerEquipage.put("Off_Hand", equipable.getEquipped(EquipmentTypes.OFF_HAND).get());
		// 头盔
		playerEquipage.put("Headwear", equipable.getEquipped(EquipmentTypes.HEADWEAR).get());
		// 胸甲
		playerEquipage.put("Chestplate", equipable.getEquipped(EquipmentTypes.CHESTPLATE).get());
		// 护腿
		playerEquipage.put("Leggings", equipable.getEquipped(EquipmentTypes.LEGGINGS).get());
		// 靴子
		playerEquipage.put("Boots", equipable.getEquipped(EquipmentTypes.BOOTS).get());
	}
	
	public Map<String, Integer> getItemAttributesOne(String string) {
		Optional<Object> itemBaiIdOptional = playerEquipage.get(string).toContainer().get(DataQuery.of("UnsafeData","BaiId"));
		String tagString = "";
		if (itemBaiIdOptional.isPresent()) {
			tagString = (String) itemBaiIdOptional.get();
		}
		if (BaiConfig.itemConfigMap.containsKey(tagString)) {
			for (String string2 : AttributesType) {
				playerAttributes.put(string2, playerAttributes.get(string2)+BaiConfig.itemConfigMap.get(tagString).getNode(string2).getInt(0));
			}
		}
		return playerAttributes;
	}
	
	public Map<String, Integer> getItemAttributes() {
		for (String string : playerEquipage.keySet()) {
			Optional<Object> itemBaiIdOptional = playerEquipage.get(string).toContainer().get(DataQuery.of("UnsafeData","BaiId"));
			String tagString = "";
			if (itemBaiIdOptional.isPresent()) {
				tagString = (String) itemBaiIdOptional.get();
			}
			if (BaiConfig.itemConfigMap.containsKey(tagString)) {
				for (String string2 : AttributesType) {
					playerAttributes.put(string2, playerAttributes.get(string2)+BaiConfig.itemConfigMap.get(tagString).getNode(string2).getInt(0));
				}
			}
		}
		return playerAttributes;
	}
}
