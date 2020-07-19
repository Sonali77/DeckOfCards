# DeckOfCards
## Utility
This package is present in src\main\java\utility. It contains the below utils classes-
1. **HttpUtil**
2. **PropertyUtils**
## Property Files
This package is present in src\main\resources\properties\ui. It contains below property files-
1. **ui.properties**

Note- **ui.properties** file contains keys such as **url**,**post.key**,**post.value**. Key **url** has the link for GET url, **post.key** contains parameter key for POST and **post.value** contains parameter value for POST
## Testcases
All Junit testcases are present in src\test\java. It contains the below Junit testcases-
1. **TestDeckOfCards**- Create a new Deck of cards and support adding joker with a POST. This test cases gets the url-https://deckofcardsapi.com/api/deck/new/ from **ui.properties** and verifies if the json response **"success":true**. It then POST param "joker:sam"(read from **ui.properties**) and the new URL formed is https://deckofcardsapi.com/api/deck/new/post?joker=sam and verifies if its successful.
2. **TestDrawOneCardFromDeck**- Draw one or more cards from the Deck. This test case gets the url-https://deckofcardsapi.com/api/deck/new/ from **ui.properties** and gets the value of **deck_id** from it. It then creates a new URL(https://deckofcardsapi.com/api/deck/<<deck_id>>/draw/) using this **deck_id** and then uses GET. The resulting json is verified with response **"success":true**.
3. **TestSuite**- Test suite for the above mentioned Junit testcases
## pom.xml
pom.xml contains surefire plugin, json and junit dependencies
## Command Line for Execution
mvn test -Dtest=TestSuite

