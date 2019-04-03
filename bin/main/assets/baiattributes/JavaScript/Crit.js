PlayerAttributes = Java.type("sky_bai.sponge.BaiAttributes.PlayerAttributes");
Random = Java.type("java.util.Random").class.newInstance();
function setDamage(damage,event)  {
	entity = event.getSource().getSource();
	integer = Random.nextInt(100);
	if (entity instanceof Java.type("org.spongepowered.api.entity.projectile.arrow.Arrow")) {entity = event.getSource().getIndirectSource()}
	if (entity instanceof Java.type("org.spongepowered.api.entity.Equipable") == false) {return damage}
	if (new PlayerAttributes(entity).getItemAttributes().get("CritDamage") == 0) {return damage}
	if (new PlayerAttributes(entity).getItemAttributes().get("Crit") == 0) {return damage}
	if (new PlayerAttributes(entity).getItemAttributes() <= integer) {return damage}
	if (new PlayerAttributes(entity).getItemAttributesOne("Main_Hand").get("CritDamage") == 0) {return damage + (event.getBaseDamage() * (new PlayerAttributes(entity).getItemAttributes().get("CritDamage") / 100))}
	return damage
}