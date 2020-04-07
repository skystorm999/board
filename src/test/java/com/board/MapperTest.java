package com.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO param = new BoardDTO();
		param.setTitle("1번 게시물");
		param.setContent("1번 게시물 내용");
		param.setWriter("테스터");
		
		int result = boardMapper.insertBoard(param);
		System.out.println("결과는 " + result + " 입니다.");
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long) 1);
		
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			
			System.out.println("=========================");
			System.out.println(boardJson);
			System.out.println("=========================");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
	}
	
	@Test
	public void testOfUpdateBoard() {
		BoardDTO param = new BoardDTO();
		
		param.setTitle("1번째 게시글 제목을 수정합니다.");
		param.setContent("1번 게시글 내용을 수정합니다.");
		param.setWriter("홍길동");
		param.setIdx((long) 1);
		
		int result = boardMapper.updateBoard(param);
		
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 1);
		
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				
				System.out.println("=========================");
				System.out.println(boardJson);
				System.out.println("=========================");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testMultipleInsert() {
		for (int i = 2; i <= 50; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			params.setWriter(i + "번 게시글 작성자");
			boardMapper.insertBoard(params);
		}
	}
	
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			
			if(CollectionUtils.isEmpty(boardList) == false) {
				for(BoardDTO board : boardList) {
					System.out.println("=========================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("=========================");
				}
			}
		}
	}
}
