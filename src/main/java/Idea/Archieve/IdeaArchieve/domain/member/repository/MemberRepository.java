package Idea.Archieve.IdeaArchieve.domain.member.repository;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
