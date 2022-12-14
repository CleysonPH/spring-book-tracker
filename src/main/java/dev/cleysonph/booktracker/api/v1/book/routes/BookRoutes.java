package dev.cleysonph.booktracker.api.v1.book.routes;

import dev.cleysonph.booktracker.api.v1.common.routes.ApiV1Routes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookRoutes {

    public static final String RESOURCE = "/books";
    public static final String IDENTIFIER = "/{bookId}";

    public static final String CREATE_BOOK_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE;
    public static final String FIND_ALL_BOOKS_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE;
    public static final String FIND_BOOK_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;
    public static final String DELETE_BOOK_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;
    public static final String UPDATE_BOOK_BY_ID_ROUTE = ApiV1Routes.BASE_ROUTE + RESOURCE + IDENTIFIER;

}
