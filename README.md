### Sprint planner

Api for planning sprints in the scrum model

### Running the project locally

The project is configured to run via docker, just run the command

```docker compose up -d```

to run the entire environment locally, with elastic search database, keycloak and nginx.

### Integrations

Swagger <br />
Elastic search <br />
Keycloak <br />
Flyway

### Project structure

```
sprint-planner
├── docker-compose.yml
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── server
│   └── nginx
│       └── nginx.conf
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── sprintplanner
    │   │           └── planner
    │   │               ├── domain
    │   │               │   ├── enumeration
    │   │               │   │   ├── Complexity.java
    │   │               │   │   ├── Priority.java
    │   │               │   │   ├── UserAction.java
    │   │               │   │   └── UserStatus.java
    │   │               │   ├── exception
    │   │               │   │   └── AuthException.java
    │   │               │   ├── listeners
    │   │               │   │   ├── MemberListener.java
    │   │               │   │   ├── ProjectListener.java
    │   │               │   │   ├── SprintListener.java
    │   │               │   │   ├── TaskListener.java
    │   │               │   │   └── TeamListener.java
    │   │               │   ├── mapper
    │   │               │   │   └── Mapper.java
    │   │               │   ├── model
    │   │               │   │   ├── Member.java
    │   │               │   │   ├── Project.java
    │   │               │   │   ├── search
    │   │               │   │   │   ├── SearchMember.java
    │   │               │   │   │   ├── SearchProject.java
    │   │               │   │   │   ├── SearchSprint.java
    │   │               │   │   │   ├── SearchTask.java
    │   │               │   │   │   └── SearchTeam.java
    │   │               │   │   ├── Sprint.java
    │   │               │   │   ├── Task.java
    │   │               │   │   └── Team.java
    │   │               │   ├── providers
    │   │               │   │   └── sso
    │   │               │   │       └── SSOProvider.java
    │   │               │   ├── repository
    │   │               │   │   ├── MemberRepository.java
    │   │               │   │   ├── ProjectRepository.java
    │   │               │   │   ├── search
    │   │               │   │   │   ├── SearchMemberRepository.java
    │   │               │   │   │   ├── SearchProjectRepository.java
    │   │               │   │   │   ├── SearchSprintRepository.java
    │   │               │   │   │   ├── SearchTaskRepository.java
    │   │               │   │   │   └── SearchTeamRepository.java
    │   │               │   │   ├── SprintRepository.java
    │   │               │   │   ├── TaskRepository.java
    │   │               │   │   └── TeamRepository.java
    │   │               │   └── service
    │   │               │       ├── AuthService.java
    │   │               │       ├── CrudService.java
    │   │               │       ├── dto
    │   │               │       │   ├── AuthLoginDTO.java
    │   │               │       │   ├── AuthLoginDTOResponse.java
    │   │               │       │   ├── MemberDTO.java
    │   │               │       │   ├── MemberDTOResponse.java
    │   │               │       │   ├── PageableConfigDTO.java
    │   │               │       │   ├── PageableDTO.java
    │   │               │       │   ├── ProjectDTO.java
    │   │               │       │   ├── ProjectDTOResponse.java
    │   │               │       │   ├── search
    │   │               │       │   │   ├── SearchMemberDTO.java
    │   │               │       │   │   ├── SearchProjectDTO.java
    │   │               │       │   │   ├── SearchSprintDTO.java
    │   │               │       │   │   ├── SearchTaskDTO.java
    │   │               │       │   │   └── SearchTeamDTO.java
    │   │               │       │   ├── SprintDTO.java
    │   │               │       │   ├── SprintDTOResponse.java
    │   │               │       │   ├── TaskDTO.java
    │   │               │       │   ├── TaskDTOResponse.java
    │   │               │       │   ├── TeamDTO.java
    │   │               │       │   └── TeamDTOResponse.java
    │   │               │       ├── KeycloakService.java
    │   │               │       ├── MemberService.java
    │   │               │       ├── ProjectService.java
    │   │               │       ├── search
    │   │               │       │   ├── SearchMemberService.java
    │   │               │       │   ├── SearchProjectService.java
    │   │               │       │   ├── SearchService.java
    │   │               │       │   ├── SearchSprintService.java
    │   │               │       │   ├── SearchTaskService.java
    │   │               │       │   └── SearchTeamService.java
    │   │               │       ├── SprintService.java
    │   │               │       ├── TaskService.java
    │   │               │       └── TeamService.java
    │   │               ├── Entrypoint.java
    │   │               ├── impl
    │   │               │   ├── listeners
    │   │               │   │   ├── MemberAuditListener.java
    │   │               │   │   ├── ProjectAuditListener.java
    │   │               │   │   ├── SprintAuditListener.java
    │   │               │   │   ├── TaskAuditListener.java
    │   │               │   │   └── TeamAuditListener.java
    │   │               │   ├── mappers
    │   │               │   │   ├── MemberMapperImpl.java
    │   │               │   │   ├── ProjectMapperImpl.java
    │   │               │   │   ├── search
    │   │               │   │   │   ├── SearchMemberMapperImpl.java
    │   │               │   │   │   ├── SearchProjectMapperImpl.java
    │   │               │   │   │   ├── SearchSprintMapperImpl.java
    │   │               │   │   │   ├── SearchTaskMapperImpl.java
    │   │               │   │   │   └── SearchTeamMapperImpl.java
    │   │               │   │   ├── SprintMapperImpl.java
    │   │               │   │   ├── TaskMapperImpl.java
    │   │               │   │   └── TeamMapperImpl.java
    │   │               │   ├── providers
    │   │               │   │   └── sso
    │   │               │   │       └── KeycloakProvider.java
    │   │               │   ├── schedulers
    │   │               │   │   └── UserVerifySchedule.java
    │   │               │   └── services
    │   │               │       ├── AuthServiceImpl.java
    │   │               │       ├── KeycloakServiceImpl.java
    │   │               │       ├── MemberServiceImpl.java
    │   │               │       ├── ProjectServiceImpl.java
    │   │               │       ├── search
    │   │               │       │   ├── SearchMemberServiceImpl.java
    │   │               │       │   ├── SearchProjectServiceImpl.java
    │   │               │       │   ├── SearchSprintServiceImpl.java
    │   │               │       │   ├── SearchTaskServiceImpl.java
    │   │               │       │   └── SearchTeamServiceImpl.java
    │   │               │       ├── SprintServiceImpl.java
    │   │               │       ├── TaskServiceImpl.java
    │   │               │       └── TeamServiceImpl.java
    │   │               ├── infra
    │   │               │   └── security
    │   │               │       ├── filters
    │   │               │       │   └── UserResourceFilter.java
    │   │               │       ├── JwtConverter.java
    │   │               │       ├── RoutesSecurityConfig.java
    │   │               │       ├── SecurityConfig.java
    │   │               │       └── swagger
    │   │               │           └── OpenAPISecurityConfig.java
    │   │               ├── PlannerApplication.java
    │   │               └── presentation
    │   │                   └── controllers
    │   │                       ├── AuthController.java
    │   │                       ├── CrudController.java
    │   │                       ├── MemberController.java
    │   │                       ├── ProjectController.java
    │   │                       ├── search
    │   │                       │   ├── SearchController.java
    │   │                       │   ├── SearchMemberController.java
    │   │                       │   ├── SearchProjectController.java
    │   │                       │   ├── SearchSprintController.java
    │   │                       │   ├── SearchTaskController.java
    │   │                       │   └── SearchTeamController.java
    │   │                       ├── SprintController.java
    │   │                       ├── TaskController.java
    │   │                       └── TeamController.java
    │   └── resources
    │       ├── application.properties
    │       └── db
    │           └── migration
    │               ├── V2__create_default_team.sql
    │               ├── V3__create_default_sprint.sql
    │               ├── V4__create_default_user.sql
    │               ├── V5__create_default_tasks.sql
    │               ├── V6__create_member_sprint_relation.sql
    │               ├── V7__create_member_team_relation.sql
    │               ├── V8__create_default_project.sql
    │               └── V9__create_project_team_relation.sql
    └── test
        └── java
            └── com
                └── sprintplanner
                    └── planner
                        └── PlannerApplicationTests.java
```
