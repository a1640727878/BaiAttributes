package sky_bai.sponge.BaiAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class BaiCommand implements CommandCallable {

	@Override
	public CommandResult process(CommandSource source, String arguments) throws CommandException {
		if (arguments.startsWith("item")) {
			if (source instanceof Player == false) {
				source.sendMessage(Text.of("§l[BaiAttributes]§r当前命令只能由玩家执行"));
				return CommandResult.empty();
			}
			if (arguments.startsWith("item info")) {
				Player player = (Player) source;
				Optional<Object> a1 = player.getEquipped(EquipmentTypes.MAIN_HAND).get().toContainer().get(DataQuery.of("UnsafeData", "BaiId"));
				String a2 = "物品内置标签: null";
				if (a1.isPresent()) {
					a2 = "物品标签: " + a1.get();
				}
				player.sendMessage(Text.of("§l[BaiAttributes]§r" + a2));
				return CommandResult.success();
			}
			if (arguments.startsWith("item set")) {
				Player player = (Player) source;
				DataContainer a1 = player.getItemInHand(HandTypes.MAIN_HAND).get().toContainer();
				String a2 = arguments.replace("item set ", "");
				player.setItemInHand(HandTypes.MAIN_HAND, ItemStack.builder().fromContainer(a1.set(DataQuery.of("UnsafeData", "BaiId"), a2)).build());
				source.sendMessage(Text.of("§l[BaiAttributes]§r物品标签设置为 " + a2));
				return CommandResult.success();
			}
			return CommandResult.empty();
		} else if (arguments.startsWith("reload")) {
			BaiAttributes.setConfig();
			source.sendMessage(Text.of("§l[BaiAttributes]§r插件重载完成"));
			return CommandResult.success();
		}
		return CommandResult.empty();
	}

	@Override
	public boolean testPermission(CommandSource source) {
		return source.hasPermission("BaiAttributes.plugin");
	}

	@Override
	public Optional<Text> getShortDescription(CommandSource source) {
		return Optional.of(Text.of());
	}

	@Override
	public Optional<Text> getHelp(CommandSource source) {
		return Optional.of(Text.of());
	}

	@Override
	public Text getUsage(CommandSource source) {
		return Text.of();
	}

	@Override
	public List<String> getSuggestions(CommandSource source, String arguments, Location<World> targetPosition) throws CommandException {
		List<String> argumentsList = new ArrayList<String>();
		if (arguments.length() == 0) {
			argumentsList.clear();
			argumentsList.add("item");
			argumentsList.add("reload");
			return argumentsList;
		} else if (arguments.contains("item ")) {
			if (arguments.contains("item set ")) {
				argumentsList.clear();
				argumentsList.addAll(BaiConfig.itemConfigMap.keySet());
				return argumentsList;
			}
			argumentsList.clear();
			argumentsList.add("info");
			argumentsList.add("set");
			return argumentsList;
		}
		return argumentsList;
	}
}
