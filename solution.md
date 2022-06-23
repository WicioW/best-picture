1. omdbapi is very basic in it's functionality and data that it returns.
The biggest issue is, how to provide a list of 10 top-rated movies order by box office value.
- we cannot search the results by this value
- when we try to download movies list by providing ambiguous movie title('t' or 's' parameters)
then we got list of 10 movies with closest title or `Too many results` error.

The second issue is that we can make only 1000 calls per day.
The best way that I see to resolve problem from this task is to write some scrapper in scheduler,
that will download each day 1000 different movies, write them to database(certainly `box office value`)
so we can query our own database to generate 10 top-rated movies.


