package Idea.Archive.IdeaArchive.global.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    public String getTime() {

        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createdDate, now);

        if (duration.getSeconds() < 60) {
            long seconds = duration.getSeconds();
            return "방금 전";
        } else if (duration.toMinutes() < 60) {
            long minutes = duration.toMinutes();
            return minutes + "분 전";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            return hours + "시간 전";
        } else if (duration.toDays() < 7) {
            long days = duration.toDays();
            return days + "일 전";
        } else if (duration.toDays() < 30) {
            long weeks = duration.toDays() / 7;
            return weeks + "주 전";
        } else {
            long months = duration.toDays() / 30;
            return months + "개월 전";
        }
    }
}
