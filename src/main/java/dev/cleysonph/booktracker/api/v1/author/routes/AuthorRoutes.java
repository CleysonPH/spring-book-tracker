package dev.cleysonph.booktracker.api.v1.author.routes;

import dev.cleysonph.booktracker.api.v1.common.routes.ApiV1Routes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorRoutes {

    public static final String RESOURCE = "/authors";
    public static final String IDENTIFIER = "/{authorId}";

    public static final String CREATE_AUTHOR_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE;
    public static final String FIND_ALL_AUTHORS_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE;
    public static final String FIND_AUTHOR_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;
    public static final String DELETE_AUTHOR_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;
    public static final String UPDATE_AUTHOR_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;

}
