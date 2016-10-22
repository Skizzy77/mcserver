package com.broaderator.mcserver.core.modules.security;

public class Security {
    /*
    What are some security policies that we can implement in our application?
    > Block change (different types)
        > This includes /setblock!
    > Interaction (button press)
        > Sapling growth
    > Damage (different types)
    > TNT usage
    > Emitters (Dropper and Dispenser)
    > Creepers (similar to TNT)
    > Block change logging (!!)
    > Chat spam protection
    > Mob spawn
    > Lava/Water usage
    > Spawn protection
    > Fire (disable fire spread)
    > Map corruption (limit map size)
    > Redstone, Minecarts, Boats causing lag
    > Disguises
    > Drop spamming / join bots (metaquota)
    > SecuredRegions

    It: should also store policy options in namespaces

    !Note! Command permission control is left to CommandWrapper, not this.

    >> Git versioning control for world (backup)

    What is a security policy?
    A security policy is a law that dictates whether a bukkit event is permitted or not, and/or edits its attributes.

    Process
    -> Bukkit event is passed to this
    -> Event is passed along Policy's for attribute editing and event cancelling
    -> Event output is passed on to action (or cancelled and discarded)
     */
}
