package Personal.CRUDproject.web.basic;

import Personal.CRUDproject.domain.board.Board;

import Personal.CRUDproject.domain.board.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/boards")
@RequiredArgsConstructor
public class BasicBoardController {
    private final BoardRepository boardRepository;

    @GetMapping
    public String boards(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "/basic/boards";
    }

    @GetMapping("/{boardId}")
    public String board(@PathVariable("boardId") long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        int vc = board.getViewCount();
        board.setViewCount(++vc);
        model.addAttribute("board",board);
        return "/basic/board";
    }

    @GetMapping("/addForm")
    public String addForm(){
        return "/basic/addForm";
    }

    @PostMapping("/addForm")
    public String addBoard(Board board, RedirectAttributes redirectAttributes){
        Board savedBoard = boardRepository.registerBoard(board);
        redirectAttributes.addAttribute("boardId",savedBoard.getId());
        return "redirect:/basic/boards";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable("boardId") long boardId, Model model){
        Board board = boardRepository.findById(boardId);
        model.addAttribute("board", board);
        return "/basic/editForm";
    }
    @PostMapping("/{boardId}/edit")
    public String editBoard(@PathVariable("boardId") long boardId,
                                @RequestParam("title") String title,
                               @RequestParam("writer") String writer,
                               @RequestParam("content") String content) {
        Board boardToUpdate = boardRepository.findById(boardId);
        boardToUpdate.setTitle(title);
        boardToUpdate.setWriter(writer);
        boardToUpdate.setContent(content);
        boardRepository.updateBoard(boardId,boardToUpdate);

        return "redirect:/basic/boards";
    }

    @PostMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable("boardId") long boardId){
        boardRepository.deleteBoard(boardId);
        return "redirect:/basic/boards";
    }

    @PostConstruct
    public void init(){
        boardRepository.registerBoard(new Board("테스트 제목1","테스트 작가1","테스트 내용1"));
        boardRepository.registerBoard(new Board("테스트 제목2","테스트 작가2","테스트 내용2"));
    }
}
