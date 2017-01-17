# FinalProject
<html>
<h1>Scrabble Terminal Game</h1>
<img src="http://globaltoynews.typepad.com/.a/6a0133ec87bd6d970b0168e4f88ca9970c-800wi">
<br>
<h5>Jenny Gao and Winnie Chen (Incandescent Mind)</h5>
<h5>Period 10</h5><br><br>
<h3>Overview</h3>
<br>
<p>	This is a terminal version of Scrabble with a 15x15 grid of squares representing the board, each of which accommodates a single letter tile. The goal of Scrabble is to score as many points as possible by placing letter tiles to create words onto the game board. When all letters have been drawn and one player uses his or her last letter, or when all possible plays have been made, the game ends.
Each player has 7 tiles; whenever a tile is used, a new tile is generated randomly (based on probabilities listed below, in Critical Features section).  Players score points by forming words with tiles. Each tile has one letter and letters have different point values. Players can choose where to place tiles by entering the starting point’s (square’s) x and y coordinates, as well as the direction, so the format would be [&lt;word&gt; &lt;x-cor/col&gt; &lt;y-xor/row&gt; &lt;direction&gt;].  The direction would be “h” or “v” for horizontal or vertical.  Premium letter squares can double or triple (depending on the type of premium letter square) the worth of the tile placed at its location. A word check will make sure the word a player spells out is valid.  </p>
<h3>Instructions</h3>
<br>
<p>To run a Scrabble game with the default settings, type 'default' as an argument after the command. After that, include 2 or more one-word player names as such: java Scrabble default &lt;name of Player 1&gt; &lt;name of Player 2&gt; [&lt;name of Player 3&gt;] [&lt;name of Player 4&gt;] [&lt;name of Player 5&gt;]<br><br>To run a Scrabble game with custom settings for how many rounds the game should last, type: custom-rounds &lt;number of rounds&gt; &lt;name of Player 1&gt; &lt;name of Player 2&gt; [&lt;name of Player 3&gt;] [&lt;name of Player 4&gt;] ...etc.</p>
<br><br>
<h5>Commands while game is running: </h5><br>
<p>To attempt to place a word, enter &lt;word&gt; &lt;x-cor&gt; &lt;y-cor&gt; &lt;direction&gt;
<br>Single digit x-cor and y-cor values should be entered as a single digit
<br>Direction can be indicated by typing 'h' for horizontal and 'v' for vertical
<br><br>
To attempt to exchange tiles, enter the positions of the tiles you would like to exchange, leftmost being 1 and rightmost being 7
<br><br>
To turn a blank tile into any letter of the alphabet, enter &lt;position of the '?' tile on rack&gt; &lt;desired letter&gt;
<br><br>
To pass, enter 0
</p>
<br>
<h5>Try Again When ... </h5>
<p>
Position is off the board (x-cor or y-cor is not between 0 and 14).
<br><br>
Position is on the board but word can't be laid out.  Example, a user may want to place the word "TEA" vertically at position "0 1". However, this is not possible because "A" would be off the board.
<br><br>
Word does not use at least one of the letters already on the board (i.e. user can't place words anywhere on the board.  Word must intersect an existing word/use an existing word's letter on the board at least once)
<br><br>
Letter(s) in the word user attempts to lay down are not in user's rack.
<br><br>
First word does not have a letter on the center square.
<br><br>
Letter(s) are not in rack and are also not on the board at the appropriate positions if word is to be laid out.
<br><br>
Tiles are in rack but will be laid on a square that is already occupied (with a different letter) on the board.
<br><br>
Also, if a blank tile was just turned into a tile containing any letter of user's choice, the user can continue to place a word (still that user's turn).
</p>
<br>
<h5>Move On To Next Player If/When ... </h5>
<p>
Word is placed on the board.
<br><br>
Word entered is invalid (not found in dictionary).
</p>
<br>
<h3>Features</h3>
<p>
The score for each turn is the sum of the letter values in each word(s) formed or modified on that turn, plus the additional points obtained from placing letters on Premium Squares (different colored squares in terminal).<br><br>
Regular Squares (black) - based on value of tiles<br>
Double Letter (blue) - doubles the score of a letter placed on it<br>
Triple Letter (green) - triples the score of a letter placed on it<br>
Double Word (red) - score for an entire word is doubled. NOTE: the center square is a red square, which doubles the score for the first word<br>
Triple Word (yellow) - score for an entire word is tripled<br><br>
Letter and word premiums count only on the turn in which they are played. On later turns, letters already played on premium squares count at face value.<br><br>
When two or more words are formed in the same play, each is scored. The points from intersections is displayed on the top of the board following "Points from intersections: "<br><br>
BINGO! If you play seven tiles on a turn, it's a Bingo. You score a premium of 50 points after totaling your score for the turn.<br><br>
Unplayed Letters: When the game ends, each player's score is reduced by the sum of his or her unplayed letters. In addition, if a player has used all of his or her letters, the sum of the other players' unplayed letters is added to that player's score. The resulting score is used for determining the winner and rankings, with the total score before deductions used for tie-breaking.<br><br>
The player with the highest final score wins the game.<br><br>
Note: At the end of each round (after the last player takes his/her turn), the Current Round Score column resets to 0.
</p>
</html>
