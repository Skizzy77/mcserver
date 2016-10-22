package com.broaderator.mcserver.core.modules.security;

public interface Policy {

    default Event onBlockChange(Event bcEvent) {
        return bcEvent;
    }

    default Event onPlayerInteract(Event piEvent) {
        return piEvent;
    }

    default Event onTreeGrowth(Event tgEvent) {
        // also handles Spawn Protection
        return tgEvent;
    }

    default Event onEntityDamage(Event edEvent) {
        return edEvent;
    }

    default Event onTntObtain(Event toEvent) {
        return toEvent;
    }

    default Event onTntIgnite(Event tiEvent) {
        return tiEvent;
    }

    default Event onItemDrop(Event idEvent) {
        return idEvent;
    }

    default Event onPlayerChat(Event pcEvent) {
        return pcEvent;
    }

    default Event onMobSpawn(Event msEvent) {
        return msEvent;
    }

    default Event onLiquidPlace(Event lpEvent) {
        return lpEvent;
    }

    default Event onFireIgnite(Event fiEvent) {
        return fiEvent;
    }

    // unfinished
}
