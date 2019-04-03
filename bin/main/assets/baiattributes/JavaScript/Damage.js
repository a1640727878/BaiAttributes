PlayerAttributes = Java.type("sky_bai.sponge.BaiAttributes.PlayerAttributes");

function setDamage(damage,event)  {
	entity = event.getSource().getSource();
	if (entity instanceof Java.type("org.spongepowered.api.entity.projectile.arrow.Arrow")) {entity = event.getSource().getIndirectSource()}
	if (entity instanceof Java.type("org.spongepowered.api.entity.Equipable") == false) {return event.getBaseDamage()}
	if (new PlayerAttributes(entity).getItemAttributes().get("Damage") == 0) {return event.getBaseDamage()}
	if (new PlayerAttributes(entity).getItemAttributesOne("Main_Hand").get("Damage") == 0) {return damage + event.getBaseDamage() + PlayerAttributes.getItemLore(entity).get("Damage")}
	return damage + new PlayerAttributes(entity).getItemAttributes().get("Damage");
}