Compiles clean. EXIT=0.

Change done. `BookServiceImpl.getAllBooks`: when `pageable.getSort().isUnsorted()`, rebuild `PageRequest` with `Sort.by("title").ascending()` before query. Explicit sort passes through untouched. Pagination preserved (page number + size copied).

Controller unchanged — pure passthrough, service owns default sort logic.
