WELCOME TO DESERT WARS!

This game was made with extensibility in mind - it's very easy to add new screens
or add new extensions to the game's overall structure. 

[Class] Game: 
Creates a new instance of a JFrame, sets the content of the frame the MainPanel
class and sets frame properties.

[Class] MainPanel (extends JPanel, implements Runnable, KeyListener) :
Creates the panel with methods that handle how the game is run. 
The draw, update and key listener methods are routed to the ScreenManager class.

[Interface] Screen:
Lists methods needed by every screen (so that they can be handled appropriately

[Class] ScreenManager:
Stores an ArrayList of Screen types at specific indices and routes method handling
to each of the classes according to the current screen state of the game. 
Initial screen state = STARTSCREEN
setScreen allows you to change the current screen index.

NOTE - THIS APPLIES TO ALL XXXXSCREEN CLASSES THAT IMPLEMENT SCREEN:
The methods init, update, draw, keyPressed and keyReleased are handled accordingly.

[Class] StartScreen (implements Screen) :
Displays a menu which can be used to set the current screen state. 
It is the default current screen in the screen manager class.

[Class] InstructionsScreen (implements Screen) :
Displays game instructions in a graphical manner. Additional instructions are lazily
read from "INSTR.txt" and pushed to a JOptionPane (triggered by keyPress 'F')

[Class] HighScoreScreen (implements Screen) :
Creates an instance of the HighScoreManager class then reads and displays the top 10
high scores from the high score data file in two columns.

[Class] HighScoreManager:
Uses ObjectInputStreams to read/write the high score data as an ArrayList of Score
which is another class. In this manner, each score class can store private data
which can be extended upon later easily and in an efficient manner. On each 
getScores() request the scores are first sorted with Collections.sort(), using a
self defined ScoreComparator class. Only the top 10 high scores and names are read 
from the file (although again this is easily customizable).

[Class] ScoreComparator (implements Comparator) :
Defines the compare method to compare only the integer score values from the score
class.

[Class] Score (implements Serializable) :
Stores an integer score value, a String name and methods to retrieve both.

[Class] GameScreen (implements Screen) :
Creates a new instance of Player, Missiles, AIBoss, Enemies and draws things 
appropriately and creatively. Collisions are check with the checkCollisions method.
Collisions between enemies and missiles remove both from their local ArrayLists.

[Class] MovingBackground :
Uses BufferedImages to draw a steadily moving looping background with methods
to change the current speed. Called by all classes implementing Screen for a
nice integrated effect.

[Class] Enemies :
Creates an enemy object, with X and Y coordinates and a move method. An initial
state is alive.

[Class] Missiles :
Creates a missile object with X and Y coordinates and a move method. An initial 
state is visible.

[Class] Player :
Creates a player object with X and Y coordinates and key listener methods that 
change the coordinates as appropriate. A check is made from the Konami class which
checks if the sequence of keys entered is the konami and if true, increments the
initial size of the missiles array stored in each player's class. 

[Class] Konami :
Inputs key code sequences and checks if they belong to the Konami code sequence.
If not, the sequence resets.

[Class] AIBoss :
Creates a single AI boss object, with X and Y coordinates which also inputs the 
instance of the player class. It's main move function calls the smartMove() method,
which moves the boss towards the player while ensuring it is in the same 
y - coordinate spacial area. It uses the player class to check when weapons are
fired and then a smartDodge() method is called which rushes the boss to the closer
side of the screen to avoid a missile.

ENJOY PLAYING!


