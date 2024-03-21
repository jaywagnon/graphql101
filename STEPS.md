# Adding Basic GraphQL Support

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
    "data": {
        "healthCheck": true
    }
}
```



# Adding More Queries

## Defining Data Types

Data objects are defined in `type` declarations in the GraphQL schema file.  The definition include the name of the `type` and a list of field names and scalar types for each property.

GraphQL includes several built-in scalar types, such as `Int`, `String`, `Boolean`.  Additional scalar types can be defined or imported using other libraries, such as `graphql-java-extended-scalars`.

Types can contain other types to create nested relationships.

The `!` character denotes that the field is non-nullable.

Collections are denoted by square braces around the type that will be contained in the collection.  To create a non-null collection that will not contain any null values, use `[typeName!]!`.  The inner `typeName!` declares that no entity of `typeName` will be null.  The outer `[]!` declares that the collection itself will never be null.

### 1. Create a `type` declaration for Books

Add the following to the GraphQL schema:

```graphql
type Book {
    id: Int!
    name: String!
}
```

This declares that the Book `type` has an integer `id` field and a string `name` field.  All fields will never be `null`.


### 2. Create a `type` declaration for Authors

Add the following to the GraphQL schema:

```graphql
type Author {
    id: Int!
    name: String!
}
```

This declares that the Author `type` has an integer `id` field and a string `name` field.  All fields will never be `null`.


### 3. Create `Query` declarations

Add the following to the `Query` type in the GraphQL schema:

```graphql
type Query {
    getAuthors: [Author!]!
    getAuthorById(id: Int!): Author

    getBooks: [Book!]!
    getBookById(id: Int!): Book
}
```

This creates fetch queries to return all Authors or Books, or to fetch an individual Author or Book by their `id`.


## Defining the Resolvers

Now that the `type` and `Query` declarations are done, we need to create resolvers for each of the queries.


### 1. Create a Resolver for Books

```java
@Controller
@RequiredArgsConstructor
public class BooksResolver {

    private final BookService bookService;

    @QueryMapping
    public List<Book> getBooks() {
      return bookService.getAll();
    }

    @QueryMapping
    public Book getBookById(@Argument Integer id) {
      return bookService.getById(id).get();
    }
}
```

This class maps the `Query` declarations for fetching `Book` types to the service that manages `Book` entities.


### 2. Create a Resolver for Authors

```java
@Controller
@RequiredArgsConstructor
public class AuthorsResolver {

    private final AuthorService authorService;

    @QueryMapping
    public List<Author> getAuthors() {
      return authorService.getAll();
    }

    @QueryMapping
    public Author getAuthorById(@Argument Integer id) {
      return authorService.getById(id).get();
    }
}
```

This class maps the `Query` declarations for fetching `Author` types to the service that manages `Author` entities.


## Testing the Author and Book Queries

### 1. Start up the application server


### 2. Send a GraphQL request for all data to the application server

Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query {
    getAuthors {
        id
        name
    }

    getBooks {
        id
        name
    }
}
```

> NOTE: You can specify more than one query in request!

The response should have a `200 - OK` status code and the following payload (clipped for brevety):

```json
{
    "data": {
        "getAuthors": [
            {
                "id": 1,
                "name": "Jane Austen"
            },
            ...
            {
                "id": 6,
                "name": "Lewis Carroll"
            }
        ],
        "getBooks": [
            {
                "id": 1,
                "name": "Pride and Prejudice"
            },
            ...
            {
                "id": 9,
                "name": "Alice's Adventures in Wonderland & Through the Looking-Glass"
            }
        ]
    }
}
```

### 3. Send a GraphQL request for a specific Author to the application server

The `Query` declaration for `getAuthorById` takes a non-null input parameter `id` of scalar type `Int`.

`getAuthorById(id: Int!): Author`

To specify the value of the input parameter, you send a JSON structure with the key name `variables` in the request body.  Some HTTP clients like Postman or Insomnia handle this for you when you select `GraphQL` as the body format.


Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query($id: Int!) {
    getAuthorById(id: $id) {
        id
        name
    }
}
```

variables:
```json
{
    "id": 1
}
```

The response should have a `200 - OK` status code and the following payload:

```json
{
    "data": {
        "getAuthorById": {
            "id": 1,
            "name": "Jane Austen"
        }
    }
}
```


### 4. Send a GraphQL request for a specific Book to the application server

As with the `getAuthorById` query, the `getBookById` query also takes a non-null input parameter `id` of scalar type `Int`.

`getBookById(id: Int!): Book`


Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query($id: Int!) {
    getBookById(id: $id) {
        id
        name
    }
}
```

variables:
```json
{
    "id": 1
}
```

The response should have a `200 - OK` status code and the following payload:

```json
{
    "data": {
        "getBookById": {
            "id": 1,
            "name": "Pride and Prejudice"
        }
    }
}
```


# Adding Relationships Between `types`

The `Author` and `Book` data have a one-to-many relationship in the data.  This relationship can be expressed in the GraphQL schema.  Indeed, this is one of the primary benefits of using GraphQL.

> NOTE: The Spring GraphQL library can automatically handle fetching and rendering the related data.  There will be no need to change the resolvers.

## Define the Relationship in the Schema

### 1. Add a relationship to `Book` in the `Author` type.

Change the `Author` type declaration to:

```graphql
type Author {
    id: Int!
    name: String!
    books: [Book!]!
}
```

This adds a non-null collection of non-null `Book` types.

### 2. Add a relationship to `Author` in the `Book` type.

Change the `Author` type declaration to:

```graphql
type Book {
    id: Int!
    name: String!
    author: Author!
}
```

## Testing the relationship

To request related data, simply add the nested `type` to the query body.

### 1. Start up the application server


### 2. Send a GraphQL request for all data to the application server

Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query {
    getAuthors {
        id
        name
        books {
            name
        }
    }

    getBooks {
        id
        name
        author {
            name
        }
    }
}
```

> NOTE: We need to declare which fields we want in the related type.


The response should have a `200 - OK` status code and the following payload (clipped for brevety):

```json
{
    "data": {
        "getAuthors": [
            {
                "id": 1,
                "name": "Jane Austen",
                "books": [
                    {
                        "name": "Pride and Prejudice"
                    },
                    {
                        "name": "Persuasion"
                    }
                ]
            },
            ...
            {
                "id": 6,
                "name": "Lewis Carroll",
                "books": [
                    {
                        "name": "Alice's Adventures in Wonderland & Through the Looking-Glass"
                    }
                ]
            }
        ],
        "getBooks": [
            {
                "id": 1,
                "name": "Pride and Prejudice",
                "author": {
                    "name": "Jane Austen"
                }
            },
            ...
            {
                "id": 9,
                "name": "Alice's Adventures in Wonderland & Through the Looking-Glass",
                "author": {
                    "name": "Lewis Carroll"
                }
            }
        ]
    }
}
```

Note that each `Author` now includes a collection of their books and each `Book` includes its author.


# Enumerations

Some fields may need to be restricted to a predictable set of values.  A special `enum` declaration can be used to define these value sets.

## Define the Enumeration in the Schema

### 1. Add the `enum` declaration to the schema

Add the following to the GraphQL schema:

```graphql
enum Genre {
    ROMANCE,
    FICTION,
    FANTASY,
    SCI_FI
}
```

### 2. Include the `Genre` type in the `Book` type declaration

Change the `Book` type declaration to:

```graphql
type Book {
    id: Int!
    name: String!
    genre: Genre!
    author: Author!
}
```

> NOTE: A matching Java `enum` class for `Genre` already exists in the application and the example data already includes the values.

### 3. Add a `Query` to fetch books by `Genre`

Add the following to the `Query` type declaration:

```graphql
type Query {
    getBooksByGenre(genre: Genre!): [Book!]!
}
```

### 4. Add a resolver for the `getBooksByGenre` query

Add the following method to the `BookResolver` class:

```java
  @QueryMapping
  public List<Book> getBooksByGenre(@Argument Genre genre) {
      return bookService.getAllByGenre(genre);
  }
```


## Testing the Enumeration


### 1. Start up the application server


### 2. Send a GraphQL request to the application server

Using your preferred HTTP client, send a `POST` request to your application server's `/graphql` endpoint with the following body:

`POST - http://localhost:9000/graphql`
```graphql
query($genre: Genre!) {
    getBooksByGenre(genre: $genre) {
        name
        genre
    }
}
```


The response should have a `200 - OK` status code and the following payload:

```json
{
    "data": {
        "getBooksByGenre": [
            {
                "name": "Frankenstein",
                "genre": "SCI_FI"
            },
            {
                "name": "The Invisible Man",
                "genre": "SCI_FI"
            },
            {
                "name": "The Time Machine",
                "genre": "SCI_FI"
            }
        ]
    }
}
```