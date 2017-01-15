# FinalProject
<html>
<h1>Scrabble Terminal Game</h1>
<img src="http://globaltoynews.typepad.com/.a/6a0133ec87bd6d970b0168e4f88ca9970c-800wi">
<br>
<h5>Jenny Gao and Winnie Chen</h5>
<h5>Period 10</h5>
<h3>Overview</h3>
<br>
<p></p>
<h3>Instructions</h3>
<br>
<p>To run a Scrabble game with the default settings, type 'default' as an argument after the command. After that, include 2 one-word player names as such: java Scrabble default &lt;name of Player 1&gt; &lt;name of Player 2&gt;
<br><br>
<h5>Commands while game is running: </h5><br>
To attempt to place a word, enter &lt;word&gt; &lt;x-cor&gt; &lt;y-cor&gt; &lt;direction&gt;
<br><br>
To attempt to exchange tiles, enter the positions of the tiles you would like to exchange, leftmost being 1 and rightmost being 7
<br><br>
To pass, enter 0
<br><br>
To turn a blank tile into a tile with any letter in the alphabet, enter position of "?" tile in rack (leftmost being 1 and rightmost being 7), followed by letter you want.  Format: &lt;position of "?" tile&gt; &lt;letter in alphabet&gt;
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
<p></p>
</html>
