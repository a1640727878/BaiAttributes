package sky_bai.sponge.BaiAttributes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;

public class EventListener {
	final private ScriptEngine JavaScriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

	@Listener
	public void onDamageEntity(DamageEntityEvent event) {
		if (((DamageSource) event.getSource()).getType() != DamageTypes.ATTACK  & ((DamageSource) event.getSource()).getType() != DamageTypes.PROJECTILE) {
			return;
		}
		double damage = 0;
		for (String string : BaiConfig.JavaScriptMap.keySet()) {
			damage = (double) this.runJavaScript(string,"setDamage", damage, event);
		}
		if (damage < 0) {
			damage = 0;
		}
		event.setBaseDamage(damage);
	}

	private Object runJavaScript(String javaScriptName, String method, Double damage, Event event) {
		Object returnValue = null;
		try {
			JavaScriptEngine.eval(new FileReader(BaiConfig.JavaScriptMap.get(javaScriptName)));
			Invocable invocable = (Invocable) JavaScriptEngine;
			returnValue = invocable.invokeFunction(method, damage, event);
			return returnValue;
		} catch (ScriptException | FileNotFoundException | NoSuchMethodException e) {
			return returnValue;
		}
	}
}
