package Personal.CRUDproject.basic;
import Personal.CRUDproject.domain.board.Board;
import Personal.CRUDproject.domain.board.BoardRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;



public class BoardRepositoryTest {
    BoardRepository boardRepository = new BoardRepository();

    @Test
    void create(){
        Board board = new Board("테스트 게시글", "테스트 작성자", "테스트 내용");
        Board savedBoard = boardRepository.registerBoard(board);
        Board findBoard = boardRepository.findById(board.getId());
        assertThat(findBoard).isEqualTo(savedBoard);

    }

}
