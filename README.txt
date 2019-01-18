******************************************
Jason Quon		0969675
CIS*2430		Assignment 3
November 29, 2017	jquon@uoguelph.ca
******************************************

Program description
*******************
This program allows a user to simulate an investment portfolio 
obtaining stocks and mutual funds.

User Guide
**********
Command tab in menu bar
	- Buy investment
		- Select stock or mutual fund from drop down menu
		- Enter symbol (string)
		- Enter name (string)
		- Enter quantity (int)
		- Enter price (double)
		- Buy or reset
	- Sell investment
		- Enter symbol (string)
		- Enter quantity (int)
		- Enter price (double)
		- Sell or reset
	- Update prices
		- Next or previous
		- Enter symbol (string)
		- Enter name (string)
		- Enter price (double)
		- Update
	- Get gain
	- Search
		- Enter symbol
		- Enter name key words
		- Enter low price bound
		- Enterhigh price bound
	- Quit

Test plan
*********

Entering an input that is invalid in type will result in the program asking you to enter a valid input. (ie. entering "five" or -5 instead of 5 when the program asks for quantity.)

Attempting to enter a negative number for price or quantity will prompt the user to input a valid number.

Attempting to buy a stock or mutual fund with the same symbol as that of an investment that already exists in the list of the opposite type will result in no investment being purchased as investment symbols must be unique. (ie. buying stock with symbol TD then attempting to buy mutual fund with symbol TD)

Attempting to sell a quantity greater than the quantity previously bought will result in no investments being sold. (ie. buying 50 stocks then attempting to sell 100)

Attempting to sell an investment that has not previously been bought will result in the program stating "Investment not found."

Searching for a word that does not appear exactly in an investments name instance variable will not result in the investment being printed (ie. Name: Bank Search: ban will not return the investment with name "Bank")

Searching for a symbol is case sensitive while searching for a keyword in the names instance variable is case insensitive (ie. searching "amazon" will return a stock or mutual fund with name "Amazon"

If all search fields are left empty, the program will return all investments.









