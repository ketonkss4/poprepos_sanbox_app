# poprepos_sanbox_app
Sample application featuring MVVM clean architecture, Room Database, LiveData, RxJava, and Coroutines

## Github Popular Repo App Design Document
This is a github repository search and viewing application that will have the following functionality:
User search text input
Intended input will be that of an organization’s name on github
Use search input to make a Github API call
API call should be scoped to return 3 repos by ascending value of stars
Display search result to UI
Navigate to repo online using in-app web display on user click


## Architecture Diagram 
![App Diagram](https://i.ibb.co/Q8SrDT8/App-Diagram.png)

## Conceptual Breakdown
I believe the following components can be used to implement the application:
### Search/Results View
Can take user input and display search result data in a list view of 3 or less
Displays App State
Search State
Progress of search
Error State
Search parameter not found
I/O Error
Connectivity State
Persistent Snackbar when offline
### RepoWebViewer
Takes target url input
Builds and launches WebViewComponent
### SearchViewModel
Maintains the view state for user search input
Maintains the view state for results data
Provides LiveData UI result data
Initiates search request process 
### Repository
Mediates data operations between different data sources
Subscribes to DB changes
Initiates API request process when neeeded
### GithubApiService
Makes requests and returns results from github API
### RepoRequestUseCase
Handles API requests in a clean architecture manner allowing the repository to maintain a single responsibility
Updates local data base
### RoomDatabase
Persistence components that will allow for storing of new search queries to limit amounts of network operations for infrequently changing data
Provides DB query results as LiveData

## Implementation Strategy
### User Search
This will be done via EditTextView in a fragment view as the single activity view architecture will be used in this application
User input will be captured by the ViewModel via databinding to the EditTextView updating a stored LiveData value of the input. 
This will save user input in case of phone rotation
A search complete button will be used to signal a search event
Search will be disabled until result is returned (For the first iteration of the app at least)
### IO Search Request
Input received by the ViewModel will be passed to its Repository dependency to process
The Repository will then check against the local persistence layer and return the resulting LiveData if the search data exists otherwise it will recruit its RepoRequestUseCase to make a network request.
The RepoRequestUseCase will make a network request and update its DB dependencies with the result. 
If there are errors during this process a callback will be made to the repository with these errors to handle 
The Repository being subscribed to the Local DB will receive the updated result and can then pass the live data result back to the ViewModel
### Display Search Result
The LiveData from the DB can be returned to the ViewModel via the repository and then map transformed to the final UI LiveData to which the view is subscribed to once the result is obtained the view will automatically update its state
Display Repo Website 
ViewHolder Click Listener will use a RepoWebViewer to launch the repo website

### Planned Improvements:
Better error state for no search results 
Notice when offline that only cached results would be saved



