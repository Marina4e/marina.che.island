According to the requirements, the island model is programmed, consisting of a playing field containing the island, buttons for the game, and the output of event statistics,
on the screen next to the playing field instead of the usual console. The locations will initially be filled with plants and animals and then thanks to the extra buttons
next to the statistics screen that offer different variations of the game's course, i.e. the development of life on the island. Animals can:
eat plants and/or other animals (if there is suitable food in their location),
move (to neighboring locations),
multiply (if there is a pair for them in the location),
be eaten.
The "Next Move" button is randomly responsible for animal movement and plant enlargement. The transition of an animal or plant is created through a portal, 
and on the field each subsequent move creates a simulation of the life of animals and plants.
The function of changing evolution on the island has also been added, thanks to the addition of more wolves ("Add Wolf" button). 
The game can be considered completed when only wolves remain on the island.
To demonstrate multithreading in the game, an additional function of accelerating plant growth on the island has been created,
for which there is a "Start Fast Grass Growth" button - it creates streaming plant growth and accelerates the improvement of the island's appearance and 
provides the possibility of growth and reproduction of herbivores on the island.
OOP was applied:
1. Class hierarchy created:
Predator: Wolf, Fox
Herbivores: Sheep, Hare, Parrot, Boa
Plants: Aloe, Grass - which are the subject of food for all animals on the island
A separate type of harmful plants, such as wolf berries (which are poisonous if the animal accidentally eats them then dies)

2. The animal has methods: eat, multiply, choose the direction of movement.
3. In the classes of herbivore and predator, the method of eating is implemented.
4. In specific classes of a particular, all methods for the characteristics of the animal have been finalized.

Statistics on the state of the island (in the console near the playing field, changing at each stroke, recording everything that happened on the island)

You can change the following settings:
island size
duration of simulation clock cycle
number of animals of each species at the start of the simulation.


# marina.che.island
