CREATE TABLE IF NOT EXISTS posts
(
    id         UUID primary key,
    title      VARCHAR   NOT NULl,
    content    VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL
);
