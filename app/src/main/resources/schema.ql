type Task {
    id: String
    title: String
    done: Boolean
}

type PagedTasks {
    page: Int
    results: [Task]
    totalPages: Int
    totalResults: Int
}

type Query {
    task(id: String) : Task
    tasks(page: Int, limit: Int): PagedTasks
}

type Mutation {
    create(title: String): Int
    update(id: String!, title: String, done: Boolean): Int
    delete(id: String!): Int
}
