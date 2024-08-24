insert into 
        task (
          id, 
          complexity, 
          created_at, 
          description, 
          updated_at, 
          name, 
          priority, 
          punctuation, 
          member_id, 
          sprint_id
        )
      values
        (
          gen_random_uuid(), 
          0, 
          now(), 
          'flyway task description 1', 
          now(), 
          'flyway task 1', 
          0, 
          5, 
          (select id from member where username = 'flyway'), 
          (select id from sprint where name = 'flyway sprint')
        ),
        (
          gen_random_uuid(), 
          0, 
          now(), 
          'flyway task description 2', 
          now(), 
          'flyway task 2', 
          0, 
          5, 
          (select id from member where username = 'flyway'), 
          (select id from sprint where name = 'flyway sprint')
        ),
        (
          gen_random_uuid(), 
          0, 
          now(), 
          'flyway task description 3', 
          now(), 
          'flyway task 3', 
          0, 
          5, 
          (select id from member where username = 'flyway'), 
          (select id from sprint where name = 'flyway sprint')
        );