Solution has 3 main modules wired together by facades that can call each other.
If we would like to create microservice applications from this one we can divide it by these modules.

After start up application is reading and persisting movies info from csv file.
After that we are looking up movie info from OMDB api and persisting f.e. box office value to database(reasons below).


Omdbapi is very basic in its functionality and data that it returns.
The biggest issue is, how to provide a list of 10 top-rated movies order by box office value.
- we cannot search the results by this value
- when we try to download movies list by providing ambiguous movie title('t' or 's' parameters)
then we got list of 10 movies with closest title or `Too many results` error.

The second issue is that we can make only 1000 calls per day.
The best way that I see to resolve that problem is to download box office information per each movie from csv file,
that way we can query our own database to generate 10 top-rated movies.


If we could query omdb api by pages than we could write scrapper in scheduler to download 1000 movies each day.
Or we can use some other API that would allow us to return top-rated movies by its properties.



