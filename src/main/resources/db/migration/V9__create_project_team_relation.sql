INSERT INTO project_team(
	team_id, project_id)
	VALUES ((select id from team where name = 'flyway') , (select id from project where name = 'flyway project'));