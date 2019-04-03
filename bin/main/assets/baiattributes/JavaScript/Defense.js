PlayerAttributes = Java.type("sky_bai.sponge.BaiAttributes.PlayerAttributes");

function setDamage(damage,event)  {
	entity = event.getTargetEntity();
	if (entity instanceof Java.type("org.spongepowered.api.entity.Equipable") == false) {return damage};
	return damage - new PlayerAttributes(entity).getItemAttributes().get("Defense");
}