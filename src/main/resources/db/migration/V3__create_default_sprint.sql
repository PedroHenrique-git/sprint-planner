insert into 
      sprint (
        id, 
        created_at, 
        description,
        end_date,
        updated_at, 
        name,
        start_date,
        team_id
      )
    values
      (
        gen_random_uuid(), 
        now(), 
        'flyway sprint',
        now(),
        now(), 
        'flyway sprint',
        now(),
        (select id from team where name = 'flyway')
      );