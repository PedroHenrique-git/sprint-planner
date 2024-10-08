package com.sprintplanner.planner.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprintplanner.planner.domain.enumeration.UserStatus;
import com.sprintplanner.planner.domain.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    @Query(value = "select m from member m where m.email = ?1", nativeQuery = false)
    Member findByEmail(String email);

    @Query(value = "select m from member m where m.status = ?1", nativeQuery = false)
    List<Member> findByStatus(UserStatus status);
}
