# Iteration 1

## OBJECTIVE
Title: Default BookController getAllBooks ordering by title
Description: Change BookController and BookService so the getAllBooks operation returns books ordered by title ascending by default when the client does not specify a sort order.
Acceptance criteria: GET /api/books returns books sorted by title ascending by default; an explicit sort parameter is still honored; existing pagination keeps working; compiles clean.
Likely intent: Modify the getAllBooks operation in BookController and BookService so that, when the incoming request supplies no sort ordering (empty/unsorted Pageable and no explicit sort parameter), the service applies a default sort of title ascending before querying; an explicitly provided sort must override this default, and existing pagination behavior must be preserved.

## BINDING CLAUSES (intent anchors — must satisfy)
- [BRAIN_FACT] (unstated claim) (java.method:com.daniellaera.backend.controller.BookController#getAllBooks(Pageable,String):PageResponse<BookDTO>)
- [BRAIN_FACT] (unstated claim) (java.class:com.daniellaera.backend.controller.BookController)
- [BRAIN_FACT] (unstated claim) (java.method:com.daniellaera.backend.service.BookService#getAllBooks(Pageable,String):Page<BookDTO>)
- [BRAIN_FACT] (unstated claim) (java.class:com.daniellaera.backend.service.BookService)

## BINDING CLAUSES (machine-checkable — must satisfy)


## STYLE GUIDE (idiomatic patterns from this repo)


## ADVISORY CONTEXT (AMBER facts — context, not contract)


## Additional Context
## Brain context (RAG-retrieved facts)
- **Method** (`java.method:com.daniellaera.backend.controller.BookController#getAllBooks(Pageable,String):PageResponse<BookDTO>`) — name: getAllBooks; source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; signature: getAllBooks(Pageable,String):PageResponse<BookDTO>; fqcn: com.daniellaera.backend.controller.BookController; visibility: public;  [com.daniellaera.backend.controller.BookController#getAllBooks(Pageable,String):PageResponse<BookDTO>]
- **Method** (`java.method:com.daniellaera.backend.service.BookService#getAllBooks(Pageable,String):Page<BookDTO>`) — name: getAllBooks; source: backend\src\main\java\com\daniellaera\backend\service\BookService.java; signature: getAllBooks(Pageable,String):Page<BookDTO>; fqcn: com.daniellaera.backend.service.BookService; visibility: public;  [com.daniellaera.backend.service.BookService#getAllBooks(Pageable,String):Page<BookDTO>]
- **Method** (`java.method:com.daniellaera.backend.controller.BookController#getBook(Integer):ResponseEntity<BookDTO>`) — name: getBook; source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; signature: getBook(Integer):ResponseEntity<BookDTO>; fqcn: com.daniellaera.backend.controller.BookController; visibility: public;  [com.daniellaera.backend.controller.BookController#getBook(Integer):ResponseEntity<BookDTO>]
- **Method** (`java.method:com.daniellaera.backend.service.BookService#getBooksAiOptimizedView(List<Integer>):List<BookAiView>`) — name: getBooksAiOptimizedView; source: backend\src\main\java\com\daniellaera\backend\service\BookService.java; signature: getBooksAiOptimizedView(List<Integer>):List<BookAiView>; fqcn: com.daniellaera.backend.service.BookService; visibility: public;  [com.daniellaera.backend.service.BookService#getBooksAiOptimizedView(List<Integer>):List<BookAiView>]
- **Boundary** (`boundary.http:GET:/api/v3/book/{bookId}`) — source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; method: GET; httpPath: /api/v3/book/{bookId}; crossRepo: false; boundaryClass: HTTP; derivedFrom: annotation;  [GET:/api/v3/book/{bookId}]
- **Method** (`java.method:com.daniellaera.backend.service.BookService#getTotalBookCount():long`) — name: getTotalBookCount; source: backend\src\main\java\com\daniellaera\backend\service\BookService.java; signature: getTotalBookCount():long; fqcn: com.daniellaera.backend.service.BookService; visibility: public;  [com.daniellaera.backend.service.BookService#getTotalBookCount():long]
- **Method** (`java.method:com.daniellaera.backend.service.BookService#findBookById(Integer):Optional<BookDTO>`) — name: findBookById; source: backend\src\main\java\com\daniellaera\backend\service\BookService.java; signature: findBookById(Integer):Optional<BookDTO>; fqcn: com.daniellaera.backend.service.BookService; visibility: public;  [com.daniellaera.backend.service.BookService#findBookById(Integer):Optional<BookDTO>]
- **doc.rule:GET /api/v3/book** (`doc.rule:README.md:518b5fef6e9906fc`) — docClaim: {"subjectRef":"GET /api/v3/book","predicate":"requires_auth","value":"Public","evidencePointer":{"sourceFile":"README.md","section":"Books","rawExcerpt":"| GET | `/api/v3/book` | Public | List books (paginated) |"}};  [README.md:518b5fef6e9906fc]
- **Method** (`java.method:com.daniellaera.backend.controller.BookController#createBook(BookDTO,User):ResponseEntity<BookDTO>`) — name: createBook; source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; signature: createBook(BookDTO,User):ResponseEntity<BookDTO>; fqcn: com.daniellaera.backend.controller.BookController; visibility: public;  [com.daniellaera.backend.controller.BookController#createBook(BookDTO,User):ResponseEntity<BookDTO>]
- **Contract** (`contract.rr:boundary.http:GET:/api/v3/book/`) — source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; sideEffects: []; nondeterministicOutputs: []; outputShape: {"returnType":"PageResponse<BookDTO>"}; boundaryFactId: boundary.http:GET:/api/v3/book/; invariants: []; contractKind: REQUEST_RESPONSE; inputShape: {"params":[{"name":"pageable","type":"Pageable"},{"name":"search","type":"String"}]};  [boundary.http:GET:/api/v3/book/]
- **Contract** (`contract.rr:boundary.http:GET:/api/v3/book/{bookId}`) — source: backend\src\main\java\com\daniellaera\backend\controller\BookController.java; sideEffects: []; nondeterministicOutputs: []; outputShape: {"returnType":"ResponseEntity<BookDTO>"}; boundaryFactId: boundary.http:GET:/api/v3/book/{bookId}; invariants: []; contractKind: REQUEST_RESPONSE; inputShape: {"params":[{"name":"bookId","type":"Integer"}]};  [boundary.http:GET:/api/v3/book/{bookId}]
- **doc.rule:GET /api/v3/book/{id}** (`doc.rule:README.md:d7f387e91faf1b9b`) — docClaim: {"subjectRef":"GET /api/v3/book/{id}","predicate":"requires_auth","value":"Public","evidencePointer":{"sourceFile":"README.md","section":"Books","rawExcerpt":"| GET | `/api/v3/book/{id}` | Public | Get book by ID |"}};  [README.md:d7f387e91faf1b9b]