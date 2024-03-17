package Personal.CRUDproject.domain.board;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static java.sql.DriverManager.getConnection;

@Repository
public class BoardRepository {

    private static final Map<Long, Board> store = new HashMap<>();

    private static long sequence =0L;

    public Board registerBoard(Board board){
        board.setId(++sequence);
        board.setCreatedDate(LocalDateTime.now());
        board.setViewCount(0);
        store.put(board.getId(),board);
        return board;
    }

    public Board findById(Long id){
        return store.get(id);
    }

    public void deleteBoard(Long id){
        store.remove(id);
    }
    public List<Board> findAll(){
        return new ArrayList<>(store.values());
    }

    public void updateBoard(Long id,Board updateParam){
        Board findBoard = findById(id);
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setWriter(updateParam.getWriter());
        findBoard.setContent(updateParam.getContent());

    }
}
