# Vote Services API

Micro service responsible for creating Vote Schedule, Vote Session, register Votes and process vote session results.

## Domain meaning
- VoteSchedule: "Agenda/Pauta"

## Development startup

- Stack: 
  - Spring 
  - OpenJdk11 (I have used adoptopenjdk-11.jdk) 
  - docker 
  - rabbitmq 
  - mysql

- Run docker compose:

> ```docker-compose up -d```

- Run Spring Boot:
> ```mvn spring-boot:run```
The server will be at ```http://localhost:8080```

## Testing locally
- Create Vote Schedule by using request below:
```
curl --request POST \
  --url http://localhost:8080/vote-services/api/voteschedules \
  --header 'content-type: application/json' \
  --data '{
	"voteScheduleDescription": "testing 2"
}'
```   

- Create Vote Session by using request below:
```
curl --request POST \
  --url http://localhost:8080/vote-services/api/votesessions \
  --header 'content-type: application/json' \
  --data '{
	"voteScheduleId": 38,
	"voteSessionDuration": 1
}'
```

- Submit Vote by using request below:
```
curl --request POST \
  --url http://localhost:8080/vote-services/api/votes \
  --header 'content-type: application/json' \
  --data '{
  "cpf": "01089661002",
  "voteOption": "YES",
  "voteSessionId": 39
}'
```

### Rabbit Mq
can be accessed thru:
- url: http://localhost:15672/#/exchanges
- user: guest
- password: guest

> There is a process that runs every 60 seconds to check vote sessions that are closed. When finding any
> it will publish the `votingSessionId` in `Vote-session-topic` exchange. This exchange binds has bindings 
> to `vote-session-processing-queue`. 
> - Refer to: `ProcessVoteSession.java`
>
> Then there is a consumer that constantly reads `vote-session-processing-queue`. All votes related to `sessionId` will be fetched.
> After calculating all votes it will publish to `Vote-session-results-topic` (binds to `vote-session-results-queue`).
> Each vote session results will available to be consumed across other platform apps.  
>
> - Refer to: `NotifyVoteSessionResults.java`
>  

### Swagger
can be accessed thru:
- url: http://localhost:8080/vote-services/api/swagger-ui/