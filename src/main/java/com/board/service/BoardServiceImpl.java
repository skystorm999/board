package com.board.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDTO param) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		if(param.getIdx() == null) {
			//param의 pk값이 없을 경우 새글 생성
			queryResult = boardMapper.insertBoard(param);
		} else {
			//param의 pk값이 존재할 경우 글 수정
			queryResult = boardMapper.updateBoard(param);
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardDetail(idx);
	}

	@Override
	public boolean deleteBoard(Long idx) {
		// TODO Auto-generated method stub
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		
		if(board != null && "N".equals(board.getDeleteYn())) {
			//글이 존재하고 deleteYn이 N인 경우
			queryResult = boardMapper.deleteBoard(idx);
		}
		
		return (queryResult == 1) ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		// TODO Auto-generated method stub
		//값이 비어있는 리스트 생성
		List<BoardDTO> boardList = Collections.emptyList();
		
		//글 총 개수
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {
			//글의 총 개수가 0보다 크면 전체리스트를 불러온다
			boardList = boardMapper.selectBoardList();
		}
		return boardList;
	}

}
