## Creating the GraphQL Schema

The schema defines what data is available from the GraphQL service and what actions can be performed on that data.

The schema file must have:
* A `schema` section with a `Query` definition
* A `Query` section with at least one query definition


### 1. Create the schema file

Create the schema file in your Spring project:

`~/src/main/resources/graphql/schema.graphqls`


### 2. Add the minimum required content

For example (`schema.graphqls`):
```graphql
schema {
    query: Query
}

type Query {
    "Simply returns 'true'."
    healthCheck: Boolean!
}
```



## Creating a Resolver

Resolvers process GraphQL requests, perform the requested action and return the results of the action.

For Spring Boot applications, the `@QueryMapping` and `@MutationMapping` annotations can be added to `@Controller` methods to tie the methods to the GraphQL schema.

If the method names are the same as the `Query` or `Mutation` names in the schema, no other annotation properties are necessary.


### 1. Create a resolver for the `healthCheck` query defined in our basic schema

```java
@Controller
public class GraphQLQueryResolver {

    @QueryMapping
    public Boolean healthCheck() {
        return true;
    }
}
```



## Testing the Schema and Resolver


### 1. Start up the application server


### 2. Send a GraphQL request to the application server

Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query {
    healthCheck
}
```

The response should have a `200 - OK` status code and the following payload:

```json
{
    "healthCheck": true
}
