PlayerAttributes = Java.type("sky_bai.sponge.BaiAttributes.PlayerAttributes");
Keys = Java.type("org.spongepowered.api.data.key.Keys")
function setDamage(damage,event)  {
	entity = event.getSource().getSource();
	if (entity instanceof Java.type("org.spongepowered.api.entity.living.player.Player")) {entity.offer(Keys.HEALTH, entity.health().get() + (damage * (new PlayerAttributes(entity).getItemAttributes().get("Vampire") / 100)))}
	return damage
}