package Idea.Archieve.IdeaArchieve.domain.member.repository;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
