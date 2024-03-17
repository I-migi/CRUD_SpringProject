package Personal.CRUDproject.domain.board;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {


    private Long id;
    private String title;
    private String writer;
    private LocalDateTime createdDate;
    private int viewCount;
    private String content;

    public Board(){

    }

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

}
