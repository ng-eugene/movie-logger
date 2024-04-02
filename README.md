# My Personal Project

## A movie logger

The application will log the **movies** a user watches, alongside
details like:
- A rating e.g. from 0-10
- A review
- Whether the user would rewatch it 

The *intended user* would be anyone who would want to 
keep track of the movies they watch as well as how much they 
liked the movie. This project is of interest to me as I often
watch movies when I have the free time to do so, and having an
easy way to note down movies I watch as well as note down my 
thoughts on them as they are fresh.

## User Stories
- As a user, I want to be able to add a movie to my watchlist
- As a user, I want to be able to view the movies in my watchlist
- As a user, I want to be able to add a movie to my 'watched' list
- As a user, I want to be able to mark a movie as watched and move 
it to my 'watched' list, add a rating and a review
- As a user, I want to be able to save my movie lists if I choose
- As a user, I want to be able to load my movie lists from file if I choose

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by...
1. Select the menu tab "Watchlist"
2. Select the menu item "Add to watchlist"
3. Input a name and click "Ok"
4. Select the menu tab "Watchlist"
5. Select the menu item "View watchlist"

- You can generate the second required action related to the user story "adding multiple Xs to a Y" by...
1. Select the menu tab "Watchlist"
2. Select the menu item "Suggest random movie"

- You can locate my visual component by...
1. Select the menu tab "Watchlist"
2. Select the menu item "View watchlist"
3. Select any of the items in the list (after having added to the list already)

- You can save the state of my application by...
1. Select the menu tab "Save/Load"
2. Select the menu item "Save to file"

- You can reload the state of my application by...
1. Select the menu tab "Save/Load"
2. Select the menu item "Load from file"

## Phase 4: Task 2
* Mon Apr 01 12:59:58 PDT 2024
* Added a movie 1 to MovieList
* Mon Apr 01 13:00:00 PDT 2024
* Added a movie 2 to MovieList
* Mon Apr 01 13:00:03 PDT 2024
* Removed movie 2 from MovieList
* Mon Apr 01 13:00:12 PDT 2024
* Logged movie 4
* Mon Apr 01 13:00:12 PDT 2024
* Added a movie 4 to MovieLog
* Mon Apr 01 13:00:19 PDT 2024
* Logged movie 1
* Mon Apr 01 13:00:19 PDT 2024
* Added a movie 1 to MovieLog
* Mon Apr 01 13:00:23 PDT 2024
* Removed movie 4 from MovieLog
* Mon Apr 01 13:00:27 PDT 2024
* Randomly generated movie 1

## Phase 4: Task 3
One particular area of my code that I would like to refactor with more time/knowledge would 
be the UI portion of my code, particularly having a more streamlined way of swapping
between the different views instead of creating new instances every time a new view is
required. 

One way to do this could be to create each view as a singleton instance with lazy 
initialisation, updating the data within each view as needed. This would mean less 
potential issues with excess instances floating around as well as maintain efficient
resource utilisation.

### References:
- https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- https://github.students.cs.ubc.ca/CPSC210/TellerApp
- https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html