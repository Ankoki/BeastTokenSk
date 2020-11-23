# BeastTokenSk
BeastTokenSk is a Skript addon which allows users to utilise the BeastTokensAPI and control how tokens are used in Skript.  
This requires BeastTokens v3.9.8, if any earlier versions are detected, BeastTokenSk will disabled.

# How to use BeastTokenSk
At this time, BeastTokenSk has a total of 5 Events, 2 Expressions and 1 Condition.  
  
BeastTokenSk depends on Skript and BeastTokens, and cannot function without them. Here are the links:
github.com/SkriptLang/Skript/releases
https://www.spigotmc.org/resources/beasttokens-multishop-mob-and-block-token-drops.20806/  
  
To use, place BeastTokens, Skript, and BeastTokenSk in the plugins folder in your server, and your good to start Skripting!

# Events
**Block Token Drop Event**  
Fired when a block drops BeastTokens.  
Pattern: "*[beast[ ]token[[']s]] block [beast[ ]]token[s] (drop|dropping)*"   
Since: 1.0  
***Example:***
```
on block token drop:
    if player has permission "tokens.gain":
        send "You broke %event-block% and recieved %event-tokens% tokens!"
    else:
        cancel event
        send "You cannot unlock tokens yet!"
```  
  
**Farming Token Drop Event**  
Fired when a crop drops BeastTokens.  
Pattern: "*[beast[ ]token[[']s]] (farm[ing]|crop[s]) [beast[ ]]token[s] (drop|dropping)*"  
Since: 1.0  
***Example:***
```
on farming token drop:
    if player has permission "tokens.gain":
        send "You broke %event-block% and recieved %event-tokens% tokens!"
    else:
        cancel event
        send "You cannot unlock tokens yet!"
```  
  
**Mob Token Drop Event**  
Fired when a monster drops BeastTokens.  
Pattern: "*[beast[ ]token[[']s]] (mob[s]|monster[s]) [beast[ ]]token[s] (drop|dropping)*"  
Since: 1.0  
***Example:***
```
on mob token drop:
    if player has permission "tokens.gain":
        send "You killed %event-entity% and recieved %event-tokens% tokens!" 
    else:
        cancel event
        send "You cannot unlock tokens yet!"
```  
  
**Mythic Mob Token Drop Event**  
Fired when a MythicMob drops BeastTokens.  
This requires MythicMobs.  
Pattern: "*mythic (mob[[']s]|monster[[']s]) [beast[ ]]token[[']s] drop*"  
Since: 1.0  
***Example:***  
```
on mythic mob token drop:
    if player has permission "tokens.gain":
        send "You killed %event-entity% and recieved %event-tokens% tokens!"  
    else:
        cancel event
        send "You cannot unlock tokens yet!"
```  
  
**Token Redeem Event**  
Fired when a player uses an item to redeem BeastTokens.  
Pattern: "*[beast[ ]token[[']s]] token[[']s] redeem*"  
Since: 1.0  
***Example:***  
```
on token redeem:
    if event-item is paper:
        send "You used a paper withdraw slip!"
    else:
        cancel event
        send "You have used an illegal slip!"
```  
  
# Expressions  
**Player Tokens**  
Gets the amount of players BeastTokens.  
Pattern: "*(%player%'s [beast][ ]token[s]|[beast][ ]token[']s of %player%)*"  
Since: 1.0  
***Examples:***  
```
on death:
    remove 100 from player's tokens
    send "You have lost 100 tokens!"
```  
  
**Max Tokens**  
Gets the max amount of BeastTokens a player can have.  
Pattern: "*(max[imum][ poss[ible]]|total poss[ible]) [beast[ ]]token[s]*"  
Since: 1.0  
***Examples:***  
```
command /max:
    trigger:
        send "The max amount of tokens are %max tokens%"
```  
  
# Conditions  
**Player Has Max Tokens**  
Returns if the player has the maximum amount of BeastTokens.  
Pattern: "*[the ]max[imum] [beast[ ]]tokens*"  
Since: 1.0  
***Examples:***  
```
command /redeem:
    cooldown: 1 day
    trigger:
        if player has the max amount of tokens:
            send "Sorry! You cannot redeem any tokens!"
            cancel cooldown
            stop
        send "You have redeemed your daily tokens!"
        add 100 to player's tokens
```  
# Support  
If you have any issues, feel free to join our discord here -> discord.com/invite/aCDNj8s 
You can also send me a private DM at **Ankoki#0001**
# Current Version 1.0  
This concludes the documentation for BeastTokenSk.  
This will be updated as soon as the plugin itself, and documentation may be hosted in other areas at some point.  
Thank you for using BeastTokenSk!
