package com.octanner.graphql101.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GraphQLQueryResolver {

    @QueryMapping
    public Boolean healthCheck() {
        return true;
    }
}
