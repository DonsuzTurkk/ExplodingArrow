package tr.donsuzturk.explodingarrow;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class ExplodingArrow extends JavaPlugin implements Listener
{
    double damage;

    public ExplodingArrow() {
        this.damage = 1.5;
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        if (!new File(this.getDataFolder(), "config.yml").exists()) {
            this.saveDefaultConfig();
        }
        this.damage = this.getConfig().getDouble("Settings.Damage");
    }

    @EventHandler
    public void projectileHit(final ProjectileHitEvent event) {
        if (event.getEntityType() == EntityType.ARROW) {
            final Location loc = event.getEntity().getLocation();
            Objects.requireNonNull(loc.getWorld()).createExplosion(loc, (float)this.damage);
        }
    }
}
