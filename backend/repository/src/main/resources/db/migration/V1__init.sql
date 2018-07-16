CREATE TABLE strategy (
  id LONG NOT NULL
);

CREATE TABLE objective (
  id LONG NOT NULL,
  strategy_id LONG NOT NULL
)