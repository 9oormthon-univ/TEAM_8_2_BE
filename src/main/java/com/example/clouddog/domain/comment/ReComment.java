package com.example.clouddog.domain.comment;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
@Getter
@Setter
public class ReComment {
    private Long rcmId;
    private String rcmContent;
    private LocalDate rcmTime;

    public ReComment() {
    }

}