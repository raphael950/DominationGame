package net.hydramc.domination.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    @EventHandler public void onWeather(WeatherChangeEvent event) {

        event.setCancelled(event.toWeatherState());

    }

}
