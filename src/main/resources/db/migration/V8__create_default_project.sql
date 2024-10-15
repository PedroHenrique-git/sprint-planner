INSERT INTO project(
	id, created_at, description, updated_at, name)
	VALUES (gen_random_uuid(), now(), 'flyway project', now(), 'flyway project');