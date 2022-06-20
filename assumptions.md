1. We only want to provide list of "Best Picture" movies to users. That's why I'm importing only this category from csv.
2. We need to import info from imdb API about movies we have in database(imported "Best Picture" from csv)
Why?
Because:
- We need to provide list of 10 top-rated movies order by box office value.
- Imdb API does not support searching for list of movies by box office value.
- Box office value is returned only when you are fetching one certain movie.
- To generate list of 10 top-rated movies we have to have it already in our database
- Imdb API allows to make 1000 calls per day, so we can make ~400 calls for movies from csv file.
3. Regarding this requirement "Your API needs to expect an API token from the caller"
, I'm assuming user needs to provide API_KEY which is unique per user.
If my assumption is wrong then I would add JWT tokens and make whole creating, login user services and functionalities.
4. 