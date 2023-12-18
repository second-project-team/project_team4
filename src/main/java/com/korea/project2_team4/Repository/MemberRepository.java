package com.korea.project2_team4.Repository;

import com.korea.project2_team4.Model.Entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
