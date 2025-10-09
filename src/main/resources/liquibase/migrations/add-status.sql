--liquibase formatted sql

--changeset user:2
CREATE TYPE TODO_STATUS as ENUM ('ACTIVE', 'PENDING', 'COMPLETED');
ALTER TABLE todo
    ADD COLUMN status TODO_STATUS NULL;

--changeset user:notnull-status
UPDATE todo
SET status = CASE
                 WHEN is_completed THEN 'COMPLETED'::TODO_STATUS
                 ELSE 'ACTIVE'::TODO_STATUS
    END;

ALTER TABLE todo DROP COLUMN is_completed;
ALTER TABLE todo ALTER COLUMN status SET NOT NULL;
