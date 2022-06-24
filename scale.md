This application is written as a monolith. So we can only scale it vertically when number of users increases.

We could divide this app by modules, creating microservices. 
Then we could update our rest api, so that each facade from module could to communicate with others.
Or we could use message broker or other event based tool to connect our services.

Adding docker and/or kubernetes to containerise application would help with CI/CD.

Then we can try deploying application in chosen cloud service and configure it in such a way that our application would scale horizontally.

