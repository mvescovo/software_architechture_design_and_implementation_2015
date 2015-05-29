Assignment 2 Dice Game by Michael Vescovo s3459317

- Hello marker. There's no issues that I'm aware of, but the following is how it should work.

What my implementation does (as far as I can tell):

- Let's multiple players join the game simultaneously at any time.
If a game is in progress it will immediately start receiving callbacks with a suitable message.

- Any functionality that would cause a problem should be disabled. If it's not
then it shouldn't have any adverse effects no matter when the player chooses to use it. All
functionality should then be re-enabled as soon as it's safe to do so.

- A player has committed to a round once they've rolled. If they haven't yet rolled they are
considered to be sitting out, even if they've placed a bet. This is to avoid making other players
wait for them to roll. Their bet will stay in place until they enter a round.

- When the house is rolled the round is "frozen" and no one can enter or leave the round.

- If a player rolls the house and other players have entered the round, but not finished rolling,
a message is displayed to only that player that it's waiting for roles to complete.

- If the house is rolled while player rolls are in progress then the house will wait until all
rolls are finished and then immediately start rolling.

- A player sitting out will see the house roll but have a message that they sat out.

- All player rolls are sent to each player, but since all that is required is to display this
to the console of each player, there was no benefit to populating the local gameEngine
collections; whey would never have been called. So I created the refresh button but then
realised I didn't need it. I've proved that the players are being sent back because the local
consoles of each player receives the updates. Anyway I left the button there but it doesn't do
anything.

- players can top up their points at any time when the field is enabled.

- when a player places a bet, the points are deducted from their balance and displayed on the
current bet label. They are deducted on the server in this step also. After the round points are
adjusted again.

That's all I can think of off the top of my head.

I hope I've covered everything. It was a tricky but fun assignment.

Thanks,
Michael.




